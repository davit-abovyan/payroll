package am.vector.api.v1.controller;

import am.vector.service.AbsenceService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/v1.0/PAYSLIP", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIPaySlipController {

    AbsenceService absenceService;

    @Autowired
    public void setAbsenceService(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/calculate")
    public ResponseEntity getAbsenceById(HttpSession session,
                                         @RequestParam("period") String period,
                                         @RequestParam("ssn") String ssn){
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(""), HttpStatus.OK);
    }
}
