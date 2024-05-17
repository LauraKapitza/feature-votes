package com.featurevotes.web;

import com.featurevotes.domain.Product;
import com.featurevotes.domain.User;
import com.featurevotes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String getProducts (ModelMap model) {
        return "product";
    }

    @GetMapping("/products/{productId}")
    public String getProduct (@PathVariable int productId) {
        return "product";
    }

    @PostMapping("/products")
    public String createProduct(@AuthenticationPrincipal User user) {
        Product product = new Product();

        product.setPublished(false);
        product.setUser(user);

        Product savedProduct = productRepository.save(product);

        return "redirect:/products/" + savedProduct.getId();
    }
}
