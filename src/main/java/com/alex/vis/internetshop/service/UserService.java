package com.alex.vis.internetshop.service;

import com.alex.vis.internetshop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO user);
}
