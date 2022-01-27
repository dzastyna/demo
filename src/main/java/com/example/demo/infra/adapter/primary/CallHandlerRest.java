package com.example.demo.infra.adapter.primary;

import com.example.demo.domain.model.CallCenter;
import com.example.demo.domain.model.status.CallCenterStatus;
import com.example.demo.domain.port.primary.CallHandler;
import com.example.demo.domain.port.secondary.CallCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class CallHandlerRest {

    @Autowired
    private CallCenterRepository callCenterRepository;

    @Autowired
    private CallHandler callHandler;

    @GetMapping("/callCenters")
    Set<String> getCallCenters() {
        return callCenterRepository.all();
    }

    @GetMapping("/callCenterStatus/{callCenterNumber}")
    CallCenterStatus callCenterStatus(@PathVariable String callCenterNumber) {
        CallCenter callCenter = callCenterRepository.get(callCenterNumber);
        return callCenter.status();
    }

    @PostMapping("/newCall")
    int newCall(@RequestBody NewCall newCall) {
        return callHandler.handleCall(newCall.getCallCenterNumber(), newCall.getCallerName());
    }

    @PostMapping("/escalate")
    void escalate(@RequestBody CallEscalation escalation) {
        callHandler.escalateCallToManager(escalation.getCallCenterNumber(), escalation.getCallId());
    }


}
