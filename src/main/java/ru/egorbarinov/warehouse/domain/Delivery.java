package ru.egorbarinov.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egorbarinov.warehouse.dto.DeliveryDto;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "deliveries_tbl")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonFormat(
//            shape = JsonFormat.Shape.STRING,
////            pattern = "yyyy.MM.dd")
//            pattern = "dd.MM.yyyy")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    @Column
    private LocalDate deliveryDate;

    @ManyToOne
    private DeliveryTime deliveryTime;

    @Column
    private String carInfo;

    @Column
    private String driverInfo;

    @ManyToOne
    private Brand brand;

    @Column
    private String orderNumber;

    @ManyToOne
    private DeliveryType deliveryType;

    @Column
    private String sender;

    @Column
    private String comment;

    @ManyToOne
    private Shop shop;

    @Column
    private String numberOfPlaces;

    @Column
    private String torgNumber;

    @Column
    private String invoice;

    @ManyToOne
    private User user;

    @ManyToOne
    private Warehouse warehouse;

    public Delivery(DeliveryDto deliveryDto) {
        updateFields(deliveryDto);
    }

    public void updateFields(DeliveryDto deliveryDto) {
        this.id = deliveryDto.getId();
        this.deliveryDate = deliveryDto.getDeliveryDate();
        this.deliveryTime = new DeliveryTime(deliveryDto.getDeliveryTime());
        this.carInfo = deliveryDto.getCarInfo();
        this.driverInfo = deliveryDto.getDriverInfo();
        this.brand = new Brand(deliveryDto.getBrand());
        this.orderNumber = deliveryDto.getOrderNumber();
        this.deliveryType = new DeliveryType(deliveryDto.getDeliveryType());
        this.sender = deliveryDto.getSender();
        this.comment = deliveryDto.getComment();
        this.shop = new Shop(deliveryDto.getShop());
        this.numberOfPlaces = deliveryDto.getNumberOfPlaces();
        this.torgNumber = deliveryDto.getTorgNumber();
        this.invoice = deliveryDto.getInvoice();
        this.user = new User(deliveryDto.getUser());
        this.warehouse = new Warehouse(deliveryDto.getWarehouse());
    }
}
