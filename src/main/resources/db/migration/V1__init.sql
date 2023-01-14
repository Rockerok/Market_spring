 create table categories
 (
     id    bigserial primary key,
     title varchar(255)
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

create table users
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

create table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
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