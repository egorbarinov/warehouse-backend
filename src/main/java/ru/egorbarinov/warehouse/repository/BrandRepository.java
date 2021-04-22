package ru.egorbarinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egorbarinov.warehouse.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
