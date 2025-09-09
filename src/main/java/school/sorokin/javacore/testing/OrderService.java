package school.sorokin.javacore.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        if (order == null) {
            throw new NullPointerException("Передан null вместо объекта Order");
        }
        int id = orderRepository.saveOrder(order);
        return (id == 0) ? "Order processing failed" : "Order processed successfully";
    }

    public double calculateTotal(int id) {
        Optional<Order> orderById = orderRepository.getOrderById(id);
        orderById.ifPresent(order -> {
            if (order.getQuantity() <= 0 || order.getUnitPrice() <= 0.0) {
                throw new IllegalArgumentException("Некорректные значения, которые могут привести к неправильному результату при умножении: " +
                        "quantity = " + order.getQuantity() + ", unitPrice = " + order.getUnitPrice());
            }
        });
        Optional<Double> totalPrice = orderById.map(Order::getTotalPrice);
        // если Optional не получит какого-нибудь значения, то метод orElse вернет 0.0
        return totalPrice.orElse(0.0);
    }


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
