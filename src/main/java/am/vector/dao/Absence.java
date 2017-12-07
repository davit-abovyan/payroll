package am.vector.dao;

import am.vector.model.AbsenceModel;

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
