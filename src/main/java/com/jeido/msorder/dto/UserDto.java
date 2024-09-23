package com.jeido.msorder.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotNull
    private long id;
    @Size(min = 2, max = 20)
    private String username;
    @Email
    private String email;
}
