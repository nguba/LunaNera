package io.github.nguba.lunanera.presentation;

import io.github.nguba.lunanera.IntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@IntegrationTest
class LunaNeraControllerTest {

    @Autowired
    WebTestClient client;

    @Test
    @Disabled
    void testDevices() throws Exception {
        // client.get().uri("/api/devices").exchange().expectStatus().isOk().expectBodyList(Actuator.class);
    }
}