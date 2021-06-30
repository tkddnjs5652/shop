package com.shop.orders;

import com.shop.domain.Orders;
import com.shop.domain.Product;
import com.shop.ordersProduct.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public Orders saveNewOrders() {
        Orders orders = Orders.builder()
                .ordersChecked(true)
                .build();
        Orders newOrders = ordersRepository.save(orders);
        return newOrders;
    }

    public void uploadData(Orders orders) {
        orders.uploadDate();
    }

}
