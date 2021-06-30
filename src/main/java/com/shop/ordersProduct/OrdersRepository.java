package com.shop.ordersProduct;


import com.shop.domain.Orders;
import com.shop.domain.OrdersProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
