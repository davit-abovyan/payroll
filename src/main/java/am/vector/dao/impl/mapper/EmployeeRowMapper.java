package am.vector.dao.impl.mapper;

import am.vector.model.EmployeeModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<EmployeeModel> {
    @Override
    public EmployeeModel mapRow(ResultSet resultSet, int i) throws SQLException {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setSSN(resultSet.getString("ssn"));
        employeeModel.setFullName(resultSet.getString("full_name"));
        employeeModel.setEmail(resultSet.getString("email"));
        employeeModel.setBirthDay(resultSet.getDate("birthday").toLocalDate());
        employeeModel.setDepartmentCode(resultSet.getString("department_code"));
        employeeModel.setHireDate(resultSet.getDate("hire_date").toLocalDate());
        return employeeModel;
    }
}
