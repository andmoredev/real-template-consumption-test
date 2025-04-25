package com.cubic.umo.example.grpc;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: hello.proto")
public interface HelloService extends MutinyService {

    io.smallrye.mutiny.Uni<com.cubic.umo.example.grpc.HelloResponse> sayHello(com.cubic.umo.example.grpc.HelloRequest request);
}
