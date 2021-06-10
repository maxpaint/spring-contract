package com.pub.producer.client.api.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.StringJoiner;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class ClientDto {

    private int age;

    public boolean isAdult() {
        return age >= 18;
    }

    public boolean isNotAdult() {
        return ! isAdult();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientDto.class.getSimpleName() + "[", "]")
            .add("age=" + age)
            .toString();
    }
}
