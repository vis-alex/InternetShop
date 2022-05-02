package com.alex.vis.internetshop.controller;

import com.alex.vis.internetshop.dto.UserDTO;
import com.alex.vis.internetshop.model.User;
import com.alex.vis.internetshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model) {
        if (userService.save(userDTO)) {
            return "redirect:/users";
        }
        model.addAttribute("user", userDTO);
        return "user";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        if (principal == null ) {
            throw new RuntimeException("You are not authorize");
        }

        User user = userService.findByName(principal.getName());

        UserDTO dto = UserDTO.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();

        model.addAttribute("user", dto);
        return "profile";
    }

    @PostMapping("/profile")
    public String getProfile(UserDTO dto, Model model, Principal principal) {
        //Reason - user shouldn`t change his name
        if (principal == null || !Objects.equals(principal.getName(), dto.getUsername())) {
            throw new RuntimeException("You are not authorize");
        }

        if (dto.getPassword() != null
                && !dto.getPassword().isEmpty()
                && !Objects.equals(dto.getPassword(), dto.getMatchingPassword())) {

            model.addAttribute("user", dto);
            //TODO Need to add some Message
            return "profile";
        }

        userService.updateProfile(dto);
        return "redirect:/users/profile";
    }
}
