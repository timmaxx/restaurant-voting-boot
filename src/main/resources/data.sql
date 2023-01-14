INSERT INTO USERS (NAME, EMAIL, PASSWORD, CALORIES_PER_DAY)
VALUES ('User', 'user@yandex.ru', '{noop}password', 2005),
       ('Admin', 'admin@gmail.com', '{noop}admin', 1900),
       ('Guest', 'guest@gmail.com', '{noop}guest', 2000);

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO MEAL (date_time, description, calories, user_id)
VALUES ('2020-01-30 10:00:00', 'Завтрак', 500, 1),
       ('2020-01-30 13:00:00', 'Обед', 1000, 1),
       ('2020-01-30 20:00:00', 'Ужин', 500, 1),
       ('2020-01-31 00:00:00', 'Еда на граничное значение', 100, 1),
       ('2020-01-31 10:00:00', 'Завтрак', 500, 1),
       ('2020-01-31 13:00:00', 'Обед', 1000, 1),
       ('2020-01-31 20:00:00', 'Ужин', 510, 1),
       ('2020-01-31 14:00:00', 'Админ ланч', 510, 2),
       ('2020-01-31 21:00:00', 'Админ ужин', 1500, 2);

INSERT INTO RESTAURANT (name)
VALUES ('Ресторан А'),
       ('Ресторан Б');

INSERT INTO MENU_ITEM (name, restaurant_id, mdate, price)
VALUES ('Стейк', 1, '2022-01-08', 100),
       ('Рыба', 1, '2022-01-08', 80),
       ('Птица', 1, '2022-01-09', 90),
       ('Стейк', 1, '2022-01-09', 92),
       ('Рыба', 2, '2022-01-08', 82),
       ('Птица', 2, '2022-01-09', 87);

INSERT INTO VOTE (user_id, restaurant_id, vdate)
VALUES ( 1, 1, '2022-01-14'),
       ( 2, 1, '2022-01-14');
