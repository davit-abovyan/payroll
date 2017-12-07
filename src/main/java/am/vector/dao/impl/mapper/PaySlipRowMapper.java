package am.vector.dao.impl.mapper;

import am.vector.model.PaySlipModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaySlipRowMapper implements RowMapper<PaySlipModel> {
    @Override
    public PaySlipModel mapRow(ResultSet resultSet, int i) throws SQLException {
        PaySlipModel paySlipModel = new PaySlipModel();
        paySlipModel.setPeriod(resultSet.getString("period"));
        paySlipModel.setSSN(resultSet.getString("ssn"));
        paySlipModel.setWage(resultSet.getInt("wage"));
        paySlipModel.setBonus(resultSet.getInt("bonus"));
        paySlipModel.setSSP(resultSet.getInt("ssp"));
        paySlipModel.setIT(resultSet.getInt("it"));
        paySlipModel.setArmy(resultSet.getInt("army"));
        paySlipModel.setAbsenceID(resultSet.getLong("absence"));
        return paySlipModel;
    }
}
