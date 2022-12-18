package com.packtpub.mmj.user.domain.repository;

import java.util.Collection;

public interface UserRepository<Booking, String> extends Repository<Booking, String> {

    boolean containsName(String name);

    Collection<Booking> findByName(String name) throws Exception;
}
