package am.vector.dao;

import am.vector.model.DepartmentModel;

import java.util.List;

public interface Department {
    /**
     * Record a new department in DB
     * @param
     * @return true if new department is created otherwise false
     */
    public boolean create(DepartmentModel absenceModel);

    /**
     * Return a department recorded in DB with provided id
     * @param departmentCode corresponding department code
     * @return the department with specified code
     */
    public DepartmentModel read(String departmentCode);

    /**
     * Return all registered departments
     * @return list of departments
     */
    public List<DepartmentModel> getAll();
    /**
     * Updated the recorder department in DB
     * @param
     * @return true if update is successfully applied, otherwise false
     */
    public boolean update(DepartmentModel absenceModel);

    /**
     * Removes an department recoded from DB with provided id
     * @param department_code code corresponding to department
     * @return true if department is removed successfully
     */
    public boolean delete(String department_code);
}
