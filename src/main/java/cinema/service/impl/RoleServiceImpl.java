package cinema.service.impl;

import cinema.dao.RoleDao;
import cinema.model.Role;
import cinema.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        logger.info("Add Role to DB. Params: Role = {}", role);
        return roleDao.add(role);
    }

    @Override
    public Role getByName(String roleName) {
        if (roleDao.getByName(roleName).isPresent()) {
            logger.info("Get Role from DB. Params: Role name = {}", roleName);
            return roleDao.getByName(roleName).get();
        }
        logger.error("Can't get Role to DB. Params: Role name = {}", roleName);
        throw new RuntimeException("Can't get role by name " + roleName);
    }
}
