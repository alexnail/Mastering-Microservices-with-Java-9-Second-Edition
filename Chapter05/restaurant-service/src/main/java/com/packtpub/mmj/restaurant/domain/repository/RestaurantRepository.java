package com.packtpub.mmj.restaurant.domain.repository;

import java.util.Collection;

public interface RestaurantRepository<Restaurant, String> extends Repository<Restaurant, String> {

    boolean containsName(String name);

    Collection<Restaurant> findByName(String name) throws Exception;
}
