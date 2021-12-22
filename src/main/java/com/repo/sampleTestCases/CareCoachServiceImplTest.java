package com.catasys.pre.dfappl.dfapplsummaryapi.service.implementation;

import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import com.catasys.pre.dfappl.dfapplsummaryapi.models.CareCoachAssignmentResponse;
import com.catasys.pre.dfappl.dfapplsummaryapi.models.exceptions.CareCoachNotFoundException;
import com.catasys.pre.dfappl.dfapplsummaryapi.models.exceptions.FeatureFlagTurnedOffException;
import com.catasys.pre.dfappl.dfapplsummaryapi.models.exceptions.UnauthorizedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

@ContextConfiguration(classes = {CareCoachAssignmentServiceImpl.class, String.class})
class CareCoachServiceImplTest {

    public static MockWebServer mockBackEnd;
    private CareCoachAssignmentResponse careCoachAssignmentResponse;
    private CareCoachAssignmentServiceImpl careCoachAssignmentService;
    private String baseUrl;

    @BeforeEach
    void setup() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        careCoachAssignmentResponse = new CareCoachAssignmentResponse();
        careCoachAssignmentResponse.setSuccess(true);
        careCoachAssignmentResponse.setError("sampleError");

        baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        careCoachAssignmentService = new CareCoachAssignmentServiceImpl(baseUrl);

        ReflectionTestUtils.setField(careCoachAssignmentService, "eotCareCoachMemberURL", baseUrl);
        ReflectionTestUtils.setField(careCoachAssignmentService, "eotCareCoachMemberAPIUsername", "catasys");
        ReflectionTestUtils.setField(careCoachAssignmentService, "eotCareCoachMemberAPIPassword", "Catasys@2");

        ReflectionTestUtils.setField(careCoachAssignmentService, "updateEotCcAssignment", "true");
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void testConstructor(){
        careCoachAssignmentService = new CareCoachAssignmentServiceImpl();
        Assertions.assertNotNull(careCoachAssignmentService);
    }
    @Test
    void testFor200Scenario() throws JsonProcessingException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(careCoachAssignmentResponse))
                .addHeader("Content-Type", "application/json"));

        CareCoachAssignmentResponse careCoachAssignmentResponse1 = careCoachAssignmentService.recordCareCoachAssignment("226363572", "123654", "2021-11-12T09:29:21.291Z");


        RecordedRequest recordedRequest = mockBackEnd.takeRequest();

        Assertions.assertEquals(true, careCoachAssignmentResponse1.getSuccess());
        Assertions.assertEquals("POST", recordedRequest.getMethod());

    }

    @Test
    void testFor404Scenario() {

        mockBackEnd.enqueue(new MockResponse()
                .setResponseCode(404)
                .addHeader("Content-Type", "application/json"));

        Assertions.assertThrows(CareCoachNotFoundException.class,
                () -> careCoachAssignmentService.recordCareCoachAssignment("226363572", "sampleCareCoachID", "2021-11-12T09:29:21.291Z"),
                "eOnTrak feature flag turned off in Azure KV");

    }


    @Test
    void testFor401Scenario() {
        mockBackEnd.enqueue(new MockResponse()
                .setResponseCode(401)
                .addHeader("Content-Type", "application/json"));

        Assertions.assertThrows(UnauthorizedException.class,
                () -> careCoachAssignmentService.recordCareCoachAssignment("226363572", "sampleCareCoachID", "2021-11-12T09:29:21.291Z"));


    }

    @Test
    void testFor400InvalidRequestException() {
        mockBackEnd.enqueue(new MockResponse()
                .setResponseCode(400)
                .addHeader("Content-Type", "application/json"));

        Assertions.assertThrows(InvalidRequestException.class,
                () -> careCoachAssignmentService.recordCareCoachAssignment("226363572", "sampleCareCoachID", "2021-11-12T09:29:21.291Z"));

    }


    @Test
    void testFor500() {
        mockBackEnd.enqueue(new MockResponse()
                .setResponseCode(500)
                .addHeader("Content-Type", "application/json"));

        Assertions.assertThrows(RuntimeException.class,
                () -> careCoachAssignmentService.recordCareCoachAssignment("226363572", "sampleCareCoachID", "2021-11-12T09:29:21.291Z"));

    }

    @Test
    void testCallEOTMemberEnrollmentDateForRunTimException() {
        ReflectionTestUtils.setField(careCoachAssignmentService, "updateEotCcAssignment", "false");
        Assertions.assertThrows(FeatureFlagTurnedOffException.class,
                () -> careCoachAssignmentService.recordCareCoachAssignment("226363572", "sampleCareCoachID", "2021-11-12T09:29:21.291Z"),
                "eOnTrak feature flag turned off in Azure KV");
    }

}
