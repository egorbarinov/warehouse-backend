package ru.egorbarinov.warehouse.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.warehouse.dto.ShopDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.service.shop_service.ShopService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {this.shopService = shopService;}

    @GetMapping("/shops")
    public List<ShopDto> getAllShop() {return shopService.findAll();}

    @GetMapping(value = "/shops/{id}")
    public ResponseEntity<ShopDto> getShopById(@PathVariable(value = "id") Long shopId) throws ResourceNotFoundException {
        ShopDto shopDto = shopService.findById(shopId);
        return ResponseEntity.ok().body(shopDto);
    }

    @PostMapping("/shops")
    public ResponseEntity<ShopDto> addNewBrand(@RequestBody ShopDto shopDto) throws ResourceNotFoundException, WarehouseException {
        shopDto.setId(null);
        return ResponseEntity.ok(shopService.save(shopDto));
    }

    @PutMapping("/shops/{id}")
    public ResponseEntity<ShopDto> updateBrand(@PathVariable(value = "id") Long id, @RequestBody ShopDto shopDto) throws ResourceNotFoundException, WarehouseException {
        shopDto.setId(id);
        shopService.save(shopDto);
        return ResponseEntity.ok(shopDto);
    }

    @DeleteMapping("/shops/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable(value = "id") Long id) {
        shopService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/grouped-shops")
    public void saveAllShops(@RequestBody List<ShopDto> shopDtos) {
        shopService.saveAll(shopDtos);
    }

}
