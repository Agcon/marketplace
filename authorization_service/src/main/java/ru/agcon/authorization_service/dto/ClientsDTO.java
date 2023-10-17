package ru.agcon.authorization_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ClientsDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Email
    private String email;

    @NotEmpty
    @NotNull
    private String login;

    @NotEmpty
    @NotNull
    private String password;

}
