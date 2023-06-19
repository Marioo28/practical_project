import dao.OrderDao;
import dao.ProductDao;
import dao.UserDao;
import entity.Orders;
import entity.Product;
import entity.User;
import entity.enums.PaymentType;
import entity.enums.StatusOrder;

import java.time.LocalDate;
import java.util.List;


public class Main {

    public static void main(String[] args){
        OrderDao orderDao = new OrderDao();
        ProductDao productDao = new ProductDao();
        UserDao userDao = new UserDao();
        User user1 = new User("Popescu Adrian", "adrian@mail.ro", "123456", "Galati");
        User user2 = new User("Tache Mihai", "mihai@mail.ro", "987654", "Fetesti");
        User user3 = new User("Vasile Ion", "Ion@mail.ro", "34534", "Iasi");
        userDao.saveUser(user1);
        userDao.saveUser(user2);
        userDao.saveUser(user3);

//        userDao.deleteUser(2);

        User user2b = new User("Serban Aurelian", "aurelian@mail.ro", "987654", "Fetesti");
        userDao.saveUser(user2b);

        User userNew = new User("Brailescu Ion", "ion@mail.ro", "987654", "Fetesti");
        userDao.updateUser(user2b.getId(), userNew);

        System.out.println("-------------------------");

        Orders orders1 = new Orders(LocalDate.now(), "str.Galati nr.25", StatusOrder.PROCESSING, PaymentType.CARD);
        Product product1 = new Product("Iphone", "SmartPhone", 30);
        product1.setOrders(orders1);

        Product product2 = new Product("Samsung", "SmartPhone", 50);
        product2.setOrders(orders1);

        orders1.setProducts(List.of(product1, product2));
        orders1.setUser(user1);
//        user1.setOrders(orders1);

        orderDao.saveOrder(orders1);

//        productDao.deleteProduct(product1.getId());

        Product productNew = new Product("BEKO", "CombinaFrigorifica", 500);
        productDao.updateProduct(product2.getId(),productNew);

        /* sterge orderu- insa sterge si produsul si userul asociat orderului */
//        orderDao.deleteOrder(orders1.getId());

        System.out.println("-------------------------------");

        Orders orders2 = new Orders(LocalDate.now(),"str.Cluj nr.10", StatusOrder.REFUNDED, PaymentType.CASH);

        Product product3 = new Product("SMART TV", "LG", 3999.9);

        product3.setOrders(orders2);
        orders2.setProducts(List.of(product3));

        orders2.setUser(user3);
//        user3.setOrders(orders2);

        orderDao.saveOrder(orders2);

        System.out.println("---------------------------");

        Orders orders4 = new Orders(LocalDate.now() ,"str.Bucuresti nr.1", StatusOrder.SHIPPED, PaymentType.CASH);

        Product product4 = new Product("Asus", "Monitor",332211);
        product4.setOrders(orders4);
        orders4.setProducts(List.of(product4));

        orders4.setUser(user2);
//        user2.setOrders(orders4);

        orderDao.saveOrder(orders4);

        Orders orderNew = new Orders(LocalDate.now(), "str.Tufanelelor nr.2", StatusOrder.PROCESSING , PaymentType.CASH);
        orderDao.updateOrder(orders4.getId(),orderNew);

//        orderDao.deleteOrder(orders4.getId());


    }
}