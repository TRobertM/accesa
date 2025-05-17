package com.demo.service.interfaces;

import com.demo.dto.ShoppingListDTO;

import java.util.List;

public interface IShoppingListService {
    String optimizeBasket();
    List<ShoppingListDTO> getShoppingLists();
}
