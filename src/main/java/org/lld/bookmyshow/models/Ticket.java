package org.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.lld.bookmyshow.models.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel {
    private Show show;
    private double price;
    private LocalDateTime bookedAt;
    private User bookedBy;
    private List<Seat> bookedSeats;
    private List<Payment> payments;
    private TicketStatus tickeStatus;
}
