package com.example.ecommerce.service;

import com.example.ecommerce.dto.RazorpayOrderRequest;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    // Use your actual Razorpay test credentials
    private static final String KEY_ID = "rzp_test_S65Lb6nPYksVkS";
    private static final String KEY_SECRET = "kpujxiuNN7DVDEcNhcvizdy2";

    public Object createRazorpayOrder(RazorpayOrderRequest request) throws Exception {

        // Initialize Razorpay client
        RazorpayClient client = new RazorpayClient(KEY_ID, KEY_SECRET);

        // Prepare order options
        JSONObject options = new JSONObject();

        // Amount in paise (so 1000 = ₹10.00)
        options.put("amount", 1000);
        options.put("currency", "INR");

        // Using your internal order id as receipt
        options.put("receipt", request.getOrderId());

        // Create order on Razorpay
        Order order = client.orders.create(options);

        // Return full order response as JSON
        return order.toJson();
    }
}
