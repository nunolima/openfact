-->>>>>>>>>> AREAS <<<<<<<<<<--
CREATE TABLE "areas" 				 
(
----------------------------------------CAMPOS-------------------------------------------------------------------------
	id 				bigint                  ,	-- Identificador (pkey)
	dsc				varchar(100)	not null,	-- Descri��o (unique)
	obs				varchar(50)		,	-- Observa��es 
----------------------------------------PRIMARY KEY--------------------------------------------------------------------
	CONSTRAINT areas_pkey PRIMARY KEY (id),
	CONSTRAINT areas_unique UNIQUE (dsc)
----------------------------------------FOREIGN KEY--------------------------------------------------------------------	
);
-->>>>>>>>>> EDITORAS <<<<<<<<<<--
CREATE TABLE "editoras" 				 
(
----------------------------------------CAMPOS-------------------------------------------------------------------------
	id 				bigint                  ,	-- Identificador (pkey)
	nome				varchar(100)	not null,	-- Editora (unique)
	obs				varchar(50)		,	-- Observa��es
----------------------------------------PRIMARY KEY--------------------------------------------------------------------
	CONSTRAINT editoras_pkey PRIMARY KEY (id),
	CONSTRAINT editoras_unique UNIQUE (nome)
----------------------------------------FOREIGN KEY--------------------------------------------------------------------	
);
-->>>>>>>>>> LIVROS <<<<<<<<<<--
CREATE TABLE "livros" 				 
(
----------------------------------------CAMPOS-------------------------------------------------------------------------
	id 				bigint                  ,	-- Identificador (pkey)
	titulo				varchar(100)	not null,	-- Titulo (unique)
	isbn				varchar(17)	not null,	-- ISBN (unique)
	ano				bigint			,	-- Ano
	edicao_nr			bigint			,	-- Edi��o N�mero
	editora_id			bigint                  ,	-- Editora
	area_id				bigint                  ,	-- Area
	aquisicao_data			date		not null,	-- Data de aquisi��o
	aquisicao_valor			numeric		not null,	-- Valor de aquisi��o
----------------------------------------PRIMARY KEY--------------------------------------------------------------------
	CONSTRAINT livros_id PRIMARY KEY (id),
	CONSTRAINT livros_titulo_unique UNIQUE (titulo),
	CONSTRAINT livros_isbn_unique UNIQUE (isbn)
----------------------------------------FOREIGN KEY--------------------------------------------------------------------	
);
-->>>>>>>>>> AUTORES <<<<<<<<<<--
CREATE TABLE "AUTORES"
(
----------------------------------------CAMPOS-------------------------------------------------------------------------
	id 				bigint                  ,	-- Identificador (pkey)
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
	id 				bigint                  ,	-- Identificador (pkey)
	livro_id			bigint                  ,	-- Livro
	autor_id			bigint                  ,	-- Autor
----------------------------------------PRIMARY KEY--------------------------------------------------------------------
	CONSTRAINT autores_livros_pkey PRIMARY KEY (id),
	CONSTRAINT autores_livros_unique UNIQUE (livro_id, autor_id)
----------------------------------------FOREIGN KEY--------------------------------------------------------------------
);
ALTER TABLE "autores_livros" ADD CONSTRAINT livro_id_fkey FOREIGN KEY (livro_id) REFERENCES "livros" (id);
ALTER TABLE "autores_livros" ADD CONSTRAINT autor_id_fkey FOREIGN KEY (autor_id) REFERENCES "AUTORES" (id);
ALTER TABLE "livros" ADD CONSTRAINT area_id_fkey FOREIGN KEY (area_id) REFERENCES "areas" (id);
ALTER TABLE "livros" ADD CONSTRAINT editora_id_fkey FOREIGN KEY (editora_id) REFERENCES "editoras" (id);