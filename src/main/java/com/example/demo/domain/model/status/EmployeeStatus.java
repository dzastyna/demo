package com.example.demo.domain.model.status;

public class EmployeeStatus {
    private final String id;
    private final String status;

    public EmployeeStatus(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
