package cinema.service.mapper;

import cinema.dto.response.OrderResponseDto;
import cinema.model.Order;
import cinema.model.Ticket;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OrderMapper implements ResponseDtoMapper<OrderResponseDto, Order> {

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setUserId(order.getUser().getId());
        responseDto.setOrderTime(order.getOrderTime());
        responseDto.setTicketIds(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        log.info("Order from Model to Dto mapping. Params: Order = {}", order);
        return responseDto;
    }
}
