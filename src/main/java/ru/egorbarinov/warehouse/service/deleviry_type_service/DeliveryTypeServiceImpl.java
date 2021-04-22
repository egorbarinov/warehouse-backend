package ru.egorbarinov.warehouse.service.deleviry_type_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egorbarinov.warehouse.dto.DeliveryTypeDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.domain.DeliveryType;
import ru.egorbarinov.warehouse.repository.DeliveryTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DeliveryTypeServiceImpl implements DeliveryTypeService{

    private final DeliveryTypeRepository deliveryTypeRepository;

    @Override
    public List<DeliveryTypeDto> findAll() {
        log.info("Working method DeliveryType findAll");
        return deliveryTypeRepository.findAll().stream().map(DeliveryTypeDto::new).collect(Collectors.toList());
    }

    @Override
    public DeliveryTypeDto findById(Long id) throws ResourceNotFoundException {
        DeliveryType deliveryType = deliveryTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Дата по указанному id не найдена:  id = " + id));
        log.info("Working method DeliveryType findById: {}", deliveryType);
        return new DeliveryTypeDto(deliveryType);
    }

    @Override
    public DeliveryTypeDto save(DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException {
        if (deliveryTypeDto.getId() == null) {
            log.info("Working method DeliveryType save: {} is null, create new", deliveryTypeDto.getId());
            return new DeliveryTypeDto(deliveryTypeRepository.save(new DeliveryType(deliveryTypeDto)));
        }
        DeliveryType deliveryType = deliveryTypeRepository.findById(deliveryTypeDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Тип поставки с id = " + deliveryTypeDto.getId() + " не найден"));
        deliveryType.updateFields(deliveryTypeDto);
        log.info("Working method DeliveryType save: {}", deliveryType);
        return new DeliveryTypeDto(deliveryTypeRepository.save(deliveryType));
    }

    @Override
    public void delete(Long id) {
        deliveryTypeRepository.deleteById(id);
        log.info("Working method DeliveryType delete: id {} is delete ", id);
    }

    @Override
    public void saveAll(List<DeliveryTypeDto> deliveryTypeDto) {
        List<DeliveryType> deliveryTypes = deliveryTypeDto.stream().map(DeliveryType::new).collect(Collectors.toList());
        deliveryTypeRepository.saveAll(deliveryTypes);
        log.info("Working method DeliveryType saveAll: {} is save", deliveryTypes);
    }
}
