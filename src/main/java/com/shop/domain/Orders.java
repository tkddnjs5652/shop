package com.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Orders {


    @Id @GeneratedValue
    //@Column(name = "ORDERS_ID")
    private Long id;

    private LocalDateTime ordersDate;

    private boolean ordersChecked = true;

    @OneToOne(mappedBy = "orders")
    //@JoinColumn(name = "ORDERS_PRODUCT_ID")
    private OrdersProduct ordersProduct;

    public void uploadDate() {
        this.ordersDate = LocalDateTime.now();
    }




}
