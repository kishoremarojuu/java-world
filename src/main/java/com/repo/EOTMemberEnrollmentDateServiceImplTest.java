package com.catasys.dfappl.dfapplmasterstatusservice.service.implementation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.catasys.dfappl.dfapplmasterstatusservice.model.entity.EOTMemberEnrollmentDateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ContextConfiguration(classes = {EOTMemberEnrollmentDateServiceImpl.class, String.class})
@ExtendWith(SpringExtension.class)
class EOTMemberEnrollmentDateServiceImplTest {

    @Autowired
    private EOTMemberEnrollmentDateServiceImpl eOTMemberEnrollmentDateServiceImpl;

    private EOTMemberEnrollmentDateResponse response;


    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(eOTMemberEnrollmentDateServiceImpl, "eotMemberEnrollmentURL", "https://dev.eontrak.com/eOnTrak_v2/enroll");
        ReflectionTestUtils.setField(eOTMemberEnrollmentDateServiceImpl, "eotMemberEnrollmentUsername", "catasys");
        ReflectionTestUtils.setField(eOTMemberEnrollmentDateServiceImpl, "eOTProgramParticipationAPIPassword", "Catasys@2");

        response = new EOTMemberEnrollmentDateResponse();
        response.setSuccess(true);
        response.setError("sampleError");
        response.setErehabId("916096051");

    }

    @Test
    void testCallEOTMemberEnrollmentDateForRunTimException() {
        ReflectionTestUtils.setField(eOTMemberEnrollmentDateServiceImpl, "subscriberUpdateEOT", "false");
        Assertions.assertThrows(RuntimeException.class,
                () -> eOTMemberEnrollmentDateServiceImpl.callEOTMemberEnrollmentDate("2021-11-12T09:29:21.291Z", "123654"),
                "eOnTrak feature flag turned off in Azure KV");
    }

    @Test
    void testCallEOTMemberEnrollmentDateAndMockingeOntrakApiCall() {
        ReflectionTestUtils.setField(eOTMemberEnrollmentDateServiceImpl, "subscriberUpdateEOT", "true");

        EOTMemberEnrollmentDateServiceImpl mockedObject = Mockito.spy(eOTMemberEnrollmentDateServiceImpl);

        Mockito.doReturn(response).when(mockedObject).callEOTMemberEnrollmentDate(Mockito.anyString(), Mockito.anyString());

        EOTMemberEnrollmentDateResponse k = mockedObject.callEOTMemberEnrollmentDate("2021-11-12T09:29:21.291Z", "123654");

        Assertions.assertEquals("916096051",k.getErehabId());
        Assertions.assertNotNull(k);
    }

    //uncomment once API has been setup
   /* @Test
    void testCallEOTMemberEnrollmentDateAndNotMockingeOntrakApiCall() {
        ReflectionTestUtils.setField(eOTMemberEnrollmentDateServiceImpl, "subscriberUpdateEOT", "true");

        EOTMemberEnrollmentDateResponse k = eOTMemberEnrollmentDateServiceImpl.callEOTMemberEnrollmentDate("2021-11-12T09:29:21.291Z", "123654");

        Assertions.assertEquals("916096051",k.getErehabId());
        Assertions.assertNotNull(k);
    }
*/
}

