package am.vector.service;

import am.vector.dao.Department;
import am.vector.exception.ServiceException;
import am.vector.model.DepartmentModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentService extends BaseService {
    private Logger log = Logger.getLogger(ContractService.class);

    private Department department;

    @Autowired
    public DepartmentService(Department department) {
        this.department = department;
    }

    public boolean add(DepartmentModel departmentModel){
        try{
            return department.create(departmentModel);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public DepartmentModel get(String code){
        try{
            return department.read(code);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public List<DepartmentModel> getAllDepartments(){
        try{
            return department.getAll();
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public boolean remove(String code){
        try{
            return department.delete(code);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }
}
