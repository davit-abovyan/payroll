package am.vector.api.v1.controller;

import am.vector.api.v1.APIController;
import am.vector.model.ContractModel;
import am.vector.service.ContractService;
import am.vector.util.SafeSet;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1.0/CONTRACT", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIContractController extends APIController{

    ContractService contractService;

    @Autowired
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{ID}")
    public ResponseEntity getContract(@PathVariable int ID){
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(contractService.get(ID)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{SSN}")
    public ResponseEntity getEmployeeCurrentContract(@PathVariable String SSN){
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(contractService.getEmployeeCurrent(SSN)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity addRole(HttpSession session,
                                  @RequestParam("SSN") String ssn,
                                  @RequestParam("roleId") long roleId,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("salary") int salary){
        ContractModel object = new ContractModel(ssn,roleId, SafeSet.date(startDate),salary);
        contractService.add(object);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(object), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{ID}")
    public ResponseEntity editRole(HttpSession session,
                                   @RequestParam("SSN") String ssn,
                                   @RequestParam("roleId") long roleId,
                                   @RequestParam("startDate") String startDate,
                                   @RequestParam(value = "endDate", required = false) String endDate,
                                   @RequestParam("salary") int salary){
        ContractModel object = new ContractModel(ssn,roleId, SafeSet.date(startDate), SafeSet.date(endDate),salary);
        contractService.update(object);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(object), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{ID}")
    public ResponseEntity removeRole(HttpSession session,
                                     @PathVariable int ID){
        return new ResponseEntity(contractService.remove(ID), HttpStatus.OK);
    }
}
