select l.id as id, u.id as userid,p.id as productid, strftime('%Y-%m-%d',l.create_date) as createDateStr, l.buy_count as buyCount, l.buy_price as buyPrice, l.remark as remark from  tbl_sh_buy_log l, tbl_sh_user u, tbl_sh_products p  where 1=1  and l.userid = u.id and l.productid = p.id  limit 5 offset 0;











insert into tbl_sh_menu(id,group_name,parent,child,name,value) values('leftm',-1,'进货管理','');
insert into tbl_sh_menu(id,group_name,parent,child,name,value) values('leftm',1,'进货管理','');
insert into tbl_sh_menu(id,group_name,parent,child,name,value) values('leftm',1,'进货管理','');
insert into tbl_sh_menu(id,group_name,parent,child,name,value) values('leftm',1,'进货管理','');
insert into tbl_sh_menu(id,group_name,parent,child,name,value) values('leftm',1,'进货管理','');

insert into tbl_sh_user(name,password,mobile,create_date) values('jhonden','0315','15822029694',datetime('now','localtime'));
insert into tbl_sh_user(name,password,mobile,create_date) values('jianxin','0315','15822029694',datetime('now','localtime'));
insert into tbl_sh_user(name,password,mobile,create_date) values('xiaowei','0315','15822029694',datetime('now','localtime'));





insert into tbl_sh_ptype(name,description,create_date) values('name','描述',datetime('now','localtime'));
select * from tbl_sh_ptype;

select id as id, name as name, strftime('%Y-%m-%d',create_date) as createDateStr, description as description from tbl_sh_ptype


select strftime(create_date) from tbl_sh_ptype;

select strftime('%Y.%m.%d %H:%M:%S','now','localtime');



insert into tbl_test_a values(2,'jhonden1',21);
insert into tbl_test_a values(3,'jhonden2',22);
insert into tbl_test_a values(4,'jhonden3',23);
commit;

select * from tbl_test_a;
select * from tbl_sh_products;

update tbl_sh_products set buy_price = 0 , pcount = 0;
commit;



select p.id as id,p.typeid as ptype_id,t.name, p.name as name, strftime('%Y-%m-%d',p.create_date) as createDateStr, p.description as description,p.icon as icon,p.sell_price as sellPrice,p.pcount as pcount from  tbl_sh_products p, tbl_sh_ptype t  where 1=1  and p.typeid=t.id   limit 5 offset 0