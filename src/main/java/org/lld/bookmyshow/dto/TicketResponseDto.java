package org.lld.bookmyshow.dto;

import lombok.Data;

import java.util.List;

@Data
public class TicketResponseDto {
    private Long ticketId;
    private double amount;
    private ResponseStatus status;
    private String failureMessage;
}
