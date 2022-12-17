package com.packtpub.mmj.booking.domain.valueobject;

import java.time.LocalDate;
import java.time.LocalTime;


public record BookingVO(String name, String id, String restaurantId, String userId, LocalDate date, LocalTime time,
                        String tableId) {

    @Override
    public String toString() {
        return String.format(
                "{id: %s, name: %s, userId: %s, restaurantId: %s, tableId: %s, date: %s, time: %s}",
                id, name, userId, restaurantId, tableId, date, time);
    }
}
