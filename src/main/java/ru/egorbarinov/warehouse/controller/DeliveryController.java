package ru.egorbarinov.warehouse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.egorbarinov.warehouse.dto.UniqueReportObject;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.dto.DeliveryDto;
import ru.egorbarinov.warehouse.exception.ExceedingAllowedDateValueException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.service.delivery_service.DeliveryService;
import ru.egorbarinov.warehouse.service.excel_report_service.ExcelReportView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin({"http://localhost:4200","https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1/deliveries")
//@SecurityRequirement(name = "bearerAuth")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryById(@PathVariable(value = "id") Long deliveryId)
            throws ResourceNotFoundException {
        DeliveryDto delivery = deliveryService.findById(deliveryId);
        return ResponseEntity.ok().body(delivery);
    }

    @PostMapping
    public ResponseEntity<DeliveryDto> addNewDelivery(@RequestBody DeliveryDto deliveryDto) throws ExceedingAllowedDateValueException, ResourceNotFoundException, WarehouseException {
        deliveryDto.setId(null);
        return ResponseEntity.ok(deliveryService.save(deliveryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryDto> updateDelivery(@PathVariable(value = "id") Long deliveryId, @RequestBody DeliveryDto deliveryDto) throws ResourceNotFoundException, ExceedingAllowedDateValueException, WarehouseException {
        deliveryDto.setId(deliveryId);
        deliveryService.save(deliveryDto);
        return ResponseEntity.ok(deliveryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDelivery(@PathVariable(value = "id") Long deliveryId) throws ResourceNotFoundException {
        deliveryService.delete(deliveryId);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/grouped-save")
    public void saveAllDeliveries(@RequestBody List<DeliveryDto> deliveries) throws WarehouseException {
        deliveryService.saveAll(deliveries);
    }

    // http://localhost:8189/api/v1/deliveries?first=2021-04-23&last=2021-12-25
    @GetMapping
    public ResponseEntity<List<DeliveryDto>> filterByDate(@RequestParam(required = false, name = "first") String first,
                               @RequestParam(required = false, name = "last") String last){
        List<DeliveryDto> lists;
        if (first == null && last == null) {
            lists =deliveryService.findAll();
        }
        else if (first != null && last == null) {
            lists = deliveryService.findByDeliveryDateGreaterThanEqual(first);
        }
        else if (first == null) {
            lists = deliveryService.findByDeliveryDateLessThanEqual(last);
        }
        else {
            lists = deliveryService.findByDeliveryDateIsBetween(first, last);
        }
        return ResponseEntity.ok().body(lists);
    }

    @PostMapping(value = "/report", produces = "application/vnd.ms-excel") // produces = "application/vnd.ms-excel"
    public ModelAndView exportDeliveriesToExcel(@RequestBody List<DeliveryDto> deliveries, @RequestParam("columns") String[] columns){
        Map<String, Object> map = new HashMap<>();
        map.put("reportDeliveries", deliveries);
        map.put("reportHeaders", columns);
        return new ModelAndView(new ExcelReportView(), map);
    }

    @GetMapping("/uniqueDeliveriesReport")
    public List<UniqueReportObject> getUniqueDeliveriesByRange(@RequestParam(required = false, name = "first") String first,
                                                               @RequestParam(required = false, name = "last") String last) {
        return  deliveryService.getUniqueDeliveriesByRange(first, last);
    }

}
