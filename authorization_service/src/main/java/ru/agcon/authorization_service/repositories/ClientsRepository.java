package ru.agcon.authorization_service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.agcon.authorization_service.models.Clients;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class ClientsRepository{
    private final RedisTemplate<String, Clients> redisTemplate;

    @Autowired
    public ClientsRepository(RedisTemplate<String, Clients> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String key, Clients value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Optional<Clients> findByLogin(String key) {
        Clients value = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(value);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public List<Clients> findAll() {
        Set<String> keys = redisTemplate.keys("*");
        List<Clients> values = redisTemplate.opsForValue().multiGet(keys);
        return values;
    }
}
