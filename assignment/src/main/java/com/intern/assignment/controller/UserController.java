package com.intern.assignment.controller;

import com.intern.assignment.entity.User;
import com.intern.assignment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // STEP 1: Form submission goes to Preview Page
    @PostMapping("/preview")
    public String previewUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

        // Duplicate email validation logic
        if (user.getId() == null && userService.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "error.user", "This email is already registered.");
        }

        // Duplicate Mobile check
        if (user.getId() == null && userService.existsByMobile(user.getMobile())) {
            result.rejectValue("mobile", "error.user", "This mobile number is already registered.");
        }

        if (result.hasErrors()) {
            return "register";
        }

        model.addAttribute("user", user);
        return "preview";
    }

    // STEP 2: Final Save after confirmation popup
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/list";
    }

    // Added required parameters for search and pagination
    @GetMapping("/list")
    public String listUsers(Model model,
                            @RequestParam(value = "keyword", required = false) String keyword,
                            @RequestParam(value = "page", defaultValue = "0") int page) {

        // Define page size (records per page)
        Pageable pageable = PageRequest.of(page, 5);

        // Call the service with required arguments
        Page<User> userPage = userService.getAllUsers(keyword, pageable);

        // Pass the actual content list to the view
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "register";
        }
        return "redirect:/list";
    }
}