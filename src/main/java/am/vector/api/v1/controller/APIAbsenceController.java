package am.vector.api.v1.controller;

import am.vector.api.v1.APIController;
import am.vector.constants.LeaveType;
import am.vector.model.AbsenceModel;
import am.vector.service.AbsenceService;
import am.vector.util.SafeSet;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1.0/ABSENCE", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIAbsenceController extends APIController {

    AbsenceService absenceService;

    @Autowired
    public void setAbsenceService(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{ID}")
    public ResponseEntity getAbsenceById(@PathVariable int ID){
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(absenceService.get(ID)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity getAllAbsences(){
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(absenceService.getAllAbsences()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/")
    public ResponseEntity getAll(HttpSession session,
                                 @RequestParam(value = "period", required = false) String period,
                                 @RequestParam(value = "ssn", required = false) String ssn){
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(absenceService.getAll(period, ssn)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity addRole(HttpSession session,
                                  @RequestParam("leaveType") String leaveType,
                                  @RequestParam("amount") int amount,
                                  @RequestParam("ssn") String ssn,
                                  @RequestParam("period") String period,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate){
        AbsenceModel object = new AbsenceModel(LeaveType.valueOf(leaveType),ssn,period,amount,
                LocalDate.parse(startDate),LocalDate.parse(endDate));
        absenceService.add(object);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(object), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{ID}")
    public ResponseEntity editRole(HttpSession session,
                                   @RequestParam("leaveType") String leaveType,
                                   @RequestParam("amount") int amount,
                                   @RequestParam("ssn") String ssn,
                                   @RequestParam("period") String period,
                                   @RequestParam("startDate") String startDate,
                                   @RequestParam("endDate") String endDate){
        AbsenceModel object = new AbsenceModel(LeaveType.valueOf(leaveType), ssn, period, amount,
                LocalDate.parse(startDate),LocalDate.parse(endDate));
        absenceService.update(object);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(object), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{ID}")
    public ResponseEntity removeRole(HttpSession session,
                                     @PathVariable int ID){
        return new ResponseEntity(absenceService.remove(ID), HttpStatus.OK);
    }
}
