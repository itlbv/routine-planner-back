package com.itlbv.routineplannerback.controller;

import com.itlbv.routineplannerback.model.Routine;
import com.itlbv.routineplannerback.service.RoutineService;
import com.itlbv.routineplannerback.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "routines")
public class RoutineController {

    private static final Logger log = LoggerFactory.getLogger(RoutineController.class);

    private final RoutineService service;

    @Autowired
    RoutineController(RoutineService service) {
        this.service = service;
    }

    @PostMapping
    void create(@RequestBody Routine routine) {
        service.create(routine, SecurityUtil.USER_01_ID);
        log.info("create " + routine.toString());
    }

    @PutMapping("/{id}")
    void update(@RequestBody Routine routine, @PathVariable int id) {
        log.info("update " + id);
        service.update(routine, SecurityUtil.USER_01_ID);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        log.info("delete " + id);
        service.delete(id, SecurityUtil.USER_01_ID);
    }

    @GetMapping("/{id}")
    Routine get(@PathVariable int id) {
        log.info("get " + id);
        return service.get(id, SecurityUtil.USER_01_ID);
    }

    @GetMapping
    List<Routine> getAll() {
        log.info("getAll");
        return service.getAll(SecurityUtil.USER_01_ID);
    }

}
