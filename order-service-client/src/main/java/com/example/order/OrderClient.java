package com.example.order;

import com.example.billing.BillingRequest;
import com.example.billing.BillingResponse;
import com.example.billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class OrderClient {

    private final BillingServiceGrpc.BillingServiceBlockingStub billingStub;

    public OrderClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // Для простоты без SSL
                .build();
        billingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public void createOrder(String orderId, String userId, String product, double amount) {
        System.out.println("Creating order:");
        System.out.println("Order ID: " + orderId);
        System.out.println("User ID: " + userId);
        System.out.println("Product: " + product);
        System.out.println("Amount: " + amount);

        BillingRequest request = BillingRequest.newBuilder()
                .setOrderId(orderId)
                .setUserId(userId)
                .setProduct(product)
                .setAmount(amount)
                .build();

        BillingResponse response = billingStub.processBilling(request);
        System.out.println("Billing Response:");
        System.out.println("Status: " + response.getStatus());
        System.out.println("Message: " + response.getMessage());
    }

}
