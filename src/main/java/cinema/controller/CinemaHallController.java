package cinema.controller;

import cinema.dto.request.CinemaHallRequestDto;
import cinema.dto.response.CinemaHallResponseDto;
import cinema.model.CinemaHall;
import cinema.service.CinemaHallService;
import cinema.service.mapper.RequestDtoMapper;
import cinema.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
@Log4j2
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final RequestDtoMapper<CinemaHallRequestDto, CinemaHall> cinemaHallRequestDtoMapper;
    private final ResponseDtoMapper<CinemaHallResponseDto, CinemaHall> cinemaHallResponseDtoMapper;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService,
            RequestDtoMapper<CinemaHallRequestDto, CinemaHall> cinemaHallRequestDtoMapper,
            ResponseDtoMapper<CinemaHallResponseDto, CinemaHall> cinemaHallResponseDtoMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallRequestDtoMapper = cinemaHallRequestDtoMapper;
        this.cinemaHallResponseDtoMapper = cinemaHallResponseDtoMapper;
    }

    @PostMapping
    public CinemaHallResponseDto add(@RequestBody @Valid CinemaHallRequestDto requestDto) {
        CinemaHall cinemaHall = cinemaHallService.add(
                cinemaHallRequestDtoMapper.mapToModel(requestDto));
        log.info("Add new Cinema Hall. Params: id = {}", cinemaHall.getId());
        return cinemaHallResponseDtoMapper.mapToDto(cinemaHall);
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        log.info("Get list of Cinema Halls.");
        return cinemaHallService.getAll()
                .stream()
                .map(cinemaHallResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
