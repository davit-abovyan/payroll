package am.vector.dao.impl.mapper;

import am.vector.model.ContractModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractRowMapper implements RowMapper<ContractModel> {
    @Override
    public ContractModel mapRow(ResultSet resultSet, int i) throws SQLException {
        ContractModel contractModel = new ContractModel();
        contractModel.setId(resultSet.getLong("id"));
        contractModel.setSSN(resultSet.getString("ssn"));
        contractModel.setRoleId(resultSet.getInt("role_id"));
        contractModel.setStartDate(resultSet.getDate("start_date").toLocalDate());
        contractModel.setEndDate(resultSet.getDate("end_date").toLocalDate());
        contractModel.setSalary(resultSet.getInt("salary"));
        return contractModel;
    }
}
