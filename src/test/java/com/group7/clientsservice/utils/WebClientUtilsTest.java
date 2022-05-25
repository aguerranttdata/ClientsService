package com.group7.clientsservice.utils;

import com.group7.clientsservice.model.Accounts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class WebClientUtilsTest {

    @Autowired
    private WebClientUtils webClientUtils = new WebClientUtils();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAccounts() {
        assertNotNull(webClientUtils.getAccounts(any(String.class)));
    }

    @Test
    void getCredits() {
        assertNotNull(webClientUtils.getCredits(any(String.class)));
    }
}