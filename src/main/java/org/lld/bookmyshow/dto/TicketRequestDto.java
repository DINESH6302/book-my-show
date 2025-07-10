package org.lld.bookmyshow.dto;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequestDto {
    private List<Long> showSeatIds;
    private Long userId;
    private Long showId;
}
