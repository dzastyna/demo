package com.example.demo.domain.model.status;

import java.util.ArrayList;
import java.util.List;

public class CallCenterStatus {

    private List<String> waitingRespondentCalls = new ArrayList<>();
    private List<String> waitingDirectorCalls = new ArrayList<>();

    private List<EmployeeStatus> employees = new ArrayList<>();

    public void addWaitingRespondentCall(String callerName) {
        waitingRespondentCalls.add(callerName);
    }

    public void addWaitingDirectorCall(String callerName) {
        waitingDirectorCalls.add(callerName);
    }

    public void addEmployee(EmployeeStatus employee) {
        this.employees.add(employee);
    }

    public List<String> getWaitingRespondentCalls() {
        return waitingRespondentCalls;
    }

    public List<String> getWaitingDirectorCalls() {
        return waitingDirectorCalls;
    }

    public List<EmployeeStatus> getEmployees() {
        return employees;
    }
}
