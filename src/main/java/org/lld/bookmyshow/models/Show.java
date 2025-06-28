package org.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.lld.bookmyshow.models.enums.Feature;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Show extends BaseModel {
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Screen screen;
    private ShowSeatStatus showSeatStatus;
    private ShowSeatType showSeatType;
    private List<Feature> features;

}
