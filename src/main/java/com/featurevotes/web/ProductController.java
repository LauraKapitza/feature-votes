package com.featurevotes.web;

import com.featurevotes.domain.Product;
import com.featurevotes.domain.User;
import com.featurevotes.repository.ProductRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String getProducts (ModelMap model) {
        return "product";
    }

    @GetMapping("/products/{productId}")
    public String getProduct (@PathVariable int productId, ModelMap model, HttpServletResponse response) throws IOException {
        Optional<Product> productOpt = productRepository.findById(productId);

        if(productOpt.isPresent()) {
            Product product = productOpt.get();
            model.put("product", product);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id " + productId + " was not found");
        }
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

    @PostMapping("/products/{productId}")
    public String saveProduct(@PathVariable int productId, Product product, @AuthenticationPrincipal User user) {
        if (productId == product.getId()) {
            product.setUser(user);
            productRepository.save(product);
        }
        return "redirect:/products/" + productId;
    }
}
