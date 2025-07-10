package org.lld.bookmyshow.services;

import org.lld.bookmyshow.models.Show;
import org.lld.bookmyshow.models.ShowSeat;
import org.lld.bookmyshow.models.ShowSeatType;

import java.util.List;

public interface PriceCalculator {
    public double calculatePrice(Show show, List<ShowSeat> seatList);
}
