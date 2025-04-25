package com.cubic.umo.example.grpc;

import java.util.function.BiFunction;
import io.quarkus.grpc.MutinyClient;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: hello.proto")
public class HelloServiceClient implements HelloService, MutinyClient<MutinyHelloServiceGrpc.MutinyHelloServiceStub> {

    private final MutinyHelloServiceGrpc.MutinyHelloServiceStub stub;

    public HelloServiceClient(String name, io.grpc.Channel channel, BiFunction<String, MutinyHelloServiceGrpc.MutinyHelloServiceStub, MutinyHelloServiceGrpc.MutinyHelloServiceStub> stubConfigurator) {
        this.stub = stubConfigurator.apply(name, MutinyHelloServiceGrpc.newMutinyStub(channel));
    }

    private HelloServiceClient(MutinyHelloServiceGrpc.MutinyHelloServiceStub stub) {
        this.stub = stub;
    }

    public HelloServiceClient newInstanceWithStub(MutinyHelloServiceGrpc.MutinyHelloServiceStub stub) {
        return new HelloServiceClient(stub);
    }

    @Override
    public MutinyHelloServiceGrpc.MutinyHelloServiceStub getStub() {
        return stub;
    }

    @Override
    public io.smallrye.mutiny.Uni<com.cubic.umo.example.grpc.HelloResponse> sayHello(com.cubic.umo.example.grpc.HelloRequest request) {
        return stub.sayHello(request);
    }
}
