package ru.egorbarinov.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egorbarinov.warehouse.domain.Delivery;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDto {

    private Long id;
//    @JsonFormat(
//            shape = JsonFormat.Shape.STRING,
//            pattern = "dd.MM.yyyy")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deliveryDate;

    private DeliveryTimeDto deliveryTime;

    private String carInfo;

    private String driverInfo;

    private BrandDto brand;

    private String orderNumber;

    private DeliveryTypeDto deliveryType;

    private String sender;

    private String comment;

    private ShopDto shop;

    private String numberOfPlaces;

    private String torgNumber;

    private String invoice;

    private UserDto user;

    private WarehouseDto warehouse;

    public DeliveryDto(Delivery delivery) {
        this.id = delivery.getId();
        this.deliveryDate = delivery.getDeliveryDate();
        this.deliveryTime = new DeliveryTimeDto(delivery.getDeliveryTime());
        this.carInfo = delivery.getCarInfo();
        this.driverInfo = delivery.getDriverInfo();
        this.brand = new BrandDto(delivery.getBrand());
        this.orderNumber = delivery.getOrderNumber();
        this.deliveryType = new DeliveryTypeDto(delivery.getDeliveryType());
        this.sender = delivery.getSender();
        this.comment = delivery.getComment();
        this.shop = new ShopDto(delivery.getShop());
        this.numberOfPlaces = delivery.getNumberOfPlaces();
        this.torgNumber = delivery.getTorgNumber();
        this.invoice = delivery.getInvoice();
        this.user = new UserDto(delivery.getUser());
        this.warehouse = new WarehouseDto(delivery.getWarehouse());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryDto)) return false;
        DeliveryDto that = (DeliveryDto) o;
        return deliveryDate.equals(that.deliveryDate)
                && deliveryTime.equals(that.deliveryTime)
                && carInfo.equals(that.carInfo)
                && driverInfo.equals(that.driverInfo)
                && brand.equals(that.brand)
                && deliveryType.equals(that.deliveryType)
                && warehouse.equals(that.warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryDate, deliveryTime, carInfo, driverInfo, brand, deliveryType, warehouse);
    }
}
