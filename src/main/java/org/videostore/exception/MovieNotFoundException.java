package org.videostore.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor(staticName = "withId")
@Getter
public class MovieNotFoundException extends EntityNotFoundException {

    private final Long id;

    @Override
    public String getMessage() {
        return format("Movie [%d] not found", id);
    }



}
