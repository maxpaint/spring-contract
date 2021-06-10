package com.pub.producer.client.api.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("clients")
public class ClientController {

    //private final ClientService clientService;

    @PostMapping(value = "/{id}/check",
                 produces = MediaType.APPLICATION_JSON_VALUE,
                 consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CheckDto> check(@RequestBody ClientDto client) {
        //var check = clientService.check();
        log.info("client is {}", client);
        if (client.isNotAdult()) {
            var checkDto = new CheckDto().setAdult(false);
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body(checkDto);
        }
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new CheckDto().setAdult(true));
    }
}
