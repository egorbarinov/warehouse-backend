package ru.egorbarinov.warehouse.service.delivery_time_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.repository.DeliveryTimeRepository;
import ru.egorbarinov.warehouse.domain.DeliveryTime;
import ru.egorbarinov.warehouse.dto.DeliveryTimeDto;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DeliveryTimeServiceImpl implements DeliveryTimeService{

    private final DeliveryTimeRepository deliveryTimeRepository;

    @Override
    public List<DeliveryTimeDto> findAll() {
        log.info("Working method DeliveryTime findAll");
        return deliveryTimeRepository.findAll().stream().map(DeliveryTimeDto::new).collect(Collectors.toList());
    }

    @Override
    public DeliveryTimeDto findById(Long id) throws ResourceNotFoundException {
        DeliveryTime deliveryTime = deliveryTimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Дата по указанному id не найдена:  id = " + id));
        log.info("Working method DeliveryTime findById: {}", id);
        return new DeliveryTimeDto(deliveryTime);
    }

    @Override
    public DeliveryTimeDto save(DeliveryTimeDto deliveryTimeDto) throws ResourceNotFoundException {
        if (deliveryTimeDto.getId() == null) {
            log.info("Working method DeliveryTime save: {} is null, create new", deliveryTimeDto.getId());
            return new DeliveryTimeDto(deliveryTimeRepository.save(new DeliveryTime(deliveryTimeDto)));
        }
        DeliveryTime deliveryTime = deliveryTimeRepository.findById(deliveryTimeDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Время поставки с id = " + deliveryTimeDto.getId() + " не найдено"));
        deliveryTime.updateFields(deliveryTimeDto);
        log.info("Working method DeliveryTime save: {}", deliveryTime);
        return new DeliveryTimeDto(deliveryTimeRepository.save(deliveryTime));
    }

    @Override
    public void delete(Long id) {
        log.info("Working method DeliveryTime delete: {}", id);
        deliveryTimeRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<DeliveryTimeDto> deliveryTimeDto) {
        List<DeliveryTime> deliveryTime = deliveryTimeDto.stream().map(DeliveryTime::new).collect(Collectors.toList());
        deliveryTimeRepository.saveAll(deliveryTime);
        log.info("Working method DeliveryTime saveAll: {}", deliveryTime);
    }
}
