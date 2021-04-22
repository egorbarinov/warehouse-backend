package ru.egorbarinov.warehouse.service.warehouse_service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.egorbarinov.warehouse.domain.Warehouse;
import ru.egorbarinov.warehouse.dto.WarehouseDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.repository.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WarehouseServiceImplTest {

    Warehouse warehouse;
    WarehouseDto warehouseDto;

    List<Warehouse> warehousesList;
    List<WarehouseDto> warehouseDtosList;

    @Autowired
    WarehouseServiceImpl warehouseService;

    @MockBean
    WarehouseRepository warehouseRepository;

    @BeforeEach
    void setUp() {
        warehouse = Warehouse.builder()
                .id(1L)
                .name("WH1")
                .abbr("ABBR1")
                .build();

        warehouseDto = WarehouseDto.builder()
                .name("WH2")
                .abbr("ABBR2")
                .build();

        warehousesList = new ArrayList<>();
        warehousesList.add(new Warehouse(1L, "WH1", "ABBR1"));
        warehousesList.add(new Warehouse(2L, "WH2", "ABBR2"));

        warehouseDtosList = new ArrayList<>();
        warehouseDtosList.add(new WarehouseDto(1L, "WH1", "ABBR1"));
        warehouseDtosList.add(new WarehouseDto(2L, "WH2", "ABBR2"));
    }

    @AfterEach
    void tearDown() {
        warehousesList.clear();
        warehouseDtosList.clear();
    }

    //TestMethod_Condition_ExpectedResult
    @Test
    void givenWarehouseDto_whenWarehouseServiceFindById_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(warehouse))
                .when(warehouseRepository)
                .findById(1L);

        WarehouseDto warehouseDto = warehouseService.findById(1L);
        assertNotNull(warehouseDto);
        Mockito.verify(warehouseRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    }

    @Test
    void givenResourceNotFoundException_whenWarehouseIdIsNotFound() {
        Mockito.doReturn(Optional.of(warehouse))
                .when(warehouseRepository)
                .findById(1L);
        warehouseDto.setId(3L);
        assertThrows(ResourceNotFoundException.class, () -> {
            warehouseService.save(warehouseDto);
        });
    }

    @Test
    void givenAllWarehouseDto_whenWarehouseServiceFindAll_thenOk() {
        Mockito.doReturn(warehousesList)
                .when(warehouseRepository)
                .findAll();
        List<WarehouseDto> lists = warehouseService.findAll();
        assertEquals(warehousesList.size(), lists.size());
        Mockito.verify(warehouseRepository, Mockito.times(1)).findAll();
    }

    @Test
    void givenWarehouseDto_whenWarehouseServiceSave_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(warehouse)
                .when(warehouseRepository)
                .save(Mockito.any());
        WarehouseDto report = warehouseService.save(warehouseDto);
        assertNotNull(report);
    }

    @Test
    void deleteWarehouseById_whenWarehouseServiceDeleteById_thenOk() {
        Mockito.doNothing().when(warehouseRepository).deleteById(1L);
        warehouseService.delete(1L);
        Mockito.verify(warehouseRepository, Mockito.times(1)).deleteById(ArgumentMatchers.eq(1L));

    }

    @Test
    void saveAllWarehouseDto_whenWarehouseServiceSaveAll_thenOk() {
        Mockito.doReturn(warehousesList)
                .when(warehouseRepository)
                .saveAll(Mockito.any());
        warehouseService.saveAll(warehouseDtosList);
        Mockito.verify(warehouseRepository, Mockito.times(1)).saveAll(warehousesList);

    }
}