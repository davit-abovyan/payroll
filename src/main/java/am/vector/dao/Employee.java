package am.vector.dao;

import am.vector.model.EmployeeModel;

import java.util.List;

public interface Employee {

    /**
     * Record a new employee in DB
     * @param
     * @return true if the employee is created successfully
     */
    public boolean create(EmployeeModel absenceModel);

    /**
     * Return a employee recorded in DB with provided ssn
     * @param ssn the social security number corresponding to employee
     * @return the employee with specified id
     */
    public EmployeeModel read(String ssn);

    /**
     * Returns all current employees within the department with provided id
     * @param departmentCode
     * @return
     */
    public List<EmployeeModel> getAllByDepartment(String departmentCode);

    /**
     * Updated the recorder employee in DB
     * @param
     * @return true if update is successfully applied, otherwise false
     */
    public boolean update(EmployeeModel absenceModel);

    /**
     * Removes an employee recoded from DB with provided ssn
     * @param ssn the social security number corresponding to employee
     * @return true if the employee is removed successfully
     */
    public boolean delete(String ssn);
}
