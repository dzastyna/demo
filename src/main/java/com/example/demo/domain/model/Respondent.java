package com.example.demo.domain.model;

public class Respondent extends Employee {
    public Respondent(String id) {
        super(id);
    }

    @Override
    public boolean isRespondent() {
        return true;
    }
}
