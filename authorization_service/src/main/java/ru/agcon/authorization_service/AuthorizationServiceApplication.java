package ru.agcon.authorization_service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import ru.agcon.authorization_service.models.Clients;

@SpringBootApplication
public class AuthorizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
