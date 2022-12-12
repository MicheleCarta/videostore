package org.videostore.repository;

import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.videostore.model.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "select m.* from movies m, movies_directors md, directors d" +
            " where m.id = md.movie_id and d.id = md.director_id" +
            " and md.director_id = :directorId", nativeQuery = true)
    List<Movie> getMoviesByDirector(@Param(value = "directorId") Long directorId);

    @Query(value = "select m.* from movies as m" +
            " where m.rating >= :rating", nativeQuery = true)
    List<Movie> getMoviesByRating(@Param(value = "rating") Double rating);
}
