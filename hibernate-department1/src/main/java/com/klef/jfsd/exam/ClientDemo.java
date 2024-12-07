package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Insert Operation
        insertDepartment(session);

        // Delete Operation (Replace 1 with the Department ID to delete)
        deleteDepartmentById(session, 1);

        session.close();
        sessionFactory.close();
    }

    private static void insertDepartment(Session session) {
        Transaction tx = session.beginTransaction();
        session.save(new Department("IT", "New York", "John Doe"));
        session.save(new Department("HR", "Los Angeles", "Jane Smith"));
        tx.commit();
    }

    private static void deleteDepartmentById(Session session, int id) {
        Transaction tx = session.beginTransaction();
        session.createQuery("DELETE FROM Department WHERE departmentId = :id")
               .setParameter("id", id)
               .executeUpdate();
        tx.commit();
    }
}
