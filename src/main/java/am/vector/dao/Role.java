package am.vector.dao;

import am.vector.model.RoleModel;

import java.util.List;

public interface Role {

    /**
     * Record a new role in DB
     * @param
     * @return id of new created role
     */
    public long create(RoleModel absenceModel);

    /**
     * Return the role with provided id
     * @param id corresponding to role
     * @return the role with specified id
     */
    public RoleModel read(long id);

    /**
     * Return all recorded roles
     * @return list of roles
     */
    public List<RoleModel> getAll();

    /**
     * Updated the recorder role in DB
     * @param
     * @return true if update is successfully applied, otherwise false
     */
    public boolean update(RoleModel absenceModel);

    /**
     * Removes an role recoded from DB with provided id
     * @param id corresponding to role
     * @return true if the role is removed successfully
     */
    public boolean delete(long id);
}
