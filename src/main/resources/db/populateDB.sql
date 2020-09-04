DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (email, password)
VALUES ('user@mail.ru', '{noop}password'),
       ('admin@mail.ru', '{noop}admin');

INSERT INTO user_roles (user_id, role)
VALUES (100000, 'USER'),
       (100001, 'ADMIN');

INSERT INTO restaurants (description)
VALUES ('Restaurant1'),
       ('Restaurant2');

INSERT INTO dishes (description, price, rest_id, date)
VALUES ('Restaurant1_Dish1_Date1', 111, 100002, '2020-01-01'),
       ('Restaurant1_Dish2_Date1', 121, 100002, '2020-01-01'),
       ('Restaurant1_Dish1_Date2', 112, 100002, '2020-02-02'),
       ('Restaurant2_Dish1_Date1', 211, 100003, '2020-01-01'),
       ('Restaurant2_Dish2_Date2', 222, 100003, '2020-02-02');

INSERT INTO votes (user_id, rest_id, date)
VALUES (100000, 100002, '2020-01-01'),
       (100001, 100002, '2020-01-01'),
       (100000, 100002, '2020-02-02'),
       (100001, 100003, '2020-02-02');
