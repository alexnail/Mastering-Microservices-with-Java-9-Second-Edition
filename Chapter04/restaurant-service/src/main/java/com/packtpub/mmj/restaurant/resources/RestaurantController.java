package com.packtpub.mmj.restaurant.resources;

import com.packtpub.mmj.restaurant.domain.model.entity.Entity;
import com.packtpub.mmj.restaurant.domain.model.entity.Restaurant;
import com.packtpub.mmj.restaurant.domain.service.RestaurantService;
import com.packtpub.mmj.restaurant.domain.valueobject.RestaurantVO;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sourabh Sharma
 */
@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class.getName());

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<Collection<Restaurant>> findByName(@RequestParam("name") String name) {
        logger.info("restaurant-service findByName() invoked:{} for {} ", restaurantService.getClass().getName(), name);
        Collection<Restaurant> restaurants;
        try {
            restaurants = restaurantService.findByName(name.trim().toLowerCase());
        } catch (Exception ex) {
            logger.error("Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurants.size() > 0
                ? new ResponseEntity<>(restaurants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{restaurant_id}")
    public ResponseEntity<Entity<String>> findById(@PathVariable("restaurant_id") String id) {
        logger.info("restaurant-service findById() invoked:{} for {} ", restaurantService.getClass().getName(), id);
        Entity<String> restaurant;
        try {
            restaurant = restaurantService.findById(id.trim());
        } catch (Exception ex) {
            logger.warn("Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurant != null
                ? new ResponseEntity<>(restaurant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Restaurant> add(@RequestBody RestaurantVO restaurantVO) {
        logger.info("restaurant-service add() invoked: {} for {}", restaurantService.getClass().getName(),
                restaurantVO.name());
        System.out.println(restaurantVO);
        Restaurant restaurant = new Restaurant(null, null, null, null);
        BeanUtils.copyProperties(restaurantVO, restaurant);
        try {
            restaurantService.add(restaurant);
        } catch (Exception ex) {
            logger.warn("Exception raised add Restaurant REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
