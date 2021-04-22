package ru.egorbarinov.warehouse.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WarehouseException extends Exception{

	public WarehouseException(String message){
    	super(message);
		log.warn("WarehouseException: {}",message );
    }
}
