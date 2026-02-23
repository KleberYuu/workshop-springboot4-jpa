-- =========================
-- CATEGORY
-- =========================
create table tb_category (
    id bigserial primary key,
    name varchar(255)
);

-- =========================
-- USER
-- =========================
create table tb_user (
    id bigserial primary key,
    name varchar(255),
    email varchar(255) unique,
    phone varchar(255),
    password varchar(255)
);

-- =========================
-- PRODUCT
-- =========================
create table tb_product (
    id bigserial primary key,
    name varchar(255) unique,
    description varchar(255),
    img_url varchar(255),
    price double precision
);

-- =========================
-- ORDER
-- =========================
create table tb_order (
    id bigserial primary key,
    moment timestamp with time zone,
    order_status integer,
    client_id bigint references tb_user(id)
);

-- =========================
-- ORDER ITEM
-- =========================
create table tb_order_item (
    order_id bigint not null,
    product_id bigint not null,
    quantity integer,
    price double precision,
    primary key (order_id, product_id),
    foreign key (order_id) references tb_order(id),
    foreign key (product_id) references tb_product(id)
);

-- =========================
-- PAYMENT
-- =========================
create table tb_payment (
    order_id bigint primary key,
    moment timestamp with time zone,
    foreign key (order_id) references tb_order(id)
);

-- =========================
-- PRODUCT CATEGORY
-- =========================
create table tb_product_category (
    product_id bigint not null,
    category_id bigint not null,
    primary key (product_id, category_id),
    foreign key (product_id) references tb_product(id),
    foreign key (category_id) references tb_category(id)
);