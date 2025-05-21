package com.demo.service.implementations;

import com.demo.dto.CreatePriceAlertDTO;
import com.demo.dto.PriceAlertDTO;
import com.demo.exception.ProductDoesNotExistException;
import com.demo.exception.UserDoesNotExistException;
import com.demo.mapper.PriceAlertMapper;
import com.demo.model.PriceAlert;
import com.demo.model.Product;
import com.demo.model.User;
import com.demo.repository.PriceAlertRepository;
import com.demo.repository.ProductRepository;
import com.demo.repository.UserRepository;
import com.demo.security.SecurityUtil;
import com.demo.service.interfaces.IPriceAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAlertService implements IPriceAlertService {

    private final PriceAlertRepository priceAlertRepository;
    private final SecurityUtil securityUtil;
    private final PriceAlertMapper priceAlertMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public PriceAlertService(PriceAlertRepository priceAlertRepository, SecurityUtil securityUtil, PriceAlertMapper priceAlertMapper, ProductRepository productRepository, UserRepository userRepository) {
        this.priceAlertRepository = priceAlertRepository;
        this.securityUtil = securityUtil;
        this.priceAlertMapper = priceAlertMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<PriceAlertDTO> getUserPriceAlerts() {
        List<PriceAlert> alerts = priceAlertRepository.findAllByUserId(securityUtil.getUserId());
        if (!alerts.isEmpty()) {
            return alerts.stream().map(priceAlertMapper::priceAlertToDTO).toList();
        } else return List.of();
    }

    public PriceAlertDTO createUserPriceAlert(CreatePriceAlertDTO createPriceAlertDTO) {
        Product product = productRepository.findById(createPriceAlertDTO.productId()).orElseThrow(ProductDoesNotExistException::new);
        User user = userRepository.findById(securityUtil.getUserId()).orElseThrow(UserDoesNotExistException::new);
        PriceAlert priceAlert = new PriceAlert(product, user, createPriceAlertDTO.wantedPrice());
        user.addPriceAlert(priceAlert);
        return priceAlertMapper.priceAlertToDTO(priceAlertRepository.save(priceAlert));
    }
}
