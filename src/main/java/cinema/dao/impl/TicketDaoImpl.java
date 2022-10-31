package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.TicketDao;
import cinema.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {
    @Autowired
    public TicketDaoImpl(SessionFactory factory) {
        super(factory, Ticket.class);
    }
}
