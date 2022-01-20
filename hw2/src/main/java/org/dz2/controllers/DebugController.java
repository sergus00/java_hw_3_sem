package org.dz2.controllers;

import org.dz2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DebugController {
    private final UserService userService;

    public DebugController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/debug")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/debug")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Integer userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }
}
