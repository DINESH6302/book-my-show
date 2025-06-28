package org.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.lld.bookmyshow.models.enums.SeatStatus;

@Getter
@Setter
@Entity
public class ShowSeatStatus extends BaseModel {
    private Show show;
    private Seat seat;
    private SeatStatus seatStatus;
}
