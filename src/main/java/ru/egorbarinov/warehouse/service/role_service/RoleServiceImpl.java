package ru.egorbarinov.warehouse.service.role_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egorbarinov.warehouse.dto.RoleDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.domain.Role;
import ru.egorbarinov.warehouse.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public List<RoleDto> findAll() {
        log.info("Working method RoleService findAll");
        return roleRepository.findAll().stream().map(RoleDto::new).collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Long id) throws ResourceNotFoundException {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Роль по указанному id не найдена:  id = " + id));;
        log.info("Working method RoleService findById {}", id);
        return new RoleDto(role);
    }

    @Override
    public RoleDto save(RoleDto roleDto) throws ResourceNotFoundException {
        if (roleDto.getId() == null) {
            log.info("Working method RoleService save: {} is null, create new", roleDto.getId());
            RoleDto roleDto1 = new RoleDto(roleRepository.save(new Role(roleDto)));
            return roleDto1;
        }
        Role role = roleRepository.findById(roleDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Роль с id = " + roleDto.getId() + " не найдена"));
        role.updateFields(roleDto);
        log.info("Working method RoleService save {}", role);
        return new RoleDto(roleRepository.save(role));
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
        log.info("Working method RoleService delete {}", id);
    }

    @Override
    public void saveAll(List<RoleDto> roleDto) {
        List<Role> roles = roleDto.stream().map(Role::new).collect(Collectors.toList());
        roleRepository.saveAll(roles);
        log.info("Working method RoleService saveAll {}", roles);
    }
}
