-- INSERT INTO usuarios (id, login, password) VALUES (1, 'Paulo', '1234');
INSERT INTO usuarios (id, login, password) VALUES (1, 'Paulo', '$2a$10$B9vcQFyCeVl1gZI1ro1mN.gtpT9SC7B7Scgk7XvtRFVhTKHHT5XJW');

-- INSERT INTO usuarios (id, login, password) VALUES (2, 'Maikon', '5678');
INSERT INTO usuarios (id, login, password) VALUES (2, 'Maikon', '$2a$10$2oM3dWrYS8wlgD0uSLlhhOEwr.bZcCao/iOUcdS0VV0KDFyTTlXkG');

-- INSERT INTO usuarios (id, login, password) VALUES (3, 'Jeff', '9012');
INSERT INTO usuarios (id, login, password) VALUES (3, 'Jeff', '$2a$10$AVhulb0dkxjsl8amv9YgrOJdWf07nplzomWgLF3XiWSvmneS.5BOS');

INSERT INTO lancamento (tipo, valor, descricao, data) VALUES ('credito', 1500.00, 'Salário', '2023-05-06');
INSERT INTO lancamento (tipo, valor, descricao, data) VALUES ('debito', 200.00, 'Conta de luz', '2023-05-06');
INSERT INTO lancamento (tipo, valor, descricao, data) VALUES ('debito', 50.00, 'Assinatura de revista', '2023-05-06');
INSERT INTO lancamento (tipo, valor, descricao, data) VALUES ('debito', 20.00, 'Assinatura de Streaming', '2023-05-06');
