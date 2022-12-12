package org.videostore.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
@Builder
@Getter
public class MovieRequest {
    private final String title;
    private final String description;
    private final String category;
    private final Integer yearProduction;
    private final Integer yearReleased;
    private final Integer monthReleased;
    private final String casting;
    private final Double rating;
    private final List<Long> directors;
}
