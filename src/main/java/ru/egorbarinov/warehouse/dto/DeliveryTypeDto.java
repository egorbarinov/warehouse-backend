package ru.egorbarinov.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egorbarinov.warehouse.domain.DeliveryType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryTypeDto {
    private Long id;
    private String type;

    public DeliveryTypeDto(DeliveryType deliveryType) {
        this.id = deliveryType.getId();
        this.type = deliveryType.getType();
    }

}
