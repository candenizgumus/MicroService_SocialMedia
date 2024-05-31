package com.candenizgumus.config;



import com.candenizgumus.entities.UserProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfig
{
    @Value(value = "localhost")
    String redisHost;
    @Value(value = "6379")
    int redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory()
    {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost,redisPort));
    }

    @Bean
    public RedisTemplate<String, UserProfile> redisTemplate(){
        RedisTemplate<String,UserProfile> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());


        return template;
    }
}
