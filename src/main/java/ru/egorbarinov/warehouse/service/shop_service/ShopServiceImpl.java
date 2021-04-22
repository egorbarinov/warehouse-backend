package ru.egorbarinov.warehouse.service.shop_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egorbarinov.warehouse.dto.ShopDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.domain.Shop;
import ru.egorbarinov.warehouse.repository.ShopRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    @Override
    public ShopDto findById(Long id) throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Магазин по указанному id не найден: id = " + id));
        log.info("Working method ShopService findById {}", shop);
        return new ShopDto(shop);
    }

    @Override
    public List<ShopDto> findAll() {
        log.info("Working method ShopService findAll");
        return shopRepository.findAll().stream().map(ShopDto::new).collect(Collectors.toList());
    }

    @Override
    public ShopDto save(ShopDto shopDto) throws ResourceNotFoundException {
        if (shopDto.getId() == null) {
            log.info("Working method ShopService save {} is null, create new", shopDto.getId());
            return new ShopDto(shopRepository.save(new Shop(shopDto)));
        }
        Shop shop = shopRepository.findById(shopDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Магазин с id = " + shopDto.getId() + " не найден"));
        shop.updateFields(shopDto);
        log.info("Working method ShopService save {}", shop);
        return new ShopDto(shopRepository.save(shop));
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
        log.info("Working method ShopService delete {}", id);
    }

    @Override
    public void saveAll(List<ShopDto> shopDtos) {
        List<Shop> shops = shopDtos.stream().map(Shop::new).collect(Collectors.toList());
        shopRepository.saveAll(shops);
        log.info("Working method ShopService saveAll {}", shops);
    }
}
