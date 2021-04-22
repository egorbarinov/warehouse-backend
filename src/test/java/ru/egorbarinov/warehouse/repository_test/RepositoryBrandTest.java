package ru.egorbarinov.warehouse.repository_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.egorbarinov.warehouse.repository.BrandRepository;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoryBrandTest {

    @Autowired
    private BrandRepository brandRepository;
}
