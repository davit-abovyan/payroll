package am.vector.dao.impl;

import am.vector.dao.Role;
import am.vector.dao.impl.mapper.RoleRowMapper;
import am.vector.model.RoleModel;
import com.mysql.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Component
public class RoleDAO extends NamedParameterJdbcDaoSupport implements Role {

    @Autowired
    public RoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * @see Role#create(am.vector.model.RoleModel)
     */
    @Override
    public long create(RoleModel roleModel) {
        // default return value which means nothing added
        long id = -1;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String query = "INSERT  INTO role (position_name, description, salary_range_top, salary_range_bottom)" +
                " VALUES ( ?, ?, ?, ?)";
        int result = getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, roleModel.getPositionName());
            ps.setString(2,roleModel.getDescription());
            ps.setInt(3,roleModel.getSalaryRangeTop());
            ps.setInt(4,roleModel.getSalaryRangeBottom());
            return ps;
        },keyHolder);

        if(result == 1){
            id = keyHolder.getKey().intValue();
            roleModel.setId(id);
        }
        return id;
    }

    /**
     * @see Role#read(long)
     */
    @Override
    public RoleModel read(long id) {
        final String query = "SELECT * FROM role WHERE id = ? LIMIT 1";
        return getJdbcTemplate().queryForObject(query, new Object[]{id},new RoleRowMapper());

    }

    @Override
    public List<RoleModel> getAll() {
        final String query = "SELECT * FROM role";
        return getJdbcTemplate().query(query, new Object[]{},new RoleRowMapper());
    }

    /**
     * @see Role#update(am.vector.model.RoleModel)
     */
    @Override
    public boolean update(RoleModel roleModel) {
        final String query = "UPDATE role SET position_name = ?, description = ?, salary_range_top = ?, salary_range_bottom = ? " +
                " WHERE id = ?";
        return getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, roleModel.getPositionName());
            ps.setString(2,roleModel.getDescription());
            ps.setInt(3,roleModel.getSalaryRangeTop());
            ps.setInt(4,roleModel.getSalaryRangeBottom());
            ps.setLong(5,roleModel.getId());
            return ps;
        }) == 1;
    }

    /**
     * @see Role#delete(long)
     */
    @Override
    public boolean delete(long id) {
        final String query = "DELETE FROM role WHERE id = ?";
        return getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            return ps;
        }) == 1;
    }
}
