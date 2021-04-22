package ru.egorbarinov.warehouse.service.role_service;

import ru.egorbarinov.warehouse.dto.RoleDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();
    RoleDto findById(Long id) throws ResourceNotFoundException;
    RoleDto save(RoleDto roleDto) throws ResourceNotFoundException;
    void delete(Long id);
    void saveAll(List<RoleDto> roleDto);
}
