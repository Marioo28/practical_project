package dao;

import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil2;

public class OrderDao {

    public void saveOrder(Orders orders) {
        Transaction transaction = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(orders);
            System.out.println("Order is saved");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateOrder(int id, Orders ordersToBeUpdated) {

        Transaction transaction = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            Orders orderFound = getOrder(id);

            if (orderFound!=null){

                Orders orders = new Orders();

                orders.setId(orderFound.getId());
                orders.setOrderDate(ordersToBeUpdated.getOrderDate());
                orders.setDeliveryAddress(ordersToBeUpdated.getDeliveryAddress());
                orders.setTotalCost(ordersToBeUpdated.getTotalCost());
                orders.setStatusOrder(ordersToBeUpdated.getStatusOrder());
                orders.setPaymentType(ordersToBeUpdated.getPaymentType());


                session.update(orders);
                System.out.println("Order is updated");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Orders orders = session.get(Orders.class, id);
            if (orders != null) {
                session.delete(orders);
                System.out.println("Order is deleted");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Orders getOrder(int id) {

        Transaction transaction = null;
        Orders orders = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            orders = session.get(Orders.class, id);
            transaction.commit();
            System.out.println("The order is: " + orders);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return orders;
    }
}
