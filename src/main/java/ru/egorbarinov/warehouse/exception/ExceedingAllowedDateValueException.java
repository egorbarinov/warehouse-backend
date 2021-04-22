package ru.egorbarinov.warehouse.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class ExceedingAllowedDateValueException extends Exception {
    public ExceedingAllowedDateValueException(String message) {
        super(message);
        log.warn("{}", message);
    }
}
