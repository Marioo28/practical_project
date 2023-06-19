package dao;

import entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil2;

public class ProductDao {
    public void saveProduct(Product product) {

        Transaction transaction = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(product);
            System.out.println("Product is saved");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateProduct(int id, Product productToBeUpdated) {

        Transaction transaction = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            Product productFound = getProduct(id);
            productFound.reducingTotalPrice();
            if (productFound != null){
                Product product = new Product();
                product.setId(productFound.getId());
                product.setTitle(productToBeUpdated.getTitle());
                product.setDescription(productToBeUpdated.getDescription());
                product.setPrice(productToBeUpdated.getPrice());
                product.setOrders(productFound.getOrders());
//              product.settingTotalPrice();
//              product.reducingTotalPrice();
                product.settingTotalPrice(productToBeUpdated.getPrice());

                session.merge(product);

                System.out.println("Product is updated");

            }else throw new Exception("Product not found exception");

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Product product = session.get(Product.class, id);
            if (product != null) {

                product.reducingTotalPrice();

                session.remove(product);
                System.out.println("Product is deleted");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Product getProduct(int id) {

        Transaction transaction = null;
        Product product = null;
        try (Session session = HibernateUtil2.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            product = session.get(Product.class, id);
            transaction.commit();
            System.out.println("The product is: "+ product);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return product;
    }
}
