package ru.egorbarinov.warehouse.service.warehouse_service;

import ru.egorbarinov.warehouse.dto.WarehouseDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;

import java.util.List;

public interface WarehouseService {

    WarehouseDto findById(Long id) throws ResourceNotFoundException;
    List<WarehouseDto> findAll();
    WarehouseDto save(WarehouseDto warehouseDto) throws ResourceNotFoundException;
    void delete(Long id);
    public void saveAll(List<WarehouseDto> warehouseDtos);
}
