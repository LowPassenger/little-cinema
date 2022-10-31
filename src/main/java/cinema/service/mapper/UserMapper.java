package cinema.service.mapper;

import cinema.dto.response.UserResponseDto;
import cinema.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements ResponseDtoMapper<UserResponseDto, User> {
    private static final Logger logger = LogManager.getLogger(UserMapper.class);

    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        logger.info("User from Model to Dto mapping. Params: User = {}", user);
        return responseDto;
    }
}
