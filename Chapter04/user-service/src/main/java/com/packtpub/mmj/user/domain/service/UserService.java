package com.packtpub.mmj.user.domain.service;

import com.packtpub.mmj.user.domain.model.entity.Entity;
import com.packtpub.mmj.user.domain.model.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author Sourabh Sharma
 */
public interface UserService {

    /**
     * @param booking
     * @throws Exception
     */
    void add(User booking) throws Exception;

    /**
     * @param booking
     * @throws Exception
     */
    void update(User booking) throws Exception;

    /**
     * @param id
     * @throws Exception
     */
    void delete(String id) throws Exception;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    Entity<String> findById(String id) throws Exception;

    /**
     * @param name
     * @return
     * @throws Exception
     */
    Collection<User> findByName(String name) throws Exception;

    /**
     * @param name
     * @return
     * @throws Exception
     */
    Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
