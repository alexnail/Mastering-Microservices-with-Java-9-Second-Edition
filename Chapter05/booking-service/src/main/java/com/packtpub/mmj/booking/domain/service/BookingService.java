package com.packtpub.mmj.booking.domain.service;

import com.packtpub.mmj.booking.domain.model.entity.Booking;
import com.packtpub.mmj.booking.domain.model.entity.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface BookingService {

    void add(Booking booking) throws Exception;

    void update(Booking booking) throws Exception;

    void delete(String id) throws Exception;

    Entity<String> findById(String id) throws Exception;

    Collection<Booking> findByName(String name) throws Exception;

    Collection<Booking> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
