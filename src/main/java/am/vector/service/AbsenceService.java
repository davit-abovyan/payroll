package am.vector.service;

import am.vector.dao.Absence;
import am.vector.exception.ServiceException;
import am.vector.model.AbsenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

import java.util.List;

@Service
public class AbsenceService extends BaseService {
    private Logger log = Logger.getLogger(AbsenceService.class);
    private Absence absence;

    @Autowired
    public AbsenceService(Absence absence) {
        this.absence = absence;
    }

    public long add(AbsenceModel absenceModel){
        try{
            return absence.create(absenceModel);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public AbsenceModel get(long id){
        try{
            return absence.read(id);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    private List<AbsenceModel> getAllByEmployeeByPeriod(String period, String ssn){
        try{
            return absence.getAllByEmployeeByPeriod(period,ssn);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    private List<AbsenceModel> getAllByPeriod(String period){
        try{
            return absence.getAlleByPeriod(period);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    private List<AbsenceModel> getAllByEmployee(String ssn){
        try{
            return absence.getAllByEmployee(ssn);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    private List<AbsenceModel> getAllAbsences(){
        try{
            return absence.getAllAbsences();
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public List<AbsenceModel> getAll(String period, String ssn){
        List<AbsenceModel> list;
        if(period == null){
            if(ssn == null){
                list = getAllAbsences();
            } else {
                list = getAllByEmployee(ssn);
            }
        } else if(ssn == null){
            list = getAllByPeriod(period);
        } else {
            list = getAllByEmployeeByPeriod(period, ssn);
        }
        return list;
    }

    public boolean update(AbsenceModel absenceModel){
        try{
            return absence.update(absenceModel);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public boolean remove(long id){
        try{
            return absence.delete(id);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }
}
