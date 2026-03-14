package cg.demo.assessment1;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class DaoClient {

    static OrderDao dao = new OrderDaoImpl();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        String opt = null;

        do {

            System.out.println("1-ADD");
            System.out.println("2-VIEW BY ORDER ID");
            System.out.println("3-VIEW BY CUSTOMER NAME");

            int mtype = scan.nextInt();

            processMenu(mtype);

            System.out.println("press y to continue");
            opt = scan.next();

        } while (opt.equalsIgnoreCase("y"));
    }

    public static void processMenu(int mtype) {

        switch (mtype) {

        case 1:
            addOrder();
            break;

        case 2:
            viewOrderByOrderID();
            break;

        case 3:
            viewOrdersByCustName();
            break;

        default:
            System.out.println("Invalid option");
        }
    }

    public static void addOrder() {

        System.out.println("Enter Order Amount");
        double amt = scan.nextDouble();

        System.out.println("Enter Order Date (yyyy-mm-dd)");
        String d = scan.next();

        Date date = Date.valueOf(d);

        System.out.println("Enter Customer ID");
        int cid = scan.nextInt();

        Order o = new Order();
        o.setOrderAmt(amt);
        o.setOrderDate(date);

        boolean status = dao.addOrder(o, cid);

        if (status)
            System.out.println("Order Added Successfully");
        else
            System.out.println("Order Not Added");
    }

    public static void viewOrderByOrderID() {

        System.out.println("Enter Order ID");
        int id = scan.nextInt();

        Order o = dao.getOrder(id);

        if (o != null) {

            System.out.println("Order ID: " + o.getOrderId());
            System.out.println("Order Amount: " + o.getOrderAmt());
            System.out.println("Order Date: " + o.getOrderDate());

        } else {
            System.out.println("Order not found");
        }
    }

    public static void viewOrdersByCustName() {

        System.out.println("Enter Customer Name");
        String name = scan.next();

        List<Order> list = dao.getOrders(name);

        if (list.isEmpty()) {
            System.out.println("No orders found");
        }

        for (Order o : list) {

            System.out.println();
            System.out.println("Order ID: " + o.getOrderId());
            System.out.println("Order Amount: " + o.getOrderAmt());
            System.out.println("Order Date: " + o.getOrderDate());
        }
    }
}