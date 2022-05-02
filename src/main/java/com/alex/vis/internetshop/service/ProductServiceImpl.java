package com.alex.vis.internetshop.service;

import com.alex.vis.internetshop.dto.ProductDTO;
import com.alex.vis.internetshop.mapper.ProductMapper;
import com.alex.vis.internetshop.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductMapper mapper = ProductMapper.productMapper;

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }
}
