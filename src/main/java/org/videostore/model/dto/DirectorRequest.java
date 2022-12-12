package org.videostore.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
@Builder
@Getter
public class DirectorRequest {
    private final String name;
    private final String surname;
    private final Instant bDay;
}
