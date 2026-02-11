-- V1__baseline.sql

CREATE TABLE app_user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE role (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE user_role (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE,
  CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE dish (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description TEXT,
  price DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE category (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE dish_category (
  dish_id INT NOT NULL,
  category_id INT NOT NULL,
  PRIMARY KEY (dish_id, category_id),
  CONSTRAINT fk_dish_category_dish FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE,
  CONSTRAINT fk_dish_category_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE lunch_menu (
  id INT AUTO_INCREMENT PRIMARY KEY,
  menu_date DATE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT uq_lunch_menu_date UNIQUE (menu_date)
) ENGINE=InnoDB;

CREATE TABLE lunch_menu_dish (
  lunch_menu_id INT NOT NULL,
  dish_id INT NOT NULL,
  PRIMARY KEY (lunch_menu_id, dish_id),
  CONSTRAINT fk_lunch_menu_dish_menu FOREIGN KEY (lunch_menu_id) REFERENCES lunch_menu(id) ON DELETE CASCADE,
  CONSTRAINT fk_lunch_menu_dish_dish FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE a_la_carte_menu (
  id INT AUTO_INCREMENT PRIMARY KEY,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE a_la_carte_menu_dish (
  a_la_carte_menu_id INT NOT NULL,
  dish_id INT NOT NULL,
  PRIMARY KEY (a_la_carte_menu_id, dish_id),
  CONSTRAINT fk_alacarte_menu_dish_menu FOREIGN KEY (a_la_carte_menu_id) REFERENCES a_la_carte_menu(id) ON DELETE CASCADE,
  CONSTRAINT fk_alacarte_menu_dish_dish FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE restaurant_table (
  id INT AUTO_INCREMENT PRIMARY KEY,
  table_number INT NOT NULL UNIQUE,
  capacity INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sales_order (
  id INT AUTO_INCREMENT PRIMARY KEY,
  table_id INT NULL,
  status VARCHAR(30) NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_sales_order_table FOREIGN KEY (table_id) REFERENCES restaurant_table(id) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE order_dish (
  id INT AUTO_INCREMENT PRIMARY KEY,
  order_id INT NOT NULL,
  dish_id INT NOT NULL,
  quantity INT NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  CONSTRAINT fk_order_dish_order FOREIGN KEY (order_id) REFERENCES sales_order(id) ON DELETE CASCADE,
  CONSTRAINT fk_order_dish_dish FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE kitchen_order (
  id INT AUTO_INCREMENT PRIMARY KEY,
  order_id INT NOT NULL,
  status VARCHAR(30) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_kitchen_order_order FOREIGN KEY (order_id) REFERENCES sales_order(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE booking (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100),
  phone_no VARCHAR(30),
  booking_date DATE NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  no_of_persons INT NOT NULL,
  table_id INT NULL,
  CONSTRAINT fk_booking_table FOREIGN KEY (table_id) REFERENCES restaurant_table(id) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE shift (
  id INT AUTO_INCREMENT PRIMARY KEY,
  employee_id INT NULL,
  shift_date DATE NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  CONSTRAINT fk_shift_employee FOREIGN KEY (employee_id) REFERENCES app_user(id) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE post (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  description TEXT,
  event_date DATE,
  start_time TIME,
  end_time TIME,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by INT NULL,
  CONSTRAINT fk_post_created_by FOREIGN KEY (created_by) REFERENCES app_user(id) ON DELETE SET NULL
) ENGINE=InnoDB;
