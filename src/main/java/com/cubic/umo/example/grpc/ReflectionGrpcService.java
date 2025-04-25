package com.cubic.umo.example.grpc;

import io.grpc.BindableService;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.quarkus.grpc.GrpcService;
import jakarta.inject.Singleton;

@GrpcService
@Singleton
public class ReflectionGrpcService implements BindableService {

    @Override
    public io.grpc.ServerServiceDefinition bindService() {
        return ProtoReflectionService.newInstance().bindService();
    }
}
