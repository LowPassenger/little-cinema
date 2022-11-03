package cinema.service.impl;

import cinema.dao.RoleDao;
import cinema.model.Role;
import cinema.service.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        log.info("Add Role to DB. Params: Role = {}", role);
        return roleDao.add(role);
    }

    @Override
    public Role getByName(String roleName) {
        if (roleDao.getByName(roleName).isPresent()) {
            log.info("Get Role from DB. Params: Role name = {}", roleName);
            return roleDao.getByName(roleName).get();
        }
        log.error("Can't get Role to DB. Params: Role name = {}", roleName);
        throw new RuntimeException("Can't get role by name " + roleName);
    }
}
