/*角色表*/
create table RoleInfo
(
 	  roleId varchar(36) primary key not null COMMENT '角色ID主键',
    roleName varchar(100) not null COMMENT '角色名字'
);
/*插入数据*/
insert into RoleInfo values('1','超级管理员');
insert into RoleInfo values('2','运营管理');
insert into RoleInfo values('3','市场管理');
/*查询*/
select * from RoleInfo;
/*用户表*/
create table UserInfo
(
    userId varchar(36) primary key not null COMMENT '用户ID；用户表主键',
    userName varchar(20) not null COMMENT '用户姓名',
		userAccount varchar(20) not null COMMENT '用户账号',
    userPwd varchar(20) not null COMMENT '用户密码',
		userStatus int not null COMMENT '用户状态：0 审核中  1 成功  2失败',
    userTime timestamp NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '注册时间',
    userRemark varchar(500) not null COMMENT '用户备注',
    roleId varchar(36) not null COMMENT '用户外键/角色'/*角色外键*/
);
alter table UserInfo
         add constraint fk_UserInfo_roleId foreign key(roleId) references RoleInfo(roleId);
/*插入数据*/
insert into UserInfo values('1','聂伟','niewei','123456',1,default,'此人是超级管理员','1');
insert into UserInfo values('2','test','test','123456',1,default,'此人是运营总监','2');


/*查询*/
select * from UserInfo;
/*用户登录*/
select * from UserInfo where userName='aaa' or userAccount='niewei' and userPwd='123456';





/*菜单类型*/
create table MenuType
(
	menuTypeId varchar(36) primary key not null COMMENT '菜单类型表主键ID',
	menuTypeName varchar(60) not null COMMENT '菜单类型名字',
	menuTypeicon varchar(60) not null COMMENT '菜单类型图标',
	remark varchar(100) COMMENT '菜单类型说明'
);
insert into MenuType values('1','用户管理','aa','权限管理');
insert into MenuType values('2','BATP管理','aa','BATP管理');
insert into MenuType values('3','享泊管理','aa','享泊管理');
/*查询*/
select * from MenuType;
/*菜单表*/
create table MenuInfo
(
 	   menuId varchar(36) primary key not null COMMENT '菜单表主键ID',
		 menuName varchar(30) not null COMMENT '菜单名字',
		 href varchar(300) not null COMMENT '路径',
		 target varchar(100) COMMENT '其他',
		 menuTypeId varchar(36) references MenuType(menuTypeId)
);


select * from menutype;
select * from  menuinfo;

select * from menuinfo m INNER JOIN menutype mt on mt.menutypeid=m.menutypeid
/*插入数据*/
insert into MenuInfo values('1','用户管理测试1','路径1111','rightFrame','1');
insert into MenuInfo values('2','用户管理测试2','路径1111','rightFrame','1');
insert into MenuInfo values('3','BATP管理','路径1111','rightFrame','2');
insert into MenuInfo values('4','享泊管理','路径1111','rightFrame','3');
insert into MenuInfo values('5','享泊管理2','路径1111','rightFrame','3');
insert into MenuInfo values('6','享泊管理6','路径1111','rightFrame','3');
insert into MenuInfo values('7','享泊管理7','路径1111','rightFrame','3');
/*查询*/
select * from MenuInfo;

select * from MenuInfo m left JOIN MenuType mt on mt.menuTypeId=m.menuTypeId;

select * from menuinfo m,menutype mt where mt.menuTypeId=m.menuTypeId;

select * from menutype
select * from menuinfo m where m.menutypeid in (select mt.menutypeid from menutype mt where  mt.menutypeid='3')

/*通过菜单表 得到菜单类型*/
select * from menutype mt where mt.menutypeid =(SELECT menutypeid from menuinfo  m where m.menuid='2')


select * from aaaa
/*菜单管理表:*/
create table MenuRoleInfo
(
	rmid  varchar(36) primary key not null COMMENT '菜单管理表主键ID',
	menuId varchar(36) not null  COMMENT '菜单外键' references MenuInfo(menuId),
	roleId varchar(36) not null COMMENT '角色外键' references RoleInfo(roleId) 
);
/*插入数据*/
insert into MenuRoleInfo values('1','1','1');
insert into MenuRoleInfo values('2','2','1');
insert into MenuRoleInfo values('3','3','1');
insert into MenuRoleInfo values('4','4','1');
insert into MenuRoleInfo values('5','5','1');
insert into MenuRoleInfo values('6','6','2');
insert into MenuRoleInfo values('7','7','2');
/*查询*/
select * from MenuRoleInfo;

/*根据u  用户Id 查询该用户的菜单*/
select 
		*
	 from  MenuInfo m 
	 	left join menutype mt 
	 		on
 		 m.menutypeid=mt.menutypeid 
	WHERE 
		m.menuId in(
	select
		menuid from MenuRoleInfo 
					mr inner join roleinfo r 
					on mr.roleid=r.roleid inner join userinfo u 
					on u.roleid=r.roleid 
			where u.userid='1'
	)

select menuid from MenuRoleInfo mr inner join roleinfo r on mr.roleid=r.roleid inner join userinfo u on u.roleid=r.roleid where u.userid='1'