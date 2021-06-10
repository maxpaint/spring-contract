package com.pub.producer.client.api.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class CheckDto {

    public boolean adult;
    public boolean blocked;
    public boolean debtor;
    public long debt;
}
