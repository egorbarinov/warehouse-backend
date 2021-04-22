package ru.egorbarinov.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egorbarinov.warehouse.dto.UserDto;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "users_tbl")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String fullName;
    @Column
    private String email;
    @Column
    private String phone;

    @ManyToMany
    @JoinTable(schema = "command_project", name = "users_roles_tbl",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @ManyToMany
    @JoinTable(
            schema = "command_project",
            name = "users_brands_tbl",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "brand_id")})
    private List<Brand> brands;

    public User(UserDto userDto) {
        updateAllFieldsWithoutId(userDto);
    }

    public void updateAllFieldsWithoutId(UserDto userDto) {
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        if (userDto.getPassword() != null && (!userDto.getPassword().trim().equals(""))) {
            this.password = userDto.getPassword();
        }
        this.phone = userDto.getPhone();
        this.email = userDto.getEmail();
        this.fullName = userDto.getFullName();
        this.roles = userDto.getRoles().stream().map(Role::new).collect(Collectors.toList()); // stream
        this.brands = userDto.getBrands().stream().map(Brand::new).collect(Collectors.toList());
    }

    public void setPassword(UserDto userDto){
        this.id = userDto.getId();
        this.password = userDto.getPassword();
    }
}
