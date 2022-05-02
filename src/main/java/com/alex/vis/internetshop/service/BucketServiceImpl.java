package com.alex.vis.internetshop.service;

import com.alex.vis.internetshop.dto.BucketDTO;
import com.alex.vis.internetshop.dto.BucketDetailDTO;
import com.alex.vis.internetshop.model.Bucket;
import com.alex.vis.internetshop.model.Product;
import com.alex.vis.internetshop.model.User;
import com.alex.vis.internetshop.repo.BucketRepository;
import com.alex.vis.internetshop.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BucketServiceImpl implements BucketService{
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);

        List<Product> products = getCollectProductsByIds(productIds);
        bucket.setProducts(products);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectProductsByIds(List<Long> productIds) {
        return productIds.stream()
                .map(productRepository :: getOne)
                .collect(Collectors.toList());
    }

    @Override
    public void addProducts(Bucket bucket, List<Long> productsIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProducts = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProducts.addAll(getCollectProductsByIds(productsIds));
        bucket.setProducts(newProducts);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDTO getBucketByUser(String name) {
        User user = userService.findByName(name);
        if (user == null || user.getBucket() == null) {
            return new BucketDTO();
        }

        BucketDTO bucketDTO = new BucketDTO();
        Map<Long, BucketDetailDTO> mapByProductId = new HashMap<>();

        List<Product> products = user.getBucket().getProducts();
        for (Product product : products) {
           BucketDetailDTO detail = mapByProductId.get(product.getId());

           if (detail == null) {
               mapByProductId.put(product.getId(), new BucketDetailDTO(product));
           } else {
               detail.setAmount(detail.getAmount().add(new BigDecimal("1.0")));
               detail.setSum(detail.getSum() + Double.parseDouble(detail.getPrice().toString()));
           }
        }

        bucketDTO.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDTO.aggregate();

        return bucketDTO;
    }
}
