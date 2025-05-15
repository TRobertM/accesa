package com.demo.mapper;

import com.demo.dto.StoreDTO;
import com.demo.model.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    public StoreDTO storeToDTO(Store store) {
        return new StoreDTO(
                store.getId(),
                store.getName()
        );
    }
}
