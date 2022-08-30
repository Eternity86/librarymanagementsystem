package ru.arbiter2008.librarymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String registerUserAccount(final Model model,
                                      @ModelAttribute("user") final UserRegistrationDto registrationDto,
                                      final BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("registrationForm", registrationDto); // TODO
            return "registration";
        }
        try {
            userService.register(registrationDto);
        } catch (UserAlreadyExistsException e) {
            result.rejectValue("email", "user.email", "An account with this email already exists");
            return "registration";
        }

        return "redirect:/registration?success";
    }
}
