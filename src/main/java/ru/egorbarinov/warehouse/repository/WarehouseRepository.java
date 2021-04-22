package ru.egorbarinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egorbarinov.warehouse.domain.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
