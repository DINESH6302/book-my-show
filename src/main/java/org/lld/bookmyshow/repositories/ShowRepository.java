package org.lld.bookmyshow.services;

import org.lld.bookmyshow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository implement JpaRepository<Show, Long> {
    // Additional query methods can be defined here if needed
}
