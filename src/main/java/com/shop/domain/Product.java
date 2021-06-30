package com.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id @GeneratedValue
    //@Column(name = "PRODUCT_ID")
    private Long id;

    private String name;

    private int price;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String productImage;

    private LocalDateTime startDate;

    private int productCnt;

    private String description;

    private Long path;

    @OneToMany(mappedBy = "product")
    //@JoinColumn(name = "ORDERS_PRODUCT_ID")
    private Set<OrdersProduct> ordersProduct = new HashSet<>();

    public void uploadDate() {
        this.startDate = LocalDateTime.now();
    }

    public void uploadPath() { this.path = this.id;}

}
