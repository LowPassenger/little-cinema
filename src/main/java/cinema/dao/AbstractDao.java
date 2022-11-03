package cinema.dao;

import cinema.exception.DataProcessingException;
import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Log4j2
public abstract class AbstractDao<T> {
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
            log.info("Add Entity to DataBase. Params: Entity Class = {}, Entity = {}",
                    clazz.getSimpleName(), t);
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Can't add Entity to DataBase. Params: Entity Class = {}, Entity = {}",
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
            log.info("Get Entity from DataBase. Params: Entity Class = {}, id = {}",
                    clazz.getSimpleName(), id);
            return Optional.ofNullable(session.get(clazz, id));
        } catch (Exception e) {
            log.error("Can't get Entity from DataBase. Params: Entity Class = {}, id = {}",
                    clazz.getSimpleName(), id);
            throw new DataProcessingException("Can't get "
                    + clazz.getSimpleName() + ", id: " + id, e);
        }
    }

    public List<T> getAll() {
        try (Session session = factory.openSession()) {
            log.info("Get all Entities from DataBase. Params: Entity Class = {}",
                    clazz.getSimpleName());
            return session.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
        } catch (Exception e) {
            log.error("Can't get all Entities from DataBase. Params: Entity Class = {}",
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
            log.info("Update Entity in DataBase. Params: Entity Class = {}, Entity = {}",
                    clazz.getSimpleName(), t);
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Can't update Entity to DataBase. Params: Entity Class = {}, Entity = {}",
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
            log.info("Delete Entity from DataBase. Params: Entity Class = {}, id = {}",
                    clazz.getSimpleName(), id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Can't delete Entity from DataBase. Params: Entity Class = {}, id = {}",
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
