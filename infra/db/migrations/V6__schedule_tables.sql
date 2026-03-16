CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    android_id varchar(25)     
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

-- Prevent duplicates
ALTER TABLE shift ADD UNIQUE (date, type_id);

CREATE TABLE swap_request (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id VARCHAR(25) NOT NULL,   
    receiver_id VARCHAR(25) NOT NULL,
    shift_id INT NOT NULL,
    status ENUM('pending', 'approved', 'rejected') NOT NULL DEFAULT 'pending',
    FOREIGN KEY (shift_id) REFERENCES shift(id) ON DELETE CASCADE   
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
INSERT INTO employee (name, android_id) VALUES
('Kalle', "cd20486bd301b60e"),
('Sigrid' , "cd20486bd301b60d"),
('Mike', "cd20486bd301b60f"),
('Karl', "cd20486bd301b601"),
('Krister', "cd20486bd301b602"),
('Kristina', "cd20486bd301b603"),
('Karin', "cd20486bd301b604"),
('Kim', "cd20486bd301b605"),
('Konan Barbaren', "cd20486bd301b608"),
('Katjakaj', "cd20486bd301b609"),
('Bentebent', "cd20486bd301b61a");

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
