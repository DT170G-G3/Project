--Main Tables
CREATE TABLE dish (
    id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(7,2) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE category (
    id INT AUTO_INCREMENT,
    name VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE carte_menu(
    id INT AUTO_INCREMENT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE lunch_menu(
    id INT AUTO_INCREMENT,
    date DATE DEFAULT CURRENT_DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

--Junction Tables
CREATE TABLE dish_category(
    dish_id INT NOT NULL,
    category_id INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE dish_carte_menu(
    dish_id INT NOT NULL,
    carte_menu_id INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE dish_lunch_menu(
    dish_id INT NOT NULL,
    lunch_menu_id INT NOT NULL
) ENGINE=InnoDB;