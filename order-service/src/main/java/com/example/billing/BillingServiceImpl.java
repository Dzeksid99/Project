package com.example.billing;

import io.grpc.stub.StreamObserver;

public class BillingServiceImpl extends BillingServiceGrpc.BillingServiceImplBase {

    @Override
    public void processBilling(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {
        System.out.println("Received billing request:");
        System.out.println("Order ID: " + request.getOrderId());
        System.out.println("User ID: " + request.getUserId());
        System.out.println("Product: " + request.getProduct());
        System.out.println("Amount: " + request.getAmount());

        // Здесь вы можете добавить логику обработки биллинга
        // Пока возвращаем заглушку
        BillingResponse response = BillingResponse.newBuilder()
                .setStatus("SUCCESS")
                .setMessage("Billing processed successfully for order " + request.getOrderId())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
