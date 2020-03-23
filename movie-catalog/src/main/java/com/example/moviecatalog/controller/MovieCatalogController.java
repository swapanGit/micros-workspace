package com.example.moviecatalog.controller;

import com.example.moviecatalog.model.CatalogItem;
import com.example.moviecatalog.model.Movie;
import com.example.moviecatalog.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

   /* @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogInfo(@PathVariable("userId") String userId) {
        return Collections.singletonList(
                new CatalogItem("Transformer", "Test Catalog", 1));
    }*/

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://rating-data/ratingsdata/user/" + userId, UserRating.class);

        return userRating.getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());

        /*Alternative WebClient way
        Movie movie = webClientBuilder.build()
        .get()
        .uri("http://localhost:8802/movies/"+ rating.getMovieId())
        .retrieve()
        .bodyToMono(Movie.class)
        .block();
            */
                })
                .collect(Collectors.toList());

    }
}



