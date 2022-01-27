package com.example.demo.domain.model;

public class Director extends Employee {
    public Director(String id) {
        super(id);
    }

    @Override
    public boolean isDirector() {
        return true;
    }
}
