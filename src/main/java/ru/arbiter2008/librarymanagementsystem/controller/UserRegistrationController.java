package ru.arbiter2008.librarymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.arbiter2008.librarymanagementsystem.dto.UserRegistrationDto;
import ru.arbiter2008.librarymanagementsystem.exception.UserAlreadyExistsException;
import ru.arbiter2008.librarymanagementsystem.service.UserService;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        try {
            userService.register(registrationDto);
        } catch (UserAlreadyExistsException e) {
            // TODO add error info to UI
            return "registration";
        }


        return "redirect:/registration?success";
    }
}
