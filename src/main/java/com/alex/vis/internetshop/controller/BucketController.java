package com.alex.vis.internetshop.controller;

import com.alex.vis.internetshop.dto.BucketDTO;
import com.alex.vis.internetshop.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BucketController {
    private final BucketService bucketService;

    @GetMapping("/bucket")
    public String aboutBucket(Model model, Principal principal) {
        if (principal == null) {
            model. addAttribute("bucket", new BucketDTO());
        } else {
            BucketDTO  bucketDTO = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDTO);
        }
        return "bucket";
    }
}
