package com.itlbv.routineplannerback.controller;

import com.itlbv.routineplannerback.model.User;
import com.itlbv.routineplannerback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(RoutineController.class);

    private final UserService service;

    @Autowired
    UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    void create(@RequestBody User user) {
        log.info("create " + user.toString());
        service.create(user);
    }

    @PutMapping("/{id}")
    void update(@RequestBody User user, @PathVariable int id) {
        log.info("update " + id);
        service.update(user);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    @GetMapping("/{id}")
    User get(@PathVariable int id) {
        log.info("get " + id);
        return service.get(id);
    }

    @GetMapping
    List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

}