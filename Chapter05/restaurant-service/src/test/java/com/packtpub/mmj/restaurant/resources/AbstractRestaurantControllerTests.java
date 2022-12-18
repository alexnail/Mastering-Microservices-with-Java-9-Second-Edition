package com.packtpub.mmj.restaurant.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.packtpub.mmj.restaurant.domain.model.entity.Entity;
import com.packtpub.mmj.restaurant.domain.model.entity.Restaurant;
import com.packtpub.mmj.restaurant.domain.valueobject.RestaurantVO;
import java.util.Collection;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author sousharm
 */
public abstract class AbstractRestaurantControllerTests {

    protected static final String RESTAURANT = "1";

    protected static final String RESTAURANT_NAME = "Le Meurice";

    protected static final String RESTAURANT_ADDRESS = "228 rue de Rivoli, 75001, Paris";

    @Autowired
    RestaurantController restaurantController;

    @Test
    public void validRestaurantById() {
        Logger.getGlobal().info("Start validRestaurantById test");
        ResponseEntity<Entity<String>> restaurant = restaurantController.findById(RESTAURANT);

        assertEquals(HttpStatus.OK, restaurant.getStatusCode());
        assertTrue(restaurant.hasBody());
        assertNotNull(restaurant.getBody());
        assertEquals(RESTAURANT, restaurant.getBody().getId());
        assertEquals(RESTAURANT_NAME, restaurant.getBody().getName());
        Logger.getGlobal().info("End validRestaurantById test");
    }

    @Test
    public void validRestaurantByName() {
        Logger.getGlobal().info("Start validRestaurantByName test");
        ResponseEntity<Collection<Restaurant>> restaurants = restaurantController.findByName(RESTAURANT_NAME);
        Logger.getGlobal().info("In validAccount test");

        assertEquals(HttpStatus.OK, restaurants.getStatusCode());
        assertTrue(restaurants.hasBody());
        assertNotNull(restaurants.getBody());
        assertFalse(restaurants.getBody().isEmpty());
        Restaurant restaurant = (Restaurant) restaurants.getBody().toArray()[0];
        assertEquals(RESTAURANT, restaurant.getId());
        assertEquals(RESTAURANT_NAME, restaurant.getName());
        Logger.getGlobal().info("End validRestaurantByName test");
    }

    @Test
    public void validAdd() {
        Logger.getGlobal().info("Start validAdd test");
        RestaurantVO restaurant = new RestaurantVO("999", "Test Restaurant", null, null);

        ResponseEntity<Restaurant> restaurants = restaurantController.add(restaurant);
        assertEquals(HttpStatus.CREATED, restaurants.getStatusCode());
        Logger.getGlobal().info("End validAdd test");
    }
}
