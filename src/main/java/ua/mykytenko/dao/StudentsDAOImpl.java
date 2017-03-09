package ua.mykytenko.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.mykytenko.model.Student;
import ua.mykytenko.util.HibernateUtil;

import java.util.List;

public class StudentsDAOImpl implements StudentsDAO {
    @Override
    public Student getById(int id) {
        Session session = null;
        Student student = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            student = session.get(Student.class, id);
            tr.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeSession(session);
        }
        return student;
    }

    @Override
    public Student update(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            session.update(student);
            tr.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeSession(session);
        }
        return student;
    }

    @Override
    public Student add(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            session.save(student);
            tr.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeSession(session);
        }
        return student;
    }

    @Override
    public boolean deleteById(int id) {
        Session session = null;
        Student student = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            student = session.getReference(Student.class, id);
            session.delete(student);
            tr.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeSession(session);
        }
        return true;
    }

    @Override
    public List<Student> getAll() {
        Session session = null;
        List<Student> students = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            students = session.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            tr.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeSession(session);
        }
        return students;
    }

    private void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            try {
                session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }
}
