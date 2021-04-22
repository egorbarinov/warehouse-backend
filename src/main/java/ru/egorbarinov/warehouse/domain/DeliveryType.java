package ru.egorbarinov.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egorbarinov.warehouse.dto.DeliveryTypeDto;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "delivery_type_tbl")
public class DeliveryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;

    public DeliveryType(DeliveryTypeDto deliveryTypeDto) {
        updateFields(deliveryTypeDto);
    }

    public void updateFields(DeliveryTypeDto deliveryTypeDto){
        this.id = deliveryTypeDto.getId();
        this.type = deliveryTypeDto.getType();
    }


}
