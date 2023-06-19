package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil2;

public class UserDao {
    public void saveUser(User user) {

        Transaction transaction = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            System.out.println("User is saved");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateUser(int id, User userToBeUpdated) {

        Transaction transaction = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            User userFound = getUser(id);
            if (userFound != null) {

                User user = new User();
                user.setId(userFound.getId());
                user.setName(userToBeUpdated.getName());
                user.setAddress(userToBeUpdated.getAddress());
                user.setEmail(userToBeUpdated.getEmail());
                user.setPassword(userToBeUpdated.getPassword());

                session.update(user);
                System.out.println("User is updated");

            } else{
                throw new Exception("User not found exception.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
                System.out.println("User is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public User getUser(int id) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
            System.out.println("The user is: " + user);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }
}