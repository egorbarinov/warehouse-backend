package ru.egorbarinov.warehouse.service.brand_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egorbarinov.warehouse.dto.BrandDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.domain.Brand;
import ru.egorbarinov.warehouse.repository.BrandRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService{

   private final BrandRepository brandRepository;

    @Override
    public List<BrandDto> findAll() {
        List<BrandDto> brandDtoList = brandRepository.findAll().stream().map(BrandDto::new).collect(Collectors.toList());
        return brandDtoList;
    }

    @Override
    public BrandDto findById(Long id) throws ResourceNotFoundException {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Поставка по указанному id не найдена:  id = " + id));
        log.info("Working method findById {}",brand);
        BrandDto brandDto = new BrandDto(brand);
        return brandDto;
    }

    @Override
    public BrandDto save(BrandDto brandDto) throws ResourceNotFoundException {
        if (brandDto.getId() == null) {
            log.info("{} is null", brandDto.getId());
            BrandDto brandDto1 = new BrandDto(brandRepository.save(new Brand(brandDto)));
            return brandDto1;
        }
        Brand brand = brandRepository.findById(brandDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Бренд с id = " + brandDto.getId() + " не найден"));
        brand.updateFields(brandDto);
        log.info("Working method save {}",brand);
        brandRepository.save(brand);
        BrandDto brandDto1 = new BrandDto(brand);
        return brandDto1;
    }

    @Override
    public void delete(Long id) {
        log.info("Brand id = {} is delete ", id);
        brandRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<BrandDto> brandDto) {
        List<Brand> brands = brandDto.stream().map(Brand::new).collect(Collectors.toList());
        log.info("Brand list is save{}", brands);
        brandRepository.saveAll(brands);
    }
}
