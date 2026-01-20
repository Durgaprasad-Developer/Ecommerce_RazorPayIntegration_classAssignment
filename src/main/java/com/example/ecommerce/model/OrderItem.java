package com.example.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class OrderItem {

    @Id
    private String id;

    private String orderId;
    private String productId;
    private int quantity;
    private double price;
}
