create database nimap_machine_test;
use nimap_machine_test;
create table product(product_id int primary key, category_id int,product_name varchar(30), product_price int, foreign key (category_id) references category(category_id));
create table category(category_id int primary key, category_name varchar(30));
select * from product;
select * from category;
insert into product values(1,1,'Mango',70);
insert into product values(2,'Cricket',100);
insert into category values(1,'Fruits');
insert into category values(2,'Games');





