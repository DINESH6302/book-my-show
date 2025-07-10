package org.lld.bookmyshow.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.lld.bookmyshow.models.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel {
    private double price;
    private Date bookedAt;

    @ManyToOne
    private Show show;

    @ManyToOne
    private User bookedBy;

    // one ticket can have multiple seats and one seat can be booked in multiple tickets(booked, canceled)
    @ManyToMany
    private List<ShowSeat> bookedSeats;

    @OneToMany
    private List<Payment> payments;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;
}
