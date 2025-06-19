package ru.t1.spring.service;

import org.springframework.stereotype.Service;
import ru.t1.spring.dto.User;
import ru.t1.spring.dao.UserDao;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User create(String username) {
        return userDao.create(new User(username));
    }

    public boolean  deleteById(Long id) {
        return userDao.delete(id);
    }

    public User getById(Long id) {
        return userDao.getById(id);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
