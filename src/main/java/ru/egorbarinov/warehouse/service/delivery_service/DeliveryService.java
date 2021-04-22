package ru.egorbarinov.warehouse.service.delivery_service;

import ru.egorbarinov.warehouse.dto.UniqueReportObject;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.dto.DeliveryDto;

import java.util.List;

public interface DeliveryService {

    List<DeliveryDto> findAll();
    DeliveryDto findById(Long id) throws ResourceNotFoundException;
    void delete(Long id);
    void saveAll(List<DeliveryDto> deliveryDtos) throws WarehouseException;
    List<DeliveryDto> findByDeliveryDateIsBetween(String first, String second);
    List<DeliveryDto> findByDeliveryDateGreaterThanEqual(String date);
    List<DeliveryDto> findByDeliveryDateLessThanEqual(String date);
    List<DeliveryDto> getByDate(String first, String last);
    DeliveryDto save(DeliveryDto deliveryDto) throws ResourceNotFoundException;
    List<UniqueReportObject> getUniqueDeliveriesByRange(String first, String last);

}
