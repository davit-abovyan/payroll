package am.vector.dao.impl.mapper;

import am.vector.model.DepartmentModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper implements RowMapper<DepartmentModel> {
    @Override
    public DepartmentModel mapRow(ResultSet resultSet, int i) throws SQLException {
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setCode(resultSet.getString("department_code"));
        departmentModel.setDepartment_name(resultSet.getString("department_name"));
        departmentModel.setCost_center(resultSet.getString("cost_center"));
        return departmentModel;
    }
}
