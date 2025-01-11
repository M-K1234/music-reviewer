package com.musicreviewer.music_reviewer.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    // hej
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper(); 
    }
}

