package org.lld.bookmyshow.controllers;

import lombok.RequiredArgsConstructor;
import org.lld.bookmyshow.dto.ResponseStatus;
import org.lld.bookmyshow.dto.TicketRequestDto;
import org.lld.bookmyshow.dto.TicketResponseDto;
import org.lld.bookmyshow.models.Ticket;
import org.lld.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public TicketResponseDto bookTicket(TicketRequestDto requestDto) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();

        try {
            Ticket ticket = ticketService.bookTicket(requestDto.getShowSeatIds(), requestDto.getUserId(), requestDto.getShowId());

            ticketResponseDto.setTicketId(ticket.getId());
            ticketResponseDto.setAmount(ticket.getPrice());
            ticketResponseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            ticketResponseDto.setStatus(ResponseStatus.FAILURE);
            ticketResponseDto.setFailureMessage(e.getMessage());
        }

        return ticketResponseDto;
    }
}
