package com.packtpub.mmj.user.resources;

import com.packtpub.mmj.user.domain.model.entity.Entity;
import com.packtpub.mmj.user.domain.model.entity.User;
import com.packtpub.mmj.user.domain.service.UserService;
import com.packtpub.mmj.user.domain.valueobject.UserVO;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    protected UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Collection<User>> findByName(@RequestParam("name") String name) {
        logger.info("user-service findByName() invoked:{} for {} ", userService.getClass().getName(), name);
        Collection<User> users;
        try {
            users = userService.findByName(name.trim().toLowerCase());
        } catch (Exception ex) {
            logger.error("Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return users.size() > 0
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Entity<String>> findById(@PathVariable("id") String id) {
        logger.info("user-service findById() invoked:{} for {} ", userService.getClass().getName(), id);
        Entity<String> user;
        try {
            user = userService.findById(id.trim());
        } catch (Exception ex) {
            logger.warn("Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> add(@RequestBody UserVO userVO) {
        logger.info("user-service add() invoked: {} for {}", userService.getClass().getName(), userVO.name());
        System.out.println(userVO);
        User user = new User(null, null, null, null, null);
        BeanUtils.copyProperties(userVO, user);
        try {
            userService.add(user);
        } catch (Exception ex) {
            logger.warn("Exception raised add Booking REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
