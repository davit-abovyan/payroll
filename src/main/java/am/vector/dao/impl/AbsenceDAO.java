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
import java.util.List;

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
        final String query = "INSERT  INTO absence (leave_type, ssn, period, amount, start_date, end_date)" +
                " VALUES ( ?, ?, ?, ?, ?, ?)";
        int result = getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, absenceModel.getLeaveType().toString());
            ps.setString(2,absenceModel.getSsn());
            ps.setString(3,absenceModel.getPeriod());
            ps.setInt(4, absenceModel.getAmount());
            ps.setDate(5, java.sql.Date.valueOf(absenceModel.getStartDate()));
            ps.setDate(6, java.sql.Date.valueOf(absenceModel.getEndDate()));
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
     * @see Absence#getAllByEmployeeByPeriod(java.lang.String, java.lang.String)
     * @return
     */
    @Override
    public List<AbsenceModel> getAllByEmployeeByPeriod(String period, String ssn) {
        final String query = "SELECT * FROM absence WHERE period = ? AND ssn = ?";
        return getJdbcTemplate().query(query, new Object[]{period, ssn},new AbsenceRowMapper());

    }

    /**
     * @see Absence#getAllByEmployee(java.lang.String)
     */
    @Override
    public List<AbsenceModel> getAllByEmployee(String ssn) {
        final String query = "SELECT * FROM absence WHERE ssn = ?";
        return getJdbcTemplate().query(query, new Object[]{ssn},new AbsenceRowMapper());

    }

    /**
     * @see Absence#getAlleByPeriod(java.lang.String)
     */
    @Override
    public List<AbsenceModel> getAlleByPeriod(String period) {
        final String query = "SELECT * FROM absence WHERE period = ? ";
        return getJdbcTemplate().query(query, new Object[]{period},new AbsenceRowMapper());

    }

    /**
     * @see Absence#getAllAbsences()
     */
    @Override
    public List<AbsenceModel> getAllAbsences() {
        final String query = "SELECT * FROM absence LIMIT 1000";
        return getJdbcTemplate().query(query, new Object[]{},new AbsenceRowMapper());
    }

    /**
     * @see Absence#getAbsenceDaysByEmployee(java.lang.String, java.lang.String)
     */
    @Override
    public int getAbsenceDaysByEmployee(String ssn, String period) {
        final String query = "SELECT sum(datediff(end_date, start_date)) AS days FROM absence" +
                " WHERE ssn = ? AND period = ?";
        return getJdbcTemplate().queryForObject(query, new Object[]{ssn, period}, Integer.class);
    }

    /**
     * @see Absence#update(am.vector.model.AbsenceModel)
     */
    @Override
    public boolean update(AbsenceModel absenceModel) {
        final String query = "UPDATE absence SET" +
                " leave_type = ? , ssn = ?, period = ?, amount = ?, start_date = ?, end_date = ?" +
                "WHERE id = ?";
        return getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, absenceModel.getLeaveType().toString());
            ps.setString(2, absenceModel.getSsn());
            ps.setString(3, absenceModel.getPeriod());
            ps.setInt(4, absenceModel.getAmount());
            ps.setDate(5, java.sql.Date.valueOf(absenceModel.getStartDate()));
            ps.setDate(6, java.sql.Date.valueOf(absenceModel.getEndDate()));
            ps.setLong(7,absenceModel.getId());
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
