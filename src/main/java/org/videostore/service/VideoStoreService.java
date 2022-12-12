package org.videostore.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.videostore.exception.DirectorNotFoundException;
import org.videostore.exception.MovieNotFoundException;
import org.videostore.model.Category;
import org.videostore.model.Directors;
import org.videostore.model.Movie;
import org.videostore.model.dto.DirectorRequest;
import org.videostore.model.dto.MovieRequest;
import org.videostore.repository.DirectorRepository;
import org.videostore.repository.MovieRepository;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VideoStoreService {
    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public Movie addMovie(MovieRequest movieRequest) {
        Movie entity = modelMapper.map(movieRequest, Movie.class);
        entity.setCreatedAt(Instant.now());
        entity.setCategory(Category.valueOf(movieRequest.getCategory()).name());
        movieRequest.getDirectors().stream().forEach(e -> {
                    entity.getDirectors().
                            add(getDirector(e));
                }
        );
        movieRepository.saveAndFlush(entity);
        return getMovie(entity.getId());
    }

    @Transactional
    public Movie updateMovie(MovieRequest movieRequest, Long id) {
        Movie entity = modelMapper.map(movieRequest, Movie.class);
        Movie movie = getMovie(id);
        movie.setUpdatedAt(Instant.now());
        movie.setRating(entity.getRating());
        movie.setYearReleased(entity.getYearReleased() != null ? entity.getYearReleased() : movie.getYearReleased());
        movie.setMonthReleased(entity.getMonthReleased() != null ? entity.getMonthReleased() : movie.getMonthReleased());
        movieRepository.saveAndFlush(movie);
        return getMovie(movie.getId());
    }

    public Movie getMovie(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> MovieNotFoundException.withId(id));
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    public Directors addDirector(DirectorRequest directorsRequest) {
        Directors entity = modelMapper.map(directorsRequest, Directors.class);
        entity.setCreatedAt(Instant.now());
        return directorRepository.saveAndFlush(entity);
    }

    @Transactional
    public Directors updateDirector(DirectorRequest directorsRequest, Long id) {
        Directors entity = modelMapper.map(directorsRequest, Directors.class);
        Directors director = getDirector(id);
        director.setUpdatedAt(Instant.now());
        director.setName(entity.getName());
        director.setSurname(entity.getSurname());
        director.setBDay(entity.getBDay());
        return directorRepository.save(director);
    }

    public Directors getDirector(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> DirectorNotFoundException.withId(id));
    }

    public List<Directors> getAllDirectors() {
        return directorRepository.findAll();
    }

    public List<Movie> getMovieByDirector(Long directorId) {
        return movieRepository.getMoviesByDirector(directorId);
    }

    public List<Movie> getMovieByRating(Double rating) {
        return movieRepository.getMoviesByRating(rating);
    }

    public void deleteMovie(Long movieId){
        movieRepository.deleteById(movieId);
    }

    public void deleteDirector(Long directorId){
        directorRepository.deleteById(directorId);
    }
}
