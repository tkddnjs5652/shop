package com.shop.ordersProduct;

import com.shop.domain.Account;
import com.shop.domain.Orders;
import com.shop.domain.OrdersProduct;
import com.shop.domain.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Transactional(readOnly = true)
public interface OrdersProductRepository extends JpaRepository<OrdersProduct,Long> {

    @EntityGraph(value = "OrdersProduct.withAll", type = EntityGraph.EntityGraphType.LOAD)
    OrdersProduct findByAccount(Account account);

    @EntityGraph(value = "OrdersProduct.withAll", type = EntityGraph.EntityGraphType.LOAD)
    List<OrdersProduct> findByAccountAndOrdersIsNull(Account account);

    @EntityGraph(value = "OrdersProduct.withAll", type = EntityGraph.EntityGraphType.LOAD)
    List<OrdersProduct> findByAccountAndOrdersIsNotNull(Account account);

}
