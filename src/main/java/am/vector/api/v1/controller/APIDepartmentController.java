package am.vector.api.v1.controller;

import am.vector.api.v1.APIController;
import am.vector.model.DepartmentModel;
import am.vector.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1.0/department")
public class APIDepartmentController extends APIController{

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{ID}")
    public ResponseEntity getDepartment(@PathVariable String ID){
        String name = departmentService.get(ID).getDepartment_name();
        return new ResponseEntity("The name "+name, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity addDepartment(HttpSession session,
                                       @RequestParam("code") String code,
                                       @RequestParam("name") String name,
                                        @RequestParam("cost") String cost){
        departmentService.add(new DepartmentModel(code, name, cost));
        return new ResponseEntity("Code :" + code + " name: "+name, HttpStatus.OK);
    }
}
