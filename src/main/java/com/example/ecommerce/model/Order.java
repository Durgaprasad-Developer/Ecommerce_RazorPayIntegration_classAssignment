package com.example.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class Order {

    @Id
    private String id;

    private String userId;
    private double totalAmount;
    private String status;
    private Instant createdAt;
}
