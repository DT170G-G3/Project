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

-- Booking for Sitting 1
('2026-03-01', '12:00:00', 60, 2, "Some note", 'Anna Svensson', 'anna.svensson@email.se', '0701234567');
