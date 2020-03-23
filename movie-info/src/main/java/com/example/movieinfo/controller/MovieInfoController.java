package com.example.movieinfo.controller;

import com.example.movieinfo.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
    @RequestMapping("/{movieId}")
    public Movie getMovies(@PathVariable("movieId") String movieId){
        return new Movie("movie name","test","4");
    }
}
