package org.lld.bookmyshow.services;

import org.lld.bookmyshow.models.Ticket;

import java.util.List;

public interface TicketService {
    public Ticket bookTicket(List<Long> showSeatIds, Long userId, Long showId);
}
