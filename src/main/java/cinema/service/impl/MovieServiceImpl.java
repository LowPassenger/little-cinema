package cinema.service.impl;

import cinema.dao.MovieDao;
import cinema.model.Movie;
import cinema.service.MovieService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        log.info("Add Movie to DB. Params: movie = {}", movie);
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        if (movieDao.get(id).isPresent()) {
            log.info("Get Movie from DB. Params: id = {}", id);
            return movieDao.get(id).get();
        }
        log.error("Can't get Movie. Params: id = {}", id);
        throw new RuntimeException("Can't get movie by id " + id);
    }

    @Override
    public List<Movie> getAll() {
        log.info("Get all Movies from DB.");
        return movieDao.getAll();
    }
}
