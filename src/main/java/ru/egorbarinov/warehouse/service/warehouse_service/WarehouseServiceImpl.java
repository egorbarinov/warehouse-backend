package ru.egorbarinov.warehouse.service.warehouse_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ru.egorbarinov.warehouse.domain.Warehouse;
import ru.egorbarinov.warehouse.dto.WarehouseDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseRepository warehouseRepository;

    @Override
    public WarehouseDto findById(Long id) throws ResourceNotFoundException {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Склад по указанному id не найден: id = " + id));
        log.info("Working method WarehouseService findById: {}", warehouse);
        return new WarehouseDto(warehouse);
    }

    @Override
    public List<WarehouseDto> findAll() {
        log.info("Working method WarehouseService findAll");
        return warehouseRepository.findAll().stream().map(WarehouseDto::new).collect(Collectors.toList());
    }

    @Override
    public WarehouseDto save(WarehouseDto warehouseDto) throws ResourceNotFoundException {
        if (warehouseDto.getId() == null) {
            log.info("Working method WarehouseService save: {} is null, create new", warehouseDto.getId());
            return new WarehouseDto(warehouseRepository.save(new Warehouse(warehouseDto)));
        }
        Warehouse warehouse = warehouseRepository.findById(warehouseDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Склад с id = " + warehouseDto.getId() + " не найден"));
        warehouse.updateFields(warehouseDto);
        log.info("Working method WarehouseService save: {}", warehouse);
        return new WarehouseDto(warehouseRepository.save(warehouse));
    }

    @Override
    public void delete(Long id) {
        warehouseRepository.deleteById(id);
        log.info("Working method WarehouseService delete: id = {}", id);
    }

    @Override
    public void saveAll(List<WarehouseDto> warehouseDtos) {
        List<Warehouse> warehouses = warehouseDtos.stream().map(Warehouse::new).collect(Collectors.toList());
        warehouseRepository.saveAll(warehouses);
        log.info("Working method WarehouseService saveAll: {}", warehouses);
    }
}
