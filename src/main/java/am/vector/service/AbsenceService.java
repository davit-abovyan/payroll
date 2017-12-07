package am.vector.service;

import am.vector.dao.Absence;
import am.vector.exception.ServiceException;
import am.vector.model.AbsenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

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

    public boolean remove(long id){
        try{
            return absence.delete(id);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }
}
