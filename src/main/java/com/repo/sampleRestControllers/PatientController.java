package sampleRestControllers;

import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.ontrak.dfappl.dfapplpatientapi.model.ApiError;
import com.ontrak.dfappl.dfapplpatientapi.model.DFApplResponse;
import com.ontrak.dfappl.dfapplpatientapi.model.PatientRequest;
import com.ontrak.dfappl.dfapplpatientapi.service.interfaces.PatientService;
import org.hl7.fhir.exceptions.FHIRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PatientService patientService;

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<DFApplResponse> updatePatientPatch(
            @PathVariable("id") @NotNull String id,
            @RequestBody PatientRequest patientRequest
    )
    {
        log.info("updatePatientPatch() called");

        DFApplResponse result = null;

        try {
            result = patientService.updatePatientPatch(id, patientRequest);
            log.info("result = " + result);
        }
        catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            result = new DFApplResponse();
            ApiError apiError = new ApiError();
            apiError.setStatus("NOT_FOUND");
            apiError.setMessage(String.format("The patient with ID %s could not be found", id));
            result.setApierror(apiError);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        catch (FHIRException e) {
            String error = e.getMessage();
            log.error(error);
            result = new DFApplResponse();
            ApiError apiError = new ApiError();
            apiError.setStatus("BAD_REQUEST");
            apiError.setMessage(error);
            result.setApierror(apiError);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        catch (InvalidRequestException e) {
            String error = e.getMessage();
            log.error(error);
            result = new DFApplResponse();
            ApiError apiError = new ApiError();
            apiError.setStatus("BAD_REQUEST");
            apiError.setMessage(error);
            result.setApierror(apiError);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            String error = e.getMessage();
            log.error(error);
            result = new DFApplResponse();
            ApiError apiError = new ApiError();
            apiError.setStatus("BAD_REQUEST");
            apiError.setMessage(error);
            result.setApierror(apiError);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        log.info("updatePatientPatch() completed");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
