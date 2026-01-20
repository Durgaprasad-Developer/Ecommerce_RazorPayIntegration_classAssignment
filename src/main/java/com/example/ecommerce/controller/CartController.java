package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddToCartRequest;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public CartItem add(@RequestBody AddToCartRequest req) {
        return service.addToCart(req);
    }

    @GetMapping("/{userId}")
    public List<CartItem> get(@PathVariable String userId) {
        return service.getCart(userId);
    }

    @DeleteMapping("/{userId}/clear")
    public String clear(@PathVariable String userId) {
        service.clearCart(userId);
        return "Cart cleared successfully";
    }
}
