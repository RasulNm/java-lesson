package school.sorokin.javacore.stream.dz;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Test {
    public static void main(String[] args) {

        List<Product> products = List.of(new Product(1L, "Laptop", "Электроника", BigDecimal.valueOf(1200.00)),
                new Product(2L, "Smartphone", "Электроника", BigDecimal.valueOf(800.00)),
                new Product(3L, "Headphones", "Аксессуары", BigDecimal.valueOf(150.00)),
                new Product(4L, "Coffee Maker", "Бытовая техника", BigDecimal.valueOf(99.99)),
                new Product(5L, "Gaming Mouse", "Аксессуары", new BigDecimal("60.00")),
                new Product(6L, "Office Chair", "Мебель", new BigDecimal("250.00")),
                new Product(7L, "Backpack", "Сумки", new BigDecimal("70.00")),
                new Product(8L, "Smartwatch", "Электроника", new BigDecimal("199.99")),
                new Product(9L, "Monitor", "Электроника", new BigDecimal("300.00")),
                new Product(10L, "Bluetooth Speaker", "Электроника", new BigDecimal("85.50")));


        Order orders1 = new Order(1L, LocalDate.of(2025, 8, 24)
                , LocalDate.of(2025, 8, 26), "ДОСТАВЛЕН", new HashSet<>(List.of(products.get(1), products.get(0))));

        Order orders2 = new Order(2L, LocalDate.of(2025, 8, 20)
                , LocalDate.of(2025, 8, 24), "ОТМЕНЕН", new HashSet<>(List.of(products.get(2), products.get(4), products.get(7))));

        Order orders3 = new Order(3L, LocalDate.of(2025, 8, 23)
                , LocalDate.of(2025, 8, 21), "ОТПРАВЛЕН", new HashSet<>(List.of(products.get(8))));

        Order orders4 = new Order(4L, LocalDate.of(2025, 8, 26)
                , LocalDate.of(2025, 8, 27), "ДОСТАВЛЕН", new HashSet<>(List.of(products.get(5), products.get(6), products.get(7))));

        Order orders5 = new Order(5L, LocalDate.of(2025, 8, 27)
                , LocalDate.of(2025, 8, 29), "ОТМЕНЕН", new HashSet<>(List.of(products.get(3), products.get(0))));

        Order orders6 = new Order(6L, LocalDate.of(2025, 8, 21)
                , LocalDate.of(2025, 8, 23), "ОТПРАВЛЕН", new HashSet<>(List.of(products.get(9))));


        List<Customer> customerList = Arrays.asList(
                new Customer(1L, "Петр", 2L, new HashSet<>(List.of(orders1))),
                new Customer(2L, "Иван", 3L, new HashSet<>(List.of(orders2))),
                new Customer(3L, "Сидор", 2L, new HashSet<>(List.of(orders3))),
                new Customer(4L, "Александр", 1L, new HashSet<>(List.of(orders4))),
                new Customer(5L, "Николай", 2L, new HashSet<>(List.of(orders5))),
                new Customer(6L, "Олег", 1L, new HashSet<>(List.of(orders6)))
        );


        // Задание 1. Получите список продуктов из категории "Электроника" с ценой более 200.
        System.out.println("Задание 1. Получите список продуктов из категории \"Электроника\" с ценой более 200.");

        List<Product> products1 = customerList.stream()
                .flatMap(order -> order.getOrders().stream())
                .flatMap(pr -> pr.getProducts().stream())
                .distinct()
                .filter(namePrice -> namePrice.getCategory().equals("Электроника") && namePrice.getPrice().doubleValue() > 200)
                .toList();

        for (Product product : products1) {
            System.out.println(product);
        }
        System.out.println("--------------------------------");

        // Задание 2. Получите список заказов с продуктами из категории "Аксессуары".
        System.out.println("Задание 2. Получите список заказов с продуктами из категории \"Аксессуары\".");

        List<Order> listOrders = customerList.stream()
                .flatMap(order -> order.getOrders().stream())
                .filter(category -> category.getProducts().stream()
                        .anyMatch(categ -> categ.getCategory().equals("Аксессуары")))
                .collect(Collectors.toList());

        for (Order order : listOrders) {
            System.out.println(listOrders);
        }
        System.out.println("--------------------------------");

        // Задание 3. Получите список продуктов из категории "Электроника" и примените скидку 10% и получите сумму всех
        //продуктов.
        System.out.println("Задание 3. Получите список продуктов из категории \"Электроника\" и примените скидку 10% и получите сумму всех продуктов.");

        double sumProducts = customerList.stream()
                .flatMap(order -> order.getOrders().stream()
                        .flatMap(product -> product.getProducts().stream()))
                .distinct()
                .filter(category -> category.getCategory().equals("Электроника"))
                .map(discount -> discount.getPrice().doubleValue() - (discount.getPrice().doubleValue() * 0.1))
                .reduce(0.0, Double::sum);

        System.out.println(sumProducts);
        System.out.println("--------------------------------");

        // Задание 4. Получите список продуктов, заказанных клиентом второго уровня между 20-08-2025 и 24-08-2025.
        System.out.println("Задание 4. Получите список продуктов, заказанных клиентом второго уровня между 20-08-2025 и 24-08-2025.");

        List<Product> listProducts = customerList.stream()
                .filter(level -> level.getLevel() == 2)
                .flatMap(order -> order.getOrders().stream())
                .filter(date -> date.getOrderDate().getDayOfMonth() >= 20 && date.getOrderDate().getDayOfMonth() <= 24
                        && date.getOrderDate().getMonthValue() == 8 && date.getOrderDate().getYear() == 2025)
                .flatMap(product -> product.getProducts().stream())
                .distinct()
                .toList();

        for (Product product : listProducts) {
            System.out.println(product);
        }
        System.out.println("--------------------------------");


        // Задание 5. Получите топ 2 самые дешевые продукты из категории "Электроника".
        System.out.println("Задание 5. Получите топ 2 самые дешевые продукты из категории \"Электроника\".");

        List<Product> cheapProducts = customerList.stream()
                .flatMap(order -> order.getOrders().stream().flatMap(product -> product.getProducts().stream()))
                .filter(category -> category.getCategory().equals("Электроника"))
                .sorted(Comparator.comparing(Product::getPrice)) //.sorted((price1, price2) -> price1.getPrice().compareTo(price2.getPrice()))
                .limit(2)
                .collect(Collectors.toList());

        for (Product product : cheapProducts) {
            System.out.println(product);
        }
        System.out.println("--------------------------------");

        // Задание 6. Получите 3 самых последних сделанных заказа.
        System.out.println("Задание 6. Получите 3 самых последних сделанных заказа.");

        List<Order> ordersMade = customerList.stream()
                .flatMap(order -> order.getOrders().stream())
                .sorted((date1, date2) -> date2.getOrderDate().compareTo(date1.getOrderDate()))
                .limit(3)
                .collect(Collectors.toList());

        for (Order order : ordersMade) {
            System.out.println(order);
        }
        System.out.println("--------------------------------");

        // Задание 7. Получите список заказов, сделанных 20-08-2025, выведите id заказов в консоль и затем верните
        //список их продуктов.
        System.out.println("Задание 7. Получите список заказов, сделанных 20-08-2025, выведите id заказов в консоль и затем верните " +
                "список их продуктов.");

        Map<Long, List<Product>> products10 = customerList.stream()
                .flatMap(order -> order.getOrders().stream())
                .filter(date -> date.getOrderDate().equals(LocalDate.of(2025, 8, 20)))
                .collect(Collectors.toMap(order -> order.getId()
                        , product -> new ArrayList<>(product.getProducts())));

        for (Map.Entry<Long, List<Product>> entry : products10.entrySet()) {
            System.out.println("Заказ (id) = " + entry.getKey() + ".\nСписок продуктов: " + entry.getValue());
        }
        System.out.println("--------------------------------");

        // Задание 8.  Рассчитайте общую сумму всех заказов, сделанных в августе 2025.
        System.out.println("Задание 8.  Рассчитайте общую сумму всех заказов, сделанных в августе 2025.");

        double sumOrder = customerList.stream()
                .flatMap(order -> order.getOrders().stream())
                .filter(date -> date.getOrderDate().getMonthValue() == 8 && date.getOrderDate().getYear() == 2025)
                .flatMap(product -> product.getProducts().stream())
                .reduce(0.0, (sum1, sum2) -> sum1 + sum2.getPrice().doubleValue(),
                        Double::sum);

        System.out.println("Сумма всех заказов, сделанных в августе 2025: " + sumOrder);
        System.out.println("--------------------------------");

        // Задание 9. Рассчитайте средний платеж по заказам, сделанным 26-08-2025.
        System.out.println("Задание 9. Рассчитайте средний платеж по заказам, сделанным 26-08-2025.");

        double avg = customerList.stream()
                .flatMap(order -> order.getOrders().stream())
                .filter(date -> date.getOrderDate().isEqual(LocalDate.of(2025, 8, 26)))
                .flatMap(product -> product.getProducts().stream())
                .mapToDouble(price -> price.getPrice().doubleValue())
                .average()
                //orElse - метод класса Optional, который возвращает значение,
                // если оно есть, или переданное значение в параметре (-1.0), если значение отсутствует
                .orElse(-1.0);

        System.out.println("Cредний платеж по заказам, сделанным 26-08-2025: " + avg);
        System.out.println("--------------------------------");

        // Задание 10. Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех
        // продуктов категории "Электроника".
        System.out.println("Задание 10. Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех " +
                "продуктов категории \"Электроника\".");

        double sumProducts2 = customerList.stream()
                .flatMap(order -> order.getOrders().stream()
                        .flatMap(product -> product.getProducts().stream()))
                .filter(category -> category.getCategory().equals("Электроника"))
                .distinct()
                .mapToDouble(price -> price.getPrice().doubleValue())
                .sum();

        System.out.println("Сумма для всех продуктов категории \"Электроника\": " + sumProducts2);

        OptionalDouble avgProducts = customerList.stream()
                .flatMap(order -> order.getOrders().stream()
                        .flatMap(product -> product.getProducts().stream()))
                .filter(category -> category.getCategory().equals("Электроника"))
                .distinct()
                .mapToDouble(price -> price.getPrice().doubleValue())
                .average();

        System.out.println("Средняя цена для всех продуктов категории \"Электроника\": " + avgProducts.orElse(-1.0));

        customerList.stream()
                .flatMap(order -> order.getOrders().stream()
                        .flatMap(product -> product.getProducts().stream()))
                .filter(category -> category.getCategory().equals("Электроника"))
                .mapToDouble(price -> price.getPrice().doubleValue())
                .max()
                .ifPresent(max -> System.out.println("Максимальная цена для всех продуктов категории \"Электроника\":" + max));

        customerList.stream()
                .flatMap(order -> order.getOrders().stream()
                        .flatMap(product -> product.getProducts().stream()))
                .filter(category -> category.getCategory().equals("Электроника"))
                .mapToDouble(price -> price.getPrice().doubleValue())
                .reduce(Double::min)
                .ifPresent(min -> System.out.println("Минимальная цена для всех продуктов категории \"Электроника\":" + min));


        long countProducts = customerList.stream()
                .flatMap(order -> order.getOrders().stream()
                        .flatMap(product -> product.getProducts().stream()))
                .filter(category -> category.getCategory().equals("Электроника"))
                .distinct()
                .mapToDouble(price -> price.getPrice().doubleValue())
                .count();

        System.out.println("Количество продуктов для категории \"Электроника\": " + countProducts);
        System.out.println("--------------------------------");

        // Задание 11. Получите данные Map<Long, Integer> -> key - id заказа, value - кол-во товаров в заказе
        System.out.println("Задание 11. Получите данные Map<Long, Integer> -> key - id заказа, value - кол-во товаров в заказе");

        Map<Long, List<Integer>> countProducts2 = customerList.stream()
                .flatMap(order -> order.getOrders().stream())
                .collect(Collectors.groupingBy(Order::getId
                        , Collectors.mapping(product -> (int) product.getProducts().stream().count(), Collectors.toList())));

        for (Map.Entry<Long, List<Integer>> entry : countProducts2.entrySet()) {
            System.out.println("id заказа = " + entry.getKey() + " -> кол-во товаров в заказе = " + entry.getValue());
        }

        System.out.println("2 СПОСОБ:");

        Map<Long, Integer> countProducts3 = customerList.stream()
                .flatMap(order -> order.getOrders().stream())
                .collect(Collectors.toMap(id -> id.getId()
                        , product -> ((int) product.getProducts().stream().count())));

        for (Map.Entry<Long, Integer> entry : countProducts3.entrySet()) {
            System.out.println("id заказа = " + entry.getKey() + " -> кол-во товаров в заказе = " + entry.getValue());
        }
        System.out.println("--------------------------------");

        // Задание 12. Создайте Map<Customer, List<Order>> -> key - покупатель, value - список его заказов
        System.out.println("Задание 12. Создайте Map<Customer, List<Order>> -> key - покупатель, value - список его заказов");

        Map<Customer, List<Order>> customerListMap = customerList.stream()
                .collect(Collectors.toMap(customer -> customer, customer -> new ArrayList<>(customer.getOrders())));

        customerListMap.forEach((customer, orders) -> {
            System.out.println("Клиент: " + customer.getName() + ". Список заказов: ");
            orders.stream().forEach(listOrders2 -> listOrders2.getProducts()
                    .stream()
                    .forEach(outputList -> System.out.println(outputList.toString())));
            System.out.println();
        });

        System.out.println("--------------------------------");

        // Задание 13. Создайте Map<Order, Double> -> key - заказ, value - общая сумма продуктов заказа.
        System.out.println("Задание 13. Создайте Map<Order, Double> -> key - заказ, value - общая сумма продуктов заказа.");

        Map<Order, Double> collect = customerList.stream()
                .flatMap(order -> order.getOrders().stream())
                .collect(Collectors.toMap(order -> order,
                        sumProducts3 -> sumProducts3.getProducts().stream()
                                .mapToDouble(price -> price.getPrice().doubleValue()).sum()));

        for (Map.Entry<Order, Double> entry : collect.entrySet()) {
            System.out.println(entry.getKey() + ". Общая сумма продуктов = " + entry.getValue());
        }
        System.out.println("--------------------------------");

        // Задание 14. Получите Map<String, List<String>> -> key - категория, value - список названий товаров в категории
        System.out.println("Задание 14. Получите Map<String, List<String>> -> key - категория, value - список названий товаров в категории");
        Map<String, List<String>> listPrCategory = customerList.stream()
                .flatMap(order -> order.getOrders().stream()
                        .flatMap(product -> product.getProducts().stream()))
                .distinct()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getName, Collectors.toList())));

        for (Map.Entry<String, List<String>> entry : listPrCategory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("--------------------------------");

        // Задание 15. Получите Map<String, Product> → самый дорогой продукт по каждой категории.
        System.out.println("Задание 15. Получите Map<String, Product> → самый дорогой продукт по каждой категории.");
        Map<String, Optional<Product>> expensiveProducts = customerList.stream()
                .flatMap(order -> order.getOrders().stream()
                        .flatMap(product -> product.getProducts().stream()))
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.maxBy(Comparator.comparing(Product::getPrice))));

        for (Map.Entry<String, Optional<Product>> entry : expensiveProducts.entrySet()) {
            entry.getValue().ifPresent(t -> System.out.println("Самый дорогой продукт по категории \"" + entry.getKey()
                    + "\" -> " + t));
        }

        System.out.println("2 СПОСОБ:");

        try {
            Map<String, Product> expensiveProducts2 = customerList.stream()
                    .flatMap(order -> order.getOrders().stream()
                            .flatMap(product -> product.getProducts().stream()))
                    .collect(Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(
                            Collectors.maxBy(Comparator.comparing(Product::getPrice)), Optional::orElseThrow)));

            for (Map.Entry<String, Product> entry : expensiveProducts2.entrySet()) {
                System.out.println("Самый дорогой продукт по категории: \"" + entry.getKey() + "\" -> " + entry.getValue());
            }
        } catch (NoSuchElementException message) {
            System.out.println(message);
        }
    }
}
