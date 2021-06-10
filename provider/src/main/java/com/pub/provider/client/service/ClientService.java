package com.pub.provider.client.service;

import com.pub.provider.client.api.rest.CheckDto;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public CheckInformation check() {
        return new CheckInformation();
    }
}
