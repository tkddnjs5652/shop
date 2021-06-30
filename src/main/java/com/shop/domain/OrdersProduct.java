package com.shop.domain;

import lombok.*;

import javax.persistence.*;

@NamedEntityGraph(name = "OrdersProduct.withAll", attributeNodes = {
        @NamedAttributeNode("product"),
        @NamedAttributeNode("account")})
@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class OrdersProduct {

    @Id
    @GeneratedValue
    private Long id;

    private int ordersProductCnt;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Orders orders;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Account account;



}
