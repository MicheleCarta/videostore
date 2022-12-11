package org.videostore;

import org.videostore.model.Category;
import org.videostore.model.Directors;
import org.videostore.model.Movie;

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
        return Movie.builder().title("some title").category(Category.Action).description("long description").rating(3.5).yearProduction(2022).
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


    private static final String DEFAULT_LAST_SEEN = "2021-01-02T12:34:56Z";
    public static final String preferencesDefault = """
            {
               "preferences": {
               "lastSeenNotification": "%s",
               "theme": "default", 
                 "communications": {
                   "account": {
                     "sms": true,
                     "email": true
                   },
                   "trading": {
                     "sms": true,
                     "email": true
                   },
                   "weekAhead": {
                     "sms": true,
                     "email": true
                   },
                   "promotions": {
                     "sms": true,
                     "email": true
                   }
                 }
               }
             }""".formatted(DEFAULT_LAST_SEEN);

    public static final String preferencesValidate = """
            {
              "lastSeenNotification" : "%s",
              "theme" : "default",
              "communications" : {
                "account" : {
                  "sms" : true,
                  "email" : true
                },
                "trading" : {
                  "sms" : true,
                  "email" : true
                },
                "weekAhead" : {
                  "sms" : true,
                  "email" : true
                },
                "promotions" : {
                  "sms" : true,
                  "email" : true
                }
              }
            }""".formatted(DEFAULT_LAST_SEEN);
    ;
    public static final String preferencesUpdated = """
            {
              "theme": "light",
              "lastSeenNotification": "%s",
              "communications": {
                "promotions": {
                  "sms": false,
                  "email": false
                }
              }
            }""".formatted(DEFAULT_LAST_SEEN);
    ;
    public static final String expectedPreferencesMerged = """
            {
              "lastSeenNotification" : "%s",
              "theme" : "light",
              "communications" : {
                "account" : {
                  "sms" : true,
                  "email" : true
                },
                "trading" : {
                  "sms" : true,
                  "email" : true
                },
                "weekAhead" : {
                  "sms" : true,
                  "email" : true
                },
                "promotions" : {
                  "sms" : false,
                  "email" : false
                }
              }
            }""".formatted(DEFAULT_LAST_SEEN);
}
