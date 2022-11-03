package cinema.service.mapper;

import cinema.dto.response.UserResponseDto;
import cinema.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UserMapper implements ResponseDtoMapper<UserResponseDto, User> {

    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        log.info("User from Model to Dto mapping. Params: User = {}", user);
        return responseDto;
    }
}
