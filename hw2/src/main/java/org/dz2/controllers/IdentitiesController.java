package org.dz2.controllers;

import org.dz2.entities.User;
import org.dz2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IdentitiesController {
    private final UserService userService;

    public IdentitiesController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(User user, Model model) {
        if (userService.saveUser(user)) {
            return "redirect:/login";
        } else {
            model.addAttribute("message", "User exists");
            return "register";
        }
    }
}
