package com.demo.mapper;

import com.demo.dto.ShoppingListDTO;
import com.demo.dto.ShoppingListItemDTO;
import com.demo.model.ShoppingList;
import com.demo.model.ShoppingListItem;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListMapper {

    private final ProductMapper productMapper;

    public ShoppingListMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public ShoppingListDTO toShoppingListDTO(ShoppingList shoppingList) {
        return new ShoppingListDTO(
                shoppingList.getId(),
                shoppingList.getName(),
                shoppingList.getItems().stream().map(this::shoppingListItemToDTO).toList(),
                shoppingList.getItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum(),
                shoppingList.getCreatedAt()
        );
    }

    private ShoppingListItemDTO shoppingListItemToDTO(ShoppingListItem shoppingListItem) {
        return new ShoppingListItemDTO(
                shoppingListItem.getId(),
                productMapper.productToDTO(shoppingListItem.getProduct()),
                shoppingListItem.getQuantity(),
                shoppingListItem.getPrice(),
                shoppingListItem.getDiscount(),
                shoppingListItem.getPrice() * shoppingListItem.getQuantity()
        );
    }
}
