package com.ecommerce.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("orders")
public class Orders {

    @Id
    private Long id;

    @Column("product_id")
    private Long productId;

    private int quantity;

    private Float totalPrice;

    @Column("order_date")
    private LocalDateTime orderDate;

    private String status;

    public void prePersist() {
        this.orderDate = LocalDateTime.now();
    }
}

