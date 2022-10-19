package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class AdminController {

    private final UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers(Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("currentUser", user);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin";
    }

    @GetMapping("/create_user")
    public String showPageOfCreateUser(@AuthenticationPrincipal UserDetails currentUser,
                                       @ModelAttribute("user") User user,
                                       Model model) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "create_user";
    }

    @PostMapping("/create_user")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping(value = "/edit/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") Long id, Model model) {
        userService.update(user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findUserByEmail(auth.getName());
        model.addAttribute("currentUser", currentUser);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

}
