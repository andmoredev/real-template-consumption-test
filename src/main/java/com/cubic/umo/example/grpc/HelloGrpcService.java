package com.cubic.umo.example.grpc;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;

import com.cubic.umo.example.service.HelloService;

@GrpcService
public class HelloGrpcService extends HelloServiceGrpc.HelloServiceImplBase {

    private final HelloService helloService;

    public HelloGrpcService(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String message = helloService.greet(request.getName());
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(message)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}