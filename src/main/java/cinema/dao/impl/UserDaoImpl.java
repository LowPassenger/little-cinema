package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.UserDao;
import cinema.exception.DataProcessingException;
import cinema.model.User;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    @Autowired
    public UserDaoImpl(SessionFactory factory) {
        super(factory, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = factory.openSession()) {
            Query<User> findByEmail = session.createQuery(
                    "FROM User WHERE email = :email", User.class);
            findByEmail.setParameter("email", email);
            log.info("Find User by email. Params: email = {}", email);
            return findByEmail.uniqueResultOptional();
        } catch (Exception e) {
            log.error("CAn't find User by email. Params: email = {}", email);
            throw new DataProcessingException("User with email " + email + " not found", e);
        }
    }
}
