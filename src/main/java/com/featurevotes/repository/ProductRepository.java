package com.featurevotes.repository;

import com.featurevotes.domain.Product;
import com.featurevotes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Integer> {
}
