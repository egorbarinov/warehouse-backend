package ru.egorbarinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egorbarinov.warehouse.domain.DeliveryTime;

public interface DeliveryTimeRepository extends JpaRepository <DeliveryTime, Long> {
}
