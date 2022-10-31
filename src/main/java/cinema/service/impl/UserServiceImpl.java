package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.model.User;
import cinema.service.UserService;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
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
        logger.info("Add User to DB. Params: User = {}", user);
        return userDao.add(user);
    }

    @Override
    public User get(Long id) {
        if (userDao.get(id).isPresent()) {
            logger.info("Get User from DB. Params: User id = {}", id);
            return userDao.get(id).get();
        }
        logger.error("Can't get User from DB. Params: User id = {}", id);
        throw new RuntimeException("User with id " + id + " not found");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        logger.info("Find User by email. Params: email = {}", email);
        return userDao.findByEmail(email);
    }
}
