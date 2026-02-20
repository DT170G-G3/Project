INSERT INTO restaurant_table (seats, table_no) VALUES
                                                   (4, 1),
                                                   (4, 2),
                                                   (4, 3),
                                                   (6, 4),
                                                   (6, 5),
                                                   (6, 6),
                                                   (6, 7);

INSERT INTO sitting (start_time, date, duration_minutes, restaurant_table_id) VALUES

-- ===== TABLE 1 (4 seats) =====
('12:00:00', '2026-03-01', 60, 1),
('13:15:00', '2026-03-01', 60, 1),
('17:00:00', '2026-03-01', 90, 1),
('18:45:00', '2026-03-01', 90, 1),
('20:30:00', '2026-03-01', 90, 1),
('17:30:00', '2026-03-02', 90, 1),

-- ===== TABLE 2 =====
('12:15:00', '2026-03-01', 60, 2),
('13:30:00', '2026-03-01', 60, 2),
('17:15:00', '2026-03-01', 90, 2),
('19:00:00', '2026-03-01', 90, 2),
('20:45:00', '2026-03-02', 90, 2),
('18:00:00', '2026-03-03', 90, 2),

-- ===== TABLE 3 =====
('12:30:00', '2026-03-01', 60, 3),
('13:45:00', '2026-03-01', 60, 3),
('17:30:00', '2026-03-02', 90, 3),
('19:15:00', '2026-03-02', 90, 3),
('20:50:00', '2026-03-03', 90, 3),

-- ===== TABLE 4 (6 seats) =====
('12:00:00', '2026-03-01', 75, 4),
('13:30:00', '2026-03-01', 75, 4),
('17:00:00', '2026-03-01', 120, 4),
('19:30:00', '2026-03-01', 120, 4),
('18:00:00', '2026-03-02', 120, 4),
('20:30:00', '2026-03-03', 120, 4),

-- ===== TABLE 5 =====
('12:20:00', '2026-03-01', 75, 5),
('13:50:00', '2026-03-01', 75, 5),
('17:20:00', '2026-03-02', 120, 5),
('19:50:00', '2026-03-02', 120, 5),
('18:15:00', '2026-03-03', 120, 5),

-- ===== TABLE 6 =====
('12:45:00', '2026-03-01', 75, 6),
('18:00:00', '2026-03-01', 120, 6),
('20:15:00', '2026-03-02', 120, 6),
('17:45:00', '2026-03-03', 120, 6),
('19:45:00', '2026-03-03', 120, 6),

-- ===== TABLE 7 =====
('13:00:00', '2026-03-01', 75, 7),
('18:30:00', '2026-03-01', 120, 7),
('20:30:00', '2026-03-01', 120, 7),
('17:30:00', '2026-03-02', 120, 7),
('19:30:00', '2026-03-03', 120, 7);

INSERT INTO table_order (order_no, sitting_id) VALUES

-- Sitting 1 (busy lunch)
(1001, 1),
(1002, 1),
(1003, 1),

-- Sitting 2
(1004, 2),

-- Sitting 3
(1005, 3),
(1006, 3),

-- Sitting 4 (evening rush)
(1007, 4),
(1008, 4),
(1009, 4),
(1010, 4),

-- Sitting 5
(1011, 5),

-- Sitting 6
(1012, 6),
(1013, 6),

-- Sitting 7
(1014, 7),

-- Sitting 8
(1015, 8),
(1016, 8),
(1017, 8),

-- Sitting 9
(1018, 9),

-- Sitting 10 (large table, longer sitting)
(1019, 10),
(1020, 10),
(1021, 10),

-- Sitting 11
(1022, 11),

-- Sitting 12
(1023, 12),
(1024, 12),

-- Sitting 13
(1025, 13),

-- Sitting 14
(1026, 14),
(1027, 14),

-- Sitting 15 (busy dinner)
(1028, 15),
(1029, 15),
(1030, 15),
(1031, 15),

-- Sitting 16
(1032, 16),

-- Sitting 17
(1033, 17),
(1034, 17),

-- Sitting 18
(1035, 18),

-- Sitting 19
(1036, 19),
(1037, 19),

-- Sitting 20
(1038, 20),

-- Sitting 21
(1039, 21),
(1040, 21),

-- Sitting 22
(1041, 22),

-- Sitting 23
(1042, 23),
(1043, 23),
(1044, 23),

-- Sitting 24
(1045, 24),

-- Sitting 25
(1046, 25),

-- Sitting 26
(1047, 26),
(1048, 26),

