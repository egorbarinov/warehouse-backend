package ru.egorbarinov.warehouse.service.brand_service;

import ru.egorbarinov.warehouse.dto.BrandDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;

import java.util.List;

public interface BrandService {

    List<BrandDto> findAll();
    BrandDto findById(Long id) throws ResourceNotFoundException;
    void delete(Long id);
    void saveAll(List<BrandDto> brandDtos);
    BrandDto save(BrandDto brandDto) throws ResourceNotFoundException;
}
