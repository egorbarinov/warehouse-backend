package ru.egorbarinov.warehouse.service.shop_service;

import ru.egorbarinov.warehouse.dto.ShopDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;

import java.util.List;

public interface ShopService {

    ShopDto findById(Long id) throws ResourceNotFoundException;
    List<ShopDto> findAll();
    ShopDto save(ShopDto shopDto) throws ResourceNotFoundException;
    void delete(Long id);
    void saveAll(List<ShopDto> shopDtos);
}
