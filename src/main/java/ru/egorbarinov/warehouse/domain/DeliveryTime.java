package ru.egorbarinov.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egorbarinov.warehouse.dto.DeliveryTimeDto;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "delivery_time_tbl")
public class DeliveryTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String deliveryTime;

    public DeliveryTime(DeliveryTimeDto deliveryTimeDto) {
        updateFields(deliveryTimeDto);
    }

    public void updateFields(DeliveryTimeDto deliveryTimeDto) {
        this.id = deliveryTimeDto.getId();
        this.deliveryTime = deliveryTimeDto.getDeliveryTime();
    }
}
