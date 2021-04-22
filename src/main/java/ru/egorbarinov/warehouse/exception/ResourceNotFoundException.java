package ru.egorbarinov.warehouse.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

	public ResourceNotFoundException(String message){
    	super(message);
		log.warn("ResourceNotFoundException: {}",message );
    }
}
