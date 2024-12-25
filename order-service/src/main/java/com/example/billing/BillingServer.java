package com.example.billing;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class BillingServer {

    private Server server;

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new BillingServiceImpl())
                .build()
                .start();
        System.out.println("BillingService started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Shutting down BillingService since JVM is shutting down");
            BillingServer.this.stop();
            System.err.println("*** BillingService shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // Блокирует до остановки сервера
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final BillingServer server = new BillingServer();
        server.start();
        server.blockUntilShutdown();
    }
}
