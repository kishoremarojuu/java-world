package com.catasys.dfappl.dfapplmasterstatusservice.controller;

import com.catasys.dfappl.dfapplmasterstatusservice.model.entity.MemberStatus;
import com.catasys.dfappl.dfapplmasterstatusservice.model.entity.OnTrakMember;
import com.catasys.dfappl.dfapplmasterstatusservice.model.entity.PatientsFHIR;
import com.catasys.dfappl.dfapplmasterstatusservice.model.entity.structure.*;
import com.catasys.dfappl.dfapplmasterstatusservice.repository.OnTrakMemberRepository;
import com.catasys.dfappl.dfapplmasterstatusservice.repository.PatientsFHIRRepository;
import com.catasys.dfappl.dfapplmasterstatusservice.service.interfaces.MasterStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MasterStatusController.class)
public class MasterStatusControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientsFHIRRepository patientsFHIRRepository;

    @MockBean
    private OnTrakMemberRepository onTrakMemberRepository;

    @MockBean
    private MasterStatusService masterStatusService;

    String eRehabId = "967260160";
    String eRehabIdLong = "967260160";
    List<OnTrakMember> onTrakMembers = new ArrayList<>();
    OnTrakMember onTrakMember1 = new OnTrakMember();
    PatientsFHIR patientsFHIR1 = new PatientsFHIR();
    MemberStatus validMemberStatus = new MemberStatus();
    PatchOperation patchOperation = new PatchOperation();
    String mongoId = "5df129307704224577c225b0";
    List<GenericResponse> genericResponses = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        onTrakMember1.setMemberOptOut(false);
        Meta meta = new Meta();
        meta.setCreatedDateTimeUtc(new Date());
        meta.setLastUpdatedDateTimeUtc(new Date());
        Member member = new Member();
        member.setReference("reference");
        member.setType("type");
        Status status = new Status();
        status.setPhase("Pre-Enrollment");
        status.setStatus("Return to Outreach");
        status.setSubStatus("Financial");
        onTrakMember1.setCurrentMasterMemberStatus(status);
        onTrakMember1.setMember(member);
        onTrakMember1.setMeta(meta);
        onTrakMember1.setOutreachEligible(true);
        onTrakMembers.add(onTrakMember1);

        patientsFHIR1.set_id(new ObjectId(mongoId));
        patientsFHIR1.setActive(true);

        validMemberStatus.setMemberOptOut(false);
        validMemberStatus.setOutreachEligible(true);
        patchOperation.setIdType("erehabid");
        patchOperation.setOpEntityKey(eRehabIdLong);
        Operation[] operations = new Operation[2];
        Operation operationPhase = new Operation();
        operationPhase.setOperation("update");
        operationPhase.setEntity("phase");
        operationPhase.setValue("Pre-Enrollment");

        Operation operationOutreachholdenddate = new Operation();
        operationOutreachholdenddate.setOperation("update");
        operationOutreachholdenddate.setEntity("outreachholdenddate");
        operationOutreachholdenddate.setValue("2020-10-23T00:00:00.000Z");
        operations[1] = operationPhase;
        patchOperation.setOperation(operations);

        // genericResponses
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setEntity("phase");
        genericResponse.setMsg("phase updated successfully");
        genericResponse.setSuccess(true);
        genericResponses.add(genericResponse);
    }

    @Test
    public void test_getAllOnTrakMembers() throws Exception {
        Mockito.when(onTrakMemberRepository.findAll()).thenReturn(onTrakMembers);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/v1/masterstatus?userName=test@test.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }

    @Test
    public void test_getAllOnTrakMembers_404() throws Exception {
        Mockito.when(onTrakMemberRepository.findAll()).thenReturn(null);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/v1/masterstatus?userName=test@test.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }

    @Test
    public void test_getOnTrakMember_PatientNotFound() throws Exception {
      eRehabId = "sampleRehabid";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/v1/masterstatus/" + eRehabId + "?userName=test@test.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }

    @Test
    public void test_getOnTrakMember_OnTrakMemberNotFound() throws Exception {
        Mockito.when(patientsFHIRRepository.findFirstByIdentifierValue(anyString())).thenReturn(patientsFHIR1);
        Mockito.when(onTrakMemberRepository.findFirstByMemberReference(anyString())).thenReturn(null);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/v1/masterstatus/" + eRehabId + "?userName=test@test.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }

    @Test
    public void test_getOnTrakMember() throws Exception {
        Mockito.when(patientsFHIRRepository.findFirstByIdentifierValue(anyString())).thenReturn(patientsFHIR1);
        Mockito.when(onTrakMemberRepository.findFirstByMemberReference(anyString())).thenReturn(onTrakMember1);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/v1/masterstatus/" + eRehabId + "?userName=test@test.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }

    @Test
    public void test_getOnTrakMemberWithRehabidID() throws Exception {
        Mockito.when(patientsFHIRRepository.findFirstByIdentifierValue(anyString())).thenReturn(patientsFHIR1);
        Mockito.when(onTrakMemberRepository.findFirstByMemberReference(anyString())).thenReturn(onTrakMember1);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/v1/masterstatus/" + eRehabId + "?userName=test@test.com" + "&idType=eRehabid")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }

    @Test
    public void test_getOnTrakMemberWithPatientMongoID() throws Exception {
        Mockito.when(patientsFHIRRepository.findFirstByIdentifierValue(anyString())).thenReturn(patientsFHIR1);
        Mockito.when(onTrakMemberRepository.findFirstByMemberReference(anyString())).thenReturn(onTrakMember1);

        String patientMongoId="60bb185bceeb515ae40f38c6";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/v1/masterstatus/" + patientMongoId + "?userName=test@test.com" + "&idType=patientMongoId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }


    @Test
    public void test_createOnTrakMember() throws Exception {
        Mockito.when(masterStatusService.createOnTrakMember(anyString(), any())).thenReturn(onTrakMember1);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/v1/masterstatus/" + eRehabId + "?userName=test@test.com")
                .content(asJsonString(validMemberStatus))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }

    @Test
    public void test_updateOnTrakMemberPatch() throws Exception {
        Mockito.when(masterStatusService.updateOnTrakMemberPatch(any())).thenReturn(genericResponses);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .patch("/v1/masterstatus?userName=test@test.com&id=967260160")
                .content(asJsonString(patchOperation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }

    @Test
    public void test_updateOnTrakMember() throws Exception {
        Mockito.when(patientsFHIRRepository.findFirstByIdentifierValue(anyString())).thenReturn(patientsFHIR1);
        Mockito.when(onTrakMemberRepository.findFirstByMemberReference(anyString())).thenReturn(onTrakMember1);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .put("/v1/masterstatus/" + eRehabId + "?userName=test@test.com")
                .content(asJsonString(validMemberStatus))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isEmpty();
    }

    @Test
    public void test_deleteOnTrakMember_PatientNotFound() throws Exception {
        Mockito.when(onTrakMemberRepository.findFirstByMemberReference(anyString())).thenReturn(onTrakMember1);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .delete("/v1/masterstatus/" + eRehabId + "?userName=test@test.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }

    @Test
    public void test_deleteOnTrakMember() throws Exception {
        Mockito.when(patientsFHIRRepository.findFirstByIdentifierValue(anyString())).thenReturn(patientsFHIR1);
        Mockito.when(onTrakMemberRepository.findFirstByMemberReference(anyString())).thenReturn(onTrakMember1);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .delete("/v1/masterstatus/" + eRehabId + "?userName=test@test.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isEmpty();
    }

    @Test
    public void test_deleteOnTrakMember_OnTrakMemberNotFound() throws Exception {
        Mockito.when(patientsFHIRRepository.findFirstByIdentifierValue(anyString())).thenReturn(patientsFHIR1);
        Mockito.when(onTrakMemberRepository.findFirstByMemberReference(anyString())).thenReturn(null);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .delete("/v1/masterstatus/" + eRehabId + "?userName=test@test.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Assertions.assertThat(resultContent).isNotEmpty();
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
