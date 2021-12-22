package com.catasys.pre.dfappl.dfapplsummaryapi.service.implementation;

import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import com.catasys.pre.dfappl.dfapplsummaryapi.models.CareCoachAssignment;
import com.catasys.pre.dfappl.dfapplsummaryapi.models.CareCoachAssignmentResponse;
import com.catasys.pre.dfappl.dfapplsummaryapi.models.exceptions.CareCoachNotFoundException;
import com.catasys.pre.dfappl.dfapplsummaryapi.models.exceptions.FeatureFlagTurnedOffException;
import com.catasys.pre.dfappl.dfapplsummaryapi.models.exceptions.UnauthorizedException;
import com.catasys.pre.dfappl.dfapplsummaryapi.service.interfaces.CareCoachAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

import static com.catasys.pre.dfappl.dfapplsummaryapi.models.constants.SummaryConstants.EOT_FEATURE_FLAG_OFF;

@Service
public class CareCoachAssignmentServiceImpl implements CareCoachAssignmentService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected WebClient webClient;
    protected WebClient.RequestBodySpec baseRequest;

    @Value("${update-eot-cc-assignment}")
    private String updateEotCcAssignment;

    @Value("${eot-care-coach-member-url}")
    private String eotCareCoachMemberURL;

    @Value("${eot-care-coach-member-api-username}")
    private String eotCareCoachMemberAPIUsername;

    @Value("${eot-care-coach-member-api-password}")
    private String eotCareCoachMemberAPIPassword;

    public CareCoachAssignmentServiceImpl(String eotCareCoachMemberURL) {
        this.webClient = WebClient.create(eotCareCoachMemberURL);
    }

    public CareCoachAssignmentServiceImpl() {
        this.webClient = WebClient.create();
    }

    @Override
    public CareCoachAssignmentResponse recordCareCoachAssignment(String eRehabId, String careCoachId, String ccAssignmentDateUtc) {
        CareCoachAssignment careCoachAssignment = new CareCoachAssignment();
        CareCoachAssignmentResponse careCoachAssignmentResponse = new CareCoachAssignmentResponse();

        boolean shouldUpdateEOT = Boolean.parseBoolean(updateEotCcAssignment);
        log.info("shouldUpdateEOT = {}", shouldUpdateEOT);
        if (shouldUpdateEOT) {

            careCoachAssignment.setERehabId(eRehabId);
            careCoachAssignment.setCcAssignmentDateUtc(ccAssignmentDateUtc);
            careCoachAssignment.setCareCoachId(careCoachId);

            log.info("recordCareCoachAssignment called");

            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                    .fromHttpUrl(eotCareCoachMemberURL);

            UriComponents uriComponents = uriComponentsBuilder
                    .build();

            URI uri = uriComponents.toUri();
            log.debug("uri: {}", uri);

            this.baseRequest = this.webClient.method(HttpMethod.POST).uri(uri);

            try {
                careCoachAssignmentResponse = recordCareCoachAssignmentInEOT(careCoachAssignment);
            } catch (Exception e) {
                careCoachAssignmentResponse.setSuccess(false);
                careCoachAssignmentResponse.setError(e.getMessage());
                throw e;
            }
        } else {
            careCoachAssignmentResponse.setSuccess(false);
            careCoachAssignmentResponse.setError(EOT_FEATURE_FLAG_OFF);
            throw new FeatureFlagTurnedOffException(EOT_FEATURE_FLAG_OFF);
        }

        log.debug("careCoachAssignmentResponseResponsePayload: {}", careCoachAssignmentResponse);

        log.info("recordCareCoachAssignment() completed");
        return careCoachAssignmentResponse;

    }

    protected CareCoachAssignmentResponse recordCareCoachAssignmentInEOT(CareCoachAssignment careCoachAssignment) {
        return this.baseRequest
                .headers(headers -> headers.setBasicAuth(eotCareCoachMemberAPIUsername, eotCareCoachMemberAPIPassword))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(careCoachAssignment), CareCoachAssignment.class)
                .exchange()
                .flatMap(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        log.info("eOnTrak Care Coach Assignment API SUCCESS: {}", response.statusCode());
                    } else if (response.statusCode().value() == HttpStatus.UNAUTHORIZED.value()) {
                        log.error("eOnTrak Care Coach Assignment API FAILURE: {}", response.statusCode());
                        return Mono.error(new UnauthorizedException("Unauthorized", "INVALID_SESSION_ID"));
                    } else if (response.statusCode().value() == HttpStatus.BAD_REQUEST.value()) {
                        log.error("eOnTrak Care Coach Assignment API FAILURE: {}", response.statusCode());
                        return Mono.error(new InvalidRequestException("Invalid Request"));
                    } else if (response.statusCode().value() == HttpStatus.NOT_FOUND.value()) {
                        log.error("eOnTrak Care Coach Assignment API FAILURE: {}", response.statusCode());
                        return Mono.error(new CareCoachNotFoundException("Care Coach Not Found"));
                    } else {
                        log.error("eOnTrak Care Coach Assignment API FAILURE: {}", response.statusCode());
                        return Mono.error(new RuntimeException(response.statusCode().toString()));
                    }
                    return response.bodyToMono(CareCoachAssignmentResponse.class);
                })
                .block();
    }
}
