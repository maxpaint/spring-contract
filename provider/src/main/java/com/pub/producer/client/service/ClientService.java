package com.pub.producer.client.service;

import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public CheckInformation check() {
        return new CheckInformation();
    }
}
