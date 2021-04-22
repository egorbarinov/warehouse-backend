package ru.egorbarinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egorbarinov.warehouse.domain.Delivery;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findDeliveryById(Long id);
    List<Delivery> findByDeliveryDate(LocalDate localDate);
    List<Delivery> findByDeliveryDateIsBetween(LocalDate first, LocalDate second);
    List<Delivery> findByDeliveryDateGreaterThanEqual (LocalDate first);
    List<Delivery> findByDeliveryDateLessThanEqual (LocalDate last);
}
