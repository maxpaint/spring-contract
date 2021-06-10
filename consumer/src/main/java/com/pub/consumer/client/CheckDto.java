package com.pub.consumer.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
class CheckDto {

    public boolean adult;
    public boolean blocked;
    public boolean debtor;
    public long debt;
}
