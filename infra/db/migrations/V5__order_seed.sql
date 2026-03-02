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

INSERT INTO table_order_carte_dish (table_order_id, carte_dish_id) VALUES
(1, 1),
(1, 13),

(2, 2),
(2, 14),

(3, 5),
(3, 17),

(4, 4),
(4, 20),

(5, 3),
(5, 15),

(6, 6),
(6, 18),

(7, 1),
(7, 16);

INSERT INTO table_order_drink (table_order_id, drink_id) VALUES
(1, 1),
(1, 4),

(2, 2),

(3, 3),

(4, 1),
(4, 2),

(5, 4),

(6, 2),
(6, 3),

(7, 1);

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