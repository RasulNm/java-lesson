INSERT INTO users (last_name, first_name, email)
VALUES ('Иванов', 'Иван', 'ivanov@mail.ru'),
       ('Петров', 'Пётр', 'petrov@mail.ru'),
       ('Сидоров', 'Сидор', 'sidorov@mail.ru'),
       ('Смирнова', 'Анна', 'smirnova@mail.ru'),
       ('Кузнецова', 'Мария', 'kuznetsova@mail.ru');

INSERT INTO products (name, price)
VALUES ('Книга',500.00),
       ('Ручка', 50.00),
       ('Ноутбук', 55000.00),
       ('Телефон', 35000.00),
       ('Наушники', 3000.00);

INSERT INTO orders (user_id, order_date, total_amount)
VALUES (1, '2025-10-01', 550.00),
       (2, '2025-10-02', 55300.00),
       (3, '2025-10-03', 650.00),
       (1, '2025-10-05', 35000.00),
       (4, '2025-10-06', 6000.00);

INSERT INTO order_items (order_id, product_id, quantity)
VALUES (1, 1, 1),
       (1, 2, 1),
       (2, 3, 1),
       (2, 2, 6),
       (3, 1, 1),
       (3, 2, 3),
       (1, 4, 1),
       (4, 5 ,2),
       (5, 2, 10),
       (5, 1, 1);
