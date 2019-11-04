package com.itlbv.routineplannerback.repository;

import com.itlbv.routineplannerback.model.Routine;

import java.util.List;

public interface RoutineRepository {
    Routine save(Routine routine, int userId);

    boolean delete(int id, int userId);

    Routine get(int id, int userId);

    List<Routine> getAll(int userId);
}
