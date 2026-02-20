INSERT INTO tb_category (name) VALUES ('Electronics');
INSERT INTO tb_category (name) VALUES ('Books');
INSERT INTO tb_category (name) VALUES ('Computers');

INSERT INTO tb_product (name, description, price, img_url)
VALUES ('The Lord of the Rings', 'Lorem ipsum dolor sit amet, consectetur.', 90.5, '');

INSERT INTO tb_product (name, description, price, img_url)
VALUES ('Smart TV', 'Nulla eu imperdiet purus. Maecenas ante.', 2190.0, '');

INSERT INTO tb_product (name, description, price, img_url)
VALUES ('Macbook Pro', 'Nam eleifend maximus tortor, at mollis.', 1250.0, '');

INSERT INTO tb_product (name, description, price, img_url)
VALUES ('PC Gamer', 'Donec aliquet odio ac rhoncus cursus.', 1200.0, '');

INSERT INTO tb_product (name, description, price, img_url)
VALUES ('Rails for Dummies', 'Cras fringilla convallis sem vel faucibus.', 100.99, '');

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 2);

INSERT INTO tb_user (name, email, phone, password)
VALUES ('Maria Brown', 'maria@gmail.com', '988888888', '123456');

INSERT INTO tb_user (name, email, phone, password)
VALUES ('Alex Green', 'alex@gmail.com', '977777777', '123456');

INSERT INTO tb_order (moment, order_status, client_id)
VALUES ('2019-06-20T19:53:07Z', 0, 1);

INSERT INTO tb_order (moment, order_status, client_id)
VALUES ('2019-07-21T03:42:10Z', 1, 2);

INSERT INTO tb_order (moment, order_status, client_id)
VALUES ('2019-07-22T15:21:22Z', 0, 1);

INSERT INTO tb_order_item (order_id, product_id, quantity, price)
VALUES (1, 1, 2, 90.5);

INSERT INTO tb_order_item (order_id, product_id, quantity, price)
VALUES (1, 3, 1, 1250.0);

INSERT INTO tb_order_item (order_id, product_id, quantity, price)
VALUES (2, 3, 2, 1250.0);

INSERT INTO tb_order_item (order_id, product_id, quantity, price)
VALUES (3, 5, 2, 100.99);

INSERT INTO tb_payment (moment, order_id)
VALUES ('2019-06-20T21:53:07Z', 2);
