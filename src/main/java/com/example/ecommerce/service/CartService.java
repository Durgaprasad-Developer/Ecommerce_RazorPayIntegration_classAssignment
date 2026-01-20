package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddToCartRequest;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepo;

    public CartService(CartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    public CartItem addToCart(AddToCartRequest req) {

        CartItem item = new CartItem();
        item.setUserId(req.getUserId());
        item.setProductId(req.getProductId());
        item.setQuantity(req.getQuantity());

        return cartRepo.save(item);
    }

    public List<CartItem> getCart(String userId) {
        return cartRepo.findAll()
                .stream()
                .filter(c -> c.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public void clearCart(String userId) {
        cartRepo.deleteAll(getCart(userId));
    }
}
