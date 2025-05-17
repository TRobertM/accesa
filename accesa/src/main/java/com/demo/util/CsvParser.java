package com.demo.util;

import com.demo.dto.UsernamePasswordDTO;
import com.demo.model.*;
import com.demo.repository.ProductRepository;
import com.demo.repository.StoreRepository;
import com.demo.repository.UserRepository;
import com.demo.service.implementations.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Component
public class CsvParser implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CsvParser.class);
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final List<String> price_files = List.of(
//            "products/kaufland_2025-05-01.csv", "products/kaufland_2025-05-08.csv",
//            "products/lidl_2025-05-01.csv", "products/lidl_2025-05-08.csv",
//            "products/profi_2025-05-01.csv", "products/profi_2025-05-08.csv",
            "products/kaufland_2025-05-05.csv", "products/kaufland_2025-05-07.csv", "products/lidl_2025-05-07.csv", "products/profi_2025-05-07.csv"
    );
    private final List<String> discount_files = List.of(
//            "discounts/kaufland_2025-05-01.csv", "discounts/kaufland_2025-05-08.csv",
//            "discounts/lidl_2025-05-01.csv", "discounts/lidl_2025-05-08.csv",
//            "discounts/profi_2025-05-01.csv", "discounts/profi_2025-05-08.csv"
            "discounts/kaufland_2025-05-07.csv"

    );
    private final String DELIMITER = ";";
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public CsvParser(StoreRepository storeRepository, ProductRepository productRepository, UserService userService, UserRepository userRepository) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        addStores();
        parsePrices(price_files);
        parseDiscounts(discount_files);
        List<Product> products = productRepository.findTop3ByOrderByIdAsc();
        userService.registerUser(new UsernamePasswordDTO("User1", "password1"));
        Optional<User> user = userRepository.findByUsername("User1");
        if (user.isPresent()) {
            for (Product product : products) {
                BasketItem bi = new BasketItem(product, 2);
                user.get().getBasket().addItem(bi);
            }
        }
    }

    private void addStores() {
        storeRepository.save(new Store("Kaufland"));
        storeRepository.save(new Store("Lidl"));
        storeRepository.save(new Store("Profi"));
    }

    public void parseDiscounts(List<String> files) throws Exception{
        for (String file : files) {
            String store_name = getStoreName(file);
            Map<String, List<String>> uniqueRecords = new LinkedHashMap<>();
            try (InputStream is = getClass().getClassLoader().getResourceAsStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                br.readLine();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(DELIMITER);
                    String productId = data[0];
                    uniqueRecords.putIfAbsent(productId, Arrays.asList(data));
                }
            }
            addDiscounts(store_name, new ArrayList<>(uniqueRecords.values()));
        }
    }

    public void parsePrices(List<String> files) throws Exception {
        List<List<String>> records = new ArrayList<>();
        for (String file : files) {
            String store_name = getStoreName(file);
            LocalDate price_date = extractDate(file);
            extract(records, file);
            for (List<String> record : records) {
                addProduct(record);
            }
            addPrices(store_name, records, price_date);
            records.clear();
        }
    }

    private void extract(List<List<String>> records, String file) throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(file);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(DELIMITER);
                records.add(Arrays.asList(data));
            }
        }
    }

    private void addPrices(String storeName, List<List<String>> records, LocalDate price_date) {
        Store store = storeRepository.findByName(storeName);
        for (List<String> record : records) {
            String productId = record.get(0);
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                deactivatePreviousPrices(store, product.get());
                store.getPrices().add(new ProductPrice(
                        product.get(),
                        store,
                        Double.parseDouble(record.get(6)),
                        record.get(7),
                        price_date
                ));
            }
        }
        storeRepository.save(store);
    }

    private void deactivatePreviousPrices(Store store, Product product) {
        List<ProductPrice> activePrices = store.getPrices().stream()
                .filter(price -> price.getProduct().equals(product))
                .filter(ProductPrice::isActive)
                .toList();
        activePrices.forEach(price -> price.setActive(false));
    }

    private void addDiscounts(String storeName, List<List<String>> records) {
        Store store = storeRepository.findByName(storeName);
        for (List<String> record : records) {
            String productId = record.getFirst();
            Optional<Product> product = productRepository.findById(productId);
            if (product.isEmpty()) {
                continue;
            }
            LocalDate startDate = LocalDate.parse(record.get(6));
            LocalDate endDate = LocalDate.parse(record.get(7));
            int discountPercentage = Integer.parseInt(record.get(8));
            deactivateExistingDiscounts(store, product.get());
            ProductDiscount newDiscount = new ProductDiscount(
                    product.get(),
                    store,
                    discountPercentage,
                    startDate,
                    endDate
            );
            store.getDiscounts().add(newDiscount);
        }
        storeRepository.save(store);
    }

    private void deactivateExistingDiscounts(Store store, Product product) {
        store.getDiscounts().stream()
                .filter(d -> d.getProduct().equals(product))
                .filter(ProductDiscount::isActive)
                .forEach(d -> d.setActive(false));
    }

    private void addProduct(List<String> product) {
        Product p = new Product(
                product.get(0),
                product.get(1),
                product.get(2),
                product.get(3),
                Double.parseDouble(product.get(4)),
                product.get(5)
        );
        productRepository.save(p);
    }

    private String getStoreName(String path) {
        String filename = Paths.get(path).getFileName().toString();
        String storeName = filename.split("_")[0];
        return storeName.substring(0, 1).toUpperCase() + storeName.substring(1);
    }

    private LocalDate extractDate(String path) {
        String filename = Paths.get(path).getFileName().toString();
        String datePart = filename.split("_")[1].replace(".csv", "");
        return LocalDate.parse(datePart);
    }
}
