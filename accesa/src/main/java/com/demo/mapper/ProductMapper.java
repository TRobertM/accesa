package com.demo.mapper;

import com.demo.dto.ProductDTO;
import com.demo.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO productToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getBrand(),
                product.getPackage_quantity(),
                product.getPackage_unit()
        );
    }
}
