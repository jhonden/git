select * from tbl_sh_account;
drop table tbl_sh_account;
create table tbl_sh_account(
	ID varchar(20),
	amount double,
	create_date date
);
insert into tbl_sh_account(id,amount,create_date) values('momoaccount',0,datetime('now'));
commit;

drop table tbl_sh_account_flow;
create table tbl_sh_account_flow(
	ID INTEGER PRIMARY KEY   AUTOINCREMENT,
	account_id varchar(20),
	type varchar(1),
	amount double,
	create_date date,
	remark varchar(100)
);


drop table tbl_sh_buy_car;
create table tbl_sh_buy_car(
	ID varchar(20),
	userid  int,
	productid int,
	pcount int,
	create_date date
);

drop table tbl_sh_order;
create table tbl_sh_order(
	ID varchar(20),
	userid  int,
	pcount int,
	create_date date,
	sell_Price double
);
drop table tbl_sh_order_detail;
create table tbl_sh_order_detail(
	order_id varchar(20),
	product_id int,
	pcount int,
	create_date date,
	sell_Price double
);


drop table tbl_sh_user;
create table tbl_sh_user(
	ID INTEGER PRIMARY KEY   AUTOINCREMENT,
	name  varchar(30),
	password varchar(30),
	create_date date,
	mobile varchar(30)
);

drop table tbl_sh_menu;
create table tbl_sh_menu(
	ID INTEGER PRIMARY KEY   AUTOINCREMENT,
	group_name  varchar(20),
	parent int,
	name varchar(50),
	value varchar(150)
);

drop table tbl_sh_ptype;
create table tbl_sh_ptype(
	ID INTEGER PRIMARY KEY   AUTOINCREMENT,
	name varchar(20),
	description varchar(100),
	create_date date	
);

drop table tbl_sh_products;
create table tbl_sh_products(
	id INTEGER PRIMARY key AUTOINCREMENT,
	typeid int,
	name varchar(50),
	description varchar(100),
	create_date date,
	icon varchar(100),
	sell_price double,
	pcount int,
	buy_price double
);


drop table tbl_sh_buy_log;
create table tbl_sh_buy_log(
	id INTEGER PRIMARY key AUTOINCREMENT,
	userid int,
	create_date date,
	productid int,
	buy_count int,
	buy_price double,
	remark varchar(200)
);

