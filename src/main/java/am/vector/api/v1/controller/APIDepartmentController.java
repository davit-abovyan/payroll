package am.vector.api.v1.controller;

import am.vector.api.v1.APIController;
import am.vector.model.DepartmentModel;
import am.vector.service.DepartmentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1.0/department", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIDepartmentController extends APIController{

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{ID}")
    public ResponseEntity getDepartment(@PathVariable String ID){
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(departmentService.get(ID)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity getAllDepartments(){
        Gson gson = new Gson();
        List<DepartmentModel> list = departmentService.getAllDepartments();
        return new ResponseEntity(gson.toJson(list), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity addDepartment(HttpSession session,
                                       @RequestParam("code") String code,
                                       @RequestParam("name") String name,
                                        @RequestParam(value = "cost", required = false) String cost){
        DepartmentModel dep = new DepartmentModel(code, name, cost);
        departmentService.add(dep);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(dep), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{ID}")
    public ResponseEntity editDepartment(HttpSession session,
                                        @PathVariable String ID,
                                        @RequestParam("name") String name,
                                        @RequestParam("cost") String cost){
        DepartmentModel dep = new DepartmentModel(ID, name, cost);
        departmentService.update(dep);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(dep), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{ID}")
    public ResponseEntity removeDepartment(HttpSession session,
                                         @PathVariable String ID){
        return new ResponseEntity(departmentService.remove(ID), HttpStatus.OK);
    }
}
