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
(1027, NULL, 14),

-- Sitting 15 (busy dinner)
(1028, NULL, 15),
(1029, NULL, 15),
(1030, "No peanuts", 15),
(1031, NULL, 15),

-- Sitting 16
(1032, NULL, 16),

-- Sitting 17
(1033, NULL, 17),
(1034, NULL, 17),

-- Sitting 18
(1035, "Extra napkins", 18),

-- Sitting 19
(1036, NULL, 19),
(1037, NULL, 19),

-- Sitting 20
(1038, NULL, 20),

-- Sitting 21
(1039, NULL, 21),
(1040, "Birthday cake later", 21),

-- Sitting 22
(1041, NULL, 22),

-- Sitting 23
(1042, NULL, 23),
(1043, NULL, 23),
(1044, "Allergy: shellfish", 23),

-- Sitting 24
(1045, NULL, 24),

-- Sitting 25
(1046, NULL, 25),

-- Sitting 26
(1047, NULL, 26),
(1048, NULL, 26),

-- Sitting 27
(1049, NULL, 27),

-- Sitting 28
(1050, NULL, 28),
(1051, NULL, 28),

-- Sitting 29
(1052, NULL, 29),

-- Sitting 30
(1053, NULL, 30),
(1054, NULL, 30),
(1055, "No salt", 30),

-- Sitting 31
(1056, NULL, 31),

-- Sitting 32
(1057, NULL, 32),

-- Sitting 33
(1058, NULL, 33),
(1059, NULL, 33),

-- Sitting 34
(1060, NULL, 34),

-- Sitting 35
(1061, NULL, 35),
(1062, "Extra dressing", 35),

-- Sitting 36
(1063, NULL, 36),

-- Sitting 37
(1064, NULL, 37),

-- Sitting 38
(1065, NULL, 38);

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
(date, start_time, duration_minutes, no_of_people, note, name, email, phone_no, sitting_id)
VALUES

-- Booking for Sitting 1
('2026-03-01', '12:00:00', 60, 2, "Some note", 'Anna Svensson', 'anna.svensson@email.se', '0701234567', 1),

-- Booking for Sitting 3
('2026-03-01', '17:00:00', 90, 3, "Födelsedagsfirande", 'Johan Karlsson', 'johan.k@email.se', '0731122334', 3),

-- Booking for Sitting 4
('2026-03-01', '18:45:00', 90, 4, NULL, 'Lisa Andersson', 'lisa@email.se', '0723344556', 4),

-- Booking for Sitting 7
('2026-03-01', '12:15:00', 60, 4, NULL, 'Erik Johansson', 'erik.j@email.se', '0739876543', 7),

-- Booking for Sitting 8
('2026-03-01', '13:30:00', 60, 3, NULL, 'Maria Nilsson', NULL, '0761122334', 8),

-- Booking for Sitting 10
('2026-03-01', '19:30:00', 120, 6, NULL, 'Familjen Berg', NULL, '0709988776', 10),

-- Booking for Sitting 12
('2026-03-02', '18:00:00', 120, 6, NULL, 'Anders Nyström', 'anders@email.se', '0731112233', 12),

-- Booking for Sitting 14
('2026-03-02', '12:00:00', 75, 2, NULL, 'Sara Lind', 'sara.l@email.se', '0705544332', 14),

-- Booking for Sitting 15
('2026-03-02', '13:45:00', 75, 5, NULL, 'Oskar Pettersson', NULL, '0766655443', 15),

-- Booking for Sitting 17
('2026-03-02', '18:00:00', 120, 6, NULL, 'Karin Holm', 'karin@email.se', NULL, 17),

-- Booking for Sitting 18
('2026-03-02', '19:50:00', 120, 4, NULL, 'Mikael Fors', NULL, '0702223344', 18),

-- Booking for Sitting 22
('2026-03-03', '18:15:00', 120, 3, NULL, 'Niklas Öberg', NULL, NULL, 22),

-- Booking for Sitting 28
('2026-03-03', '12:45:00', 75, 2, NULL, 'Per Andersson', NULL, '0704433221', 28),

-- Booking for Sitting 30
('2026-03-03', '17:45:00', 120, 6, NULL, 'Helena Sjöberg', 'helena@email.se', '0739998877', 30),

-- Booking for Sitting 35
('2026-03-03', '19:30:00', 120, 5, NULL, 'Camilla Dahl', 'camilla@email.se', '0763344556', 35);