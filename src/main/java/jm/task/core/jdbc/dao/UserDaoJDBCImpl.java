package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Util.connect();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users(id int primary key auto_increment, name varchar(40), lastName varchar(40), age int );");
            System.out.println("Table users is created");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Util.connect();
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS users;");
            System.out.println("Table users is deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        PreparedStatement statement = null;
        final String INSER_NEW_USER = "INSERT INTO users(name, lastname, age)"
                + " VALUES(?,?,?)";
        try {
            connection = Util.connect();
            statement = connection.prepareStatement(INSER_NEW_USER);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        final String DELETE_USER = "DELETE FROM users WHERE id = ?";
        try {
            connection = Util.connect();
            statement = connection.prepareStatement(DELETE_USER);
            statement.setLong(1, id);
            int rez = statement.executeUpdate();
            if (rez != 0) {
                System.out.println("User " + id + " deleted");
            } else {
                System.out.println("User " + id + " not deleted");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        Statement statement = null;
        List<User> users = new ArrayList<>();
        try {
            connection = Util.connect();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Connection connection = null;
        PreparedStatement statement = null;
        final String DELETE_ALL_USERS = "DELETE FROM users";
        try {
            connection = Util.connect();
            statement = connection.prepareStatement(DELETE_ALL_USERS);
            int rez = statement.executeUpdate();
            if (rez != 0) {
                System.out.println("The table users is cleared");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
