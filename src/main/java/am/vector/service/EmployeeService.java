package am.vector.service;

import am.vector.dao.Employee;
import am.vector.exception.ServiceException;
import am.vector.model.DepartmentModel;
import am.vector.model.EmployeeModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private Logger log = Logger.getLogger(EmployeeService.class);

    private Employee employee;

    @Autowired
    public EmployeeService(Employee employee) {
        this.employee = employee;
    }

    public boolean add(EmployeeModel employeeModel){
        try{
            return employee.create(employeeModel);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public EmployeeModel get(String code){
        try{
            return employee.read(code);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    /**
     * Returns all employees of all department
     */
    public List<EmployeeModel> getAll(){
        return getAll(true,null);
    }

    /**
     * Returns all employees of provided department
     * @param departmentCode the code of department
     */
    public List<EmployeeModel> getAll(String departmentCode){
        return getAll(true,departmentCode);
    }

    /**
     * Returns all current employees of all department
     */
    public List<EmployeeModel> getCurrentAll(){
        return getAll(false,null);
    }

    /**
     * Returns current employees of provided department
     * @param departmentCode the code of department
     */
    public List<EmployeeModel> getCurrentAll(String departmentCode){
        return getAll(false,departmentCode);
    }

    /**
     * Return list of employees
     * @param includeTerminated include employees who's contract was terminated
     * @param departmentCode code of department which employees should be included or null for all departments
     * @return list of employees
     */
    private List<EmployeeModel> getAll(boolean includeTerminated, String departmentCode){
        try{
            if(departmentCode == null)
                if(includeTerminated)
                    return employee.getAll();
                else
                    return employee.getAllCurrent();
            else
                if(includeTerminated)
                    return employee.getAllByDepartment(departmentCode);
                else
                    return employee.getAllCurrentByDepartment(departmentCode);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error: ");
        }
    }

    public boolean update(EmployeeModel departmentModel){
        try{
            return employee.update(departmentModel);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public boolean remove(String code){
        try{
            return employee.delete(code);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }
}
