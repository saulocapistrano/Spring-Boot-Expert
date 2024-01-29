create table client(
id integer primary key AUTO_INCREMENT,
name varchar(100)
);

create table product(
id integer primary key AUTO_INCREMENT,
description varchar(100),
price numeric(20,2)
);

create table ordered(
id integer primary key AUTO_INCREMENT,
client_id integer references client(id),
    order_date timestamp,
total_order numeric(20,2)
);

create table ordered_item(
id integer primary key AUTO_INCREMENT,
order_id integer references ordered(id),
product_id integer references product(id),
amount integer
);