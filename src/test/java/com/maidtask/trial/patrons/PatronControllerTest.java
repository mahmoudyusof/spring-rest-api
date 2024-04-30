package com.maidtask.trial.patrons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import com.maidtask.trial.patrons.dto.EagerPatronDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatronControllerTest {

    @LocalServerPort
    int randomServerPort;

    RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.create("http://localhost:" + randomServerPort);
    }

    @Test
    void shouldFindAllRuns() {
        List<EagerPatronDTO> patrons = restClient.get()
                        .uri("/api/patrons")
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
        assertNotEquals(0, patrons.size());
    }

    @Test
    void shouldFindRunById() {
        EagerPatronDTO patron = restClient.get()
                .uri("/api/patrons/1")
                .retrieve()
                .body(EagerPatronDTO.class);

        assertNotNull(patron);

        assertAll(
                () -> assertEquals(1, patron.id()),
                () -> assertEquals("John Doe", patron.full_name()));
    }

    @Test
    void shouldDeleteRun() {
        ResponseEntity<Void> patron = restClient.delete()
                .uri("/api/patrons/2")
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.OK, patron.getStatusCode());
    }

}