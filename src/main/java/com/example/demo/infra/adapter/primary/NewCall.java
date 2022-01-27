package com.example.demo.infra.adapter.primary;

public class NewCall {

    private String callCenterNumber;
    private String callerName;

    void setCallCenterNumber(String callCenterNumber) {
        this.callCenterNumber = callCenterNumber;
    }

    void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    String getCallCenterNumber() {
        return callCenterNumber;
    }

    String getCallerName() {
        return callerName;
    }


}
