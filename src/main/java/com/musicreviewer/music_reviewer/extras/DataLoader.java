package com.musicreviewer.music_reviewer.extras;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.musicreviewer.music_reviewer.repositories.ReviewRepository;
import com.musicreviewer.music_reviewer.entities.News;
import com.musicreviewer.music_reviewer.entities.Review;
import com.musicreviewer.music_reviewer.repositories.NewsRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final NewsRepository newsRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public void run(String... args) {
        List<News> news = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();

        // tilføj news
        news.add(new News(0, "Entity1", "Description1", null, null, null));
        news.add(new News(0, "Entity2", "Description2", null, null, null));
        news.add(new News(0, "Entity3", "Description3", null, null, null));
        // tilføj reviews
        reviews.add(new Review( "Review1", "Description1"));
        reviews.add(new Review( "Review2", "Description2"));

        // Save all entities in batch
        newsRepository.saveAll(news);
        reviewRepository.saveAll(reviews);

        System.out.println("Inserted " + news.size() + " news into the database.");
        System.out.println("Inserted " + reviews.size() + " reviews into the database.");
    }
}
