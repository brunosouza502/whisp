BEGIN;
CREATE TABLE usuario(
	id serial,
	login VARCHAR(20) NOT NULL,
	nome VARCHAR(20) NOT NULL, --nome de exibição
	senha CHAR(32) NOT NULL,
	sexo VARCHAR(9),
	CHECK (sexo='Masculino' OR sexo='Feminino'),
	nascimento DATE,
	CHECK(nascimento >= '1900-01-01'),
	descricao VARCHAR(200), --Sobre o usuario
	
	CONSTRAINT pk_usuario PRIMARY KEY(id),
	CONSTRAINT uq_usuario_login UNIQUE(login),
	CONSTRAINT uq_usuario_nome_exibicao UNIQUE(nome)
)

CREATE TABLE post(
	id_post serial,
	id_usuario INTEGER,
	--titulo VARCHAR(10), titulo do post
	texto VARCHAR(500), --texto do post
	horario TIMESTAMP, --hora do post
	CONSTRAINT pk_id_post PRIMARY KEY(id_post),
	CONSTRAINT fk_id_usuario FOREIGN KEY(id_usuario)
		REFERENCES usuario(id) ON DELETE CASCADE
)
COMMIT;

CREATE TABLE grupo(
	id_grupo serial,
	id_dono INTEGER NOT NULL,
	nome_grupo VARCHAR(20),
	descricao_grupo VARCHAR(100),
	CONSTRAINT pk_id_grupo PRIMARY KEY(id_grupo),
	CONSTRAINT fk_id_usuario FOREIGN KEY(id_dono)
		REFERENCES usuario(id),
	CONSTRAINT uq_id_dono UNIQUE(id_dono)
)

CREATE TABLE membro_grupo(--membros de tal grupo
	id_grupo INTEGER,
	id_membro INTEGER,
	data_entrada DATE,
	CONSTRAINT pk_membro_grupo PRIMARY KEY(id_grupo, id_membro)
	CONSTRAINT fk_id_grupo FOREIGN KEY(id_grupo)
		REFERENCES grupo(id_grupo),
	CONSTRAINT fk_id_usuario FOREIGN KEY(id_membro)
		REFERENCES usuario(id)
)


INSERT INTO imagem (figura, id_user) VALUES (lo_import('C:\figura\figura.jpg'), 22);
SELECT lo_export(figura, 'c:/temp/figura1.jpg'), id_user FROM imagem WHERE id_user = 22;
SELECT u.id, u.senha, u.nome, u.nascimento, u.sexo, u.descricao, lo_export(figura, 'c:/temp/figura1.jpg') FROM usuario u JOIN imagem i ON u.id = i.id_user WHERE login = 'joey'