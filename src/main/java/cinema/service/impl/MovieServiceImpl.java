package cinema.service.impl;

import cinema.dao.MovieDao;
import cinema.model.Movie;
import cinema.service.MovieService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private static final Logger logger = LogManager.getLogger(MovieServiceImpl.class);
    private final MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        logger.info("Add Movie to DB. Params: movie = {}", movie);
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        if (movieDao.get(id).isPresent()) {
            logger.info("Get Movie from DB. Params: id = {}", id);
            return movieDao.get(id).get();
        }
        logger.error("Can't get Movie. Params: id = {}", id);
        throw new RuntimeException("Can't get movie by id " + id);
    }

    @Override
    public List<Movie> getAll() {
        logger.info("Get all Movies from DB.");
        return movieDao.getAll();
    }
}
