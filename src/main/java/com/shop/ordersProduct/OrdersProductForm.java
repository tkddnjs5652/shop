package com.shop.ordersProduct;

import com.shop.domain.OrdersProduct;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class OrdersProductForm {

    private int ordersProductCnt;

    public OrdersProductForm(OrdersProduct ordersProduct) {
        this.ordersProductCnt = ordersProduct.getOrdersProductCnt();
    }

}
