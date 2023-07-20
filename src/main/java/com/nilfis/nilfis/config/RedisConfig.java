package com.nilfis.nilfis.config;

import com.nilfis.nilfis.util.enums.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;

@Configuration
@EnableCaching
@EnableScheduling
@Slf4j

public class RedisConfig {

    @Value(value = "${cache.redis.address}")
    private String serverAddress;

    @Value(value = "${cache.redis.password}")
    private String serverPassword;

    @Bean
    public RedissonClient redissonClient() {
        var config = new Config();
        config.useSingleServer()
                .setAddress(serverAddress)
                .setPassword(serverPassword);

        return Redisson.create(config);
    }

    @Bean
    @Autowired
    public CacheManager cacheManager(RedissonClient redissonClient) {
        var config = new HashMap<String, String>();
        return new RedissonSpringCacheManager(redissonClient);
    }

    @CacheEvict(cacheNames = {
            CacheConstants.FILMS_CACHE_NAME,
            CacheConstants.SERIES_CACHE_NAME,
            CacheConstants.SUBSCRIPTIONS_CACHE_NAME,
            CacheConstants.SUBSCRIPTIONS_TYPES_CACHE_NAME,
    }, allEntries = true)
    @Scheduled(cron = CacheConstants.SCHEDULED_RESET_CACHE)
    public void deleteCache() {
        log.info("Clean cache");
    }
}