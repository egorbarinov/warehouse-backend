package ru.egorbarinov.warehouse.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.warehouse.dto.BrandDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.service.brand_service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public List<BrandDto> getAllBrand() {
        return brandService.findAll();
    }

    @GetMapping(value = "/brands/{id}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable(value = "id") Long brandId)
            throws ResourceNotFoundException {
        BrandDto brandDto = brandService.findById(brandId);
        return ResponseEntity.ok().body(brandDto);
    }

    @PostMapping("/brands")
    public ResponseEntity<BrandDto> addNewBrand(@RequestBody BrandDto brandDto) throws ResourceNotFoundException, WarehouseException {
        brandDto.setId(null);
        return ResponseEntity.ok(brandService.save(brandDto));
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<BrandDto> updateBrand(@PathVariable(value = "id") Long id, @RequestBody BrandDto brandDto) throws ResourceNotFoundException, WarehouseException {
        brandDto.setId(id);
        brandService.save(brandDto);
        return ResponseEntity.ok(brandDto);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable(value = "id") Long id) {
        brandService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/grouped-brands")
    public void saveAllBrands(@RequestBody List<BrandDto> brandDtos) {
        brandService.saveAll(brandDtos);
    }

}
