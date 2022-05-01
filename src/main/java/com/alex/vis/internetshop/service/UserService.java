package com.alex.vis.internetshop.service;

import com.alex.vis.internetshop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO user);

    List<UserDTO> getAll();
}
