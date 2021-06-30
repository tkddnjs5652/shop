package com.shop.product;

import com.shop.domain.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByPath(Long path);

    List<Product> findAllByOrderByStartDateDesc();


}
