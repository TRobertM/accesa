package com.demo.service.implementations;

import com.demo.dto.ShoppingListDTO;
import com.demo.exception.UserDoesNotExistException;
import com.demo.mapper.ShoppingListMapper;
import com.demo.model.*;
import com.demo.repository.ProductPriceRepository;
import com.demo.repository.UserRepository;
import com.demo.security.SecurityUtil;
import com.demo.service.PriceService;
import com.demo.service.interfaces.IShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShoppingListService implements IShoppingListService {

    private final ProductPriceRepository productPriceRepository;
    private final PriceService priceService;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;
    private final ShoppingListMapper shoppingListMapper;

    @Autowired
    public ShoppingListService(ProductPriceRepository productPriceRepository, PriceService priceService, UserRepository userRepository, SecurityUtil securityUtil, ShoppingListMapper shoppingListMapper) {
        this.productPriceRepository = productPriceRepository;
        this.priceService = priceService;
        this.userRepository = userRepository;
        this.securityUtil = securityUtil;
        this.shoppingListMapper = shoppingListMapper;
    }

    public String optimizeBasket() {
        Map<Store, List<ShoppingListItem>> items = new HashMap<>();
        User user = userRepository.findById(securityUtil.getUserId()).orElseThrow(UserDoesNotExistException::new);
        Basket basket = user.getBasket();

        for (BasketItem item : basket.getBasketItems()) {
            Product product = item.getProduct();
            List<ProductPrice> prices = productPriceRepository.findAllByProductAndActiveTrue(product);
            PriceService.BestPriceResult bestPrice = priceService.findBestPrice(product, prices);
            ShoppingListItem shoppingListItem = createShoppingListItem(
                    product,
                    item.getQuantity(),
                    bestPrice.price(),
                    bestPrice.discount()
            );
            items.computeIfAbsent(bestPrice.store(), s -> new ArrayList<>()).add(shoppingListItem);
        }
        createShoppingLists(user, items);
        user.getBasket().getBasketItems().clear();
        userRepository.save(user);
        return "Optimization completed";
    }

    public List<ShoppingListDTO> getShoppingLists() {
        User user = userRepository.findById(securityUtil.getUserId()).orElseThrow(UserDoesNotExistException::new);
        return user.getShoppingLists().stream().map(shoppingListMapper::toShoppingListDTO).collect(Collectors.toList());
    }

    private ShoppingListItem createShoppingListItem(Product product, int quantity, double price, int discount) {
        ShoppingListItem item = new ShoppingListItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setDiscount(discount);
        return item;
    }

    private List<ShoppingList> createShoppingLists(User user, Map<Store, List<ShoppingListItem>> storeItemsMap) {
        List<ShoppingList> shoppingLists = new ArrayList<>();

        for (Map.Entry<Store, List<ShoppingListItem>> entry : storeItemsMap.entrySet()) {
            Store store = entry.getKey();
            List<ShoppingListItem> items = entry.getValue();

            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setName(store.getName() + " shopping list");
            shoppingList.setUser(user);
            shoppingList.setStore(store);
            shoppingList.setCreatedAt(LocalDateTime.now());

            items.forEach(item -> item.setShoppingList(shoppingList));
            shoppingList.setItems(items);

            shoppingLists.add(shoppingList);
        }

        return shoppingLists;
    }
}
