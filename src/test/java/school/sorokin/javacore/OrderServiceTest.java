package school.sorokin.javacore;

import org.junit.jupiter.api.Test;
import school.sorokin.javacore.testing.Order;
import school.sorokin.javacore.testing.OrderRepository;
import school.sorokin.javacore.testing.OrderService;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    @Test
    void shouldProcessOrderSuccessfully() {
        // Arrange: создаётся мок для OrderRepository и настраиваем его поведение.
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order(1, "Мандарин", 4, 10);
        when(orderRepository.saveOrder(order)).thenReturn(1);

        // Создаётся объект OrderService с замоканным репозиторием.
        OrderService orderService = new OrderService(orderRepository);

        //Act: вызывается тестируемый метод.
        String result = orderService.processOrder(order);

        //Assert. Проверяется, что метод вернул ожидаемую строку
        // и что метод saveOrder(order) был вызван ровно один раз.
        assertEquals("Order processed successfully", result);
        verify(orderRepository, times(1)).saveOrder(order);
    }

    @Test
    void shouldThrowExceptionToProcessOrder() {
        // Arrange: создаётся мок для OrderRepository. Поведение не настравиваем, так как saveOrder() не вызывается.
        OrderRepository orderRepository = mock(OrderRepository.class);
        // Создаём объект OrderService с замоканным репозиторием.
        OrderService orderService = new OrderService(orderRepository);

        //Act & Assert
        Exception ex = assertThrows(NullPointerException.class, () -> orderService.processOrder(null));
        assertEquals("Передан null вместо объекта Order", ex.getMessage());
        // метод saveOrder() не должен быть вызван
        verify(orderRepository, times(0)).saveOrder(null);
    }

    @Test
    void shouldFaildToProcessOrder_whenOrderIdIsZero() {
        // Arrange: создаём мок для OrderRepository.
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order(0, "Мандарин", 4, 10);
        when(orderRepository.saveOrder(order)).thenReturn(0);

        OrderService orderService = new OrderService(orderRepository);
        // Act. Вызов тестируемого метода
        String result = orderService.processOrder(order);
        // Assert. Проверка результат
        assertEquals("Order processing failed", result);
        verify(orderRepository, times(1)).saveOrder(order);
    }

    @Test
    void shouldCalculateTotalSuccessfully() {
        // Arrange
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order(1, "Мандарин", 3, 300);
        when(orderRepository.getOrderById(order.getId())).thenReturn(Optional.of(order));
        // Создаём объект OrderService с замоканным репозиторием.
        OrderService orderService = new OrderService(orderRepository);

        // Act. Вызов тестируемого метода
        double result = orderService.calculateTotal(order.getId());

        // Assert. Проверка результат
        assertEquals(900.0, result, "3 * 300 = 900");
        verify(orderRepository, times(1)).getOrderById(order.getId());
    }

    @Test
    void shouldThrowIllegalArgumentException(){
        // Arrange
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order(1, "Мандарин", 3, 0);
        when(orderRepository.getOrderById(order.getId())).thenReturn(Optional.of(order));
        // Создаём объект OrderService с замоканным репозиторием.
        OrderService orderService = new OrderService(orderRepository);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> orderService.calculateTotal(order.getId()));
        assertEquals("Некорректные значения, которые могут привести к неправильному результату при умножении: " +
                "quantity = " + order.getQuantity() + ", unitPrice = " + order.getUnitPrice(), exception.getMessage());
        verify(orderRepository, times(1)).getOrderById(order.getId());

    }

    @Test
    void shouldReturnZeroWhenOrderNotFound(){
        // Arrange: создаём мок OrderRepository и настраиваем поведение. Заказ с id = 0 не найден
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.getOrderById(0)).thenReturn(Optional.empty());

        // Создаём объект OrderService с замоканным репозиторием.
        OrderService orderService = new OrderService(orderRepository);

        // Act:  вызываем метод с несуществующим ID
        double result = orderService.calculateTotal(0);

        // Assert: ожидаем, что метод вернёт 0.0, так как заказа нет
        assertEquals(0.0, result);
        verify(orderRepository, times(1)).getOrderById(0);
    }
}
