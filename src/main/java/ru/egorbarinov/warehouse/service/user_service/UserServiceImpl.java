package ru.egorbarinov.warehouse.service.user_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.repository.UserRepository;
import ru.egorbarinov.warehouse.security.principal.UserPrincipal;
import ru.egorbarinov.warehouse.domain.User;
import ru.egorbarinov.warehouse.dto.UserDto;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto getById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь по указанному id не найден: id = " + id));
        log.info("Working method UserService getById {}", id);
        return new UserDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        log.info("Working method UserService findAll");
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) throws ResourceNotFoundException, WarehouseException {
        String password = userDto.getPassword();
        if (password != null && (!password.trim().equals(""))) {
            userDto.setPassword(bCryptPasswordEncoder.encode(password));
        }

        String username = userDto.getUsername();
        // Вот тут я не знаю, есть ли смысл проверять username на null
        // или он по умолчанию инициализируется пустой строкой?
        if(username != null) {
            username = username.trim();
        }
        if (userDto.getId() == null) //создание нового пользователя
        {
            if(username == null || username.equals("")) {
                throw new WarehouseException("Ошибка при создании пользователя: логин не указан или он пустой");
            }

            if (password == null || password.trim().equals("")) {
                throw new WarehouseException("Ошибка при создании пользователя: Пароль не указан или он пустой");
            }

            // пользователя не должно быть в БД
            if (userRepository.findByUsername(username).isPresent()) {
                throw new WarehouseException("Пользователь с именем " + username + " уже существует");
            }
            return new UserDto(userRepository.save(new User(userDto)));
        }
        // обновление уже существующего пользователя
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Пользователь с id = " + userDto.getId() + " не найден"));

        userDto.setUsername(user.getUsername()); // игнорируем изменения в логине, полученном из Dto
        //обновляем поля в user, не затрагивая пароля и Id
        user.updateAllFieldsWithoutId(userDto);
        return new UserDto(userRepository.save(user));
    }

    public void delete(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь по указанному id не найден: id = " + id));
        userRepository.delete(user);
        log.info("Working method UserService delete id = {}", id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));
        log.info("Working method UserService loadUserByUsername");
        return UserPrincipal.build(user);
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//    }
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
//    }


}



