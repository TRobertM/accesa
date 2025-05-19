package com.demo.service.interfaces;

import com.demo.dto.CreatePriceAlertDTO;
import com.demo.dto.PriceAlertDTO;

import java.util.List;

public interface IPriceAlertService {
    List<PriceAlertDTO> getUserPriceAlerts();
    PriceAlertDTO createUserPriceAlert(CreatePriceAlertDTO createPriceAlertDTO);
}
