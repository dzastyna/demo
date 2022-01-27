package com.example.demo.infra.adapter.primary;

public class CallEscalation {
    private String callCenterNumber;
    private int callId;

    String getCallCenterNumber() {
        return callCenterNumber;
    }

    void setCallCenterNumber(String callCenterNumber) {
        this.callCenterNumber = callCenterNumber;
    }

    int getCallId() {
        return callId;
    }

    void setCallId(int callId) {
        this.callId = callId;
    }
}
