package am.vector.dao.impl;

import am.vector.dao.PaySlip;
import am.vector.dao.impl.mapper.PaySlipRowMapper;
import am.vector.model.PaySlipModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Component
public class PaySlipDAO extends NamedParameterJdbcDaoSupport implements PaySlip{

    @Autowired
    public PaySlipDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * @see PaySlip#create(am.vector.model.PaySlipModel)
     */
    @Override
    public boolean create(PaySlipModel paySlipModel) {
        final String query = "INSERT  INTO payslip (period, ssn, wage, bonus, ssp, it, army, absence)" +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        return getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, paySlipModel.getPeriod());
            ps.setString(2,paySlipModel.getSSN());
            ps.setInt(3,paySlipModel.getWage());
            ps.setInt(4,paySlipModel.getBonus());
            ps.setInt(5,paySlipModel.getSSP());
            ps.setInt(6,paySlipModel.getIT());
            ps.setInt(7,paySlipModel.getArmy());
            ps.setLong(8,paySlipModel.getAbsenceID());
            return ps;
        }) == 1;
    }

    /**
     * @see PaySlip#read(java.lang.String, java.lang.String)
     */
    @Override
    public PaySlipModel read(String period, String ssn) {
        final String query = "SELECT * FROM payslip WHERE period = ? AND ssn = ? LIMIT 1";
        return getJdbcTemplate().queryForObject(query, new Object[]{period, ssn},new PaySlipRowMapper());
    }

    /**
     * @see PaySlip#getAllPayslipsForPeriod(java.lang.String)
     */
    @Override
    public List<PaySlipModel> getAllPayslipsForPeriod(String period) {
        return null;
    }

    /**
     * @see PaySlip#getAllPayslipsForEmployee(java.lang.String)
     */
    @Override
    public List<PaySlipModel> getAllPayslipsForEmployee(String ssn) {
        return null;
    }

    /**
     * @see PaySlip#update(am.vector.model.PaySlipModel)
     */
    @Override
    public boolean update(PaySlipModel paySlipModel) {
        final String query = "UPDATE payslip SET wage = ?, bonus = ?, ssp = ?, it = ?, army = ?, absence = ? " +
                " WHERE period = ? AND ssn = ?";
        return getJdbcTemplate().update( connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,paySlipModel.getWage());
            ps.setInt(2,paySlipModel.getBonus());
            ps.setInt(3,paySlipModel.getSSP());
            ps.setInt(4,paySlipModel.getIT());
            ps.setInt(5,paySlipModel.getArmy());
            ps.setLong(6,paySlipModel.getAbsenceID());
            ps.setString(7, paySlipModel.getPeriod());
            ps.setString(8,paySlipModel.getSSN());
            return ps;
        }) == 1;
    }

    /**
     * @see am.vector.dao.PaySlip#delete(java.lang.String, java.lang.String)
     */
    @Override
    public boolean delete(String period, String ssn) {
        final String query = "DELETE FROM payslip WHERE period = ? AND ssn = ?";
        return getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, period);
            ps.setString(2, ssn);
            return ps;
        }) == 1;
    }
}
