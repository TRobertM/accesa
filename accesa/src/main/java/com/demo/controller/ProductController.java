package com.demo.controller;

import com.demo.dto.PricedProductDTO;
import com.demo.service.implementations.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PricedProductDTO>> getProductById(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findProductById(id));
    }
}
