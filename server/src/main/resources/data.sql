-- Insertar productos
INSERT INTO products (id, name, description, price, quantity, category)
VALUES
(1, 'Product 1', 'Description of Product 1', 19.99, 50, 'Category A'),
(2, 'Product 2', 'Description of Product 2', 29.99, 30, 'Category B'),
(3, 'Product 3', 'Description of Product 3', 39.99, 20, 'Category C')
ON CONFLICT (id) DO UPDATE SET
    name = EXCLUDED.name,
    description = EXCLUDED.description,
    price = EXCLUDED.price,
    quantity = EXCLUDED.quantity,
    category = EXCLUDED.category;

-- Insertar órdenes
INSERT INTO orders (id, product_id, quantity, total_price, status)
VALUES
(1, 1, 2, 39.98, 'Processed'),
(2, 2, 1, 29.99, 'Shipped'),
(3, 3, 3, 119.97, 'Delivered')
ON CONFLICT (id) DO UPDATE SET
    product_id = EXCLUDED.product_id,
    quantity = EXCLUDED.quantity,
    total_price = EXCLUDED.total_price,
    status = EXCLUDED.status;


