package com.example.ecommerce.controller;

import com.example.ecommerce.dto.RazorpayOrderRequest;
import com.example.ecommerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/razorpay")
@CrossOrigin
public class RazorpayController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public Object create(@RequestBody RazorpayOrderRequest request) throws Exception {
        return paymentService.createRazorpayOrder(request);
    }
}
