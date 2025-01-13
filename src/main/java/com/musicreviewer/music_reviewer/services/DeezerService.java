package com.musicreviewer.music_reviewer.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.musicreviewer.music_reviewer.config.ModelMapperConfig;
import com.musicreviewer.music_reviewer.dtos.DeezerAlbumDTO;
import com.musicreviewer.music_reviewer.dtos.ReviewDTO;
import com.musicreviewer.music_reviewer.entities.Review;
import com.musicreviewer.music_reviewer.repositories.ReviewRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter
@Setter
@Service
public class DeezerService {

    private WebClient.Builder webClientBuilder;
    
    private final ModelMapperConfig modelMapperConfig;

    

    public DeezerAlbumDTO mapToDTO(DeezerAlbumDTO deezerAlbumDTO) {
        return modelMapperConfig.modelMapper().map(deezerAlbumDTO, DeezerAlbumDTO.class);
    }

    public List<DeezerAlbumDTO> mapToDTOList(List<DeezerAlbumDTO> deezerAlbumDTOs) {
        return deezerAlbumDTOs.stream()
                      .map(this::mapToDTO)
                      .collect(Collectors.toList());
    }

    public Mono<String> fetchTopAlbums() {
        return webClientBuilder.baseUrl("https://api.deezer.com")
                .build()
                .get()
                .uri("chart/0/albums") 
                .retrieve()
                .bodyToMono(String.class); 
    }
    
}
