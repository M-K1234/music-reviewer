package com.musicreviewer.music_reviewer.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.musicreviewer.music_reviewer.config.ModelMapperConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter
@Setter
@Service
public class DeezerService {

    private WebClient.Builder webClientBuilder;
    
    private final ModelMapperConfig modelMapperConfig;

    public Mono<String> fetchTopAlbums() {
        return webClientBuilder.baseUrl("https://api.deezer.com")
                .build()
                .get()
                .uri("chart/0/albums") 
                .retrieve()
                .bodyToMono(String.class); 
    }
    
}
