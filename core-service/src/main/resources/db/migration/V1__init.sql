 create table categories
 (
     id             bigserial primary key,
     title          varchar(255) unique,
     created_at     timestamp default current_timestamp,
     updated_at     timestamp default current_timestamp
 );

 insert into categories (title)
 values ('Food'),
        ('Industrial');


create table products
 (
     id                 bigserial primary key,
     title              varchar(255),
     price              numeric(10, 2) not null,
     categories_id      bigint references categories (id),
     created_at         timestamp default current_timestamp,
     updated_at         timestamp default current_timestamp
);

insert into products (title,price,categories_id)
values  ('onion',15.00,1),
        ('tomatoes',20.00,1),
        ('beet',25.00,1),
        ('potatoes',30.00,1),
        ('radish',35.00,1),
        ('cucumbers',40.00,1),
        ('bread',45.00,1),
        ('butter',55.00,1),
        ('milk',60.00,1),
        ('egs',65.00,1),
        ('pepperoni',70.00,1),
        ('cheese',75.00,1),
        ('sausage',80.00,1),
        ('curd',85.00,1),
        ('cream',90.00,1),
        ('mayonnaise',95.00,1),
        ('ketchup',100.00,1),
        ('rice',105.00,1),
        ('buckwheat',110.00,1),
        ('pasta',115.00,1),
        ('coffee',120.00,1);

create table orders
(
    id          bigserial primary key,
    username    varchar (255),
    total_price numeric(10, 2),
    address     varchar (255),
    phone       varchar (255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table orders_items
(
    id                  bigserial primary key,
    product_id          bigint not null references products (id),
    order_id            bigint not null references orders (id),
    quantity            int,
    price_per_product   numeric(10, 2),
    price               numeric(10, 2),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);