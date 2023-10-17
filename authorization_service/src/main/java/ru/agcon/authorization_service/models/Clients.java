package ru.agcon.authorization_service.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@RedisHash("clients")
@Data
@NoArgsConstructor
public class Clients implements Serializable {
    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Email
    private String email;

    @NotEmpty
    @NotNull
    @Id
    private String login;

    @NotEmpty
    @NotNull
    private String password;

    @NotNull
    @NotEmpty
    private String role;

}
