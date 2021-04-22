package ru.egorbarinov.warehouse.service.deleviry_type_service;

import ru.egorbarinov.warehouse.dto.DeliveryTypeDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;

import java.util.List;

public interface DeliveryTypeService {
    List<DeliveryTypeDto> findAll();
    DeliveryTypeDto findById(Long id) throws ResourceNotFoundException;
    DeliveryTypeDto save(DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException;
    void delete(Long id);
    void saveAll(List<DeliveryTypeDto> deliveryTypeDto);
}
