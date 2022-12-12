package org.videostore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.videostore.model.Directors;
import org.videostore.model.Movie;
import org.videostore.repository.DirectorRepository;
import org.videostore.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DdlRepositoryTest extends DataTest {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    DirectorRepository directorRepository;

    @Test
    void addMovie() {
        Movie movie = movieRepository.saveAndFlush(buildMovie());
        Double ratingExpected = movie.getRating();
        assertEquals(3.5, ratingExpected);
    }

    @Test
    void addDirector() {
        Directors directors = directorRepository.saveAndFlush(buildDirector(null));
        assertEquals(directors.getMovies(), null);
    }

    @Test
    void addDirectorToMovie() {

        Movie movie = movieRepository.saveAndFlush(buildMovie());
        Directors directors = directorRepository.saveAndFlush(buildDirector(null));
        assertEquals(directors.getMovies(), null);
        movie.setDirectors(new ArrayList<>() {{
            add(directors);
        }});
        movieRepository.save(movie);
        Directors director = directorRepository.getReferenceById(directors.getId());
        assertEquals(director.getId(),
                movie.getDirectors().get(0).getId());
    }

    @Test
    void getMovieByDirector() {

        Movie movie = movieRepository.saveAndFlush(buildMovie());
        Directors directors = directorRepository.saveAndFlush(buildDirector(null));
        assertEquals(directors.getMovies(), null);
        movie.setDirectors(new ArrayList<>() {{
            add(directors);
        }});
        movieRepository.save(movie);
        Directors director = directorRepository.getReferenceById(directors.getId());
        assertEquals(director.getId(),
                movie.getDirectors().get(0).getId());
        List<Movie> movies = movieRepository.getMoviesByDirector(directors.getId());
        assertEquals(movies.size(), 1);
    }

    @Test
    void updateRating() {

        Movie movie = movieRepository.saveAndFlush(buildMovie());
        Directors directors = directorRepository.saveAndFlush(buildDirector(null));
        assertEquals(directors.getMovies(), null);
        movie.setDirectors(new ArrayList<>() {{
            add(directors);
        }});
        movieRepository.save(movie);
        Directors director = directorRepository.getReferenceById(directors.getId());
        assertEquals(director.getId(),
                movie.getDirectors().get(0).getId());
        movie.setRating(5.5);
        Movie movieUpdated = movieRepository.saveAndFlush(movie);
        assertEquals(movieUpdated.getRating(), 5.5);
    }


}
