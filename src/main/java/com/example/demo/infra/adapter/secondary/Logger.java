package com.example.demo.infra.adapter.secondary;

public class Logger implements com.example.demo.domain.port.secondary.Logger {

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
