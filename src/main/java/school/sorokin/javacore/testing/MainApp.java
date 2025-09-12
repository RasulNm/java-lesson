package school.sorokin.javacore.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainApp {

    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, "Апельсин", 2, 10));
        orders.add(new Order(2, "Мандарин", 3, 10));
        orders.add(new Order(3, "Груша", 0, 20));

        OrderService orderService = new OrderService(new OrderRepository() {
            @Override
            public int saveOrder(Order order) {
                orders.add(order);
                return order.getId();
            }

            @Override
            public Optional<Order> getOrderById(int id) {
                for (Order order : orders) {
                    if (order.getId() == id) {
                        return Optional.of(order);
                    }
                }
                return Optional.empty();
            }
        });

        System.out.println(orderService.calculateTotal(1));

        System.out.println("-------------------");
        System.out.println("Заказ с идентификатором 5 не будет найден, т.к. такого заказа нет в списке");
        System.out.println(orderService.calculateTotal(5));

        System.out.println("-------------------");
        try {
            System.out.println(orderService.calculateTotal(3));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }

        System.out.println("-------------------");
        try {
            System.out.println(orderService.processOrder(new Order(4, "Яблоко", 4, 120)));
            System.out.println("Список заказов:");
            for (Order order : orders) {
                System.out.println(order);
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("-------------------");
        try {
            System.out.println(orderService.processOrder(null));
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
    }
}
