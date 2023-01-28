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
     price              int,
     categories_id      bigint references categories (id),
     created_at         timestamp default current_timestamp,
     updated_at         timestamp default current_timestamp
);

insert into products (title,price,categories_id)
values  ('onion',15,1),
        ('tomatoes',20,1),
        ('beet',25,1),
        ('potatoes',30,1),
        ('radish',35,1),
        ('cucumbers',40,1),
        ('bread',45,1),
        ('butter',55,1),
        ('milk',60,1),
        ('egs',65,1),
        ('pepperoni',70,1),
        ('cheese',75,1),
        ('sausage',80,1),
        ('curd',85,1),
        ('cream',90,1),
        ('mayonnaise',95,1),
        ('ketchup',100,1),
        ('rice',105,1),
        ('buckwheat',110,1),
        ('pasta',115,1),
        ('coffee',120,1);

create table orders
(
    id          bigserial primary key,
    username    varchar (255) not null,
    total_price int not null,
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
    quantity            int not null,
    price_per_product   int not null,
    price               int not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);