package com.itlbv.routineplannerback.service;

import com.itlbv.routineplannerback.model.User;
import com.itlbv.routineplannerback.repository.UserRepository;
import com.itlbv.routineplannerback.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void update(User user) {
        repository.save(user);
    }

    public void delete(int id) throws NotFoundException {
        if (!repository.delete(id)) {
            throw new NotFoundException("Failed deleting user with id=" + id);
        }
    }

    public User get(int id) throws NotFoundException {
        User user = repository.get(id);
        if (user == null) {
            throw new NotFoundException("Not found user with id=" + id);
        }
        return user;
    }

    public User getByEmail(String email) throws NotFoundException {
        User user = repository.getByEmail(email);
        if (user == null) {
            throw new NotFoundException("Not found user with email=" + email);
        }
        return user;
    }

    public List<User> getAll() {
        return repository.getAll();
    }
}
