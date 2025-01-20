package com.musicreviewer.music_reviewer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.musicreviewer.music_reviewer.services.DeezerService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/api/charts")
public class DeezerController {

    DeezerService deezerService;

    @GetMapping("/albums")
    public Mono<String> getAlbums() {
         var x = deezerService.fetchTopAlbums();
         return x;
    }
    
}
