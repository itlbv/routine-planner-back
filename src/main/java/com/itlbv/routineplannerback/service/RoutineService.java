package com.itlbv.routineplannerback.service;

import com.itlbv.routineplannerback.model.Routine;
import com.itlbv.routineplannerback.repository.RoutineRepository;
import com.itlbv.routineplannerback.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineService {

    private final RoutineRepository repository;

    @Autowired
    public RoutineService(RoutineRepository repository) {
        this.repository = repository;
    }

    public Routine create(Routine routine, int userId) {
        return repository.save(routine, userId);
    }

    public void update(Routine routine, int userId) throws NotFoundException {
        if (repository.save(routine, userId) == null) {
            throw new NotFoundException("Failed updating routine with id=" + routine.getId());
        }
    }

    public void delete(int id, int userId) throws NotFoundException {
        if (!repository.delete(id, userId)) {
            throw new NotFoundException("Failed deleting routine with id=" + id);
        }
    }

    public Routine get(int id, int userId) throws NotFoundException {
        Routine routine = repository.get(id, userId);
        if (routine == null) {
            throw new NotFoundException("Not found routine with id=" + id);
        }
        return routine;
    }

    public List<Routine> getAll(int userId) {
        return repository.getAll(userId);
    }
}
