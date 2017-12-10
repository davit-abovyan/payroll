package am.vector.service;

import am.vector.dao.Absence;
import am.vector.dao.Contract;
import am.vector.exception.ServiceException;
import am.vector.model.AbsenceModel;
import am.vector.model.ContractModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContractService extends BaseService {
    private Logger log = Logger.getLogger(ContractService.class);

    private Contract contract;

    @Autowired
    public ContractService(Contract contract) {
        this.contract = contract;
    }

    public long add(ContractModel contractModel){
        try{
            if(!contract.isInRange(contractModel.getRoleId(), contractModel.getSalary()))
                throw new ServiceException("The salary " + contractModel.getSalary() +
                " is not in the range of allowed salaries for role " + contractModel.getRoleId());
            return contract.create(contractModel);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public ContractModel get(long id){
        try{
            return contract.read(id);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }


    public boolean update(ContractModel contractModel){
        try{
            return contract.update(contractModel);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public ContractModel getEmployeeCurrent(String ssn){
        try{
            return contract.getEmployeeCurrent(ssn);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public boolean remove(long id){
        try{
            return contract.delete(id);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }
}
