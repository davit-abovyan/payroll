package am.vector.dao.impl.mapper;

import am.vector.model.RoleModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<RoleModel> {
    @Override
    public RoleModel mapRow(ResultSet resultSet, int i) throws SQLException {
        RoleModel roleModel = new RoleModel();
        roleModel.setId(resultSet.getLong("id"));
        roleModel.setPositionName(resultSet.getString("position_name"));
        roleModel.setDescription(resultSet.getString("description"));
        roleModel.setSalaryRangeTop(resultSet.getInt("salary_range_top"));
        roleModel.setSalaryRangeBottom(resultSet.getInt("salary_range_bottom"));
        return roleModel;
    }
}
