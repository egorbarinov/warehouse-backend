package ru.egorbarinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egorbarinov.warehouse.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
