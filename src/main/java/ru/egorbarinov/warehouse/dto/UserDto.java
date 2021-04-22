package ru.egorbarinov.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egorbarinov.warehouse.domain.User;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Collection<RoleDto> roles;
    private String fullName;
    private String email;
    private String phone;
    private List<BrandDto> brands;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.roles = user.getRoles().stream().map(RoleDto::new).collect(Collectors.toList()); // stream
        this.brands = user.getBrands().stream().map(BrandDto::new).collect(Collectors.toList());
    }

}


