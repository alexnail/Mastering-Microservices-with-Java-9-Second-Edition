package com.packtpub.mmj.booking.domain.repository;

import java.util.Collection;

public interface BookingRepository<Booking, String> extends Repository<Booking, String> {

    boolean containsName(String name);

    Collection<Booking> findByName(String name) throws Exception;
}
