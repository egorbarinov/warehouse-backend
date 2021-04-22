package ru.egorbarinov.warehouse.service.user_service;

import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getById(Long id) throws ResourceNotFoundException;
    List<UserDto> findAll();
    UserDto save(UserDto userDto) throws ResourceNotFoundException, WarehouseException;
    void delete(Long id) throws ResourceNotFoundException;

}
