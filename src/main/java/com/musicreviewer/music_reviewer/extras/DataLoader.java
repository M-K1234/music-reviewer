package com.musicreviewer.music_reviewer.extras;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.musicreviewer.music_reviewer.repositories.ReviewRepository;
import com.musicreviewer.music_reviewer.repositories.UserRepository;
import com.musicreviewer.music_reviewer.entities.Account;
import com.musicreviewer.music_reviewer.entities.Login;
import com.musicreviewer.music_reviewer.entities.News;
import com.musicreviewer.music_reviewer.entities.Review;
import com.musicreviewer.music_reviewer.entities.User;
import com.musicreviewer.music_reviewer.repositories.AccountRepository;
import com.musicreviewer.music_reviewer.repositories.LoginRepository;
import com.musicreviewer.music_reviewer.repositories.NewsRepository;

import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final NewsRepository newsRepository;
    private final ReviewRepository reviewRepository;
    // private final UserRepository userRepository;
    // private final LoginRepository loginRepository;
    // private final AccountRepository accountRepository;


    @Override
    public void run(String... args) {
        List<News> news = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        // List<User> users = new ArrayList<>();
        // List<Account> accounts = new ArrayList<>();
        // List<Login> logins = new ArrayList<>();
      
        // users.add(new User(1,"John Doe","jDoe"));
        // users.add(new User(1,"Jane Smith","jSmth"));
        // users.add(new User(1,"Michael Clark","MC"));

        // logins.add(new Login(1,"jd@mail.com", "123"));
        // logins.add(new Login(1,"js@mail.com", "123"));
        // logins.add(new Login(1, "mc@mail.com", "123"));

        // accounts.add(new Account(1,LocalDateTime.now()));
        // accounts.add(new Account(2,LocalDateTime.now()));
        // accounts.add(new Account(2,LocalDateTime.now()));

                // tilføj news
        news.add(new News(0, "Entity1", "Description1", null, null, null ));
        news.add(new News(0, "Entity2", "Description2", null, null, null ));
        news.add(new News(0,"Entity3", "Description3", null, null, null ));

        // tilføj reviews
        reviews.add(new Review(0, "Indie Rock's Evolution", "John Doe",new Date(20241212),"/assets/images/0x1900-000000-80-0-0.jpg", MockData.review1[0], 8 ));
        reviews.add(new Review(0,"Pop Music in 2024: What to Expect", "Jane Smith",new Date(20241212),"/assets/images/51hyx6kkQ8L._AC_UF894,1000_QL80_.jpg", MockData.review1[1], 3 ));
        reviews.add(new Review(0,"The Revival of Vinyl Records: A Retro Trend", "Michael Clark",new Date(20241212),"/assets/images/71bBg5xM5YL._UF1000,1000_QL80_.jpg", MockData.review1[2], 4));
  
        // Save all entities in batch
        // userRepository.saveAll(users);
        // loginRepository.saveAll(logins);
        // accountRepository.saveAll(accounts);
        newsRepository.saveAll(news);
        reviewRepository.saveAll(reviews);

        System.out.println("Inserted " + news.size() + " news into the database.");
        System.out.println("Inserted " + reviews.size() + " reviews into the database.");
    }
}
