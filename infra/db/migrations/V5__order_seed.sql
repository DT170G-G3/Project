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
('2026-03-01', '12:15:00', 60, 4, NULL, 'Erik Johansson', 'erik2.j@email.se', '0739876543'),

-- Booking for Sitting 8
('2026-03-01', '13:30:00', 60, 3, NULL, 'Maria Nilsson', 'erik3.j@email.se', '0761122334'),

-- Booking for Sitting 10
('2026-03-01', '19:30:00', 120, 6, NULL, 'Familjen Berg', 'erik4.j@email.se', '0709988776'),

-- Booking for Sitting 12
('2026-03-02', '18:00:00', 120, 6, NULL, 'Anders Nyström', 'anders@email.se', '0731112233'),

-- Booking for Sitting 14
('2026-03-02', '12:00:00', 75, 2, NULL, 'Sara Lind', 'sara2.l@email.se', '0705544332'),

-- Booking for Sitting 15
('2026-03-02', '13:45:00', 75, 5, NULL, 'Oskar Pettersson', 'sara3.l@email.se', '0766655443'),

-- Booking for Sitting 17
('2026-03-02', '18:00:00', 120, 6, NULL, 'Karin Holm', 'karin@email.se', '0766655443'),

-- Booking for Sitting 18
('2026-03-02', '19:50:00', 120, 4, NULL, 'Mikael Fors', 'sara4.l@email.se', '0702223343'),

-- Booking for Sitting 22
('2026-03-03', '18:15:00', 120, 3, NULL, 'Niklas Öberg', 'sara5.l@email.se', '0702223342'),

-- Booking for Sitting 28
('2026-03-03', '12:45:00', 75, 2, NULL, 'Per Andersson', 'sara6.l@email.se', '0704433221'),

-- Booking for Sitting 30
('2026-03-03', '17:45:00', 120, 6, NULL, 'Helena Sjöberg', 'helena@email.se', '0739998877'),

-- Booking for Sitting 35
('2026-03-03', '19:30:00', 120, 5, NULL, 'Camilla Dahl', 'camilla@email.se', '0763344556');