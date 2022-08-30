package ru.arbiter2008.librarymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.arbiter2008.librarymanagementsystem.dto.UserRegistrationDto;
import ru.arbiter2008.librarymanagementsystem.exception.UserAlreadyExistsException;
import ru.arbiter2008.librarymanagementsystem.model.Role;
import ru.arbiter2008.librarymanagementsystem.repository.UserRepository;
import ru.arbiter2008.librarymanagementsystem.service.UserService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(u -> new User(u.getEmail(), u.getPassword(), mapRolesToAuthorities(u.getRoles())))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
    }

    @Override
    public ru.arbiter2008.librarymanagementsystem.model.User register(UserRegistrationDto registrationDto) throws UserAlreadyExistsException {
        if (checkIfUserExist(registrationDto.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        ru.arbiter2008.librarymanagementsystem.model.User user = new ru.arbiter2008.librarymanagementsystem.model.User(
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()),
                List.of(new Role("ROLE_USER"))
        );

        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    private boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
