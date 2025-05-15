package com.demo.util;

import com.demo.model.Product;
import com.demo.model.ProductDiscount;
import com.demo.model.ProductPrice;
import com.demo.model.Store;
import com.demo.repository.ProductRepository;
import com.demo.repository.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvParser implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final List<String> price_files = List.of(
            "products/kaufland_2025-05-01.csv", "products/kaufland_2025-05-08.csv",
            "products/lidl_2025-05-01.csv", "products/lidl_2025-05-01.csv",
            "products/profi_2025-05-01.csv", "products/profi_2025-05-08.csv"
    );
    private final List<String> discount_files = List.of(
            "discounts/kaufland_2025-05-01.csv", "discounts/kaufland_2025-05-08.csv",
            "discounts/lidl_2025-05-01.csv", "discounts/lidl_2025-05-01.csv",
            "discounts/profi_2025-05-01.csv", "discounts/profi_2025-05-08.csv"
    );
    private final String DELIMITER = ";";

    @Autowired
    public CsvParser(StoreRepository storeRepository, ProductRepository productRepository) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        addStores();
        parsePrices(price_files);
        parseDiscounts(discount_files);
    }

    private void addStores() {
        storeRepository.save(new Store("Kaufland"));
        storeRepository.save(new Store("Lidl"));
        storeRepository.save(new Store("Profi"));
    }

    public void parseDiscounts(List<String> files) throws Exception{
        List<List<String>> records = new ArrayList<>();
        for (String file : files) {
            String store_name = getStoreName(file);
            extract(records, file);
            addDiscounts(store_name, records);
            records.clear();
        }
    }

    public void parsePrices(List<String> files) throws Exception{
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
            Product product = productRepository.findById(record.get(0));
            store.getPrices().add(new ProductPrice(
                    product,
                    store,
                    Double.parseDouble(record.get(6)),
                    record.get(7),
                    price_date
            ));
        }
        storeRepository.save(store);
    }

    private void addDiscounts(String storeName, List<List<String>> records) {
        Store store = storeRepository.findByName(storeName);
        for (List<String> record : records) {
            Product product = productRepository.findById(record.get(0));
            store.getDiscounts().add(new ProductDiscount(
                    product,
                    store,
                    Integer.parseInt(record.get(8)),
                    LocalDate.parse(record.get(6)),
                    LocalDate.parse(record.get(7))
            ));
        }
        storeRepository.save(store);
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
