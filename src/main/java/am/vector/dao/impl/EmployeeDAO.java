package am.vector.dao.impl;

import am.vector.dao.Employee;
import am.vector.dao.impl.mapper.EmployeeRowMapper;
import am.vector.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Component
public class EmployeeDAO extends NamedParameterJdbcDaoSupport implements Employee {

    @Autowired
    public EmployeeDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * @see Employee#create(am.vector.model.EmployeeModel)
     */
    @Override
    public boolean create(EmployeeModel employeeModel) {

        final String query = "INSERT  INTO employee (ssn, full_name, email, birthday, department_code)" +
                " VALUES ( ?, ?, ?, ?, ?)";
        return getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, employeeModel.getSSN());
            ps.setString(2, employeeModel.getFullName());
            ps.setString(3,employeeModel.getEmail());
            ps.setDate(4, java.sql.Date.valueOf(employeeModel.getBirthDay()));
            ps.setString(5, employeeModel.getDepartmentCode());
            return ps;
        }) == 1;
    }

    /**
     * @see Employee#read(java.lang.String)
     */
    @Override
    public EmployeeModel read(String ssn) {
        final String query = "SELECT * FROM employee WHERE ssn = ? LIMIT 1";
        return getJdbcTemplate().queryForObject(query, new Object[]{ssn},new EmployeeRowMapper());
    }

    /**
     * @see Employee#getAllByDepartment(java.lang.String)
     */
    @Override
    public List<EmployeeModel> getAllByDepartment(String departmentCode) {
        return null;
    }

    /**
     * @see Employee#update(am.vector.model.EmployeeModel)
     */
    @Override
    public boolean update(EmployeeModel employeeModel) {
        final String query = "UPDATE employee SET full_name = ?, email = ?, birthday = ?, department_code = ? " +
                " WHERE ssn = ?";
        return getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, employeeModel.getFullName());
            ps.setString(2,employeeModel.getEmail());
            ps.setDate(3, java.sql.Date.valueOf(employeeModel.getBirthDay()));
            ps.setString(4, employeeModel.getDepartmentCode());
            ps.setString(5, employeeModel.getSSN());
            return ps;
        }) == 1;
    }

    /**
     * @see Employee#delete(java.lang.String)
     */
    @Override
    public boolean delete(String ssn) {
        final String query = "DELETE FROM employee WHERE ssn = ?";
        return getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, ssn);
            return ps;
        }) == 1;
    }
}
