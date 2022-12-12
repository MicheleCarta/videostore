package org.videostore;

import org.videostore.model.Category;
import org.videostore.model.Directors;
import org.videostore.model.Movie;
import org.videostore.model.dto.MovieRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataTest {
    private static final String B_DAY = "2021-01-02T12:34:56Z";
    public static Movie buildMovie() {
        List<String> actors = new ArrayList<>() {{
            add("Actor x");
            add("Actor y");
            add("Actor z");
        }};
        return Movie.builder().title("some title").category(Category.Action.name()).description("long description").rating(3.5).yearProduction(2022).
                createdAt(Instant.now()).
                casting(actors.stream()
                        .map(n -> String.valueOf(n))
                        .collect(Collectors.joining("-", "{", "}"))).

                build();
    }

    public static Directors buildDirector(List<Movie> movies){
        List<Movie> moviesDirector = new ArrayList<>() {{
            add(buildMovie());
        }};
        return Directors.builder()
                .createdAt(Instant.now())
                .bDay(Instant.parse(B_DAY))
                .name("Steven")
                .surname("Spielberg")
                .movies(movies).build();
    }

    public static MovieRequest buildMoviePost(){
        List<String> actors = new ArrayList<>() {{
            add("Actor x");
            add("Actor y");
            add("Actor z");
        }};
        return MovieRequest.builder().title("some title").category(Category.Action.name()).description("long description").rating(3.5).yearProduction(2022).
                casting(actors.stream()
                        .map(n -> String.valueOf(n))
                        .collect(Collectors.joining("-", "{", "}"))).

                build();
    }

}
