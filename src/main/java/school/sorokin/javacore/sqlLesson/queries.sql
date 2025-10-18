SELECT *
FROM orders;

SELECT name, price
FROM products
WHERE price BETWEEN 50 AND 4000;

SELECT *
FROM users
WHERE first_name IN ('Иван', 'Мария');

SELECT *
FROM users
WHERE email LIKE 's%.ru';

SELECT *
FROM users
WHERE email NOT ILIKE 'S%.ru';

SELECT name, price
FROM products
ORDER BY price;

SELECT order_date, COUNT(*), SUM(total_amount)
FROM orders
WHERE order_date >= '2025-10-03'
GROUP BY order_date
HAVING SUM(total_amount) >= 1000
ORDER BY SUM(total_amount) DESC
LIMIT 1;

-- Какие товары заказал Иван и сколько денег он потратил
SELECT u.first_name, p.name, p.price, oi.quantity, oi.quantity * p.price AS item_total
FROM users u
JOIN orders o ON (u.user_id = o.user_id)
JOIN order_items oi ON (o.order_id = oi.order_id)
JOIN products p ON (oi.product_id = p.product_id)
WHERE u.first_name = 'Пётр';

SELECT u.first_name, SUM(oi.quantity * p.price) AS total_amount
FROM users u
         JOIN orders o ON (u.user_id = o.user_id)
         JOIN order_items oi ON (o.order_id = oi.order_id)
         JOIN products p ON (oi.product_id = p.product_id)
WHERE u.first_name = 'Пётр'
GROUP BY first_name;

-- Найти пользователей, которые не сделали ни одного заказа
SELECT first_name, order_id
FROM users u
         LEFT JOIN orders o ON (u.user_id = o.user_id)
WHERE o.order_id IS NULL;

SELECT * FROM users;

UPDATE users
SET last_name = 'Соколов'
WHERE user_id = 2;

SELECT * FROM users;

INSERT INTO users (last_name, first_name, email)
VALUES ('Соловьёв', 'Алейксей', 'solovyov@mail.ru');

SELECT * FROM users;

DELETE FROM users
WHERE last_name = 'Соловьёв';

SELECT * FROM users;







