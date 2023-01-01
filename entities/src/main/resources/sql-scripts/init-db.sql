INSERT INTO category (name, description) VALUES ('Electronics', 'Electronic devices');
INSERT INTO category (name, description) VALUES ('Furniture', 'Furniture for home');
INSERT INTO category (name, description) VALUES ('FRUIT', 'Fruits');
INSERT INTO category (name, description) VALUES ('VEGETABLE', 'Vegetables');
INSERT INTO cart (name, description) VALUES ('Cart 1', 'Cart 1');
INSERT INTO cart (name, description) VALUES ('Cart 2', 'Cart 2');
INSERT INTO product (name, description, category_id) VALUES ('MacbookPro', 'Display. Retina display. 13.3-inch (diagonal) LED-backlit display with IPS technology', 1);
INSERT INTO product (name, description, category_id) VALUES ('UVI chair Sport XL', 'Gaming chair', 2);
INSERT INTO customer (name, surname, username, email, cart_id) VALUES ('Petra', 'Kos', 'petra_kos', 'petra.kos@hotmail.com', 1);
INSERT INTO customer (name, surname, username, email, cart_id) VALUES ('Miha', 'Novak', 'miha_novak', 'miha.novak@gmail.com', 2);
INSERT INTO store (name) VALUES ('mimovrste');
INSERT INTO store (name) VALUES ('bigbang');
INSERT INTO store (name) VALUES ('spar')
INSERT INTO product_store_price (price, product_id, store_id) VALUES (2229, 1, 1);
INSERT INTO product_store_price (price, product_id, store_id) VALUES (2341, 1, 2);
