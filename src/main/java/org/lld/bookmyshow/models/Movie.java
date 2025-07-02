package org.lld.bookmyshow.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.ORDINAL)
    private List<Feature> features;

    @Enumerated(EnumType.ORDINAL)
    private List<Language> languages;
}
