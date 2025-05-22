package com.demo.service.implementations;

import com.demo.dto.BasketDTO;
import com.demo.dto.BasketItemCreationDTO;
import com.demo.dto.ProductDTO;
import com.demo.exception.ProductDoesNotExistException;
import com.demo.exception.UserDoesNotExistException;
import com.demo.mapper.BasketMapper;
import com.demo.mapper.ProductMapper;
import com.demo.model.Basket;
import com.demo.model.BasketItem;
import com.demo.model.Product;
import com.demo.model.User;
import com.demo.repository.BasketRepository;
import com.demo.repository.ProductRepository;
import com.demo.repository.UserRepository;
import com.demo.security.SecurityUtil;
import com.demo.service.interfaces.IBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService implements IBasketService {

    private final BasketRepository basketRepository;
    private final SecurityUtil securityUtil;
    private final BasketMapper basketMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository, SecurityUtil securityUtil, BasketMapper basketMapper, ProductRepository productRepository, ProductMapper productMapper, UserRepository userRepository) {
        this.basketRepository = basketRepository;
        this.securityUtil = securityUtil;
        this.basketMapper = basketMapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }

    public BasketDTO getBasket() {
        String userId = securityUtil.getUserId();
        Basket basket = basketRepository.findByUserId(userId);
        return basketMapper.basketToDTO(basket);
    }

    public ProductDTO addToBasket(BasketItemCreationDTO basketItemCreationDTO) {
        Product product = productRepository.findById(basketItemCreationDTO.productId()).orElseThrow(ProductDoesNotExistException::new);
        Basket basket = userRepository.findById(securityUtil.getUserId()).orElseThrow(UserDoesNotExistException::new).getBasket();
        basket.addItem(
                new BasketItem(
                        product,
                        basketItemCreationDTO.quantity()
                )
        );
        basketRepository.save(basket);
        return productMapper.productToDTO(product);
    }
}
