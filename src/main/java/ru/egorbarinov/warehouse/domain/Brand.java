package ru.egorbarinov.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egorbarinov.warehouse.dto.BrandDto;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "brands_tbl")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String abbr;

    @OneToMany(mappedBy = "brand")
    private List<Shop> shops;

    public Brand(BrandDto brandDto) {
        updateFields(brandDto);
    }

    public Brand(Long id, String name, String abbr) {
        this.id = id;
        this.name = name;
        this.abbr = abbr;
    }

    public void updateFields(BrandDto brandDto) {
        this.id = brandDto.getId();
        this.name = brandDto.getName();
        this.abbr = brandDto.getAbbr();
    }
}
