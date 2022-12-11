package org.videostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.videostore.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
