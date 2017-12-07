package am.vector.dao.impl;

import am.vector.dao.Absence;
import am.vector.dao.impl.mapper.AbsenceRowMapper;
import am.vector.model.AbsenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Component
public class AbsenceDAO extends NamedParameterJdbcDaoSupport implements Absence {

    @Autowired
    public AbsenceDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * @see Absence#create(am.vector.model.AbsenceModel)
     */
    @Override
    public long create(AbsenceModel absenceModel) {
        // default return value which means nothing added
        long id = -1;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String query = "INSERT  INTO absence (leave_type, amount, start_date, end_date)" +
                " VALUES ( ?, ?, ?, ?)";
        int result = getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, absenceModel.getLeaveType().toString());
            ps.setInt(2, absenceModel.getAmount());
            ps.setDate(3, java.sql.Date.valueOf(absenceModel.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(absenceModel.getEndDate()));
            return ps;
        },keyHolder);

        if(result == 1){
            id = keyHolder.getKey().intValue();
            absenceModel.setId(id);
        }
        return id;
    }

    /**
     * @see Absence#read(long)
     */
    @Override
    public AbsenceModel read(long id) {
        final String query = "SELECT * FROM absence WHERE id = ? LIMIT 1";
        return getJdbcTemplate().queryForObject(query, new Object[]{id},new AbsenceRowMapper());
    }

    /**
     * @see Absence#update(am.vector.model.AbsenceModel)
     */
    @Override
    public boolean update(AbsenceModel absenceModel) {
        final String query = "UPDATE absence SET" +
                " leave_type = ? , amount = ?, start_date = ?, end_date = ?" +
                "WHERE id = ?";
        return getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, absenceModel.getLeaveType().toString());
            ps.setInt(2, absenceModel.getAmount());
            ps.setDate(3, java.sql.Date.valueOf(absenceModel.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(absenceModel.getEndDate()));
            ps.setLong(5,absenceModel.getId());
            return ps;
        }) == 1;
    }

    /**
     * @see Absence#delete(long)
     */
    @Override
    public boolean delete(long id) {
        final String query = "DELETE FROM absence WHERE id = ?";
        return getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            return ps;
        }) == 1;
    }
}
