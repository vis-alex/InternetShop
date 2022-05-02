package com.alex.vis.internetshop.service;

import com.alex.vis.internetshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    void addToUserBucket(Long productId, String username);
}
