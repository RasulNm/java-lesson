CREATE TABLE orders_audit (
    audit_id   SERIAL PRIMARY KEY,
    order_id   INT,
    old_amount NUMERIC(10, 2),
    new_amount NUMERIC(10, 2),
    changed_at TIMESTAMP DEFAULT now()
);

CREATE OR REPLACE FUNCTION log_order_change()
    RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO orders_audit (order_id, old_amount, new_amount)
    VALUES (OLD.order_id, OLD.amount, NEW.amount);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER orders_audit_trg
    AFTER UPDATE ON orders
    FOR EACH ROW
EXECUTE PROCEDURE log_order_change();

INSERT INTO orders (customer_id, product_id, created_at, amount, status)
VALUES (123, 456, '2025-04-27 14:30:00', 99.99, 'pending');

SELECT *
FROM orders
WHERE customer_id = 123 AND product_id = 456 AND created_at = '2025-04-27 14:30:00';

SELECT *
FROM orders_audit;

UPDATE orders
SET amount = 200
WHERE customer_id = 123 AND product_id = 456 AND created_at = '2025-04-27 14:30:00';

SELECT *
FROM orders_audit;

SELECT *
FROM orders
WHERE customer_id = 123 AND product_id = 456 AND created_at = '2025-04-27 14:30:00';