package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.model.User;
import cinema.service.UserService;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(PasswordEncoder encoder, UserDao userDao) {
        this.encoder = encoder;
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        log.info("Add User to DB. Params: User = {}", user);
        return userDao.add(user);
    }

    @Override
    public User get(Long id) {
        if (userDao.get(id).isPresent()) {
            log.info("Get User from DB. Params: User id = {}", id);
            return userDao.get(id).get();
        }
        log.error("Can't get User from DB. Params: User id = {}", id);
        throw new RuntimeException("User with id " + id + " not found");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Find User by email. Params: email = {}", email);
        return userDao.findByEmail(email);
    }
}
