package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.RoleDao;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    @Autowired
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        try (Session session = factory.openSession()) {
            log.info("Get Role Entity from DataBase. Params: RoleName = {}", roleName);
            return Optional.ofNullable(session.get(Role.class, roleName));
        } catch (Exception e) {
            log.error("Get Role Entity from DataBase. Params: RoleName = {}", roleName);
            throw new DataProcessingException("Can't get role by name " + roleName, e);
        }
    }
}
