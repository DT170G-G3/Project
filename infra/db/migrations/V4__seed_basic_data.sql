-- V2__seed_basic_data.sql

-- Roles
INSERT INTO role (name) VALUES
  ('ADMIN'),
  ('STAFF'),
  ('KITCHEN')
;

-- Users (OBS: lösenord är "plaintext demo" – byt till hash i appen senare)
INSERT INTO app_user (name, password) VALUES
  ('admin', 'admin'),
  ('staff', 'staff'),
  ('chef',  'chef')
;

-- User roles
INSERT INTO user_role (user_id, role_id)
SELECT u.id, r.id
FROM app_user u
JOIN role r ON r.name = 'ADMIN'
WHERE u.name = 'admin'
;

INSERT INTO user_role (user_id, role_id)
SELECT u.id, r.id
FROM app_user u
JOIN role r ON r.name = 'STAFF'
WHERE u.name = 'staff'
;

INSERT INTO user_role (user_id, role_id)
SELECT u.id, r.id
FROM app_user u
JOIN role r ON r.name = 'KITCHEN'
WHERE u.name = 'chef'
;

-- Restaurant tables
INSERT INTO restaurant_table (table_number, capacity) VALUES
  (1, 2),
  (2, 2),
  (3, 4),
  (4, 4),
  (5, 6)
;

-- Categories
INSERT INTO category (name) VALUES
  ('Starter'),
  ('Main'),
  ('Dessert'),
  ('Drink'),
  ('Vegetarian')
;

-- Dishes
INSERT INTO dish (name, description, price) VALUES
  ('Garlic Bread', 'Toasted bread with garlic butter', 49.00),
  ('Caesar Salad', 'Romaine, parmesan, croutons', 129.00),
  ('Cheeseburger', 'Beef patty, cheddar, fries', 169.00),
  ('Veggie Bowl', 'Seasonal veggies, grains, sauce', 159.00),
  ('Chocolate Cake', 'Rich chocolate cake', 89.00),
  ('Sparkling Water', '33 cl', 29.00)
;

-- Dish-category links (koppla några rätter)
-- (Använder INSERT..SELECT för att slippa hårdkoda ID:n)
INSERT INTO dish_category (dish_id, category_id)
SELECT d.id, c.id FROM dish d JOIN category c
WHERE d.name='Garlic Bread' AND c.name='Starter';

INSERT INTO dish_category (dish_id, category_id)
SELECT d.id, c.id FROM dish d JOIN category c
WHERE d.name='Caesar Salad' AND c.name IN ('Starter');

INSERT INTO dish_category (dish_id, category_id)
SELECT d.id, c.id FROM dish d JOIN category c
WHERE d.name='Cheeseburger' AND c.name='Main';

INSERT INTO dish_category (dish_id, category_id)
SELECT d.id, c.id FROM dish d JOIN category c
WHERE d.name='Veggie Bowl' AND c.name IN ('Main','Vegetarian');

INSERT INTO dish_category (dish_id, category_id)
SELECT d.id, c.id FROM dish d JOIN category c
WHERE d.name='Chocolate Cake' AND c.name='Dessert';

INSERT INTO dish_category (dish_id, category_id)
SELECT d.id, c.id FROM dish d JOIN category c
WHERE d.name='Sparkling Water' AND c.name='Drink';

-- Create today's lunch menu (one per date because of uq constraint in V1)
INSERT INTO lunch_menu (menu_date) VALUES (CURRENT_DATE());

-- Add two dishes to lunch menu
INSERT INTO lunch_menu_dish (lunch_menu_id, dish_id)
SELECT lm.id, d.id
FROM lunch_menu lm
JOIN dish d ON d.name IN ('Cheeseburger','Veggie Bowl')
WHERE lm.menu_date = CURRENT_DATE();

-- Create a-la-carte menu (single row)
INSERT INTO a_la_carte_menu () VALUES ();

-- Add some dishes to a-la-carte
INSERT INTO a_la_carte_menu_dish (a_la_carte_menu_id, dish_id)
SELECT m.id, d.id
FROM a_la_carte_menu m
JOIN dish d ON d.name IN ('Garlic Bread','Caesar Salad','Cheeseburger','Veggie Bowl','Chocolate Cake','Sparkling Water');
