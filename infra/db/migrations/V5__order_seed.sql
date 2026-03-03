INSERT INTO restaurant_table (seats, table_no) VALUES
(4, 1),
(4, 2),
(4, 3),
(6, 4),
(6, 5),
(6, 6),
(6, 7);

INSERT INTO table_order (order_no, note, table_id) VALUES
(1001, NULL, 1),
(1002, NULL, 2),
(1003, NULL, 3),
(1004, 'Birthday celebration', 4),
(1005, 'Extra napkins requested', 5),
(1006, 'Business dinner', 6),
(1007, NULL, 7);

INSERT INTO dish_order (quantity, table_order_id, dish_id) VALUES

-- Order 1 (Table 1)
(1, 1, 1),   -- Vitlöksbröd
(2, 1, 13),  -- Oxfilé
(1, 1, 7),   -- Crème Brûlée

-- Order 2 (Table 2)
(1, 2, 2),   -- Bruschetta
(1, 2, 15),  -- Grillad Laxfilé
(1, 2, 9),   -- Pannacotta

-- Order 3 (Table 3)
(2, 3, 3),   -- Räkcocktail
(2, 3, 14),  -- Entrecôte

-- Order 4 (Birthday - Table 4)
(2, 4, 4),   -- Toast Skagen
(3, 4, 20),  -- Lammracks
(2, 4, 8),   -- Chokladfondant

-- Order 5 (Table 5)
(1, 5, 5),   -- Caprese
(1, 5, 17),  -- Vegetarisk Lasagne

-- Order 6 (Business dinner - Table 6)
(2, 6, 6),   -- Svampsoppa
(2, 6, 13),  -- Oxfilé
(1, 6, 19),  -- Torskrygg

-- Order 7 (Table 7)
(1, 7, 1),   -- Vitlöksbröd
(1, 7, 18),  -- Risotto med Tryffel
(1, 7, 10);  -- Äppelpaj

INSERT INTO drink_order (quantity, table_order_id, drink_id) VALUES

-- Order 1
(2, 1, 1),   -- Coca-Cola
(1, 1, 16),  -- House Red Wine

-- Order 2
(2, 2, 6),   -- Sparkling Water
(1, 2, 17),  -- House White Wine

-- Order 3
(2, 3, 20),  -- Draft Beer
(1, 3, 21),  -- IPA Beer

-- Order 4 (Birthday)
(1, 4, 19),  -- Champagne
(3, 4, 26),  -- Mojito
(2, 4, 11),  -- Coffee

-- Order 5
(2, 5, 5),   -- Still Water
(1, 5, 10),  -- Iced Tea

-- Order 6 (Business dinner)
(1, 6, 18),  -- Prosecco
(2, 6, 16),  -- House Red Wine
(2, 6, 29),  -- Irish Coffee

-- Order 7
(1, 7, 3),   -- Fanta
(1, 7, 4),   -- Sprite
(1, 7, 12);  -- Espresso




INSERT INTO booking
(date, start_time, duration_minutes, no_of_people, note, name, email, phone_no)
VALUES

('2026-03-01', '12:00:00', 60, 2, "Some note", 'Anna Svensson', 'anna.svensson@email.se', '0701234567'),

-- Booking for Sitting 3
('2026-03-01', '17:00:00', 90, 3, "Födelsedagsfirande", 'Johan Karlsson', 'johan.k@email.se', '0731122334'),

-- Booking for Sitting 4
('2026-03-01', '18:45:00', 90, 4, NULL, 'Lisa Andersson', 'lisa@email.se', '0723344556'),

-- Booking for Sitting 7
('2026-03-01', '12:15:00', 60, 4, NULL, 'Erik Johansson', 'erik.j@email.se', '0739876543'),

-- Booking for Sitting 8
('2026-03-01', '13:30:00', 60, 3, NULL, 'Maria Nilsson', NULL, '0761122334'),

-- Booking for Sitting 10
('2026-03-01', '19:30:00', 120, 6, NULL, 'Familjen Berg', NULL, '0709988776'),

-- Booking for Sitting 12
('2026-03-02', '18:00:00', 120, 6, NULL, 'Anders Nyström', 'anders@email.se', '0731112233'),

-- Booking for Sitting 14
('2026-03-02', '12:00:00', 75, 2, NULL, 'Sara Lind', 'sara.l@email.se', '0705544332'),

-- Booking for Sitting 15
('2026-03-02', '13:45:00', 75, 5, NULL, 'Oskar Pettersson', NULL, '0766655443'),

-- Booking for Sitting 17
('2026-03-02', '18:00:00', 120, 6, NULL, 'Karin Holm', 'karin@email.se', NULL),

-- Booking for Sitting 18
('2026-03-02', '19:50:00', 120, 4, NULL, 'Mikael Fors', NULL, '0702223344'),

-- Booking for Sitting 22
('2026-03-03', '18:15:00', 120, 3, NULL, 'Niklas Öberg', NULL, NULL),

-- Booking for Sitting 28
('2026-03-03', '12:45:00', 75, 2, NULL, 'Per Andersson', NULL, '0704433221'),

-- Booking for Sitting 30
('2026-03-03', '17:45:00', 120, 6, NULL, 'Helena Sjöberg', 'helena@email.se', '0739998877'),

-- Booking for Sitting 35
('2026-03-03', '19:30:00', 120, 5, NULL, 'Camilla Dahl', 'camilla@email.se', '0763344556');