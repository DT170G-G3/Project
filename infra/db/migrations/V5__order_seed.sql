-- -------------------------------
-- Tables
-- -------------------------------
INSERT INTO restaurant_table (seats, table_no) VALUES
(4, 1),
(4, 2),
(4, 3),
(6, 4),
(6, 5),
(6, 6),
(6, 7);

-- -------------------------------
-- Orders
-- -------------------------------
INSERT INTO table_order (order_no, note, table_id) VALUES
-- Sitting 1
(1001, "Nötallergi", 1),
-- Sitting 2
(1002, NULL, 2),
-- Sitting 3
(1003, "No onions please", 3),
-- Sitting 4
(1004, "Vegan", 4),
-- Sitting 5
(1005, "Gluten free", 5),
-- Sitting 6
(1006, NULL, 6),
-- Sitting 7
(1007, "Birthday celebration", 7);

-- -------------------------------
-- Dishes per order
-- -------------------------------
INSERT INTO table_order_carte_dish (table_order_id, carte_dish_id, quantity) VALUES
-- Order 1
(1, 1, 5),
(1, 7, 1),
(1, 15, 7),

-- Order 2
(2, 2, 2),
(2, 8, 1),
(2, 16, 3),
(2, 11, 1),

-- Order 3
(3, 3, 7),
(3, 9, 2),

-- Order 4
(4, 4, 3),
(4, 10, 2),

-- Order 5
(5, 5, 2),
(5, 12, 1),

-- Order 6
(6, 6, 4),
(6, 14, 2),

-- Order 7
(7, 7, 1),
(7, 13, 3);

-- -------------------------------
-- Drinks per order
-- -------------------------------
INSERT INTO table_order_drink (table_order_id, drink_id, quantity) VALUES
-- Order 1
(1, 1, 3),   -- Coca-Cola
(1, 6, 2),   -- Sparkling Water

-- Order 2
(2, 10, 1),
(2, 11, 1),

-- Order 3
(3, 2, 2),
(3, 7, 1),

-- Order 4
(4, 3, 2),
(4, 5, 1),

-- Order 5
(5, 4, 1),
(5, 9, 2),

-- Order 6
(6, 2, 3),
(6, 6, 1),

-- Order 7
(7, 1, 2),
(7, 8, 1);

INSERT INTO booking
(date, start_time, duration_minutes, no_of_people, note, name, email, phone_no)
VALUES

-- Booking for Sitting 1
('2026-03-01', '12:00:00', 60, 2, "Some note", 'Anna Svensson', 'anna.svensson@email.se', '0701234567'),

-- Booking for Sitting 3
('2026-03-01', '17:00:00', 90, 3, "Födelsedagsfirande", 'Johan Karlsson', 'johan.k@email.se', '0731122334'),

-- Booking for Sitting 4
('2026-03-01', '18:45:00', 90, 4, NULL, 'Lisa Andersson', 'lisa@email.se', '0723344556'),

-- Booking for Sitting 7
('2026-03-01', '12:15:00', 60, 4, NULL, 'Erik Johansson', 'erik.j@email.se', '0739876543'),

-- Booking for Sitting 12
('2026-03-02', '18:00:00', 120, 6, NULL, 'Anders Nyström', 'anders@email.se', '0731112233'),

-- Booking for Sitting 14
('2026-03-02', '12:00:00', 75, 2, NULL, 'Sara Lind', 'sara.l@email.se', '0705544332'),

-- Booking for Sitting 30
('2026-03-03', '17:45:00', 120, 6, NULL, 'Helena Sjöberg', 'helena@email.se', '0739998877'),

-- Booking for Sitting 35
('2026-03-03', '19:30:00', 120, 5, NULL, 'Camilla Dahl', 'camilla@email.se', '0763344556');