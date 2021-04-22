package ru.egorbarinov.warehouse.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.warehouse.dto.WarehouseDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.service.warehouse_service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {this.warehouseService = warehouseService;}

    @GetMapping("/warehouses")
    public List<WarehouseDto> getAllWarehouses() {return warehouseService.findAll();}

    @GetMapping(value = "/warehouses/{id}")
    public ResponseEntity<WarehouseDto> getWarehouseById(@PathVariable(value = "id") Long warehouseId) throws ResourceNotFoundException {
        WarehouseDto warehouseDto = warehouseService.findById(warehouseId);
        return ResponseEntity.ok().body(warehouseDto);
    }

    @PostMapping("/warehouses")
    public ResponseEntity<WarehouseDto> addNewWarehouse(@RequestBody WarehouseDto warehouseDto) throws ResourceNotFoundException, WarehouseException {
        warehouseDto.setId(null);
        return ResponseEntity.ok(warehouseService.save(warehouseDto));
    }

    @PutMapping("/warehouses/{id}")
    public ResponseEntity<WarehouseDto> updateWarehouse(@PathVariable(value = "id") Long id, @RequestBody WarehouseDto warehouseDto) throws ResourceNotFoundException, WarehouseException {
        warehouseDto.setId(id);
        warehouseService.save(warehouseDto);
        return ResponseEntity.ok(warehouseDto);
    }

    @DeleteMapping("/warehouses/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable(value = "id") Long id) {
        warehouseService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/grouped-warehouses")
    public void saveAllWarehouses(@RequestBody List<WarehouseDto> warehouseDtos) {
        warehouseService.saveAll(warehouseDtos);
    }
}
