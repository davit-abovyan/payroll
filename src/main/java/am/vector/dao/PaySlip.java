package am.vector.dao;

import am.vector.model.PaySlipModel;

import java.util.List;

public interface PaySlip {

    /**
     * Record a new pay-slip in DB
     * @param
     * @return true if the pay-slip is created successfully
     */
    public boolean create(PaySlipModel absenceModel);

    /**
     * Return a pay-slip recorded in DB with provided id
     * @param ssn Social security number of the employee whose pay-slip is requested
     * @param period the period for which the pay-slip is requested
     * @return the pay-slip of employee with provided ssn for provided period
     */
    public PaySlipModel read(String ssn, String period);

    /**
     * Returns all pay-slips for the provided period
     * @param period the period for which the pay-slips are requested
     * @return
     */
    public List<PaySlipModel> getAllPayslipsForPeriod(String period);

    /**
     * Returns all pay-slips for provided employee for current year
     * @param ssn social security number of employee
     * @return
     */
    public List<PaySlipModel> getAllPayslipsForEmployee(String ssn);

    /**
     * Updated the recorder pay-slip in DB
     * @param
     * @return true if update is successfully applied, otherwise false
     */
    public boolean update(PaySlipModel absenceModel);

    /**
     * Removes an pay-slip recoded from DB with provided id
     * @param period the period for which the pay-slip should be removed
     * @param ssn Social security number of the employee whose pay-slip should be removed
     * @return true if the pay-slip is removed successfully
     */
    public boolean delete(String period, String ssn);
}
