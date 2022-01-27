package com.example.demo.domain.model;

import com.example.demo.domain.port.secondary.Logger;

public class Employee {
    private Call currentCall;

    private String id;

    public Employee(String id) {
        this.id = id;
    }

    public void handleCall(Logger logger, Call call) {
        if (this.currentCall != null) {
            throw new IllegalStateException("Employee is in a call with " + call.getCaller().getName() + ". Cannot handle another call");
        }

        logger.log("Employee " + id + " receiving a call with " + call.getCaller().getName());
        this.currentCall = call;
        this.currentCall.setEmployee(this);
    }

    public boolean canHandleACall() {
        return this.currentCall == null;
    }

    public void handOverTheCall() {
        this.currentCall = null;
    }

    public boolean isRespondent() {
        return false;
    }

    public boolean isManager() {
        return false;
    }

    public boolean isDirector() {
        return false;
    }

    public String getId() {
        return id;
    }

    public Call getCurrentCall() {
        return currentCall;
    }
}
