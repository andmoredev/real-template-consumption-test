package com.cubic.umo.example.grpc;

import static com.cubic.umo.example.grpc.HelloServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: hello.proto")
public final class MutinyHelloServiceGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyHelloServiceGrpc() {
    }

    public static MutinyHelloServiceStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyHelloServiceStub(channel);
    }

    public static class MutinyHelloServiceStub extends io.grpc.stub.AbstractStub<MutinyHelloServiceStub> implements io.quarkus.grpc.MutinyStub {

        private HelloServiceGrpc.HelloServiceStub delegateStub;

        private MutinyHelloServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = HelloServiceGrpc.newStub(channel);
        }

        private MutinyHelloServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = HelloServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyHelloServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyHelloServiceStub(channel, callOptions);
        }

        public io.smallrye.mutiny.Uni<com.cubic.umo.example.grpc.HelloResponse> sayHello(com.cubic.umo.example.grpc.HelloRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::sayHello);
        }
    }

    public static abstract class HelloServiceImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public HelloServiceImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        public io.smallrye.mutiny.Uni<com.cubic.umo.example.grpc.HelloResponse> sayHello(com.cubic.umo.example.grpc.HelloRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(com.cubic.umo.example.grpc.HelloServiceGrpc.getSayHelloMethod(), asyncUnaryCall(new MethodHandlers<com.cubic.umo.example.grpc.HelloRequest, com.cubic.umo.example.grpc.HelloResponse>(this, METHODID_SAY_HELLO, compression))).build();
        }
    }

    private static final int METHODID_SAY_HELLO = 0;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final HelloServiceImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(HelloServiceImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_SAY_HELLO:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.cubic.umo.example.grpc.HelloRequest) request, (io.grpc.stub.StreamObserver<com.cubic.umo.example.grpc.HelloResponse>) responseObserver, compression, serviceImpl::sayHello);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }
}
