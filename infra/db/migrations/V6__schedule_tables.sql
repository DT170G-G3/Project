CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL        
) ENGINE=InnoDB;

CREATE TABLE shift_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL    
) ENGINE=InnoDB;

CREATE TABLE shift (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    type_id INT NOT NULL,
    FOREIGN KEY (type_id) REFERENCES shift_type(id) ON DELETE CASCADE    
) ENGINE=InnoDB;

-- Junction Table

CREATE TABLE employee_works_shift(
    employee_id INT NOT NULL ,
    shift_id INT NOT NULL ,
    PRIMARY KEY (employee_id, shift_id) ,
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE ,
    FOREIGN KEY (shift_id) REFERENCES shift(id) ON DELETE CASCADE
) ENGINE=InnoDB;


-- Insert Into Employee
INSERT INTO employee (name) VALUES
('Kalle'),
('Karl'),
('Krister'),
('Kristina'),
('Karin'),
('Kim'),
('Kanelbullen'),
('Gud'),
('Konan Barbaren'),
('Katjakaj'),
('Bentebent'),
('En Tallrik Musli');

-- Insert Into Shift Type
INSERT INTO shift_type (name, start_time, end_time) VALUES
('Lunch', '10:00:00', '15:00:00'),
('Middag', '16:00:00', '22:00:00');

-- Insert Into Shift
INSERT INTO shift (date, type_id) VALUES
('2026-02-27', 1),
('2026-02-27', 2),
('2026-02-28', 1),
('2026-02-28', 2);

-- Insert Into Employee Works Shift
INSERT INTO employee_works_shift (employee_id, shift_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(5, 2),
(6, 2),
(7, 3),
(8, 3),
(9, 3),
(10, 4),
(11, 4),
(12, 4);
