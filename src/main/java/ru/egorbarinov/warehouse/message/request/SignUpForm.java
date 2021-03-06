package ru.egorbarinov.warehouse.message.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    
    private List<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
