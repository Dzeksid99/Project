package com.example.order;

import com.example.billing.*;
import io.grpc.stub.StreamObserver;

public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Override
    public void processOrder(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        System.out.println("Received order request:");
        System.out.println("Order ID: " + request.getOrderNumber());

        String host = "localhost";
        int port = 50051;

        OrderClient client = new OrderClient(host, port);

        // Создаём заказ с заглушкой данных
        client.createOrder("ORDER123", "USER456", "Product XYZ", 99.99);

        // Здесь вы можете добавить логику обработки биллинга
        // Пока возвращаем заглушку
        OrderResponse response = OrderResponse.newBuilder()
                .setStatus("SUCCESS")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
