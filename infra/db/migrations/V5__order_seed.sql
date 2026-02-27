INSERT INTO restaurant_table (seats, table_no) VALUES
(4, 1),
(4, 2),
(4, 3),
(6, 4),
(6, 5),
(6, 6),
(6, 7);

INSERT INTO booking
(date, start_time, duration_minutes, no_of_people, note, name, email, phone_no)
VALUES
-- ===== TABLE 1 =====
('2026-03-01','12:00:00',90,4,'Lunch booking','Anna Svensson','anna@example.com','0701111111'),
('2026-03-01','13:15:00',90,2,'Quick lunch','Erik Nilsson','erik@example.com','0701111112'),

-- ===== TABLE 2 =====
('2026-03-01','12:15:00',90,4,'Business lunch','Maria Berg','maria@example.com','0701111113'),
('2026-03-01','13:30:00',90,3,NULL,'Johan Lund','johan@example.com','0701111114'),

-- ===== TABLE 3 =====
('2026-03-01','12:30:00',90,2,NULL,'Sara Holm','sara@example.com','0701111115'),
('2026-03-01','13:45:00',90,4,'Friends meetup','Oskar Lind','oskar@example.com','0701111116'),

-- ===== TABLE 4 =====
('2026-03-01','12:00:00',90,6,'Family lunch','Karin Ek','karin@example.com','0701111117'),
('2026-03-01','13:30:00',90,5,NULL,'Peter Dahl','peter@example.com','0701111118'),

-- ===== TABLE 5 =====
('2026-03-01','12:20:00',90,4,NULL,'Emma Nyström','emma@example.com','0701111119'),
('2026-03-01','13:50:00',90,6,'Birthday','Lucas Sand','lucas@example.com','0701111120'),

-- ===== TABLE 6 =====
('2026-03-01','12:45:00',90,5,NULL,'Maja Wik','maja@example.com','0701111121'),
('2026-03-01','18:00:00',90,6,'Dinner reservation','David Norén','david@example.com','0701111122'),

-- ===== TABLE 7 =====
('2026-03-01','13:00:00',90,4,NULL,'Linnea Falk','linnea@example.com','0701111123'),
('2026-03-01','18:30:00',90,7,'Evening dinner','Anton Sjöberg','anton@example.com','0701111124');

INSERT INTO sitting
(start_time, end_time, date, restaurant_table_id, booking_id)
VALUES

-- ===== TABLE 1 =====
('12:00:00','13:30:00','2026-03-01',1,1),
('13:15:00','14:45:00','2026-03-01',1,2),

-- ===== TABLE 2 =====
('12:15:00','13:45:00','2026-03-01',2,3),
('13:30:00','15:00:00','2026-03-01',2,4),

-- ===== TABLE 3 =====
('12:30:00','14:00:00','2026-03-01',3,5),
('13:45:00','15:15:00','2026-03-01',3,6),

-- ===== TABLE 4 =====
('12:00:00','13:30:00','2026-03-01',4,7),
('13:30:00','15:00:00','2026-03-01',4,8),

-- ===== TABLE 5 =====
('12:20:00','13:50:00','2026-03-01',5,9),
('13:50:00','15:20:00','2026-03-01',5,10),

-- ===== TABLE 6 =====
('12:45:00','14:15:00','2026-03-01',6,11),
('18:00:00','19:30:00','2026-03-01',6,12),

-- ===== TABLE 7 =====
('13:00:00','14:30:00','2026-03-01',7,13),
('18:30:00','20:00:00','2026-03-01',7,14);


INSERT INTO table_order (order_no, note, sitting_id) VALUES

-- Sitting 1 (busy lunch)
(1001, "Nötallergi", 1),
(1002, "Extra sauce on the side", 1),
(1003, NULL, 1),

-- Sitting 2
(1004, NULL, 2),

-- Sitting 3
(1005, "No onions please", 3),
(1006, NULL, 3),

-- Sitting 4 (evening rush)
(1007, NULL, 4),
(1008, "Gluten-free option", 4),
(1009, NULL, 4),
(1010, NULL, 4),

-- Sitting 5
(1011, NULL, 5),

-- Sitting 6
(1012, NULL, 6),
(1013, "Allergic to dairy", 6),

-- Sitting 7
(1014, NULL, 7),

-- Sitting 8
(1015, NULL, 8),
(1016, NULL, 8),
(1017, "Extra spicy", 8),

-- Sitting 9
(1018, NULL, 9),

-- Sitting 10 (large table, longer sitting)
(1019, NULL, 10),
(1020, "Birthday celebration", 10),
(1021, NULL, 10),

-- Sitting 11
(1022, NULL, 11),

-- Sitting 12
(1023, NULL, 12),
(1024, NULL, 12),

-- Sitting 13
(1025, NULL, 13),

-- Sitting 14
(1026, "Vegetarian", 14),
(1027, NULL, 14);


INSERT INTO table_order_carte_dish (table_order_id, carte_dish_id) VALUES

-- Order 1 (full meal)
(1, 1),   -- Vitlöksbröd
(1, 7),   -- Oxfilé
(1, 15),  -- Crème Brûlée

-- Order 2 (light)
(2, 2),
(2, 11),

-- Order 3 (food only)
(3, 3),
(3, 9),

-- Order 5
(5, 4),
(5, 8),
(5, 20),

-- Order 7
(7, 6),
(7, 12),

-- Order 8
(8, 7),

-- Order 9
(9, 10),
(9, 18),

-- Order 10 (big dinner)
(10, 1),
(10, 5),
(10, 14),
(10, 19),

-- Order 12
(12, 9);

INSERT INTO table_order_drink (table_order_id, drink_id) VALUES

-- Order 1 (classic)
(1, 1),   -- Coca-Cola
(1, 6),   -- Sparkling Water

-- Order 2 (drinks only)
(2, 10),
(2, 11),

-- Order 4 (only drinks)
(4, 5),
(4, 20),

-- Order 6
(6, 3),

-- Order 8
(8, 21),

-- Order 10 (party table)
(10, 20),
(10, 21),
(10, 24),

-- Order 11
(11, 1),

-- Order 14
(14, 7);