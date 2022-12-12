package org.videostore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.videostore.model.Category;
import org.videostore.model.Directors;
import org.videostore.model.Movie;
import org.videostore.model.dto.DirectorRequest;
import org.videostore.model.dto.MovieRequest;
import org.videostore.service.VideoStoreService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class VideoStoreController {
    private final VideoStoreService videoStoreService;

    @RequestMapping(value = "/movie", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieRequest request) {
        Movie movie = videoStoreService.addMovie(request);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/movie/{movie_id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieRequest request,@PathVariable("movie_id") long movieId) {
        Movie movie = videoStoreService.updateMovie(request,movieId);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @RequestMapping(value = "/director", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Directors> addDirector(@RequestBody DirectorRequest request) {
        Directors director = videoStoreService.addDirector(request);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/director/{director_id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Directors> updateDirector(@RequestBody DirectorRequest request,@PathVariable("director_id") long directorId) {
        Directors director = videoStoreService.updateDirector(request,directorId);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    @RequestMapping(value = "/director-movie/{director_id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Movie>> getMovieByDirector(@PathVariable("director_id") long directorId) {
        return new ResponseEntity<>(videoStoreService.getMovieByDirector(directorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/rating/{rating}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Movie>> getMovieByRating(@PathVariable("rating") double rating) {
        return new ResponseEntity<>(videoStoreService.getMovieByRating(rating), HttpStatus.OK);
    }

    @RequestMapping(value = "/directors", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Directors>> getDirectors() {
        return new ResponseEntity<>(videoStoreService.getAllDirectors(), HttpStatus.OK);
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(videoStoreService.getAllMovies(), HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteMovie(@PathVariable("id") long id) {
         videoStoreService.deleteMovie(id);
    }

    @RequestMapping(value = "/director/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteDirector(@PathVariable("id") long id) {
        videoStoreService.deleteDirector(id);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
    public List<String> getCategories() {
        List<String> enumNames = Stream.of(Category.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return enumNames;

    }
}
