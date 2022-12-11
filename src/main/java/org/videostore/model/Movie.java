package org.videostore.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "movies")
@ToString
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @SequenceGenerator(name = "movie_seq", sequenceName = "movie_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "category", nullable = false)
    private Category category;
    @Column(name = "year_production", nullable = false)
    private Integer yearProduction;
    @Column(name = "year_released")
    private Integer yearReleased;
    @Column(name = "month_released")
    private Integer monthReleased;
    @Column(name = "casting", nullable = false)
    private String casting;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "movies_directors",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "director_id") })
    private List<Directors> directors = new ArrayList<>();
}
