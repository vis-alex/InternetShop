package com.alex.vis.internetshop.service;

import com.alex.vis.internetshop.model.Bucket;
import com.alex.vis.internetshop.model.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productsIds);
}
