package ru.egorbarinov.warehouse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.warehouse.dto.RoleDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.service.role_service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<RoleDto> getAllRoleType() {
        return roleService.findAll();
    }

    @GetMapping(value = "/roles/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable(value = "id") Long roleId)
            throws ResourceNotFoundException {
        RoleDto roleDto = roleService.findById(roleId);
        return ResponseEntity.ok().body(roleDto);
    }
    @PostMapping("/roles")
    public ResponseEntity<RoleDto> addNewRole(@RequestBody RoleDto roleDto) throws ResourceNotFoundException, WarehouseException {
        roleDto.setId(null);
        return ResponseEntity.ok(roleService.save(roleDto));
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable(value = "id") Long id, @RequestBody RoleDto roleDto) throws ResourceNotFoundException, WarehouseException {
        roleDto.setId(id);
        roleService.save(roleDto);
        return ResponseEntity.ok(roleDto);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable(value = "id") Long id) {
        roleService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
    @PostMapping("/grouped-roles")
    public void saveAllRoleTypes(@RequestBody List<RoleDto> roleDto) {
        roleService.saveAll(roleDto);
    }
}
