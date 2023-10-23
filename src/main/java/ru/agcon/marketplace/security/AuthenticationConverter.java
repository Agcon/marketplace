package ru.agcon.marketplace.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthenticationConverter {
    public static Authentication convert(String authenticationJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> authMap = objectMapper.readValue(authenticationJson, Map.class);

        String login = (String) authMap.get("login");
        List<Map<String, String>> authoritiesMap = (List<Map<String, String>>) authMap.get("authorities");

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Map<String, String> authorityMap : authoritiesMap) {
            authorities.add(new SimpleGrantedAuthority(authorityMap.get("authority")));
        }
        return new UsernamePasswordAuthenticationToken(login, null, authorities);
    }
}
