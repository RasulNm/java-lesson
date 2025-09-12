package school.sorokin.javacore.testing;

import java.util.Optional;

public interface OrderRepository {
    //сохраняет заказ и возвращает id заказа, если операция успешна.
    int saveOrder(Order order);

    //возвращает заказ по идентификатору. Если такого заказа нет, то пустой Optional
    Optional<Order> getOrderById(int id);
}
