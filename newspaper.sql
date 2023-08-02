#create database newspaper;
use newspaper;
#create table papers(paper varchar(50) primary key,price float);paperspapers
#select * from papers;
select * from hawkers;
#hawkerscreate table hawkers(hname varchar(30) primary key,mobile varchar(10),address varchar(100),alloareas varchar(100),picpath varchar(100),doj date);
select * from customers;
#create table customers(mobileno varchar(10),cname varchar(50),email varchar(40),caddress varchar(100),area varchar(50),hawker varchar(40),dos date,spapers varchar(150),sprices varchar(100));
#alter table hawkers add column(adhaarpath varchar(150));
create table bills(mobileno varchar(10),datefrom date,dateto date,bill float,billstatus int(1) default 0);
select * from bills;
insert into bills(mobileno) values ("985");
delete from bills;

