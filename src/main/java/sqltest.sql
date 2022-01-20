select * from role ;

insert into role (role_name) values ('test') returning id;


create table accounttype(
    id  serial primary key,
    type_account varchar(10)
);
 drop table accounttype;
insert into accounttype (type_account) values ('check') returning id;





drop table clerks;
select * from users;
INSERT INTO account (account_number, balance, type_id) VALUES (?,?,?);


select u.first_name,a2.account_number,a2.balance,c.number,c.expirdate from customer inner join accoutncustoer a on customer.id = a.customer_id
inner join account a2 on a2.id = a.account_id
inner join users u on u.id = customer.user_id
inner join creditcard c on c.id = a2.credit_card_id
where customer.id = 5;


select u.first_name, a2.account_number,a2.balance from customer c inner join accoutncustoer a on c.id = a.customer_id
inner join account a2 on a2.id = a.account_id
inner join users u on u.id = c.user_id
where a2.credit_card_id = 1;




drop table transactions;


select * from account inner join creditcard c on c.id = account.credit_card_id
inner join accoutncustoer a on account.id = a.account_id
inner join customer c2 on c2.id = a.customer_id;

select c.id,u.first_name,u.last_name,u.national_code,u.role_id from customer c inner join users u on u.id = c.user_id where u.id = 9;
select * from account a inner join transactions t on a.id = t.accountid where transaction_date >= '2022-01-17'::date and a.id = 4 order by transaction_date;

select * from customer c inner join accoutncustoer a on c.id = a.customer_id
    inner join account a2 on a2.id = a.account_id
    inner join creditcard c2 on c2.id = a2.credit_card_id where a2.credit_card_id is null;

select account.id,u.first_name,account.account_number from account inner join accoutncustoer a on account.id = a.account_id
inner join customer c on c.id = a.customer_id
inner join users u on u.id = c.user_id
where credit_card_id is null;


select c.id,u.first_name,u.last_name,u.national_code,a2.account_number,a2.balance from customer c
    inner join users u on u.id = c.user_id
    inner join accoutncustoer a on c.id = a.customer_id
   inner join account a2 on a2.id = a.account_id
   where a2.id = 9;

select a2.id,a2.account_number,a2.balance from customer c inner join accoutncustoer a on c.id = a.customer_id
inner join account a2 on a2.id = a.account_id
where c.id =7;




select c.id,c.number,c.expirdate,c.cvv,c.password,c.password2,c.isactive from account a inner join creditcard c on c.id = a.credit_card_id
where a.id = 4;

truncate table customer restart identity ;


select c2.id,c2.number,c2.expirdate,c2.cvv,c2.password,c2.password2,c2.isactive from customer c inner join accoutncustoer a on c.id = a.customer_id
                 inner join account a2 on a2.id = a.account_id
                  inner join creditcard c2 on c2.id = a2.credit_card_id
               where c.id = ?;


SELECT * FROM creditcard where number like '1458789452162546';

drop table transactions;


select c2.id,c2.number,c2.password,c2.password2,c2.isactive from customer c inner join accoutncustoer a on c.id = a.customer_id
  inner join account a2 on a2.id = a.account_id
   inner join creditcard c2 on c2.id = a2.credit_card_id
where c.id = 10;

UPDATE creditcard SET number = ? ,expirdate = ?,cvv = ?,password = ?,password2 = ?,isactive = ? WHERE id = ?;

select c2.id,c2.number,c2.expirdate,c2.cvv,c2.password,c2.password2,c2.isactive from account a inner join creditcard c2 on c2.id = a.credit_card_id
                where a.id = 6;

UPDATE creditcard set failed_password = failed_password + 1;branchcustomer



SELECT * FROM creditcard c INNER JOIN account a on c.id = a.credit_card_id
WHERE c.number like '1458789452162546';

SELECT * FROM account a INNER JOIN creditcard c on c.id = a.credit_card_id
WHERE c.number LIKE '1458789452162546';


INSERT INTO transactions (accountid, amount, status, types, transaction_date, account_destination)
VALUES (?,?,?,?,?,?)


select a2.id,a2.account_number,a2.balance,c2.number,c2.expirdate,c2.cvv,c2.password,c2.password2,c2.isactive,c2.id from customer c inner join accoutncustoer a on c.id = a.customer_id
                inner join account a2 on a2.id = a.account_id
                inner join users u on u.id = c.user_id
                inner join creditcard c2 on c2.id = a2.credit_card_id
                where c.id = 12;

SELECT * FROM clerks c INNER JOIN users u on u.id = c.user_id

select c.id,u.first_name,u.last_name,u.national_code,u.role_id from customer c inner join users u on u.id = c.user_id
                 where u.role_id = 2

INSERT INTO customer (user_id) VALUES (?) RETURNING id;