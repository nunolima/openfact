create table pessoas (
id bigint primary key generated always as identity,
nome varchar( 32672 ) not null,
datanasc date,
morada varchar( 32672 ),
localidade varchar( 100 ),
telfcasa varchar( 100 ),
telfempresa varchar( 100 ),
telfmovel1 varchar( 100 ),
telfmovel2 varchar( 100 ),
telfmovel3 varchar( 100 ),
activo char(1) default 'A',
datacriacao timestamp default current timestamp -- current date -- se fosse date
);

create table categorias (
id int primary key generated always as identity,
descricao varchar(100) not null,
activo integer default 1
);

create table contactos (
id_pessoa bigint not null,
id_categoria int not null,
primary key (id_pessoa, id_categoria)
);

create table teste(
id bigint,
nome varchar(100),
tipo varchar(100),
local varchar(200) unique,
CONSTRAINT NOME_TIPO_UK UNIQUE (nome, tipo)
);

