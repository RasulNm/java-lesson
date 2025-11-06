-- Создаём 10 000 клиентов
INSERT INTO customers (name, email)
SELECT
    'Customer ' || gs AS name,
    'customer' || gs || '@example.com' AS email
FROM generate_series(1, 10000) AS gs;

-- Создаём 1 000 продуктов
INSERT INTO products (name, price)
SELECT
    'Product ' || gs AS name,
    ROUND((random() * 1000 + 10)::numeric, 2) AS price  -- цена 10..1010, 2 знака после запятой
FROM generate_series(1, 1000) AS gs;

DO $$
    DECLARE
i INT;
BEGIN
FOR i IN 1..42 LOOP  -- 42 пакетов × 1 млн = 42 млн строк
        INSERT INTO orders (customer_id, product_id, created_at, amount, status)
SELECT
    (random() * 9999 + 1)::INT,                  -- случайный customer_id 1..10000
        (random() * 999 + 1)::INT,                   -- случайный product_id 1..1000
        NOW() - (random() * INTERVAL '365 days'),    -- случайная дата за последний год
    ROUND((random() * 1000 + 10)::numeric, 2),  -- сумма 10..1010, 2 знака после запятой
    CASE WHEN random() < 0.7 THEN 'completed'
         WHEN random() < 0.85 THEN 'pending'
         ELSE 'cancelled' END                   -- статус с разной вероятностью
FROM generate_series(1, 1000000);  -- вставляем 1 млн строк за раз
END LOOP;
END $$;
