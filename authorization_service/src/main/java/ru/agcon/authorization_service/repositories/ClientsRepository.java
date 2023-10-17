package ru.agcon.authorization_service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Repository;
import ru.agcon.authorization_service.models.Clients;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class ClientsRepository {
    private final RedisTemplate<String, Clients> redisTemplate;

    @Autowired
    public ClientsRepository(RedisTemplate<String, Clients> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        this.redisTemplate.afterPropertiesSet();
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

    public Set<String> getAllKeys(){
        return redisTemplate.keys("*");
    }

    public List<Clients> findAll() {
        Set<String> keys = redisTemplate.keys("*");
        return new ArrayList<>(redisTemplate.opsForValue().multiGet(keys));
    }
}
