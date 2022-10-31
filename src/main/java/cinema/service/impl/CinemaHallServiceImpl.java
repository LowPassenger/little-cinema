package cinema.service.impl;

import cinema.dao.CinemaHallDao;
import cinema.model.CinemaHall;
import cinema.service.CinemaHallService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private static final Logger logger = LogManager.getLogger(CinemaHallServiceImpl.class);
    private final CinemaHallDao cinemaHallDao;

    @Autowired
    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public CinemaHall get(Long id) {
        if (cinemaHallDao.get(id).isPresent()) {
            logger.info("Get Cinema Hall from DB. Params: id = {}", id);
            return cinemaHallDao.get(id).get();
        }
        logger.error("Can't get Cinema Hall. Params: id = {}", id);
        throw new RuntimeException("Can't get cinema hall by id " + id);
    }

    @Override
    public List<CinemaHall> getAll() {
        logger.info("Get all Cinema Halls from DB.");
        return cinemaHallDao.getAll();
    }
}
