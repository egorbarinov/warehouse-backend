package ru.egorbarinov.warehouse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.warehouse.dto.DeliveryTimeDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.service.delivery_time_service.DeliveryTimeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DeliveryTimeController {

    private final DeliveryTimeService deliveryTimeService;

    public DeliveryTimeController(DeliveryTimeService deliveryTimeService) {
        this.deliveryTimeService = deliveryTimeService;
    }

    @GetMapping("/delivery-times")
    public List<DeliveryTimeDto> getAllDeliveryTime() {
        return deliveryTimeService.findAll();
    }

    @GetMapping(value = "/delivery-times/{id}")
    public ResponseEntity<DeliveryTimeDto> getDeliveryTimeById(@PathVariable(value = "id") Long deliveryTimeId)
            throws ResourceNotFoundException {
        DeliveryTimeDto deliveryTimeDto = deliveryTimeService.findById(deliveryTimeId);
        return ResponseEntity.ok().body(deliveryTimeDto);
    }

    @PostMapping("/delivery-times")
    public ResponseEntity<DeliveryTimeDto> addNewDeliveryTime(@RequestBody DeliveryTimeDto deliveryTimeDto) throws ResourceNotFoundException, WarehouseException {
        deliveryTimeDto.setId(null);
        return ResponseEntity.ok(deliveryTimeService.save(deliveryTimeDto));
    }

    @PutMapping("/delivery-times/{id}")
    public ResponseEntity<DeliveryTimeDto> updateDeliveryTime(@PathVariable(value = "id") Long id, @RequestBody DeliveryTimeDto deliveryTimeDto) throws ResourceNotFoundException, WarehouseException {
        deliveryTimeDto.setId(id);
        deliveryTimeService.save(deliveryTimeDto);
        return ResponseEntity.ok(deliveryTimeDto);
    }

    @DeleteMapping("/delivery-times/{id}")
    public ResponseEntity<String> deleteDeliveryTime(@PathVariable(value = "id") Long id) {
        deliveryTimeService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/grouped-delivery-times")
    public void saveAllDeliveryTimes(@RequestBody List<DeliveryTimeDto> deliveryTimeDto) {
        deliveryTimeService.saveAll(deliveryTimeDto);
    }
}
