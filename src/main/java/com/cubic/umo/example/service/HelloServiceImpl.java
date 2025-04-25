package com.cubic.umo.example.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloServiceImpl implements HelloService {

    @Override
    public String greet(String name) {
        return "Hello " + name;
    }
}