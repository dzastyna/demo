package com.example.demo.domain.model;

public class Call {
    private int callId;
    private Caller caller;
    private Employee employee;

    public Call(int callId, Caller caller) {
        this.callId  = callId;
        this.caller = caller;
    }

    public int getCallId() {
        return callId;
    }

    public Caller getCaller() {
        return caller;
    }

    public void setEmployee(Employee employee) {
        this.employee =  employee;
    }

    public Employee getEmployee() {
        return this.employee;
    }
}
