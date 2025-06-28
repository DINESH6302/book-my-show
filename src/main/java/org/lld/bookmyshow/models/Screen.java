package org.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.lld.bookmyshow.models.enums.Feature;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String name;
    private List<Seat> seats;
    private List<Feature> features;
}
