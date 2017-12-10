package am.vector.api.v1.controller;

import am.vector.api.v1.APIController;
import am.vector.model.RoleModel;
import am.vector.service.RoleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1.0/ROLE", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIRoleController extends APIController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{ID}")
    public ResponseEntity getRole(@PathVariable int ID){
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(roleService.get(ID)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity getAllRoles(){
        Gson gson = new Gson();
        List<RoleModel> list = roleService.getAll();
        return new ResponseEntity(gson.toJson(list), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity addRole(HttpSession session,
                                  @RequestParam("positionName") String positionName,
                                  @RequestParam(value = "description", required = false) String description,
                                  @RequestParam("salaryRangeTop") int salaryRangeTop,
                                  @RequestParam("salaryRangeBottom") int salaryRangeBottom){
        RoleModel object = new RoleModel(positionName, description, salaryRangeTop, salaryRangeBottom);
        roleService.add(object);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(object), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{ID}")
    public ResponseEntity editRole(HttpSession session,
                                   @RequestParam("positionName") String positionName,
                                   @RequestParam(value = "description", required = false) String description,
                                   @RequestParam("salaryRangeTop") int salaryRangeTop,
                                   @RequestParam("salaryRangeBottom") int salaryRangeBottom){
        RoleModel object = new RoleModel(positionName, description, salaryRangeTop, salaryRangeBottom);
        roleService.update(object);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(object), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{ID}")
    public ResponseEntity removeRole(HttpSession session,
                                           @PathVariable int ID){
        return new ResponseEntity(roleService.remove(ID), HttpStatus.OK);
    }
}
