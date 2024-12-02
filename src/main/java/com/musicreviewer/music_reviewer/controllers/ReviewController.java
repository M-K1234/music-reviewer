package com.musicreviewer.music_reviewer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @GetMapping("/{id}")
    public String getHome(@PathVariable int id) {
        return "It sent " + id;
    }
    

}