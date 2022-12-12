package org.videostore.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(DirectorNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorMessageResponse handleResourceNotFound(final DirectorNotFoundException exception) {
        return ErrorMessageResponse.builder().code(HttpStatus.NOT_FOUND.value()).error(exception.getMessage()).build();
    }

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorMessageResponse handleBadFormatPreferences(final MovieNotFoundException exception) {
        return ErrorMessageResponse.builder().code(HttpStatus.NOT_FOUND.value()).error(exception.getMessage()).build();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public @ResponseBody
    ErrorMessageResponse handleUnsupportedContent(HttpMediaTypeNotSupportedException ex) {
        return ErrorMessageResponse.builder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).error("Unsupported content type: " + ex.getContentType()).build();
    }

    @ExceptionHandler(JsonMappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorMessageResponse handleBadFormat(JsonMappingException ex) {
        return ErrorMessageResponse.builder().code(HttpStatus.BAD_REQUEST.value()).error("Data are not well formatted").build();
    }

}
