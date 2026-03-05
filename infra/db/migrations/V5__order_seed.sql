INSERT INTO restaurant_table (seats, table_no) VALUES
(4, 1),
(4, 2),
(4, 3),
(6, 4),
(6, 5),
(6, 6),
(6, 7);

INSERT INTO table_order (order_no, note, table_id) VALUES

-- Sitting 1 (busy lunch)
(1001, "Nötallergi", 1),
(1002, "Extra sauce on the side", 1),
(1003, NULL, 1),

-- Sitting 2
(1004, NULL, 2),

-- Sitting 3
(1005, "No onions please", 3),
(1006, NULL, 3);




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
(3, 9);


INSERT INTO table_order_drink (table_order_id, drink_id) VALUES

-- Order 1 (classic)
(1, 1),   -- Coca-Cola
(1, 6),   -- Sparkling Water

-- Order 2 (drinks only)
(2, 10),
(2, 11),

-- Order 3
(3, 2),
(3, 7);

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