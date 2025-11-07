-- Партиционирование таблицы
ALTER TABLE orders
PARTITION BY RANGE (created_at);
-- не работает, потому что нельзя сделать партиционирование существующей таблицы с помощью ALTER TABLE.
-- В PostgreSQL партиционирование задаётся только при создании таблицы
-- Партиционированная таблица не может иметь первичный ключ или уникальный индекс,
-- который не включает все колонки партиционирования.
-- Создания новой таблицы с партициями
CREATE TABLE orders_partitioned (
    order_id    SERIAL NOT NULL,
    customer_id INT,
    product_id  INT,
    created_at  TIMESTAMP NOT NULL,
    amount      NUMERIC(10, 2),
    status      TEXT
) PARTITION BY RANGE (created_at);

-- Создание партиций

CREATE TABLE orders_2025_01 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-01-01') TO ('2025-02-01');

CREATE TABLE orders_2025_02 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-02-01') TO ('2025-03-01');

CREATE TABLE orders_2025_03 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-03-01') TO ('2025-04-01');

CREATE TABLE orders_2025_04 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-04-01') TO ('2025-05-01');

CREATE TABLE orders_2025_05 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-05-01') TO ('2025-06-01');

CREATE TABLE orders_2025_06 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-06-01') TO ('2025-07-01');

CREATE TABLE orders_2025_07 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-07-01') TO ('2025-08-01');

CREATE TABLE orders_2025_08 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-08-01') TO ('2025-09-01');

CREATE TABLE orders_2025_09 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-09-01') TO ('2025-10-01');

CREATE TABLE orders_2025_10 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-10-01') TO ('2025-11-01');

CREATE TABLE orders_2025_11 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-11-01') TO ('2025-12-01');

CREATE TABLE orders_2025_12 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2025-12-01') TO ('2026-01-01');

CREATE TABLE orders_2024_11 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2024-11-01') TO ('2024-12-01');

CREATE TABLE orders_2024_12 PARTITION OF orders_partitioned
    FOR VALUES FROM ('2024-12-01') TO ('2025-01-01');

-- Перенос данных
INSERT INTO orders_partitioned
SELECT * FROM orders;

-- Переименование таблиц
ALTER TABLE orders RENAME TO orders_old;
ALTER TABLE orders_partitioned RENAME TO orders;

-- Извлечение данных
EXPLAIN ANALYZE
SELECT *
FROM orders
WHERE created_at >= '2025-01-01' AND created_at < '2025-02-01';
-- Seq Scan on !!!orders_2025_01!!! orders  (cost=0.00..85954.31 rows=3568640 width=35) (actual time=0.031..1953.984 rows=3569390.00 loops=1)
-- запрос к заказам за январь теперь использует только партицию  orders_2025_01

EXPLAIN ANALYZE
SELECT o.order_id, c.name, p.name, o.amount
FROM orders o
         JOIN customers c ON o.customer_id = c.customer_id
         JOIN products p ON o.product_id = p.product_id
WHERE o.created_at BETWEEN '2025-01-01' AND '2025-01-31';
--         ->  Seq Scan on orders_2025_01 o  (cost=0.00..85954.31 rows=3452384 width=18) (actual time=0.058..886.094 rows=3455038.00 loops=1)