package com.packtpub.mmj.booking.domain.repository;

import com.packtpub.mmj.booking.domain.model.entity.Booking;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("bookingRepository")
public class InMemBookingRepository implements BookingRepository<Booking, String> {

    private final Map<String, Booking> entities;

    /**
     * Initialize the in-memory Booking Repository with sample Map
     */
    public InMemBookingRepository() {
        entities = new HashMap<>();
        entities.put("1", new Booking("1", "Booking 1", "1", "1", "1", LocalDate.now(), LocalTime.now()));
        entities.put("2", new Booking("2", "Booking 2", "2", "2", "2", LocalDate.now(), LocalTime.now()));
    }

    /**
     * Check if given booking name already exist.
     */
    @Override
    public boolean containsName(String name) {
        try {
            return this.findByName(name).size() > 0;
        } catch (Exception ignored) {
        }
        return false;
    }

    @Override
    public void add(Booking entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public void remove(String id) {
        entities.remove(id);
    }

    @Override
    public void update(Booking entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }

    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Booking get(String id) {
        return entities.get(id);
    }

    @Override
    public Collection<Booking> getAll() {
        return entities.values();
    }

    @Override
    public Collection<Booking> findByName(String name) {
        Collection<Booking> bookings = new ArrayList<>();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.toLowerCase().subSequence(0, noOfChars))) {
                bookings.add(v);
            }
        });
        return bookings;
    }

}
