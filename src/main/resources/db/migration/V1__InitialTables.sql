create table doc_type (
                          id int auto_increment primary key,
                          name varchar(50) not null,
                          description varchar(100) not null,
                          active boolean not null,
                          created_at timestamp not null,
                          created_by varchar(50) not null,
                          updated_at timestamp,
                          updated_by varchar(50)
);


create table users (
                       id int auto_increment primary key,
                       username varchar(50) not null unique,
                       password varchar(100) not null,
                       first_name varchar(30) not null,
                       last_name varchar(40) not null,
                       doc_type_id int not null,
                       doc_number varchar(20) not null,
                       role_id int not null,
                       active boolean not null,
                       created_at timestamp not null,
                       created_by varchar(50) not null,
                       updated_at timestamp,
                       updated_by varchar(50)
);


create table role (
                      id int auto_increment primary key,
                      name varchar(20) not null,
                      description varchar(100) not null,
                      active boolean not null,
                      created_at timestamp not null,
                      created_by varchar(50) not null,
                      updated_at timestamp,
                      updated_by varchar(50)
);


create table product (
                         id int auto_increment primary key,
                         name varchar(50) not null,
                         description varchar(100) not null,
                         price double not null,
                         storage int not null,
                         active boolean not null,
                         created_at timestamp not null,
                         created_by varchar(50) not null,
                         updated_at timestamp,
                         updated_by varchar(50)
);


create table sale (
                      id int auto_increment primary key,
                      issue_date timestamp not null,
                      success boolean not null,
                      observation varchar(1000),
                      active boolean not null,
                      created_at timestamp not null,
                      created_by varchar(50) not null,
                      updated_at timestamp,
                      updated_by varchar(50)
);


create table sale_detail (
                             id int auto_increment primary key,
                             sale_id int not null,
                             product_id int not null,
                             quantity int not null,
                             observation varchar(1000),
                             active boolean not null,
                             created_at timestamp not null,
                             created_by varchar(50) not null,
                             updated_at timestamp,
                             updated_by varchar(50)
);


alter table users add constraint user__role__fk foreign key (role_id) references role(id);
alter table users add constraint user_doc_type_fk foreign key (doc_type_id) references doc_type(id);
alter table sale_detail add constraint sale_detail__product__fk foreign key (product_id) references product(id);
alter table sale_detail add constraint sale_detail__sale__fk foreign key (sale_id) references sale(id);


insert into role( name, description, active, created_at, created_by)
values ('admin', 'can perform crud operations', 1, now(), 'flyway');

insert into role(name, description, active, created_at, created_by)
values ('user', 'can perform read operations', 1, now(), 'flyway');

insert into doc_type(name, description, active, created_at, created_by)
values('DNI', 'Documento Nacional de Identidad', 1, now(), 'flyway');

insert into doc_type(name, description, active, created_at, created_by)
values('CE', 'Carnet de Extranjeria', 1, now(), 'flyway');
