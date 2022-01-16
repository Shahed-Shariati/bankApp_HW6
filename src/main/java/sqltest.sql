select * from role ;

insert into role (role_name) values ('test') returning id;


create table accounttype(
    id  serial primary key,
    type_account varchar(10)
);
 drop table accounttype;
insert into accounttype (type_account) values ('check') returning id;