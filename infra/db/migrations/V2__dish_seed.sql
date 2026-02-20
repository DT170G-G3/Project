INSERT INTO category (name) VALUES
('starter'),
('main'),
('dessert');

INSERT INTO lunch_dish (name, description, price) VALUES
('Margherita Pizza', 'Klassisk pizza med tomatsås, mozzarella och färsk basilika', 95.00),
('Pepperoni Pizza', 'Pizza toppad med pepperoni och smält mozzarella', 105.00),
('Spaghetti Carbonara', 'Pasta med ägg, pancetta, pecorino och svartpeppar', 115.00),
('Lasagne Bolognese', 'Ugnsbakad lasagne med köttfärssås och béchamelsås', 125.00),
('Kyckling Alfredo', 'Krämig pastarätt med alfredosås och grillad kyckling', 119.00),
('Caesarsallad', 'Romansallad med caesardressing, krutonger och parmesan', 89.00),
('Grekisk Sallad', 'Sallad med tomat, gurka, oliver, rödlök och fetaost', 85.00),
('Hamburgare', 'Grillad nötburgare med sallad, tomat och dressing', 99.00),
('Cheeseburgare', 'Hamburgare med smält cheddarost och klassiska tillbehör', 105.00),
('Kycklingburgare', 'Krispig kycklingfilé med sallad och majonnäs', 95.00),
('Pommes Frites', 'Gyllenfriterade potatisstavar med salt', 45.00),
('Kycklingvingar', 'Kryddstarka kycklingvingar serverade med dippsås', 79.00),
('Grillad Lax', 'Grillad laxfilé med citron och färska örter', 145.00),
('Fish and Chips', 'Friterad fisk med pommes frites och remouladsås', 110.00),
('Steak Frites', 'Grillad biff serverad med pommes frites och bearnaisesås', 165.00),
('Svamprisotto', 'Krämig risotto med svamp och parmesanost', 109.00),
('Tomatsoppa', 'Varm tomatsoppa serverad med bröd', 55.00),
('Kycklingsoppa', 'Klassisk kycklingsoppa med grönsaker', 65.00),
('Pannkakor', 'Fluffiga pannkakor serverade med sylt och grädde', 75.00);

INSERT INTO carte_dish (name, description, price, category_id) VALUES

('Vitlöksbröd', 'Rostat bröd med vitlökssmör och persilja', 55.00, 1),
('Bruschetta', 'Grillat bröd med tomat, vitlök och basilika', 65.00, 1),
('Räkcocktail', 'Handskalade räkor med krispig sallad och Rhode Island-sås', 95.00, 1),
('Toast Skagen', 'Räkröra med dill och citron på rostat bröd', 110.00, 1),
('Caprese', 'Mozzarella, tomater och basilika med olivolja', 85.00, 1),
('Svampsoppa', 'Krämig svampsoppa toppad med färska örter', 75.00, 1),

('Oxfilé', 'Grillad oxfilé med rödvinssås och potatisgratäng', 295.00, 2),
('Entrecôte', 'Saftig entrecôte med bearnaisesås och pommes frites', 265.00, 2),
('Grillad Laxfilé', 'Lax serverad med citronsås och säsongens grönsaker', 225.00, 2),
('Kycklingfilé', 'Grillad kycklingfilé med örtsås och rostade rotfrukter', 195.00, 2),
('Vegetarisk Lasagne', 'Lasagne med grönsaker, tomatsås och ost', 175.00, 2),
('Risotto med Tryffel', 'Krämig risotto med tryffelolja och parmesan', 210.00, 2),
('Torskrygg', 'Ugnsbakad torskrygg med smörsås och potatis', 235.00, 2),
('Lammracks', 'Örtkryddade lammracks med vitlökssky', 285.00, 2),


('Crème Brûlée', 'Vaniljkräm med karamelliserat socker', 85.00, 3),
('Chokladfondant', 'Varm chokladkaka med rinnande kärna', 95.00, 3),
('Pannacotta', 'Vaniljpannacotta med bärsås', 75.00, 3),
('Äppelpaj', 'Serveras varm med vaniljsås', 70.00, 3),
('Glass och Sorbet', 'Urval av glass och sorbet', 65.00, 3),
('Tiramisu', 'Klassisk italiensk dessert med kaffe och mascarpone', 90.00, 3);