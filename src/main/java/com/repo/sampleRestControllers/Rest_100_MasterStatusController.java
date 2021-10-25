package sampleRestControllers;


import com.catasys.dfappl.dfapplmasterstatusservice.exception.MasterStatusNotFoundException;
import com.catasys.dfappl.dfapplmasterstatusservice.model.entity.MemberStatus;
import com.catasys.dfappl.dfapplmasterstatusservice.model.entity.OnTrakMember;
import com.catasys.dfappl.dfapplmasterstatusservice.model.entity.PatientsFHIR;
import com.catasys.dfappl.dfapplmasterstatusservice.model.entity.structure.GenericResponse;
import com.catasys.dfappl.dfapplmasterstatusservice.model.entity.structure.PatchOperation;
import com.catasys.dfappl.dfapplmasterstatusservice.repository.OnTrakMemberRepository;
import com.catasys.dfappl.dfapplmasterstatusservice.repository.PatientsFHIRRepository;
import com.catasys.dfappl.dfapplmasterstatusservice.service.interfaces.MasterStatusService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.catasys.dfappl.dfapplmasterstatusservice.model.consts.ServiceConst.PATIENTMONGOID;
import static com.catasys.dfappl.dfapplmasterstatusservice.model.consts.ServiceConst.EREHABID;

