INSERT INTO lunch_menu (date) VALUES
('2026-02-10'),
('2026-02-11'),
('2026-02-12');


-- =========================
-- LINK DISHES TO LUNCH MENUS
-- =========================

-- Lunch Menu 1 (id = 1)
INSERT INTO dish_lunch_menu (dish_id, lunch_menu_id) VALUES
(1, 1),
(2, 1),
(3, 1);

-- Lunch Menu 2 (id = 2)
INSERT INTO dish_lunch_menu (dish_id, lunch_menu_id) VALUES
(4, 2),
(5, 2),
(6, 2);

-- Lunch Menu 3 (id = 3)
INSERT INTO dish_lunch_menu (dish_id, lunch_menu_id) VALUES
(7, 3),
(8, 3),
(9, 3),
(10, 3);