package cinema.service.impl;

import cinema.dao.CinemaHallDao;
import cinema.model.CinemaHall;
import cinema.service.CinemaHallService;
import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CinemaHallServiceImpl implements CinemaHallService {
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
            log.info("Get Cinema Hall from DB. Params: id = {}", id);
            return cinemaHallDao.get(id).get();
        }
        log.error("Can't get Cinema Hall. Params: id = {}", id);
        throw new RuntimeException("Can't get cinema hall by id " + id);
    }

    @Override
    public List<CinemaHall> getAll() {
        log.info("Get all Cinema Halls from DB.");
        return cinemaHallDao.getAll();
    }
}
