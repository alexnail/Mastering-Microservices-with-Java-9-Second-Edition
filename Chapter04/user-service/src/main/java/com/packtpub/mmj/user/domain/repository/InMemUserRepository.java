package com.packtpub.mmj.user.domain.repository;

import com.packtpub.mmj.user.domain.model.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class InMemUserRepository implements UserRepository<User, String> {

    private final Map<String, User> entities;

    /**
     * Initialize the in-memory User Repository with empty Map
     */
    public InMemUserRepository() {
        entities = new HashMap<>();
        entities.put("1", new User("1", "User Name 1", "Address 1", "City 1", "9999911111"));
        entities.put("2", new User("2", "User Name 2", "Address 2", "City 2", "9999922222"));
    }

    @Override
    public boolean containsName(String name) {
        try {
            return this.findByName(name).size() > 0;
        } catch (Exception ignored) {
        }
        return false;
    }

    @Override
    public void add(User entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public void remove(String id) {
        entities.remove(id);
    }

    @Override
    public void update(User entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }

    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User get(String id) {
        return entities.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return entities.values();
    }

    @Override
    public Collection<User> findByName(String name) {
        Collection<User> users = new ArrayList<>();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.toLowerCase().subSequence(0, noOfChars))) {
                users.add(v);
            }
        });
        return users;
    }

}
