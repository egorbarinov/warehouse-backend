package ru.egorbarinov.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egorbarinov.warehouse.domain.DeliveryTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryTimeDto {
    private Long id;
    private String deliveryTime;

    public DeliveryTimeDto(DeliveryTime deliveryTime) {
        this.id = deliveryTime.getId();
        this.deliveryTime = deliveryTime.getDeliveryTime();
    }

}
