package com.cubic.umo.example.grpc;

import com.cubic.umo.example.service.HelloService;
import com.cubic.umo.example.service.HelloServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelloServiceTest {

    @Test
    void testGreet() {
        HelloService service = new HelloServiceImpl();
        String result = service.greet("CUBIC");
        assertEquals("Hello, CUBIC!", result);
    }
}