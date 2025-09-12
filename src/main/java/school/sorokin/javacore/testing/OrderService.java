package school.sorokin.javacore.testing;

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
}
