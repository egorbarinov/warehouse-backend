package ru.egorbarinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egorbarinov.warehouse.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findShopsByName(String name);
}
