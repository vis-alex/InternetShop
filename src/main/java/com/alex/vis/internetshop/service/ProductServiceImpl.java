package com.alex.vis.internetshop.service;

import com.alex.vis.internetshop.dto.ProductDTO;
import com.alex.vis.internetshop.mapper.ProductMapper;
import com.alex.vis.internetshop.model.Bucket;
import com.alex.vis.internetshop.model.User;
import com.alex.vis.internetshop.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductMapper mapper = ProductMapper.productMapper;

    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    @Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);

        if (user == null) {
            throw new RuntimeException("User not found by name " + username);
        }

        Bucket bucket = user.getBucket();

        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }

    }
}
