package com.shop.ordersProduct;

import com.shop.account.CurrentUser;
import com.shop.domain.Account;
import com.shop.domain.Orders;
import com.shop.domain.OrdersProduct;
import com.shop.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class OrdersProductService {

    private final OrdersProductRepository ordersProductRepository;

    public OrdersProduct saveNewOrdersProduct(OrdersProductForm ordersProductForm) {
        OrdersProduct ordersProduct = OrdersProduct.builder()
                .ordersProductCnt(ordersProductForm.getOrdersProductCnt())
                .build();
        return ordersProduct;
    }

    public OrdersProduct saveProduct(Account account, OrdersProduct ordersProduct, Product product) {
        ordersProduct.setProduct(product);
        ordersProduct.setAccount(account);
        return ordersProductRepository.save(ordersProduct);
    }

    public OrdersProduct uploadOrders(Orders orders, OrdersProduct ordersProduct) {
        ordersProduct.setOrders(orders);
        return ordersProductRepository.save(ordersProduct);
    }

}
