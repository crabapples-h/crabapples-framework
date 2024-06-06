package cn.crabapples.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
@Slf4j
public class RedisServerConfigure {
    @Bean
    public RedisServer redisServer() {
        log.info("start init inner redis server");
        RedisServer redisServer = RedisServer.builder()
                .port(6379)
                .setting("maxmemory 128M") //maxheap 128M
                .build();
        redisServer.start();
        redisServer.stop();
        log.info("init inner redis server finish");
        return redisServer;
    }
}
