package cinema.service.mapper;

import cinema.dto.request.CinemaHallRequestDto;
import cinema.dto.response.CinemaHallResponseDto;
import cinema.model.CinemaHall;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CinemaHallMapper implements RequestDtoMapper<CinemaHallRequestDto, CinemaHall>,
        ResponseDtoMapper<CinemaHallResponseDto, CinemaHall> {

    @Override
    public CinemaHall mapToModel(CinemaHallRequestDto dto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(dto.getDescription());
        cinemaHall.setCapacity(dto.getCapacity());
        log.info("Cinema Hall from request to Model mapping. Params: Cinema Hall = {}",
                cinemaHall);
        return cinemaHall;
    }

    @Override
    public CinemaHallResponseDto mapToDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto responseDto = new CinemaHallResponseDto();
        responseDto.setId(cinemaHall.getId());
        responseDto.setCapacity(cinemaHall.getCapacity());
        responseDto.setDescription(cinemaHall.getDescription());
        log.info("Cinema Hall from Model to Dto mapping. Params: Cinema Hall = {}",
                cinemaHall);
        return responseDto;
    }
}
