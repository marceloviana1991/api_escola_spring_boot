create table alunos(

    id bigint not null auto_increment,
    nome varchar(100) not null,

    primary key(id)
);

create table cursos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    turno varchar(45) not null,
    data_inicio date not null,
    data_termino date not null,

    primary key(id)
);

create table matriculas(

    id bigint not null auto_increment,
    aluno_id bigint not null,
    foreign key (aluno_id) references alunos(id),
    curso_id bigint not null,
    foreign key (curso_id) references cursos(id),

    primary key(id)
);