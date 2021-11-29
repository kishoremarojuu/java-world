package com.repo.sampleTestCases;

import static com.catasys.pre.dfappl.dfapplsummaryapi.models.constants.SummaryConstants.INTREATMENT;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.catasys.pre.dfappl.dfapplsummaryapi.models.MemberStatusResponse;
import com.catasys.pre.dfappl.dfapplsummaryapi.models.exceptions.MemberNotFoundException;
import com.catasys.pre.dfappl.dfapplsummaryapi.service.interfaces.SalesforceLoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@ContextConfiguration(classes = {MemberStatusServiceImpl.class, String.class, RestTemplate.class})
@ExtendWith(SpringExtension.class)
class MemberStatusServiceImplTest {
    @Autowired
    private MemberStatusServiceImpl memberStatusServiceImpl;

    @MockBean
    private SalesforceLoginService salesforceLoginService;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this); //
        ReflectionTestUtils.setField(memberStatusServiceImpl,"salesforceMemberPMBUrl", "services/data/v52.0");
        ReflectionTestUtils.setField(memberStatusServiceImpl,"salesforceIsMemberEnrolledQuery", "SELECT Master_Status__c from SD_Member__c WHERE eRehab_ID__c");

    }
    @Test
    void testFindMemberStatusForMemberNotFoundException() {
        // Arrange
        when(this.salesforceLoginService.getSalesforceInstanceUrl())
                .thenThrow(new MemberNotFoundException("An error occurred"));
        when(this.salesforceLoginService.getSalesforceAccessToken()).thenReturn("ABC123");

        // Act and Assert
        assertThrows(MemberNotFoundException.class, () -> this.memberStatusServiceImpl.findMemberStatus("E Rehabid"));
        verify(this.salesforceLoginService).getSalesforceAccessToken();
        verify(this.salesforceLoginService).getSalesforceInstanceUrl();
    }

    @Test
    void testFindMemberStatusForInTreatment() {
        // Arrange
        when(this.salesforceLoginService.getSalesforceInstanceUrl()).thenReturn("https://catasys--pmb.my.salesforce.com");
        when(this.salesforceLoginService.getSalesforceAccessToken()).thenReturn("00D530000004eWH!ARgAQJKJPO8OmJy5npMzb9xbKa24LXznHPEBOP07pnMMObdwJ1YkOngfAoxu2yrksQTVJTMXtDhckWd2peuh2PcK7Yu31imj");


        MemberStatusResponse.MemberStatusEnrolledGraduatedResponse m = new MemberStatusResponse.MemberStatusEnrolledGraduatedResponse();
        MemberStatusResponse.Record rc = new MemberStatusResponse.Record();
        rc.setMasterStatusC("In Treatment");
        m.setDone(Boolean.TRUE);
        m.setTotalSize(9L);
        m.setRecords(Collections.singletonList(rc));

        ResponseEntity<MemberStatusResponse.MemberStatusEnrolledGraduatedResponse> serviceResponse = new ResponseEntity<>(m, HttpStatus.OK);


        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<MemberStatusResponse.MemberStatusEnrolledGraduatedResponse>>any()))
                .thenReturn(serviceResponse);


        // Act and Assert
        MemberStatusResponse memberStatusResponse = this.memberStatusServiceImpl.findMemberStatus("236664681");

        Assertions.assertNotNull(memberStatusResponse);
    }


    @Test
    void testFindMemberStatusForGraduated() {
        // Arrange
        when(this.salesforceLoginService.getSalesforceInstanceUrl()).thenReturn("https://catasys--pmb.my.salesforce.com");
        when(this.salesforceLoginService.getSalesforceAccessToken()).thenReturn("00D530000004eWH!ARgAQJKJPO8OmJy5npMzb9xbKa24LXznHPEBOP07pnMMObdwJ1YkOngfAoxu2yrksQTVJTMXtDhckWd2peuh2PcK7Yu31imj");


        MemberStatusResponse.MemberStatusEnrolledGraduatedResponse m = new MemberStatusResponse.MemberStatusEnrolledGraduatedResponse();
        MemberStatusResponse.Record rc = new MemberStatusResponse.Record();
        rc.setMasterStatusC("Graduated");
        m.setDone(Boolean.TRUE);
        m.setTotalSize(9L);
        m.setRecords(Collections.singletonList(rc));

        ResponseEntity<MemberStatusResponse.MemberStatusEnrolledGraduatedResponse> serviceResponse = new ResponseEntity<>(m, HttpStatus.OK);


        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<MemberStatusResponse.MemberStatusEnrolledGraduatedResponse>>any()))
                .thenReturn(serviceResponse);


        // Act and Assert
        MemberStatusResponse memberStatusResponse = this.memberStatusServiceImpl.findMemberStatus("236664681");

        Assertions.assertNotNull(memberStatusResponse);
    }

    @Test
    void testFindMemberStatusForEmptyTotalSize() {
        // Arrange
        when(this.salesforceLoginService.getSalesforceInstanceUrl()).thenReturn("https://catasys--pmb.my.salesforce.com");
        when(this.salesforceLoginService.getSalesforceAccessToken()).thenReturn("00D530000004eWH!ARgAQJKJPO8OmJy5npMzb9xbKa24LXznHPEBOP07pnMMObdwJ1YkOngfAoxu2yrksQTVJTMXtDhckWd2peuh2PcK7Yu31imj");


        MemberStatusResponse.MemberStatusEnrolledGraduatedResponse m = new MemberStatusResponse.MemberStatusEnrolledGraduatedResponse();
        MemberStatusResponse.Record rc = new MemberStatusResponse.Record();
        rc.setMasterStatusC("Graduated");
        m.setDone(Boolean.TRUE);
        m.setTotalSize(0L);
        m.setRecords(Collections.singletonList(rc));

        ResponseEntity<MemberStatusResponse.MemberStatusEnrolledGraduatedResponse> serviceResponse = new ResponseEntity<>(m, HttpStatus.OK);


        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<MemberStatusResponse.MemberStatusEnrolledGraduatedResponse>>any()))
                .thenReturn(serviceResponse);


        // Act and Assert
        Assertions.assertThrows(MemberNotFoundException.class, ()-> this.memberStatusServiceImpl.findMemberStatus("236664681"));


    }


}

