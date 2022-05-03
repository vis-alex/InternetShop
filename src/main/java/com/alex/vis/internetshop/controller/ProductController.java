package com.alex.vis.internetshop.controller;

import com.alex.vis.internetshop.service.ProductService;
import com.alex.vis.internetshop.service.SessionObjectHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final SessionObjectHolder sessionObjectHolder;

    @GetMapping
    public String getList(Model model) {
         sessionObjectHolder.addClick();
         model.addAttribute("products", productService.getAll());
         return "products";
    }

    @GetMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal) {
        sessionObjectHolder.addClick();
        if (principal == null) {
            return "redirect:/products";
        }

        productService.addToUserBucket(id, principal.getName());
        return "redirect:/products";
    }
}
