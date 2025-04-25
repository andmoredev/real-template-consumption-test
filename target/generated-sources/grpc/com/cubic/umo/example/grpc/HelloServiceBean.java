package com.cubic.umo.example.grpc;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: hello.proto")
public class HelloServiceBean extends MutinyHelloServiceGrpc.HelloServiceImplBase implements BindableService, MutinyBean {

    private final HelloService delegate;

    HelloServiceBean(@GrpcService HelloService delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<com.cubic.umo.example.grpc.HelloResponse> sayHello(com.cubic.umo.example.grpc.HelloRequest request) {
        try {
            return delegate.sayHello(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
