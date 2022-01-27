package com.example.demo.domain.model;

public class Manager extends Employee {
    public Manager(String id) {
        super(id);
    }

    @Override
    public boolean isManager() {
        return true;
    }
}
