package org.lld.bookmyshow.services;

import org.lld.bookmyshow.models.Payment;
import org.lld.bookmyshow.models.Show;
import org.lld.bookmyshow.models.ShowSeat;
import org.lld.bookmyshow.models.ShowSeatType;
import org.lld.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorServiceImpl implements PriceCalculator {
    private final ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorServiceImpl(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    @Override
    public double calculatePrice(Show show, List<ShowSeat> seatList) {
        List<ShowSeatType> availableSeatTypes = showSeatTypeRepository.findAllByShow(show);
        double price = 0;

        for (ShowSeat showSeat : seatList) {
            for (ShowSeatType seatType : availableSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(seatType.getSeatType())) {
                    price += seatType.getPrice();
                    break;
                }
            }
        }
        return price;
    }
}
