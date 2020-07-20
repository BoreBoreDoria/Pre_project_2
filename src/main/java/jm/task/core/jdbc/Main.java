package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.saveUser("Petia","Sedysko", (byte) 13);
    }
}
