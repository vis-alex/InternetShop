INSERT INTO users (id, archived, email, name, password, role)
VALUES (1, false, 'usenkoshurik@mail.ru', 'admin', '$2a$10$SKzGi5L.3cahpahXcl9lLuSiCmRnlEOadm/FZCsdabKQjgl.K2AQ2', 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;