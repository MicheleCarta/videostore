package org.videostore.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorMessageResponse {
    private String error;
    private int code;

}
