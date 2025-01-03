package com.musicreviewer.music_reviewer.config;


import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class FakerTestConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
