package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoHibernateImpl us;

    public UserServiceImpl() {
        this.us = new UserDaoHibernateImpl();
    }

    @Override
    public void createUsersTable() {
        us.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        us.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        us.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        us.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return us.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        us.cleanUsersTable();
    }
}
