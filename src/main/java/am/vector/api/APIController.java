package am.vector.api;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0")
public class APIController {
    protected Logger LOGGER = Logger.getLogger(APIController.class);

    @RequestMapping("/get")
    public ResponseEntity getEmployeesList(){
        return new ResponseEntity("The list", HttpStatus.OK);
    }
}
