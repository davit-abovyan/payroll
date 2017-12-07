package am.vector.dao.impl.mapper;

import am.vector.constants.LeaveType;
import am.vector.model.AbsenceModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AbsenceRowMapper implements RowMapper<AbsenceModel> {

    @Override
    public AbsenceModel mapRow(ResultSet resultSet, int i) throws SQLException {
        AbsenceModel absenceModel = new AbsenceModel();
        absenceModel.setId(resultSet.getInt("id"));
        absenceModel.setLeaveType(LeaveType.valueOf(resultSet.getString("leave_type")));
        absenceModel.setAmount(resultSet.getInt("amount"));
        absenceModel.setStartDate(resultSet.getDate("start_date").toLocalDate());
        absenceModel.setEndDate(resultSet.getDate("end_date").toLocalDate());
        return absenceModel;
    }
}
