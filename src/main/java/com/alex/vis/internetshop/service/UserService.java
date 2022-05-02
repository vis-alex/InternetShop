package com.alex.vis.internetshop.service;

import com.alex.vis.internetshop.dto.UserDTO;
import com.alex.vis.internetshop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO user);

    void save(User user);

    List<UserDTO> getAll();

    User findByName(String name);

    void updateProfile(UserDTO dto);
}
