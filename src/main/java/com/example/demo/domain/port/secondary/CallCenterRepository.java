package com.example.demo.domain.port.secondary;

import com.example.demo.domain.model.CallCenter;

import java.util.Set;

public interface CallCenterRepository {

    Set<String> all();

    CallCenter get(String callNumber);

}
