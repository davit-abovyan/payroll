package am.vector.api.v1.controller;

import am.vector.api.v1.APIController;
import am.vector.model.EmployeeModel;
import am.vector.service.EmployeeService;
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
@RequestMapping(value = "/api/v1.0/EMPLOYEE", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIEmployeeController extends APIController {
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{ID}")
    public ResponseEntity getEmployee(@PathVariable String ID){
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(employeeService.get(ID)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity getAllEmployees(){
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(employeeService.getAll()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity getEmployees(HttpSession session,
                                       @RequestParam("department") String department,
                                       @RequestParam("isCurrent") boolean isCurrent){
        Gson gson = new Gson();
        List<EmployeeModel> list = employeeService.getAll(department);
        return new ResponseEntity(gson.toJson(list), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity addEmployee(HttpSession session,
                                        @RequestParam("ssn") String ssn,
                                        @RequestParam("fullName") String fullName,
                                        @RequestParam(value = "email", required = false) String email,
                                        @RequestParam(value = "birthday", required = false) String birthday,
                                        @RequestParam("departmentCode") String departmentCode,
                                        @RequestParam("hireDate") String hireDate){

        EmployeeModel object = new EmployeeModel(ssn, fullName, email, SafeSet.date(birthday),
                departmentCode, SafeSet.date(hireDate));

        employeeService.add(object);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(object), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{ID}")
    public ResponseEntity editDepartment(HttpSession session,
                                         @PathVariable String ID,
                                         @RequestParam("name") String name,
                                         @RequestParam("cost") String cost){
        EmployeeModel dep = new EmployeeModel(ID, name, cost);
        employeeService.update(dep);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(dep), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{ID}")
    public ResponseEntity removeDepartment(HttpSession session,
                                           @PathVariable String ID){
        return new ResponseEntity(employeeService.remove(ID), HttpStatus.OK);
    }

}
