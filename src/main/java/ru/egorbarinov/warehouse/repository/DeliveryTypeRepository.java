package ru.egorbarinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egorbarinov.warehouse.domain.DeliveryType;

public interface DeliveryTypeRepository extends JpaRepository <DeliveryType, Long> {
}
