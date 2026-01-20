package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateOrderRequest;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final OrderRepository orderRepo;

    public OrderService(CartRepository cartRepo,
                        ProductRepository productRepo,
                        OrderRepository orderRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Order createOrder(CreateOrderRequest req) {

        List<CartItem> items = cartRepo.findAll()
                .stream()
                .filter(c -> c.getUserId().equals(req.getUserId()))
                .toList();

        double total = 0;

        for (CartItem item : items) {
            Product p = productRepo.findById(item.getProductId()).orElse(null);

            if (p != null) {
                total += p.getPrice() * item.getQuantity();
            }
        }

        Order order = new Order();
        order.setUserId(req.getUserId());
        order.setTotalAmount(total);
        order.setStatus("CREATED");
        order.setCreatedAt(Instant.now());

        Order saved = orderRepo.save(order);

        cartRepo.deleteAll(items);

        return saved;
    }

    public Order getOrder(String id) {
        return orderRepo.findById(id).orElse(null);
    }
}
