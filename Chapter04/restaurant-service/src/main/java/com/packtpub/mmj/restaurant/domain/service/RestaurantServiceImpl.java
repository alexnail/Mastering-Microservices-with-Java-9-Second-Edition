package com.packtpub.mmj.restaurant.domain.service;

import com.packtpub.mmj.restaurant.domain.model.entity.Entity;
import com.packtpub.mmj.restaurant.domain.model.entity.Restaurant;
import com.packtpub.mmj.restaurant.domain.repository.RestaurantRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("restaurantService")
public class RestaurantServiceImpl extends BaseService<Restaurant, String> implements RestaurantService {

    private final RestaurantRepository<Restaurant, String> restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository<Restaurant, String> restaurantRepository) {
        super(restaurantRepository);
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void add(Restaurant restaurant) throws Exception {
        if (restaurantRepository.containsName(restaurant.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", restaurant.getName()));
        }

        if (StringUtils.isEmpty(restaurant.getName())) {
            throw new Exception("Restaurant name cannot be null or empty string.");
        }
        super.add(restaurant);
    }

    @Override
    public Collection<Restaurant> findByName(String name) throws Exception {
        return restaurantRepository.findByName(name);
    }

    @Override
    public void update(Restaurant restaurant) {
        restaurantRepository.update(restaurant);
    }

    @Override
    public void delete(String id) {
        restaurantRepository.remove(id);
    }

    @Override
    public Entity<String> findById(String restaurantId) {
        return restaurantRepository.get(restaurantId);
    }

    @Override
    public Collection<Restaurant> findByCriteria(Map<String, ArrayList<String>> name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
