INSERT INTO categories (name)
VALUES ('Fruits'),
       ('Dairy'),
       ('Beverages'),
       ('Bakery'),
       ('Snacks');

INSERT INTO products (name, price, description, category_id)
VALUES ('Bananas (1kg)', 1.29, 'Fresh yellow bananas, approx. 6–7 pieces per kilo.', 1),
       ('Whole Milk (1L)', 1.09, 'Full-fat pasteurized cow milk, rich in calcium.', 2),
       ('Orange Juice (1L)', 1.89, '100% pure orange juice, no added sugar or preservatives.', 3),
       ('Whole Wheat Bread (500g)', 2.49, 'Freshly baked whole grain bread with a soft center.', 4),
       ('Cheddar Cheese (200g)', 2.99, 'Matured cheddar cheese block, sharp flavor.', 2),
       ('Chocolate Chip Cookies (pack of 10)', 2.19, 'Crunchy cookies loaded with dark chocolate chips.', 5),
       ('Apples (1kg)', 2.59, 'Crisp red apples, ideal for snacking and baking.', 1),
       ('Sparkling Water (1.5L)', 0.99, 'Carbonated mineral water, refreshing and calorie-free.', 3),
       ('Croissants (4 pcs)', 3.49, 'Buttery and flaky classic French-style croissants.', 4),
       ('Yogurt Natural (500g)', 1.59, 'Plain Greek-style yogurt, high in protein.', 2);
