package am.vector.dao;

import am.vector.model.ContractModel;

import java.util.List;
import java.util.Map;

public interface Contract {

    /**
     * Record a new contract in DB
     * @param
     * @return id of new created contract
     */
    public long create(ContractModel absenceModel);

    /**
     * Return a contract recorded in DB with provided id
     * @param id corresponding to contract
     * @return the contract with specified id
     */
    public ContractModel read(long id);

    /**
     * Returns all contracts which end date is null
     * @return
     */
    public List<Map<String,Object>> getCurrents();

    /**
     * Updated the recorder contract in DB
     * @param
     * @return true if update is successfully applied, otherwise false
     */
    public boolean update(ContractModel absenceModel);

    /**
     * Removes an contract recoded from DB with provided id
     * @param id corresponding to contract
     * @return true if the contract is removed successfully
     */
    public boolean delete(long id);
}
