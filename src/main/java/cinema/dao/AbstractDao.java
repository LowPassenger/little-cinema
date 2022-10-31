package cinema.dao;

import cinema.exception.DataProcessingException;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    private static final Logger logger = LogManager.getLogger(AbstractDao.class);
    protected final SessionFactory factory;
    private final Class<T> clazz;

    public AbstractDao(SessionFactory factory, Class<T> clazz) {
        this.factory = factory;
        this.clazz = clazz;
    }

    public T add(T t) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            logger.info("Add Entity to DataBase. Params: Entity Class = {}, Entity = {}",
                    clazz.getSimpleName(), t);
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't add Entity to DataBase. Params: Entity Class = {}, Entity = {}",
                    clazz.getSimpleName(), t);
            throw new DataProcessingException("Can't insert "
                    + clazz.getSimpleName() + " " + t, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Optional<T> get(Long id) {
        try (Session session = factory.openSession()) {
            logger.info("Get Entity from DataBase. Params: Entity Class = {}, id = {}",
                    clazz.getSimpleName(), id);
            return Optional.ofNullable(session.get(clazz, id));
        } catch (Exception e) {
            logger.error("Can't get Entity from DataBase. Params: Entity Class = {}, id = {}",
                    clazz.getSimpleName(), id);
            throw new DataProcessingException("Can't get "
                    + clazz.getSimpleName() + ", id: " + id, e);
        }
    }

    public List<T> getAll() {
        try (Session session = factory.openSession()) {
            logger.info("Get all Entities from DataBase. Params: Entity Class = {}",
                    clazz.getSimpleName());
            return session.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
        } catch (Exception e) {
            logger.error("Can't get all Entities from DataBase. Params: Entity Class = {}",
                    clazz.getSimpleName());
            throw new DataProcessingException("Can't get all "
                    + clazz.getSimpleName() + "s from db", e);
        }
    }

    public T update(T t) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            logger.info("Update Entity in DataBase. Params: Entity Class = {}, Entity = {}",
                    clazz.getSimpleName(), t);
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't update Entity to DataBase. Params: Entity Class = {}, Entity = {}",
                    clazz.getSimpleName(), t);
            throw new DataProcessingException("Can't update "
                    + clazz.getSimpleName() + " " + t, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            T movieSession = session.get(clazz, id);
            session.delete(movieSession);
            transaction.commit();
            logger.info("Delete Entity from DataBase. Params: Entity Class = {}, id = {}",
                    clazz.getSimpleName(), id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't delete Entity from DataBase. Params: Entity Class = {}, id = {}",
                    clazz.getSimpleName(), id);
            throw new DataProcessingException("Can't delete "
                    + clazz.getSimpleName() + " with id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
