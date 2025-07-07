package org.lld.bookmyshow.services;

import org.lld.bookmyshow.models.Show;
import org.lld.bookmyshow.models.ShowSeat;
import org.lld.bookmyshow.models.User;
import org.lld.bookmyshow.models.enums.SeatStatus;
import org.lld.bookmyshow.models.enums.TicketStatus;
import org.lld.bookmyshow.repositories.ShowRepository;
import org.lld.bookmyshow.repositories.ShowSeatTypeRepository;
import org.lld.bookmyshow.models.Ticket;
import org.lld.bookmyshow.repositories.ShowSeatRepository;
import org.lld.bookmyshow.repositories.TicketRepository;
import org.lld.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final ShowSeatTypeRepository showSeatTypeRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;
    private final ShowSeatRepository showSeatRepository;

    @Autowired
    TicketServiceImpl(ShowRepository showRepository,
                      TicketRepository ticketRepository,
                      ShowSeatRepository showSeatRepository,
                      ShowSeatTypeRepository showSeatTypeRepository,
                      UserRepository userRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
        this.showRepository = showRepository;
        this.ticketRepository = ticketRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Ticket bookTicket(List<Long> showSeatIds, Long showId, Long userId) {
//        1. Get show by showId
        Optional<Show> showOptional = showRepository.findById(showId);
        if (showOptional.isEmpty()) {
            throw new RuntimeException("Show not found with id: " + showId);
        }

//        2. Get user by userId
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        Show show = showOptional.get();
        User user = userOptional.get();

//        3.Get and check if all show seats are available
        List<ShowSeat> availableShowSeats = getAndLockShowSeats(showSeatIds);

//        4. Create Ticked Object
        Ticket ticket = new Ticket();
        ticket.setShow(show);
        ticket.setBookedSeats(availableShowSeats);
        ticket.setBookedBy(user);
        ticket.setBookedAt(new Date());
        ticket.setTicketStatus(TicketStatus.PENDING);
        ticket.setPayments(new ArrayList<>());

        return ticket;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
    public List<ShowSeat> getAndLockShowSeats(List<Long> showSeatIds) {
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for (ShowSeat showSeat : showSeats) {
//            3.1 Check if show seats are not available
            if (!showSeat.getStatus().equals(SeatStatus.AVAILABLE)) {
//                3.1.2 If show seats are blocked before 15 minutes, then throw exception for unlock them
                if (showSeat.getStatus().equals(SeatStatus.BLOCKED) &&
                        (Duration.between(showSeat.getLockedAt().toInstant(), new Date().toInstant()).toMinutes() > 15)) {
                    throw new RuntimeException("Seat is available to unlock: " + showSeat.getId());
                }
                throw new RuntimeException("Seat is unavailable: " + showSeat.getId());
            }

//          3.2 If show seats are available, then lock them
            showSeat.setStatus(SeatStatus.BLOCKED);
            showSeat.setLockedAt(new Date());
            showSeatRepository.save(showSeat);
        }

        return showSeats;
    }
}
