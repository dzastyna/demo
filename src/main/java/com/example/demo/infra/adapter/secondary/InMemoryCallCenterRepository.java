package com.example.demo.infra.adapter.secondary;

import com.example.demo.domain.model.CallCenter;
import com.example.demo.domain.port.secondary.CallCenterRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InMemoryCallCenterRepository implements CallCenterRepository {
    private Map<String, CallCenter> callCenterSource = new HashMap<>();

    public InMemoryCallCenterRepository() {
        callCenterSource.put("48799353535", new CallCenter.Builder().respondets(8).managers(3).directors(1).build());
    }

    @Override
    public Set<String> all() {
        return callCenterSource.keySet();
    }

    public CallCenter get(String callNumber) {
        return this.callCenterSource.get(callNumber);
    }
}
