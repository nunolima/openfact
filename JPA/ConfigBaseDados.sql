SET client_encoding = 'UTF8';
-->>>>>>>>>> AREAS <<<<<<<<<<--
CREATE TABLE "areas" 				 
(
----------------------------------------CAMPOS-------------------------------------------------------------------------
	id 				bigserial		,	-- Identificador (pkey)
	dsc				varchar(100)	not null,	-- Descrição (unique)
	obs				varchar(50)		,	-- Observações 
----------------------------------------PRIMARY KEY--------------------------------------------------------------------
	CONSTRAINT areas_pkey PRIMARY KEY (id),
	CONSTRAINT areas_unique UNIQUE (dsc)
----------------------------------------FOREIGN KEY--------------------------------------------------------------------	
);
-->>>>>>>>>> EDITORAS <<<<<<<<<<--
CREATE TABLE "editoras" 				 
(
----------------------------------------CAMPOS-------------------------------------------------------------------------
	id 				bigserial		,	-- Identificador (pkey)
	nome				varchar(100)	not null,	-- Editora (unique)
	obs				varchar(50)		,	-- Observações
----------------------------------------PRIMARY KEY--------------------------------------------------------------------
	CONSTRAINT editoras_pkey PRIMARY KEY (id),
	CONSTRAINT editoras_unique UNIQUE (nome)
----------------------------------------FOREIGN KEY--------------------------------------------------------------------	
);
-->>>>>>>>>> LIVROS <<<<<<<<<<--
CREATE TABLE "livros" 				 
(
----------------------------------------CAMPOS-------------------------------------------------------------------------
	id 				bigserial		,	-- Identificador (pkey)
	titulo				varchar(100)	not null,	-- Titulo (unique)
	isbn				varchar(17)	not null,	-- ISBN (unique)
	ano				bigint			,	-- Ano
	edicao_nr			bigint			,	-- Edição Número
	editora_id			bigint		not null,	-- Editora
	area_id				bigint		not null,	-- Area
	aquisicao_data			date		not null,	-- Data de aquisição
	aquisicao_valor			numeric		not null,	-- Valor de aquisição
----------------------------------------PRIMARY KEY--------------------------------------------------------------------
	CONSTRAINT livros_id PRIMARY KEY (id),
	CONSTRAINT livros_titulo_unique UNIQUE (titulo),
	CONSTRAINT livros_isbn_unique UNIQUE (isbn),
----------------------------------------FOREIGN KEY--------------------------------------------------------------------	
	CONSTRAINT area_id_fkey FOREIGN KEY (area_id) REFERENCES areas(id) ON DELETE RESTRICT,
	CONSTRAINT editora_id_fkey FOREIGN KEY (editora_id) REFERENCES editoras(id) ON DELETE RESTRICT
);
-->>>>>>>>>> AUTORES <<<<<<<<<<--
CREATE TABLE "autores" 				 
(
----------------------------------------CAMPOS-------------------------------------------------------------------------
	id 				bigserial		,	-- Identificador (pkey)
	nome				varchar(100)	not null,	-- Nome (unique)
----------------------------------------PRIMARY KEY--------------------------------------------------------------------
	CONSTRAINT autores_pkey PRIMARY KEY (id),
	CONSTRAINT autores_unique UNIQUE (nome)
----------------------------------------FOREIGN KEY--------------------------------------------------------------------	
);
-->>>>>>>>>> AUTORES - LIVROS <<<<<<<<<<--
CREATE TABLE "autores_livros" 				 
(
----------------------------------------CAMPOS-------------------------------------------------------------------------
	id 				bigserial		,	-- Identificador (pkey)
	livro_id			bigint		not null,	-- Livro
	autor_id			bigint		not null,	-- Autor
----------------------------------------PRIMARY KEY--------------------------------------------------------------------
	CONSTRAINT autores_livros_pkey PRIMARY KEY (id),
	CONSTRAINT autores_livros_unique UNIQUE (livro_id, autor_id),
----------------------------------------FOREIGN KEY--------------------------------------------------------------------
	CONSTRAINT livro_id_fkey FOREIGN KEY (livro_id) REFERENCES livros(id) ON DELETE RESTRICT,
	CONSTRAINT autor_id_fkey FOREIGN KEY (autor_id) REFERENCES autores(id) ON DELETE RESTRICT
);
