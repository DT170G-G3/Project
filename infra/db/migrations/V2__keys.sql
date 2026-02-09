/**
*
* This file adds the primary and foreign
* to the database tables.
*
  */


-- Primary Keys
ALTER TABLE dish ADD CONSTRAINT dish_pk PRIMARY KEY (id);
ALTER TABLE category ADD CONSTRAINT category_pk PRIMARY KEY (id);
ALTER TABLE carte_menu ADD CONSTRAINT carte_menu_pk PRIMARY KEY (id);
ALTER TABLE lunch_menu ADD CONSTRAINT lunch_menu_pk PRIMARY KEY (id);

ALTER TABLE dish_category ADD CONSTRAINT dish_category_pk PRIMARY KEY (dish_id, category_id); -- dish is category
ALTER TABLE dish_carte_menu ADD CONSTRAINT dish_carte_menu_pk PRIMARY KEY (dish_id, carte_menu_id); -- dish is on carte_menu
ALTER TABLE dish_lunch_menu ADD CONSTRAINT dish_lunch_menu_pk PRIMARY KEY (dish_id, lunch_menu_id); --dish is on lunch menu

-- Foreign  keys

ALTER TABLE dish_category ADD CONSTRAINT dish_category_dish_fk FOREIGN KEY (dish_id) REFERENCES dish(id); -- dish is category -> dish id
ALTER TABLE dish_category ADD CONSTRAINT dish_category_category_fk FOREIGN KEY (category_id) REFERENCES category(id); -- dish is category ->category id

ALTER TABLE dish_carte_menu ADD CONSTRAINT dish_carte_menu_dish_fk FOREIGN KEY (dish_id) REFERENCES dish(id); --dish is on carte_menu -> dish id
ALTER TABLE dish_carte_menu ADD CONSTRAINT dish_carte_menu_menu_fk FOREIGN KEY (carte_menu_id) REFERENCES carte_menu(id); --dish is on carte_menu -> carte_menu id

ALTER TABLE dish_lunch_menu ADD CONSTRAINT dish_lunch_menu_dish_fk FOREIGN KEY (dish_id) REFERENCES dish(id); --dish is on lunch menu -> dish id
ALTER TABLE dish_lunch_menu ADD CONSTRAINT dish_lunch_menu_menu_fk FOREIGN KEY (lunch_menu_id) REFERENCES lunch_menu(id); --dish is on lunch menu -> lunch menu id
