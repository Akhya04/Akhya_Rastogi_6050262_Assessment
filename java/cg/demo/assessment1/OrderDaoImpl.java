package cg.demo.assessment1;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class OrderDaoImpl implements OrderDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();

    @Override
    public boolean addOrder(Order order, int custId) {

        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            Customer cust = em.find(Customer.class, custId);

            if (cust == null) {
                System.out.println("Customer not found");
                return false;
            }

            order.setCustomer(cust);

            em.persist(order);

            tx.commit();

            return true;

        } catch (Exception e) {

            tx.rollback();
            return false;
        }
    }

    @Override
    public Order getOrder(int orderId) {

        return em.find(Order.class, orderId);
    }

    @Override
    public List<Order> getOrders(String custName) {

        String jpql = "select o from Order o where o.customer.customerName = :name";

        TypedQuery<Order> query = em.createQuery(jpql, Order.class);
        query.setParameter("name", custName);

        return query.getResultList();
    }
}