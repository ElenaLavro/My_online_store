/*
create table product

(
    id                uuid    not null
        constraint product_pk
            primary key,
    name              varchar not null,
    short_description varchar not null,
    description       varchar not null,
    price             integer,
    image             varchar not null,
    available         boolean not null,
    amount            integer not null
);

alter table product
    owner to postgres;

create unique index product_id_uindex
    on product (id);

create table users
(
    id       serial
        constraint users_pk
            primary key,
    email    varchar not null,
    password varchar not null,
    name     varchar not null,
    phone    varchar not null,
    active   boolean not null,
    role     varchar not null
);

alter table users
    owner to postgres;

create unique index users_id_uindex
    on users (id);

create table authorities
(
    id integer not null
        constraint authorities_pk
            primary key
);

alter table authorities
    owner to postgres;

create table refresh_tokens
(
    id        serial
        constraint refresh_tokens_pk
            primary key,
    issued_at timestamp not null,
    expire_at timestamp not null,
    user_id   integer
        constraint refresh_tokens_users_id_fk
            references users
            on update restrict on delete cascade,
    next      integer
        constraint refresh_tokens_refresh_tokens_id_fk
            references refresh_tokens
            on update restrict on delete cascade
);

alter table refresh_tokens
    owner to postgres;

create unique index refresh_tokens_id_uindex
    on refresh_tokens (id);

create table user_authorities
(
    id           serial
        constraint user_authorities_pk
            primary key,
    user_id      integer
        constraint user_authorities_users_id_fk
            references users
            on update restrict on delete cascade,
    authority_id integer
        constraint user_authorities_authorities_id_fk
            references authorities
            on update restrict on delete cascade
);

alter table user_authorities
    owner to postgres;

create unique index user_authorities_id_uindex
    on user_authorities (id);

create unique index user_authorities_id_uindex_2
    on user_authorities (id);

create table "order"
(
    id          serial
        constraint order_pk
            primary key,
    buyer_email varchar,
    buyer_name  varchar,
    buyer_phone varchar,
    amount      integer,
    status      boolean,
    create_time timestamp,
    update_time timestamp
);

alter table "order"
    owner to postgres;

create table product_in_order
(
    id                  serial
        constraint product_in_order_pk
            primary key,
    order_id            integer
        constraint product_in_order_order_id_fk
            references "order"
            on update restrict on delete cascade
        constraint product_in_order_order_id_fk_2
            references "order"
            on update restrict on delete cascade,
    product_id          integer,
    product_name        varchar,
    product_description varchar,
    product_price       integer,
    count               integer,
    cart_id             integer
);

alter table product_in_order
    owner to postgres;

create unique index product_in_order_id_uindex
    on product_in_order (id);

create unique index order_id_uindex
    on "order" (id);

create table cart
(
    id      serial
        constraint cart_pk
            primary key,
    user_id integer
        constraint cart_users_id_fk
            references users
            on update restrict on delete cascade
);

alter table cart
    owner to postgres;

create unique index cart_id_uindex
    on cart (id);


*/

INSERT INTO users
values (1, 'elena@gmail.com', 'ElenaOneLove', 'elenaAdmin', '0951672965', true, 'ROLE_ADMIN');
insert into users
values (111111, 'client1@gmail.com', 'passwordClient1', 'client1', '0990540364', true, 'ROLE_CLIENT');
insert into users
values (111112, 'client2@gmail.com', 'passwordClient2', 'client2', '0994030564', true, 'ROLE_CLIENT');
insert into users
values (111113, 'client3@gmail.com', 'passwordClient3', 'client3', '0955903640', true, 'ROLE_CLIENT');
insert into users
values (111114, 'client4@gmail.com', 'passwordClient4', 'client4', '0990365904', true, 'ROLE_CLIENT');


INSERT into "order"
values (55511, 'client1@gmail.com', 'client1', '0990540364', 2, 'true', '2021-09-11 12:52:20.439',
        '2021-09-11 13:52:20.439');
INSERT into "order"
values (55512, 'client1@gmail.com', 'client1', '0990540364', 4, 'true', '2021-09-11 12:52:20.439',
        '2021-09-11 13:52:20.439');
INSERT into "order"
values (55513, 'client3@gmail.com', 'client3', '0955903640', 2, 'true', '2021-09-11 12:52:20.439',
        '2021-09-11 13:52:20.439');
INSERT into "order"
values (55514, 'client1@gmail.com', 'client4', '0990540364', 2, 'true', '2021-09-11 12:52:20.439',
        '2021-09-11 13:52:20.439');
INSERT into "order"
values (55515, 'client1@gmail.com', 'client2', '0990540364', 2, 'true', '2021-09-11 12:52:20.439',
        '2021-09-11 13:52:20.439');
INSERT into "order"
values (555116, 'client1@gmail.com', 'client2', '0990540364', 2, 'true', '2021-09-11 12:52:20.439',
        '2021-09-11 13:52:20.439');
