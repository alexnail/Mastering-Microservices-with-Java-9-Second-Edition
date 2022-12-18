package com.packtpub.mmj.user.domain.service;

import com.packtpub.mmj.user.domain.model.entity.Entity;
import com.packtpub.mmj.user.domain.model.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface UserService {

    void add(User booking) throws Exception;

    void update(User booking) throws Exception;

    void delete(String id) throws Exception;

    Entity<String> findById(String restaurantId) throws Exception;

    Collection<User> findByName(String name) throws Exception;

    Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
