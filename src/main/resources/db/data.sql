-- noinspection SqlWithoutWhere
DELETE
FROM users;
-- noinspection SqlWithoutWhere
DELETE
FROM routines;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users(name, last_name, email, reg_date_time)
VALUES ('user1', 'last_name1', 'email1@gmail.com', '2001-01-01'),
       ('user2', 'last_name2', 'email2@hotmail.no', '2001-01-01'),
       ('user3', 'last_name3', 'email3@yandex.ru', '2001-01-01');

INSERT INTO routines(user_id, name, description, start_date, end_date, time_of_day, period)
VALUES (100000, 'routine1_1',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua',
        '2001-01-05', '2001-10-05', '01:00:00', '02:00:00'),
       (100000, 'routine2_1', 'Lorem ipsum', '2001-01-05', '2001-10-05', '02:00:00', '02:00:00'),
       (100000, 'routine3_1', 'Lorem ipsum', '2001-01-05', '2001-10-05', '03:00:00', '02:00:00'),
       (100001, 'routine1_2', 'Lorem ipsum', '2001-01-05', '2001-10-05', '04:00:00', '02:00:00'),
       (100001, 'routine2_2', 'Lorem ipsum', '2001-01-05', '2001-10-05', '12:00:00', '02:00:00'),
       (100001, 'routine3_2', 'Lorem ipsum', '2001-01-05', '2001-10-05', '13:00:00', '02:00:00'),
       (100002, 'routine1_3', 'Lorem ipsum', '2001-01-05', '2001-10-05', '10:00:00', '02:00:00'),
       (100002, 'routine2_3', 'Lorem ipsum', '2001-01-05', '2001-10-05', '11:00:00', '02:00:00'),
       (100002, 'routine3_3', 'Lorem ipsum', '2001-01-05', '2001-10-05', '05:00:00', '02:00:00');