-- Main Tables

CREATE TABLE lunch_dish (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(7,2) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE drink (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    price DECIMAL(6,2) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE type_of (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    display_order INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE carte_menu(
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
) ENGINE=InnoDB;

CREATE TABLE carte_dish (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE ,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(7,2) NOT NULL,
    category_id INT,
    type_of_id INT,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE,
    FOREIGN KEY (type_of_id) REFERENCES type_of(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE lunch_menu(
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL UNIQUE ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
) ENGINE=InnoDB;

CREATE TABLE restaurant_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seats INT NOT NULL,
    table_no INT NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE table_order(
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    order_no INT,
    note VARCHAR(255) DEFAULT NULL,
    table_id INT NOT NULL,
    FOREIGN KEY (table_id) REFERENCES restaurant_table(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE booking(
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    duration_minutes INT NOT NULL,
    no_of_people INT NOT NULL,
    note VARCHAR(255) DEFAULT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    phone_no VARCHAR(30)
) ENGINE=InnoDB;

-- Junction Tables

CREATE TABLE dish_carte_menu(
    dish_id INT NOT NULL ,
    carte_menu_id INT NOT NULL ,
    PRIMARY KEY (dish_id, carte_menu_id) ,
    FOREIGN KEY (dish_id) REFERENCES carte_dish(id) ON DELETE CASCADE ,
    FOREIGN KEY (carte_menu_id) REFERENCES carte_menu(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE dish_lunch_menu(
    dish_id INT NOT NULL ,
    lunch_menu_id INT NOT NULL ,
    PRIMARY KEY (dish_id, lunch_menu_id) ,
    FOREIGN KEY (dish_id) REFERENCES lunch_dish(id) ON DELETE CASCADE ,
    FOREIGN KEY (lunch_menu_id) REFERENCES lunch_menu(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE table_order_carte_dish(
    table_order_id INT NOT NULL,
    carte_dish_id INT NOT NULL,
    PRIMARY KEY (table_order_id, carte_dish_id),
    FOREIGN KEY (table_order_id) REFERENCES table_order(id) ON DELETE CASCADE,
    FOREIGN KEY (carte_dish_id) REFERENCES carte_dish(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE table_order_drink(
    table_order_id INT NOT NULL,
    drink_id INT NOT NULL,
    PRIMARY KEY (table_order_id, drink_id),
    FOREIGN KEY (table_order_id) REFERENCES table_order(id) ON DELETE CASCADE,
    FOREIGN KEY (drink_id) REFERENCES drink(id) ON DELETE CASCADE
) ENGINE=InnoDB;