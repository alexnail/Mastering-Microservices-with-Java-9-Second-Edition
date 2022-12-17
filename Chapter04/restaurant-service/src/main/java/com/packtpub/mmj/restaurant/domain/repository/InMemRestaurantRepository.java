package com.packtpub.mmj.restaurant.domain.repository;

import com.packtpub.mmj.restaurant.domain.model.entity.Restaurant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("restaurantRepository")
public class InMemRestaurantRepository implements RestaurantRepository<Restaurant, String> {

    private final Map<String, Restaurant> entities;

    /**
     * Initialize the in-memory Restaurant Repository with empty Map
     */
    public InMemRestaurantRepository() {
        entities = new HashMap<>();
        entities.put("1", new Restaurant("Le Meurice", "1", "228 rue de Rivoli, 75001, Paris", null));
        entities.put("2", new Restaurant("L'Ambroisie", "2", "9 place des Vosges, 75004, Paris", null));
        entities.put("3", new Restaurant("Arpège", "3", "84, rue de Varenne, 75007, Paris", null));
        entities.put("4",
                new Restaurant("Alain Ducasse au Plaza Athénée", "4", "25 avenue de Montaigne, 75008, Paris", null));
        entities.put("5", new Restaurant("Pavillon LeDoyen", "5", "1, avenue Dutuit, 75008, Paris", null));
        entities.put("6", new Restaurant("Pierre Gagnaire", "6", "6, rue Balzac, 75008, Paris", null));
        entities.put("7", new Restaurant("L'Astrance", "7", "4, rue Beethoven, 75016, Paris", null));
        entities.put("8", new Restaurant("Pré Catelan", "8", "Bois de Boulogne, 75016, Paris", null));
        entities.put("9", new Restaurant("Guy Savoy", "9", "18 rue Troyon, 75017, Paris", null));
        entities.put("10",
                new Restaurant("Le Bristol", "10", "112, rue du Faubourg St Honoré, 8th arrondissement, Paris", null));
    }

    /**
     * Check if given restaurant name already exist.
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
    public void add(Restaurant entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public void remove(String id) {
        entities.remove(id);
    }

    @Override
    public void update(Restaurant entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }

    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Restaurant get(String id) {
        return entities.get(id);
    }

    @Override
    public Collection<Restaurant> getAll() {
        return entities.values();
    }

    @Override
    public Collection<Restaurant> findByName(String name) {
        Collection<Restaurant> restaurants = new ArrayList<>();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.subSequence(0, noOfChars))) {
                restaurants.add(v);
            }
        });
        return restaurants;
    }

}
