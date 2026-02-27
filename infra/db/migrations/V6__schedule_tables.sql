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