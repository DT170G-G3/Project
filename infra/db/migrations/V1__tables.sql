-- Main Tables

CREATE TABLE lunch_dish (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE ,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(7,2) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE carte_dish (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE ,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(7,2) NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE carte_menu(
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
) ENGINE=InnoDB;

CREATE TABLE lunch_menu(
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL UNIQUE ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
) ENGINE=InnoDB;

-- Junction Tables
-- CREATE TABLE dish_category(
--    dish_id INT NOT NULL,
--    category_id INT NOT NULL,
--    PRIMARY KEY (dish_id, category_id) ,
--    FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE ,
--    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
-- ) ENGINE=InnoDB;

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