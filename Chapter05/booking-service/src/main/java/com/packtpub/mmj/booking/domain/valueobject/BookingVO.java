package com.packtpub.mmj.booking.domain.valueobject;

import java.time.LocalDate;
import java.time.LocalTime;


public record BookingVO(String id, String name, String restaurantId, String tableId,String userId, LocalDate date, LocalTime time) {

    @Override
    public String toString() {
        return "{id: " + id
                + ", name: " + name
                + ", userId: " + userId
                + ", restaurantId: " + restaurantId
                + ", tableId: " + tableId
                + ", date: " + date
                + ", time: " + time + "}";
    }
}
