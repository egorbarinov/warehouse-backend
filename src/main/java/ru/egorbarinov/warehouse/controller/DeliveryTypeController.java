package ru.egorbarinov.warehouse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.warehouse.dto.DeliveryTypeDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.service.deleviry_type_service.DeliveryTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DeliveryTypeController {

    private final DeliveryTypeService deliveryTypeService;

    public DeliveryTypeController(DeliveryTypeService deliveryTypeService) {
        this.deliveryTypeService = deliveryTypeService;
    }

    @GetMapping("/delivery-types")
    public List<DeliveryTypeDto> getAllDeliveryType() {
        return deliveryTypeService.findAll();
    }

    @GetMapping(value = "/delivery-types/{id}")
    public ResponseEntity<DeliveryTypeDto> getDeliveryTypeById(@PathVariable(value = "id") Long deliveryTypeId)
            throws ResourceNotFoundException {
        DeliveryTypeDto deliveryTypeDto = deliveryTypeService.findById(deliveryTypeId);
        return ResponseEntity.ok().body(deliveryTypeDto);
    }

    @PostMapping("/delivery-types")
    public ResponseEntity<DeliveryTypeDto> addNewDeliveryType(@RequestBody DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException, WarehouseException {
        deliveryTypeDto.setId(null);
        return ResponseEntity.ok(deliveryTypeService.save(deliveryTypeDto));
    }

    @PutMapping("/delivery-types/{id}")
    public ResponseEntity<DeliveryTypeDto> updateDeliveryType(@PathVariable(value = "id") Long id, @RequestBody DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException, WarehouseException {
        deliveryTypeDto.setId(id);
        deliveryTypeService.save(deliveryTypeDto);
        return ResponseEntity.ok(deliveryTypeDto);
    }

    @DeleteMapping("/delivery-types/{id}")
    public ResponseEntity<String> deleteDeliveryType(@PathVariable(value = "id") Long id) {
        deliveryTypeService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/grouped-delivery-types")
    public void saveAllDeliveryTypes(@RequestBody List<DeliveryTypeDto> deliveryTypeDto) {
        deliveryTypeService.saveAll(deliveryTypeDto);
    }
}
