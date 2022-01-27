package com.example.demo.domain.port.primary;

import com.example.demo.domain.model.CallCenter;
import com.example.demo.domain.model.Caller;
import com.example.demo.domain.port.secondary.CallCenterRepository;
import com.example.demo.domain.port.secondary.Logger;

public class CallHandler {
    private Logger logger;
    private CallCenterRepository callCenterRepository;

    public CallHandler(Logger logger, CallCenterRepository callCenterRepository) {
        this.logger = logger;
        this.callCenterRepository = callCenterRepository;
    }

    public int handleCall(String number, String caller) {
        logger.log("Incoming call from " + caller + " to " + number);
        CallCenter callCenter = callCenterRepository.get(number);
        return callCenter.dispatchCall(logger, new Caller(caller));
    }

    public void escalateCallToManager(String callCenterNumber, int callId) {
        CallCenter callCenter = callCenterRepository.get(callCenterNumber);
        callCenter.escalateCall(logger, callId);
    }
}