-- Sitting 27
(1049, 27),

-- Sitting 28
(1050, 28),
(1051, 28),

-- Sitting 29
(1052, 29),

-- Sitting 30
(1053, 30),
(1054, 30),
(1055, 30),

-- Sitting 31
(1056, 31),

-- Sitting 32
(1057, 32),

-- Sitting 33
(1058, 33),
(1059, 33),

-- Sitting 34
(1060, 34),

-- Sitting 35
(1061, 35),
(1062, 35),

-- Sitting 36
(1063, 36),

-- Sitting 37
(1064, 37),

-- Sitting 38
(1065, 38);

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
(12, 9),

-- Order 15
(15, 2),
(15, 13),

-- Order 18
(18, 16),

-- Order 19
(19, 17),
(19, 20),

-- Order 21
(21, 8),

-- Order 23
(23, 7),
(23, 15),

-- Order 24
(24, 10),

-- Order 27
(27, 3),
(27, 14),

-- Order 30 (large table)
(30, 7),
(30, 8),
(30, 19),
(30, 20),

-- Order 31
(31, 11),

-- Order 33
(33, 12),
(33, 18),

-- Order 36
(36, 13),

-- Order 40
(40, 9),
(40, 15),

-- Order 44
(44, 14),

-- Order 48
(48, 6),
(48, 16),

-- Order 52
(52, 7),

-- Order 55
(55, 8),
(55, 19),

-- Order 60
(60, 10),

-- Order 65
(65, 20);

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
(14, 7),

-- Order 17
(17, 18),

-- Order 19
(19, 25),

-- Order 22
(22, 6),

-- Order 23
(23, 4),

-- Order 26
(26, 12),

-- Order 28
(28, 13),
(28, 14),

-- Order 30
(30, 20),
(30, 22),

-- Order 34
(34, 8),

-- Order 37
(37, 9),

-- Order 41
(41, 21),

-- Order 45
(45, 23),

-- Order 50
(50, 1),
(50, 2),

-- Order 58
(58, 27),

-- Order 63
(63, 24),

-- Order 65
(65, 15);

INSERT INTO booking
(date, start_time, duration_minutes, no_of_people, name, email, phone_no, sitting_id)
VALUES

-- Booking for Sitting 1
('2026-03-01', '12:00:00', 60, 2, 'Anna Svensson', 'anna.svensson@email.se', '0701234567', 1),

-- Booking for Sitting 3
('2026-03-01', '17:00:00', 90, 3, 'Johan Karlsson', 'johan.k@email.se', '0731122334', 3),

-- Booking for Sitting 4
('2026-03-01', '18:45:00', 90, 4, 'Lisa Andersson', 'lisa@email.se', '0723344556', 4),

-- Booking for Sitting 7
('2026-03-01', '12:15:00', 60, 4, 'Erik Johansson', 'erik.j@email.se', '0739876543', 7),

-- Booking for Sitting 8
('2026-03-01', '13:30:00', 60, 3, 'Maria Nilsson', NULL, '0761122334', 8),

-- Booking for Sitting 10
('2026-03-01', '19:30:00', 120, 6, 'Familjen Berg', NULL, '0709988776', 10),

-- Booking for Sitting 12
('2026-03-02', '18:00:00', 120, 6, 'Anders Nyström', 'anders@email.se', '0731112233', 12),

-- Booking for Sitting 14
('2026-03-02', '12:00:00', 75, 2, 'Sara Lind', 'sara.l@email.se', '0705544332', 14),

-- Booking for Sitting 15
('2026-03-02', '13:45:00', 75, 5, 'Oskar Pettersson', NULL, '0766655443', 15),

-- Booking for Sitting 17
('2026-03-02', '18:00:00', 120, 6, 'Karin Holm', 'karin@email.se', NULL, 17),

-- Booking for Sitting 18
('2026-03-02', '19:50:00', 120, 4, 'Mikael Fors', NULL, '0702223344', 18),

-- Booking for Sitting 22
('2026-03-03', '18:15:00', 120, 3, 'Niklas Öberg', NULL, NULL, 22),

-- Booking for Sitting 28
('2026-03-03', '12:45:00', 75, 2, 'Per Andersson', NULL, '0704433221', 28),

-- Booking for Sitting 30
('2026-03-03', '17:45:00', 120, 6, 'Helena Sjöberg', 'helena@email.se', '0739998877', 30),

-- Booking for Sitting 35
('2026-03-03', '19:30:00', 120, 5, 'Camilla Dahl', 'camilla@email.se', '0763344556', 35);