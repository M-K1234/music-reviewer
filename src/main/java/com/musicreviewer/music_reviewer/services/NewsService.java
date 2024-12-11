package com.musicreviewer.music_reviewer.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.musicreviewer.music_reviewer.config.ModelMapperConfig;
import com.musicreviewer.music_reviewer.dtos.NewsDTO;
import com.musicreviewer.music_reviewer.entities.News;
import com.musicreviewer.music_reviewer.repositories.NewsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final ModelMapperConfig modelMapperConfig;

    public NewsDTO mapToDTO(News news) {
        return modelMapperConfig.modelMapper().map(news, NewsDTO.class);
    }

    public List<NewsDTO> mapToDTOList(List<News> news) {
        return news.stream()
                      .map(this::mapToDTO)
                      .collect(Collectors.toList());
    }

    // Create a new review
    public News createNews(News news) {
        return newsRepository.save(news);
    }

    public List<NewsDTO> getAllNews() {
        List<News> news = newsRepository.findAll();
        return mapToDTOList(news);
    }

    public NewsDTO getNewsById(int newsID) {
        News news = newsRepository.findById(newsID).get();
        return mapToDTO(news);
    }

    public News updateReview(int newsID, News updatedNews) {
        

        if (newsRepository.existsById(newsID)) {
           News news = newsRepository.findById(newsID).get();
            news.setAuthor(updatedNews.getAuthor());  
        news.setCreation_date(updatedNews.getCreation_date());
        news.setImgurl(updatedNews.getImgurl());
        news.setText(updatedNews.getText());
        news.setTitle(updatedNews.getTitle());   
        return newsRepository.save(news);
        } else
        {
            throw new RuntimeException("News not found with id: " + newsID);
        }
        
                
    }

    public void deleteReview(int newsID) {
        if (newsRepository.existsById(newsID)) 
        {
            newsRepository.deleteById(newsID);
        } else 
        {
            throw new RuntimeException("News not found with id: " + newsID);
        }
    }
    
}
