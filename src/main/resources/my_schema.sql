create table client(
id integer primary key AUTO_INCREMENT,
name varchar(100),
    cpf varchar(11)
);

create table product(
id integer primary key AUTO_INCREMENT,
name_product varchar(100),
description varchar(100),
price numeric(20,2)
);

create table ordered(
id integer primary key AUTO_INCREMENT,
    client_id integer references client(id),
    order_date timestamp,
    status_oredered varchar(20),
total_order numeric(20,2)
);

create table ordered_item(
id integer primary key AUTO_INCREMENT,
order_id integer references ordered(id),
product_id integer references product(id),
amount integer
);


create table user(
id integer primary key AUTO_INCREMENT,
    login varchar(50) not null ,
    password varchar(250) not null ,
admin bool default false
);
