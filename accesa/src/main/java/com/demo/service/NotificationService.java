package com.demo.service;

import com.demo.model.PriceAlert;
import com.demo.repository.PriceAlertRepository;
import com.demo.repository.ProductPriceRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final PriceAlertRepository priceAlertRepository;
    private final ProductPriceRepository productPriceRepository;

    public NotificationService(PriceAlertRepository priceAlertRepository, ProductPriceRepository productPriceRepository) {
        this.priceAlertRepository = priceAlertRepository;
        this.productPriceRepository = productPriceRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void sendNotification() {
        List<PriceAlert> alerts = priceAlertRepository.findAll();
        for (PriceAlert alert : alerts) {
            if (productPriceRepository.checkPrices(alert.getProduct().getId(), alert.getWantedPrice())) {
                System.out.println("ALERT SENT");
            }
        }
    }
}
