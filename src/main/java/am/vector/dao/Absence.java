package am.vector.dao;

import am.vector.model.AbsenceModel;

import java.util.List;

public interface Absence {
    /**
     * Record a new absence in DB
     * @param
     * @return the is of new created absence
     */
    public long create(AbsenceModel absenceModel);

    /**
     * Return a absence recorded in DB with provided id
     * @param id corresponding to absence
     * @return the absence with specified id
     */
    public AbsenceModel read(long id);

    /**
     * Return list of all absences recorder for selected employee on selected period
     * @param period the period on for which the absences will get in "December 2017" -> "1217" format
     * @param ssn social security number of selected employee
     * @return list of absences
     */
    public List<AbsenceModel> getAllByEmployeeByPeriod(String period, String ssn);

    /**
     * Return list of all absences recorder for selected employee for all periods
     * @param ssn social security number of selected employee
     * @return list of absences
     */
    public List<AbsenceModel> getAllByEmployee(String ssn);

    /**
     * Return list of all absences recorder for all employees on selected period
     * @param period the period on for which the absences will get in "December 2017" -> "1217" format
     * @return list of absences
     */
    public List<AbsenceModel> getAlleByPeriod(String period);

    /**
     * Return list of all absences recorder for all employees on all periods
     *
     * TODO consider pagination with mySQL LIMIT
     * @return list of absences
     */
    public List<AbsenceModel> getAllAbsences();

    /**
     * Updated the recorder absence in DB
     * @param
     * @return true if update is successfully applied, otherwise false
     */
    public boolean update(AbsenceModel absenceModel);

    /**
     * Removes an absence recoded from DB with provided id
     * @param id corresponding to absence
     * @return true if the absence record is removed successfully
     */
    public boolean delete(long id);
}
