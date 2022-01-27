package com.example.demo.domain.model;

import com.example.demo.domain.model.status.CallCenterStatus;
import com.example.demo.domain.model.status.EmployeeStatus;
import com.example.demo.domain.port.secondary.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CallCenter {

    private List<Employee> respondents;
    private List<Employee> managers;
    private List<Employee> directors;

    private List<Call> respondentQueue = new ArrayList<>();
    private List<Call> directorQueue = new ArrayList<>();

    private Map<Integer, Call> ongoingCalls  = new HashMap<>();

    public int dispatchCall(Logger logger, Caller caller) {
        Call call = new Call(assignCallId(), caller);
        dispatchCall(logger, call);
        return call.getCallId();
    }

    public void escalateCall(Logger logger, int callId) {
        Call call = this.ongoingCalls.get(callId);
        Employee currentHandler = call.getEmployee();
        currentHandler.handOverTheCall();
        if (currentHandler.isRespondent()) {
            escalateCallToManager(logger, call);
        } else if (currentHandler.isManager()) {
            escalateCallToDirector(call);
        } else if (currentHandler.isDirector()) {
            //TODO future story
        }
    }

    private int nextCallId = 0;
    private int assignCallId() {
        //TODO future story
        return nextCallId++;
    }

    private void dispatchCall(Logger logger, Call call) {
        Optional<Employee> respondent = respondents
            .stream()
            .filter(e -> e.canHandleACall())
            .findFirst();

        if (respondent.isPresent()) {
            respondent
                .get()
                .handleCall(logger, call);
            ongoingCalls.put(call.getCallId(), call);
        } else {
            respondentQueue.add(call);
            logAllRespondentsBusy(logger, call);
        }
    }

    private void escalateCallToManager(Logger logger, Call call) {
        Optional<Employee> manager = managers
            .stream()
            .filter(e -> e.canHandleACall())
            .findFirst();

        if (manager.isPresent()) {
            manager
                .get()
                .handleCall(logger, call);
        } else {
            logAllManagersBusy(logger, call);
            escalateCallToDirector(call);
        }

    }

    private void escalateCallToDirector(Call call) {
        throw new UnsupportedOperationException("Escalation to a director has not been implemented yet");
    }

    private void logAllRespondentsBusy(Logger logger, Call call) {
        logger.log("Could not handle incoming call from " + call.getCaller().getName() + ". All respondents are busy. The call has been put to the queue. There are " + respondentQueue.size() + " calls waiting in the queue.");
    }

    private void logAllManagersBusy(Logger logger, Call call) {
        logger.log("There is no manager, that could handle the call with " + call.getCaller().getName() + ". The call will be escalated to a director.");
    }

    public CallCenterStatus status() {
        CallCenterStatus status = new CallCenterStatus();

        respondents.forEach(e -> status.addEmployee(toEmployeeStatus(e)));
        managers.forEach(e -> status.addEmployee(toEmployeeStatus(e)));
        directors.forEach(e -> status.addEmployee(toEmployeeStatus(e)));

        respondentQueue.forEach(c -> status.addWaitingRespondentCall(c.getCaller().getName()));
        directorQueue.forEach(c -> status.addWaitingDirectorCall(c.getCaller().getName()));

        return status;
    }

    private EmployeeStatus toEmployeeStatus(Employee e) {
        return new EmployeeStatus(e.getId(), e.getCurrentCall() == null ? null : e.getCurrentCall().getCaller().getName());
    }

    public static class Builder {
        private int numbberOfRespondetns;
        private int numberOfManagers;
        private int numberOfDirectors;

        public Builder respondets(int numbberOfRespondetns) {
            this.numbberOfRespondetns = numbberOfRespondetns;
            return this;
        }

        public Builder managers(int numberOfManagers) {
            this.numberOfManagers = numberOfManagers;
            return this;
        }

        public Builder directors(int numberOfDirectors) {
            this.numberOfDirectors = numberOfDirectors;
            return this;
        }

        public CallCenter build() {
            CallCenter callCenter = new CallCenter();
            callCenter.respondents = buildRespondentList(numbberOfRespondetns, 1);
            callCenter.managers = buildManagerList(numberOfManagers, 100);
            callCenter.directors = buildDirectorList(numberOfDirectors, 99900);
            return callCenter;
        }

        private List<Employee> buildDirectorList(int count, int idStart) {
            List<Employee> employees = new ArrayList<>();
            for(int i = 0; i < count; i++) {
                employees.add(new Director("D-" + (i+1)));
            }
            return employees;
        }

        private List<Employee> buildManagerList(int count, int idStart) {
            List<Employee> employees = new ArrayList<>();
            for(int i = 0; i < count; i++) {
                employees.add(new Manager("M-" + (i+1)));
            }
            return employees;
        }

        private List<Employee> buildRespondentList(int count, int idStart) {
            List<Employee> employees = new ArrayList<>();
            for(int i = 0; i < count; i++) {
                employees.add(new Respondent("R-" + (i+1)));
            }
            return employees;
        }
    }
}
