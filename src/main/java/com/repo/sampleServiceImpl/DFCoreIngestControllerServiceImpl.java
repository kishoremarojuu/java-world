package sampleServiceImpl;

import com.ontrak.dfcore.dfcoresubscribercometd.model.*;
import com.ontrak.dfcore.dfcoresubscribercometd.model.constants.Endpoints;
import com.ontrak.dfcore.dfcoresubscribercometd.service.interfaces.DFCoreIngestControllerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

import static com.ontrak.dfcore.dfcoresubscribercometd.util.CommonUtilHelper.logErrorResponse;

public class DFCoreIngestControllerServiceImpl extends APIBase implements DFCoreIngestControllerService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${dfcore-ingest-controller-base-url}")
    String dfCoreIngestControllerBaseUrl;

    @Override
    public DFApplAPIResponse createAppointment(AppointmentInfo appointmentInfo) {
        log.debug("createAppointment() called");
        log.debug("appointmentInfo = " + appointmentInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.appointment);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.POST)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(appointmentInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to create Appointment with http status code %s and URL endpoint is %s", response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("createAppointment() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updateAppointment(String id, AppointmentInfo appointmentInfo) {
        log.debug("updateAppointment() called");
        log.debug("id = " + id);
        log.debug("appointmentInfo = " + appointmentInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.appointment, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(appointmentInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update Appointment %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updateAppointment() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse createCarePlan(CarePlanInfo carePlanInfo) {
        log.debug("createCarePlan() called");
        log.debug("carePlanInfo = " + carePlanInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.careplan);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.POST)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(carePlanInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to create CarePlan with http status code %s and URL endpoint is %s", response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("createCarePlan() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updateCarePlan(String id, CarePlanInfo carePlanInfo) {
        log.debug("updateCarePlan() called");
        log.debug("id = " + id);
        log.debug("carePlanInfo = " + carePlanInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.careplan, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(carePlanInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update CarePlan %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updateCarePlan() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse createCareTeam(CareTeamRequest careTeamInfo) {
        log.debug("createCareTeam() called");
        log.debug("careTeamInfo = " + careTeamInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.careteam);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.POST)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(careTeamInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to create CareTeam with http status code %s and URL endpoint is %s", response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("createCareTeam() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updateCareTeam(String id, PatchOperationCareTeam patchOperationCareTeam) {
        log.debug("updateCareTeam() called");
        log.debug("id = " + id);
        log.debug("patchOperationCareTeam = " + patchOperationCareTeam);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.careteam, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(patchOperationCareTeam))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update CareTeam %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updateCareTeam() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse createPractitioner(PractitionerRequest practitionerRequest) {
        log.debug("createPractitioner() called");
        log.debug("practitionerRequest = " + practitionerRequest);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.practitioner);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.POST)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(practitionerRequest))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to create Practitioner with http status code %s and URL endpoint is %s", response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("createPractitioner() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updatePractitioner(String id, PractitionerRequest practitionerRequest) {
        log.debug("updatePractitioner() called");
        log.debug("id = " + id);
        log.debug("practitionerRequest = " + practitionerRequest);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.practitioner, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(practitionerRequest))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update Practitioner %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updatePractitioner() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updatePatientCallCenterInfo(String id, PatientInfo patientInfo) {
        log.debug("updatePatientCallCenterInfo() called");
        log.debug("id = " + id);
        log.debug("patientInfo = " + patientInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.patientinfo, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(patientInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update Patient Call Center Info %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updatePatientCallCenterInfo() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updatePatient(String id, PatientRequest patientRequest) {
        log.debug("updatePatient() called");
        log.debug("id = " + id);
        log.debug("patientRequest = " + patientRequest);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.patient, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(patientRequest))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update Patient %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updatePatient() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse createCondition(ConditionInfo conditionInfo) {
        log.debug("createCondition() called");
        log.debug("conditionInfo = " + conditionInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.condition);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.POST)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(conditionInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to create Condition with HTTP status code %s and the URL endpoint is %s", response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("createCondition() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updateCondition(String id, ConditionInfo conditionInfo) {
        log.debug("updateCondition() called");
        log.debug("id = " + id);
        log.debug("conditionInfo = " + conditionInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.condition, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(conditionInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update Condition %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updateCondition() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse createEncounter(EncounterInfo encounterInfo) {
        log.debug("createEncounter() called");
        log.debug("encounterInfo = " + encounterInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.encounter);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.POST)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(encounterInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to create Encounter with http status code %s and URL endpoint is %s", response.statusCode(), uri)));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("createEncounter() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updateEncounter(String id, EncounterUpdateInfo encounterUpdateInfo) {
        log.debug("updateEncounter() called");
        log.debug("id = " + id);
        log.debug("encounterUpdateInfo = " + encounterUpdateInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.encounter, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(encounterUpdateInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update Encounter %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updateEncounter() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse createGoal(GoalInfo goalInfo) {
        log.debug("createGoal() called");
        log.debug("goalInfo = " + goalInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.goal);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.POST)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(goalInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to create Goal with http status code %s and URL endpoint is %s", response.statusCode(), uri)));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("createGoal() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updateGoal(String id, GoalInfo goalInfo) {
        log.debug("updateGoal() called");
        log.debug("id = " + id);
        log.debug("goalInfo = " + goalInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.goal, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(goalInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update Goal %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updateGoal() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse createQuestionnaire(NoteTemplateInfo questionnaire) {
        log.debug("createQuestionnaire() called");
        log.debug("questionnaire = " + questionnaire);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.notetemplate);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.POST)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(questionnaire))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to create Questionnaire with http status code %s and URL endpoint is %s", response.statusCode(), uri)));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("createQuestionnaire() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updateQuestionnaire(String id, NoteTemplateRequest questionnaireInfo) {
        log.debug("updateQuestionnaire() called");
        log.debug("id = " + id);
        log.debug("questionnaireInfo = " + questionnaireInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.notetemplate, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(questionnaireInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update Questionnaire %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updateQuestionnaire() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse createQuestionnaireResponse(NoteInfo noteInfo) {
        log.debug("createQuestionnaireResponse() called");
        log.debug("noteInfo = " + noteInfo);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.note);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.POST)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(noteInfo))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to create QuestionnaireResponse with http status code %s and URL endpoint is %s", response.statusCode(), uri)));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("createQuestionnaireResponse() completed");
        return dfApplAPIResponse;
    }

    @Override
    public DFApplAPIResponse updateQuestionnaireResponse(String id, QuestionnaireResponseRequest questionnaireResponseRequest) {
        log.debug("updateQuestionnaireResponse() called");
        log.debug("id = " + id);
        log.debug("questionnaireResponseRequest = " + questionnaireResponseRequest);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(dfCoreIngestControllerBaseUrl)
                .pathSegment(Endpoints.baseVersion, Endpoints.note, id);

        UriComponents uriComponents = uriComponentsBuilder
                .build();

        URI uri = uriComponents.toUri();
        log.debug("uri: " + uri);

        DFApplAPIResponse dfApplAPIResponse = this.webClient
                .method(HttpMethod.PATCH)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(questionnaireResponseRequest))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    logErrorResponse(log, response);
                    return Mono.error(new IllegalStateException(
                            String.format("Failed to update QuestionnaireResponse %s with HTTP status code %s and the URL endpoint is %s", id, response.statusCode(), uri)
                    ));
                })
                .bodyToMono(DFApplAPIResponse.class)
                .block();

        log.debug("dfApplAPIResponse = " + dfApplAPIResponse);
        log.debug("updateQuestionnaireResponse() completed");
        return dfApplAPIResponse;
    }
}