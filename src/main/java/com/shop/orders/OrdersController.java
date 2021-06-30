package com.shop.orders;

import com.shop.account.CurrentUser;
import com.shop.domain.Account;
import com.shop.domain.Orders;
import com.shop.domain.OrdersProduct;
import com.shop.ordersProduct.OrdersProductRepository;
import com.shop.ordersProduct.OrdersProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    private final OrdersProductService ordersProductService;
    private final OrdersProductRepository ordersProductRepository;

    @PostMapping("/orders/{nickname}/add")
    public String addOrders(@PathVariable String nickname, @CurrentUser Account account) {
        List<OrdersProduct> ordersProduct = ordersProductRepository.findByAccountAndOrdersIsNull(account);
        if(ordersProduct == null) {
            throw new NullPointerException("상품이 없습니다");
        }
        Orders orders = ordersService.saveNewOrders();
        ordersService.uploadData(orders);
        for(OrdersProduct product : ordersProduct) {
            ordersProductService.uploadOrders(orders, product);
        }
        return "redirect:/profile/{nickname}";
    }

}
