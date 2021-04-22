package ru.egorbarinov.warehouse.service.delivery_service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.egorbarinov.warehouse.domain.*;
import ru.egorbarinov.warehouse.dto.*;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.repository.DeliveryRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryServiceImplTest {

    Delivery delivery;
    Delivery delivery2;
    DeliveryDto deliveryDto;
    DeliveryDto deliveryDto2;

    @Autowired
    DeliveryServiceImpl deliveryService;
    @MockBean
    DeliveryRepository deliveryRepository;

    List<Delivery> deliveries;
    List<DeliveryDto> deliveriesDtos;

    @BeforeEach
    void setUp() {
        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, "ROLE_ADMIN"));

        Collection<RoleDto> rolesDto = new ArrayList<>();
        rolesDto.add(new RoleDto(1L, "ROLE_ADMIN"));




        DeliveryTime deliveryTime = DeliveryTime.builder()
                .id(1L)
                .deliveryTime("первая половина дня")
                .build();

        DeliveryTimeDto deliveryTimeDto = DeliveryTimeDto.builder()
                .id(1L)
                .deliveryTime("первая половина дня")
                .build();

        Brand adidas = Brand.builder()
                .id(1L)
                .name("Adidas")
                .abbr("adds")
                .build();

        Brand asics = Brand.builder()
                .id(1L)
                .name("Asics")
                .abbr("ascs")
                .build();

        List<Brand> brands = new ArrayList<>();
        brands.add(adidas);
        brands.add(asics);

        BrandDto adidasDto = BrandDto.builder()
                .id(1L)
                .name("Adidas")
                .abbr("adds")
                .build();

        BrandDto asicsDto = BrandDto.builder()
                .id(1L)
                .name("Asics")
                .abbr("ascs")
                .build();

        List<BrandDto> brandsDtos = new ArrayList<>();
        brandsDtos.add(adidasDto);
        brandsDtos.add(asicsDto);

        DeliveryType deliveryType = DeliveryType.builder()
                .id(1L)
                .type("to_warehouse")
                .build();

        DeliveryTypeDto deliveryTypeDto = DeliveryTypeDto.builder()
                .id(1L)
                .type("to_warehouse")
                .build();

        User user = User.builder()
                .id(1L)
                .username("Admin")
                .password("password")
                .fullName("Admin Adminovich Adminov")
                .email("admin@gaer.ru")
                .phone("88002223322")
                .roles(roles)
                .brands(brands)
                .build();

        UserDto userDto = UserDto.builder()
                .id(1L)
                .username("Admin")
                .password("password")
                .fullName("Admin Adminovich Adminov")
                .email("admin@gaer.ru")
                .phone("88002223322")
                .roles(rolesDto)
                .brands(brandsDtos)
                .build();

        Shop adidasShop = new Shop(1L, "Shop1", "sh1", adidas);
        Shop asicsShop = new Shop(2L, "Shop2", "sh2", asics);

        ShopDto adidasShopDto = new ShopDto(1L, "Shop1", "sh1", adidasDto);
        ShopDto asicsShopDto = new ShopDto(2L, "Shop2", "sh2", asicsDto);

        List<Shop> shopList =new ArrayList<>();
        shopList.add(new Shop(1L, "Shop1", "sh1", adidas));
        shopList.add(new Shop(2L, "Shop2", "sh2", asics));

        delivery = Delivery.builder()
                .id(1L)
                .deliveryDate(LocalDate.now())
                .deliveryTime(deliveryTime)
                .carInfo("н496хв197")
                .driverInfo("Vasya Lozhkin")
                .brand(new Brand(1L, "Adidas", "adds"))
                .orderNumber("123")
                .deliveryType(deliveryType)
                .sender("ООО Какая-то ккомпания")
                .comment("без опазданий")
                .shop(adidasShop)
                .numberOfPlaces("12")
                .torgNumber("12345")
                .invoice("1345")
                .user(user)
                .warehouse(new Warehouse(1L, "WH1", "wh1"))
                .build();

        delivery2 = Delivery.builder()
                .id(1L)
                .deliveryDate(LocalDate.now().plusDays(1))
                .deliveryTime(deliveryTime)
                .carInfo("x777хх177")
                .driverInfo("Gosha Kutcenko")
                .brand(new Brand(1L, "Asics", "ascs"))
                .orderNumber("333")
                .deliveryType(deliveryType)
                .sender("ООО главный поставщик Асиксов")
                .comment("без опазданий")
                .shop(asicsShop)
                .numberOfPlaces("20")
                .torgNumber("1243")
                .invoice("1243")
                .user(user)
                .warehouse(new Warehouse(1L, "WH1", "wh1"))
                .build();

        deliveryDto = DeliveryDto.builder()
                .id(1L)
                .deliveryDate(LocalDate.now())
                .deliveryTime(deliveryTimeDto)
                .carInfo("н496хв197")
                .driverInfo("Vasya Lozhkin")
                .brand(new BrandDto(1L, "Adidas", "adds"))
                .orderNumber("123")
                .deliveryType(deliveryTypeDto)
                .sender("ООО Какая-то ккомпания")
                .comment("без опазданий")
                .shop(adidasShopDto)
                .numberOfPlaces("12")
                .torgNumber("12345")
                .invoice("1345")
                .user(userDto)
                .warehouse(new WarehouseDto(1L, "WH1", "wh1"))
                .build();

        deliveryDto2 = DeliveryDto.builder()
                .id(1L)
                .deliveryDate(LocalDate.now().plusDays(1))
                .deliveryTime(deliveryTimeDto)
                .carInfo("x777хх177")
                .driverInfo("Gosha Kutcenko")
                .brand(new BrandDto(1L, "Asics", "ascs"))
                .orderNumber("333")
                .deliveryType(deliveryTypeDto)
                .sender("ООО главный поставщик Асиксов")
                .comment("без опазданий")
                .shop(asicsShopDto)
                .numberOfPlaces("20")
                .torgNumber("1243")
                .invoice("1243")
                .user(userDto)
                .warehouse(new WarehouseDto(1L, "WH1", "wh1"))
                .build();

        deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        deliveriesDtos = new ArrayList<>();
        deliveriesDtos.add(deliveryDto);
        deliveriesDtos.add(deliveryDto2);

    }

    @AfterEach
    void tearDown() {
        deliveries.clear();
        deliveriesDtos.clear();
    }

    @Test
    void givenAllDeliveryDto_whenWDeliveryServiceFindAll_thenOk() {
        Mockito.doReturn(deliveries)
                .when(deliveryRepository)
                .findAll();
        List<DeliveryDto> lists = deliveryService.findAll();
        assertEquals(deliveries.size(), lists.size());
        Mockito.verify(deliveryRepository, Mockito.times(1)).findAll();
    }

    @Test
    void givenDeliveryDto_whenDeliveryServiceFindById_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(delivery))
                .when(deliveryRepository)
                .findById(1L);
        DeliveryDto deliveryDto = deliveryService.findById(1L);
        assertNotNull(deliveryDto);
        Mockito.verify(deliveryRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    }

    @Test
    void givenResourceNotFoundException_whenWarehouseIdIsNotFound() {
        Mockito.doReturn(Optional.of(delivery))
                .when(deliveryRepository)
                .findById(1L);
        deliveryDto.setId(3L);
        assertThrows(ResourceNotFoundException.class, () -> {
            deliveryService.save(deliveryDto);
        });
    }

    @Test
    void givenDeliveryDto_whenDeliveryServiceSave_thenOk() throws ResourceNotFoundException {

        Mockito.doReturn(Optional.of(delivery))
                .when(deliveryRepository)
                .findById(1L);
        Mockito.doReturn(delivery)
                .when(deliveryRepository)
                .save(Mockito.any());
        DeliveryDto report = deliveryService.save(deliveryDto);
        assertNotNull(report);
        Mockito.verify(deliveryRepository, Mockito.times(1)).save(delivery);
    }

    @Test
    void deleteDeliveryById_whenDeliveryServiceDeleteById_thenOk() {
        Mockito.doNothing().when(deliveryRepository).deleteById(1L);
        deliveryService.delete(1L);
        Mockito.verify(deliveryRepository, Mockito.times(1)).deleteById(ArgumentMatchers.eq(1L));
    }

    @Test
    void saveAllDeliveryDto_whenDeliveryServiceSaveAll_thenOk() throws WarehouseException {
        Mockito.doReturn(deliveries)
                .when(deliveryRepository)
                .saveAll(Mockito.any());
        deliveryService.saveAll(deliveriesDtos);
        Mockito.verify(deliveryRepository, Mockito.times(1)).saveAll(deliveries);
    }


    @Test
    void checkFindByDeliveryDateIsBetween() {
        String first = LocalDate.now().plusMonths(2).toString();
        String last = LocalDate.now().plusMonths(3).toString();

        LocalDate firstDate = LocalDate.parse(first, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate lastDate = LocalDate.parse(last, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Mockito.doReturn(deliveries)
                .when(deliveryRepository)
                .findByDeliveryDateIsBetween(firstDate, lastDate);

        List<DeliveryDto> reportList = deliveryService.findByDeliveryDateIsBetween(first, last);
        assertEquals(deliveriesDtos, reportList);
        Mockito.verify(deliveryRepository, Mockito.times(1)).findByDeliveryDateIsBetween(firstDate, lastDate);


    }

    @Test
    void checkFindByDeliveryDateGreaterThanEqual() {
        String first = LocalDate.now().plusMonths(2).toString();
        LocalDate firstDate = LocalDate.parse(first, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Mockito.doReturn(deliveries)
                .when(deliveryRepository)
                .findByDeliveryDateGreaterThanEqual(firstDate);

        List<DeliveryDto> reportList = deliveryService.findByDeliveryDateGreaterThanEqual(first);
        assertEquals(deliveriesDtos, reportList);
        Mockito.verify(deliveryRepository, Mockito.times(1)).findByDeliveryDateGreaterThanEqual(firstDate);
    }

    @Test
    void checkFindByDeliveryDateLessThanEqual() {
    }

    @Test
    void getByDate() {
    }

    @Test
    void getUniqueDeliveriesByRange() {
    }
}