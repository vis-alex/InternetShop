-- USERS
drop table if exists users cascade;
drop sequence if exists user_seq;
create sequence user_seq start 1 increment 1;
create table users
(
    id        int8    not null,
    archived  boolean not null,
    email     varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      varchar(255),
    primary key (id)
);

-- BUCKETS
drop table if exists buckets cascade;
drop sequence if exists bucket_seq;
create sequence bucket_seq start 1 increment 1;
create table buckets
(
    id      int8 not null,
    user_id int8,
    primary key (id)
);
alter table if exists buckets
    add constraint buckets_fk_user
        foreign key (user_id) references users;

-- PRODUCTS
drop table if exists products cascade;
drop sequence if exists product_seq;
create sequence product_seq start 1 increment 1;
create table products
(
    id    int8 not null,
    price numeric(19, 2),
    title varchar(255),
    primary key (id)
);

-- CATEGORIES
drop table if exists categories cascade;
drop sequence if exists category_seq;
create sequence category_seq start 1 increment 1;
create table categories
(
    id    int8 not null,
    title varchar(255),
    primary key (id)
);

-- ORDERS
drop table if exists orders cascade;
drop sequence if exists order_seq;
create sequence order_seq start 1 increment 1;
create table orders
(
    id      int8 not null,
    address varchar(255),
    created timestamp,
    status  varchar(255),
    sum     numeric(19, 2),
    updated timestamp,
    user_id int8,
    primary key (id)
);
alter table if exists orders
    add constraint order_fk_user
        foreign key (user_id) references users;

-- BUCKETS_PRODUCTS
drop table if exists buckets_products cascade;
create table buckets_products
(
    bucket_id  int8 not null,
    product_id int8 not null
);
alter table if exists buckets_products
    add constraint buckets_products_fk_product foreign key (product_id) references products;
alter table if exists buckets_products
    add constraint buckets_products_fk_bucket foreign key (bucket_id) references buckets;

-- ORDER_DETAILS
drop table if exists order_details cascade;
drop sequence if exists order_details_seq;
create sequence order_details_seq start 1 increment 1;
create table order_details
(
    id         int8 not null,
    amount     numeric(19, 2),
    price      numeric(19, 2),
    order_id   int8,
    product_id int8,
    primary key (id)
);
alter table if exists order_details
    add constraint order_details_fk_order foreign key (order_id) references orders;
alter table if exists order_details
    add constraint order_details_fk_product foreign key (product_id) references products;

-- ORDERS_DETAILS
drop table if exists orders_details cascade;
create table orders_details
(
    order_id   int8 not null,
    details_id int8 not null
);
alter table if exists orders_details
    add constraint orders_details_unique unique (details_id);
alter table if exists orders_details
    add constraint orders_details_fk_details foreign key (details_id) references order_details;
alter table if exists orders_details
    add constraint orders_details_fk_order foreign key (order_id) references orders;


-- PRODUCTS_CATEGORIES
drop table if exists products_categories cascade;
create table products_categories
(
    product_id  int8 not null,
    category_id int8 not null
);
alter table if exists products_categories
    add constraint products_categories_fk_category foreign key (category_id) references categories;
alter table if exists products_categories
    add constraint products_categories_fk_product foreign key (product_id) references products;



