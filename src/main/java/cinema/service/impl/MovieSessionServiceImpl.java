package cinema.service.impl;

import cinema.dao.MovieSessionDao;
import cinema.model.MovieSession;
import cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private static final Logger logger = LogManager.getLogger(MovieServiceImpl.class);
    private final MovieSessionDao movieSessionDao;

    @Autowired
    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        logger.info("Find available Session. Params: Movie id = {}, available date = {}",
                movieId, date);
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        logger.info("Add Movie Session to DB. Params: Movie Session = {}", session);
        return movieSessionDao.add(session);
    }

    @Override
    public MovieSession get(Long id) {
        if (movieSessionDao.get(id).isPresent()) {
            logger.info("Get Movie Session from DB. Params: id = {}", id);
            return movieSessionDao.get(id).get();
        }
        logger.error("Can't get Movie Session. Params: id = {}", id);
        throw new RuntimeException("Can't get movie session by id " + id);
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        logger.info("Update Movie Session in DB. Params: Movie Session = {}", movieSession);
        return movieSessionDao.update(movieSession);
    }

    @Override
    public void delete(Long id) {
        logger.info("Delete Movie Session from DB. Params: Movie Session id = {}", id);
        movieSessionDao.delete(id);
    }
}
