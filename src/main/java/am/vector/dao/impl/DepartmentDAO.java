package am.vector.dao.impl;

import am.vector.dao.Department;
import am.vector.dao.impl.mapper.DepartmentRowMapper;
import am.vector.model.DepartmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Component
public class DepartmentDAO extends NamedParameterJdbcDaoSupport implements Department{

    @Autowired
    public DepartmentDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * @see Department#create(am.vector.model.DepartmentModel)
     */
    @Override
    public boolean create(DepartmentModel departmentModel) {
        final String query = "INSERT INTO department (department_code, department_name, cost_center)" +
                " VALUES ( ?, ?, ?)";
        return getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, departmentModel.getCode());
            ps.setString(2, departmentModel.getDepartment_name());
            ps.setString(3, departmentModel.getCost_center());
            return ps;
        }) == 1;
    }

    /**
     * @see Department#read(java.lang.String)
     */
    @Override
    public DepartmentModel read(String departmentCode) {
        final String query = "SELECT * FROM department WHERE department_code = ? LIMIT 1";
        return getJdbcTemplate().queryForObject(query, new Object[]{departmentCode}, new DepartmentRowMapper());
    }

    @Override
    public List<DepartmentModel> getAll() {
        final String query = "SELECT * FROM department";
        return getJdbcTemplate().query(query, new Object[]{},new DepartmentRowMapper());
    }

    /**
     * @see Department#update(am.vector.model.DepartmentModel)
     */
    @Override
    public boolean update(DepartmentModel departmentModel) {
        final String query = "UPDATE department SET department_name = ?, cost_center = ? " +
                " WHERE department_code = ?";
        return getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, departmentModel.getDepartment_name());
            ps.setString(2, departmentModel.getCost_center());
            ps.setString(3, departmentModel.getCode());
            return ps;
        }) == 1;
    }

    /**
     * @see Department#delete(java.lang.String)
     */
    @Override
    public boolean delete(String department_code) {
        final String query = "DELETE FROM department WHERE department_code = ?";
        return getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, department_code);
            return ps;
        }) == 1;
    }
}
