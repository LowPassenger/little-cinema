package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.MovieDao;
import cinema.model.Movie;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    @Autowired
    public MovieDaoImpl(SessionFactory factory) {
        super(factory, Movie.class);
    }
}
