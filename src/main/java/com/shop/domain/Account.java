package com.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private LocalDateTime joinedAt;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    private String bio;

    @OneToMany(mappedBy="account")
    //@JoinColumn(name = "ORDERS_ID")
    private Set<OrdersProduct> ordersProduct = new HashSet<>();

    public void completeSignUp() {
        this.joinedAt = LocalDateTime.now();
    }

}
