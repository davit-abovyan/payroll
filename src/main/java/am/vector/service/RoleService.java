package am.vector.service;

import am.vector.dao.Role;
import am.vector.exception.ServiceException;
import am.vector.model.RoleModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {
    private Logger log = Logger.getLogger(RoleService.class);

    private Role role;

    @Autowired
    public void setPosition(Role role) {
        this.role = role;
    }

    public long add(RoleModel roleModel){
        try{
            return role.create(roleModel);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public RoleModel get(int id){
        try{
            return role.read(id);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public List<RoleModel> getAll(){
        try{
            return role.getAll();
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public boolean update(RoleModel roleModel){
        try{
            return role.update(roleModel);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }

    public boolean remove(long id){
        try{
            return role.delete(id);
        } catch (RuntimeException e){
            log.warn(e.getMessage());
            throw new ServiceException("Error:");
        }
    }
}
