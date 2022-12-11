package org.videostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.videostore.model.Directors;

public interface DirectorRepository extends JpaRepository<Directors, Long> {
}