@RestController
@Valid
@RequestMapping("/v1/masterstatus")
public class Rest_100_MasterStatusController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PatientsFHIRRepository patientsFHIRRepository;

    @Autowired
    private OnTrakMemberRepository onTrakMemberRepository;

    @Autowired
    private MasterStatusService masterStatusService;

    @ApiOperation(value = "Get All Member Status", response = List.class)
    @RequestMapping(method = RequestMethod.GET)
    public List<OnTrakMember> getAllOnTrakMembers(
            @ApiParam(value = "User Name") @RequestParam(value = "userName", required = true) String userName

    ) {
        log.debug("getAllOnTrakMembers() start");
        Long secondsStart = System.currentTimeMillis() / 1000;
        List<OnTrakMember> onTrakMembers = onTrakMemberRepository.findAll();
        if (onTrakMembers != null) {

            Long secondsEnd = System.currentTimeMillis() / 1000;
            log.debug("getAllOnTrakMembers() end took [" + (secondsEnd - secondsStart) + "] secs for [" + onTrakMembers.size() + "]");
            return onTrakMembers;
        } else {
            throw new MasterStatusNotFoundException("No ontrakMembers found");
        }

    }

    @ApiOperation(value = "Get onTrakMember by eRehabId or patientMongoId", response = MemberStatus.class)
    @RequestMapping(value = ("/{id}"), method = RequestMethod.GET)
    public OnTrakMember getOnTrakMember(
            @ApiParam(value = "User Name") @RequestParam(value = "userName", required = true) String userName,
            @ApiParam(value = "MemId") @PathVariable(value = "id") String id,
            @ApiParam(value = "MemId Type") @RequestParam(value = "idType", required = false) String idType
    ) {
        log.debug("getOnTrakMember() start");
        Long secondsStart = System.currentTimeMillis() / 1000;

        OnTrakMember onTrakMember = null;
        PatientsFHIR patientsFHIR;

        if ((id != null && idType == null) || idType != null && idType.equalsIgnoreCase(EREHABID)) {
            patientsFHIR = patientsFHIRRepository.findFirstByIdentifierValue(id);
            if (patientsFHIR != null) {
                onTrakMember = onTrakMemberRepository.findFirstByMemberReference(patientsFHIR.get_id().toString());
            } else {
                log.error("Patient with eRehabId [ {}] not found", id);
                throw new MasterStatusNotFoundException("Patient with eRehabId  [" + id + "] not found");
            }
        }
        else if (idType != null && idType.equalsIgnoreCase(PATIENTMONGOID)) {
            onTrakMember = onTrakMemberRepository.findFirstByMemberReference(id);
        }

        if (onTrakMember != null) {
            Long secondsEnd = System.currentTimeMillis() / 1000;
            log.debug("getOnTrakMember() end took [{}] secs", secondsEnd - secondsStart);
            return onTrakMember;
        } else {
            log.error("Patient with eRehabId or patientMongoId [ {}] not found", id);
            throw new MasterStatusNotFoundException("onTrakMember for eRehabId  or patientMongoId  [" + id + "] is not found");
        }
    }


    @ApiOperation(value = "Create OnTrakMember Master Status", response = MemberStatus.class)
    @RequestMapping(value = "/{eRehabId}", method = RequestMethod.POST)
    public OnTrakMember createOnTrakMember(
            @ApiParam(value = "User Name") @RequestParam(value = "userName", required = true) String userName,
            @ApiParam(value = "Patient's dRehabId") @PathVariable("eRehabId") @NotNull String eRehabId,
            @ApiParam(value = "Member Status Body") @RequestBody @NotNull MemberStatus memberStatus

    ) {
        log.info("createOnTrakMember() called");

        OnTrakMember onTrakMember = masterStatusService.createOnTrakMember(eRehabId, memberStatus);

        log.debug("onTrakMember = " + onTrakMember);
        log.info("createOnTrakMember() completed");
        return onTrakMember;
    }


    @ApiOperation(value = "PATCH Update OnTrak Member info", response = List.class, produces = "application/json")
    @RequestMapping(method = RequestMethod.PATCH)
    public List<GenericResponse> updateOnTrakMemberPatch(
            @ApiParam(value = "User Name") @RequestParam(value = "userName", required = true) String userName,
            @ApiParam(value = "Patient's id") @RequestParam("id") @NotNull String id,
            @ApiParam(value = "Patch Request Body") @RequestBody @NotNull PatchOperation patchRequest

    ) {
        log.debug("updateOnTrakMemberPatch() called");

        List<GenericResponse> responseList = masterStatusService.updateOnTrakMemberPatch(patchRequest);

        log.debug("responseList = " + responseList);
        log.debug("updateOnTrakMemberPatch() completed");
        return responseList;
    }

    @ApiOperation(value = "Update OnTrak Member Master Status", response = MemberStatus.class)
    @RequestMapping(value = "/{eRehabId}", method = RequestMethod.PUT)
    public OnTrakMember updateOnTrakMember(
            @ApiParam(value = "User Name") @RequestParam(value = "userName", required = true) String userName,
            @ApiParam(value = "Patient's dRehabId") @PathVariable("eRehabId") @NotNull String eRehabId,
            @ApiParam(value = "Member Status Body") @RequestBody @NotNull MemberStatus memberStatus

    ) {
        log.debug("updateMemberStatus() start");
        OnTrakMember onTrakMember = masterStatusService.updateOnTrakMember(eRehabId, memberStatus);
        return onTrakMember;
    }

    @ApiOperation(value = "Delete Member Status", response = ResponseEntity.class)
    @RequestMapping(value = "/{eRehabId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOnTrakMember(
            @ApiParam(value = "User Name") @RequestParam(value = "userName", required = true) String userName,
            @ApiParam(value = "Patient's dRehabId") @PathVariable("eRehabId") @NotNull String eRehabId
    ) {
        log.debug("deleteOnTrakMember() start");
        Long secondsStart = System.currentTimeMillis() / 1000;
        OnTrakMember onTrakMember;
        PatientsFHIR patientsFHIR = patientsFHIRRepository.findFirstByIdentifierValue(eRehabId);

        if (patientsFHIR != null) {

            onTrakMember = onTrakMemberRepository.findFirstByMemberReference(patientsFHIR.get_id().toString());

            if (onTrakMember != null) {
                onTrakMemberRepository.delete(onTrakMember);
                Long secondsEnd = System.currentTimeMillis() / 1000;
                log.debug("deleteOnTrakMember() end took [" + (secondsEnd - secondsStart) + "] secs");
                return ResponseEntity.ok().build();
            } else {
                log.error("OnTrakMember Master Status for eRehabId [" + eRehabId + "] is not found");
                throw new MasterStatusNotFoundException("OnTrakMember Master Status for eRehabId [" + eRehabId + "] is not found");
            }

        } else {
            log.error("Patient with eRehabId [" + eRehabId + "] not found");
            throw new MasterStatusNotFoundException("Patient with eRehabId  [" + eRehabId + "] not found");

        }
    }

}
