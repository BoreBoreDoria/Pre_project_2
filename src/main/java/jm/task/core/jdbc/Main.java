package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl us = new UserDaoJDBCImpl();
        us.saveUser("Pet","Reg", (byte) 13);
    }
}
