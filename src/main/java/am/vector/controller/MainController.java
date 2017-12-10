package am.vector.controller;

import am.vector.constants.LeaveType;
import am.vector.model.AbsenceModel;
import am.vector.model.ContractModel;
import am.vector.service.AbsenceService;
import am.vector.service.ContractService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class MainController {
    private Logger log = Logger.getLogger(MainController.class);

//    private AbsenceService absenceService;
    private ContractService contractService;

    @Autowired
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

//    @Autowired
//    public void setAbsenceService(AbsenceService absenceService) {
//        this.absenceService = absenceService;
//    }

    @RequestMapping("/")
    public ModelAndView index(HttpSession session){
//        AbsenceModel absenceModel = new AbsenceModel(LeaveType.MTL,1000, LocalDate.now(),LocalDate.now());
//        long id = absenceService.add(absenceModel);
//        boolean deleted = absenceService.remove(id);
//
//        ContractModel contractModel = new ContractModel();
//
        ModelAndView modelAndView = new ModelAndView("index");
//        modelAndView.addObject("arg","Davit: "+id+" removed: "+deleted);
        return modelAndView;
    }
}
