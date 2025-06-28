package org.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.lld.bookmyshow.models.enums.Feature;
import org.lld.bookmyshow.models.enums.Language;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String title;
    private long duration;
    private List<Feature> features;
    private List<Language> languages;
}
