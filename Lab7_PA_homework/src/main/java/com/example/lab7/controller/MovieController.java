package com.example.lab7.controller;

import com.example.lab7.exception.MovieNotFoundException;
import com.example.lab7.model.Movie;
import com.example.lab7.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie newMovieDetails) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(newMovieDetails.getTitle());
            movie.setScore(newMovieDetails.getScore());
            return movieRepository.save(movie);
        }).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @PatchMapping("/{id}")
    public Movie updateScore(@PathVariable Long id, @RequestParam Double score) {
        return movieRepository.findById(id).map(movie -> {
            movie.setScore(score);
            return movieRepository.save(movie);
        }).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        movieRepository.deleteById(id);
    }
}