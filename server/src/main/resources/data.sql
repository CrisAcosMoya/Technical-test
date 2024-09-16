-- Insert products only if they don't exist, or update them if they do
INSERT INTO products (id, name, description, price, quantity, category)
VALUES
(1, 'Product 1', 'Description of Product 1', 19.99, 50, 'Category A'),
(2, 'Product 2', 'Description of Product 2', 29.99, 30, 'Category B'),
(3, 'Product 3', 'Description of Product 3', 39.99, 20, 'Category C')
ON DUPLICATE KEY UPDATE
    name = VALUES(name),
    description = VALUES(description),
    price = VALUES(price),
    quantity = VALUES(quantity),
    category = VALUES(category);

-- Insert orders only if they don't exist, or update them if they do
INSERT INTO orders (id, product_id, quantity, total_price, status)
VALUES
(1, 1, 2, 39.98, 'Processed'),
(2, 2, 1, 29.99, 'Shipped'),
(3, 3, 3, 119.97, 'Delivered')
ON DUPLICATE KEY UPDATE
    product_id = VALUES(product_id),
    quantity = VALUES(quantity),
    total_price = VALUES(total_price),
    status = VALUES(status);


