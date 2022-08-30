package ru.arbiter2008.librarymanagementsystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.arbiter2008.librarymanagementsystem.dto.UserRegistrationDto;
import ru.arbiter2008.librarymanagementsystem.exception.UserAlreadyExistsException;
import ru.arbiter2008.librarymanagementsystem.model.User;

public interface UserService extends UserDetailsService {

    User register(UserRegistrationDto registrationDto) throws UserAlreadyExistsException;

}
