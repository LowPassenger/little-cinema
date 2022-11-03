package cinema.service.mapper;

import cinema.dto.response.ShoppingCartResponseDto;
import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import java.util.stream.Collectors;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ShoppingCartMapper implements
        ResponseDtoMapper<ShoppingCartResponseDto, ShoppingCart> {

    @Override
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setUserId(shoppingCart.getUser().getId());
        responseDto.setTicketIds(shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        log.info("Shopping Cart from Model to Dto mapping. Params: Shopping Cart = {}",
                shoppingCart);
        return responseDto;
    }
}
