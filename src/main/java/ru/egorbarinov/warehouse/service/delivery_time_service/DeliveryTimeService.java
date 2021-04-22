package ru.egorbarinov.warehouse.service.delivery_time_service;

import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.dto.DeliveryTimeDto;

import java.util.List;


public interface DeliveryTimeService {
    List<DeliveryTimeDto> findAll();
    DeliveryTimeDto findById(Long id) throws ResourceNotFoundException;
//    void createDeliveryTime(DeliveryTimeDto deliveryTimeDto);
//    void updateDeliveryTime(DeliveryTimeDto deliveryTimeDto) throws ResourceNotFoundException;
    DeliveryTimeDto save(DeliveryTimeDto deliveryTimeDto) throws ResourceNotFoundException;
    void delete(Long id);
    void saveAll(List<DeliveryTimeDto> deliveryTimeDto);
}
