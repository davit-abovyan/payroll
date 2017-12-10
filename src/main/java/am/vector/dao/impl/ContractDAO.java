package am.vector.dao.impl;

import am.vector.dao.Contract;
import am.vector.dao.impl.mapper.ContractRowMapper;
import am.vector.model.ContractModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Component
public class ContractDAO extends NamedParameterJdbcDaoSupport implements Contract{

    @Autowired
    public ContractDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * @see Contract#isInRange(long, int)
     */
    @Override
    public boolean isInRange(long roleId, int salary) {
        final String query = "SELECT count(id) FROM role " +
                " WHERE id = ? AND ( ? > salary_range_top AND ? < salary_range_bottom) LIMIT 1";

        return getJdbcTemplate().queryForObject(query, Boolean.class, new Object[]{roleId, salary, salary});
    }

    /**
     * @see Contract#create(am.vector.model.ContractModel)
     */
    @Override
    public long create(ContractModel contractModel) {
        // default return value which means nothing added
        long id = -1;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String query = "INSERT  INTO contract (ssn, role_id, start_date, end_date, salary)" +
                " VALUES ( ?, ?, ?, ?, ?)";
        int result = getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contractModel.getSSN() );
            ps.setLong(2, contractModel.getRoleId());
            ps.setDate(3, java.sql.Date.valueOf(contractModel.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(contractModel.getEndDate()));
            ps.setInt(5, contractModel.getSalary());
            return ps;
        },keyHolder);

        if(result == 1){
            id = keyHolder.getKey().intValue();
            contractModel.setId(id);
        }
        return id;
    }

    /**
     * @see Contract#read(long)
     */
    @Override
    public ContractModel read(long id) {
        final String query = "SELECT * FROM contract WHERE id = ? LIMIT 1";
        return getJdbcTemplate().queryForObject(query, new Object[]{id},new ContractRowMapper());
    }

    /**
     * @see Contract#getEmployeeCurrent(java.lang.String)
     */
    @Override
    public ContractModel getEmployeeCurrent(String ssn) {
        final String query = "SELECT * FROM contract WHERE ssn = ? AND end_date IS NULL";
        return getJdbcTemplate().queryForObject(query, new Object[]{ssn}, new ContractRowMapper());
    }

    /**
     * @see Contract#update(am.vector.model.ContractModel)
     */
    @Override
    public boolean update(ContractModel contractModel) {
        final String query = "UPDATE contract " +
                " SET ssn = ?, role_id = ?, start_date = ?, end_date = ?, salary = ? " +
                " WHERE id = ?";
        return getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, contractModel.getSSN() );
            ps.setLong(2, contractModel.getRoleId());
            ps.setDate(3, java.sql.Date.valueOf(contractModel.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(contractModel.getEndDate()));
            ps.setInt(5, contractModel.getSalary());
            ps.setLong(6, contractModel.getId());
            return ps;
        }) == 1;
    }

    /**
     * @see Contract#delete(long)
     */
    @Override
    public boolean delete(long id) {
        final String query = "DELETE FROM contract WHERE id = ?";
        return getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            return ps;
        }) == 1;
    }
}
