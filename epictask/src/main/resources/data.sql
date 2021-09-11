CREATE TABLE user (
	id INT PRIMARY KEY auto_increment,
	name varchar(200),
	email varchar(200), 
	password varchar(200)
);
INSERT INTO user VALUES (1, 'administrator', 'admin@mail.com', '123456');


CREATE TABLE task (
	id INT PRIMARY KEY auto_increment, 
	title varchar(200), 
	description TEXT, 
	points INT
);
INSERT INTO task VALUES (1, 'Banco de dados', 'Criar banco padrão MySQL', 10);
INSERT INTO task VALUES (2, 'Modelagem', 'Criar os diagramas', 8);
INSERT INTO task VALUES (3, 'Prototipação', 'Criar o protótipo de alta fidelidade', 9);