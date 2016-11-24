/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/4/27 10:29:55                           */
/*==============================================================*/


drop table if exists Advertising;

drop table if exists Certification;

drop table if exists Collect;

drop table if exists Community;

drop table if exists Dayrules;

drop table if exists InvitedRecord;

drop table if exists Message;

drop table if exists OrderInfo;

drop table if exists OrderTime;

drop table if exists Parkingspace;

drop table if exists Purse;

drop table if exists TouchBalance;


drop table if exists Vehicle;

drop table if exists Weekrules;

drop table if exists WithdrawalRecord;

drop table if exists feedback;

drop table if exists version;


drop table if exists User;

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   userid               varchar(36) not null comment '用户主键',
   userName             varchar(20) comment '用户姓名',
   mobile               varchar(11) not null comment '手机号码',
   password             varchar(50)  comment '登录密码',
   sex                  int(1) comment '性别(0:未填写， 1：男    2  女 )',
   age                  int(2) comment '年龄',
   headportrait         varchar(40) comment '头像路径',
   invitecode           varchar(10) comment '邀请码',
   regid                varchar(30) comment '消息推送，Id点对点',
   token                varchar(20) comment '安全验证，为了防止多方登录',
   is_guard             int not null comment '是否为保安（0：不是，1：是保安）',
   id_card              varchar(18) comment '身份证号码（保安）',
   date_created         datetime  comment '注册时间，创建时间',
   date_updated         datetime not null comment '最后更新时间（手机号）',
   smscode              varchar(6) not null comment '短信验证码',
   terminal             varchar(300) comment '设备id（拼接）',
   bank_open_name       varchar(40) comment '银行卡开户姓名',
   bank_open_no         varchar(18) comment '银行卡卡号',
   bank_type            varchar(20) comment '银行名字：（比如 招商银行）',
   payname              varchar(30) comment '支付宝姓名',
   payno                varchar(30) comment '支付宝号码',
   communityName        varchar(50) comment '停车场/小区名字',
   Community_address    varchar(100) comment '小区地址（认证保安/代理）',
   entrance             varchar(100) comment '小区入口（认证保安）',
   primary key (userid,mobile)
);
#insert into user(userid,mobile,is_guard,date_updated) values('123');
alter table User comment '用户表';
# select * from user;
#alter table User modify column regid varchar(30);
#select count(1) from user where mobile='18581343206' and smscode='123456'
#update user set smscode='123456', where mobile='18581343206';
#update user set password='',date_created=now(),is_guard=1,userName={userName},smscode='',bank_open_name={bank_open_name},bank_open_no={bank_open_no},communityName={communityName},Community_address={community_address} where 
#select * from user where mobile='18581343207' and password ='E10ADC3949BA59ABBE56E057F20F883E' and is_guard=1
/*==============================================================*/
/* Table: Advertising                                           */
/*==============================================================*/
create table Advertising
(
   advid                varbinary(36) not null comment '广告主键',
   adname               varchar(30),
   adv_img_url          varchar(50) not null comment '广告图片路径',
   primary key (advid)
);

alter table Advertising comment '广告表Advertising';

/*==============================================================*/
/* Table: Certification                                         */
/*==============================================================*/
create table Certification
(
   certificationId      varchar(36) not null comment '车辆认证表主键',
   userid               varchar(36) not null comment '用户ID(外键)',
   vehicleid            varchar(36) not null comment '车辆ID(外键)',
   driver_license_positive varchar(70) comment '驾驶证（正面）',
   driver_license_reverse varchar(70) comment '驾驶证（反面）',
   vehicle_license_positive varchar(70) comment '行驶证（正面）',
   vehicle_license_reverse varchar(70) comment '行驶证（背面）',
   email                varchar(50) comment '邮箱',
   certification_status int(1) comment '认证状态（0：未认证，1：已认证，2：拒绝认证）',
   thistime             datetime comment '认证时间',
   audittime            datetime,
   primary key (certificationId)
);

alter table Certification comment '车主认证表';

/*==============================================================*/
/* Table: Collect                                               */
/*==============================================================*/
create table Collect
(
   collectid            varchar(36) not null comment '车位收藏表Id',
   userid               varchar(36) not null comment '用户表（外键）',
   parkid               varchar(36) not null comment '车位表（外键）',
   collecttime          datetime not null comment '标记时间',
   primary key (collectid)
);

alter table Collect comment '车位收藏表';

/*==============================================================*/
/* Table: Community                                             */
/*==============================================================*/
create table Community
(
   communityId          varchar(36) not null comment '小区表主键ID',
   community_name       varchar(40) not null comment '小区名字',
   community_address    varchar(200) not null comment '小区地址',
   administrative       varchar(50) comment '行政区',
   addtime              datetime not null comment '录入时间',
   isaudit              int(1) not null comment '是否审核（0：未审核，1,：已审核）',
   primary key (communityId)
);

alter table Community comment '小区表';
select * from Community  where community_name like '%%' LIMIT 10 ;
/*==============================================================*/
/* Table: Dayrules                                              */
/*==============================================================*/
create table Dayrules
(
   dayid                varchar(36) not null,
   thisDay              DATE comment '日期',
   parkid               varchar(36) comment '车位外键',
   hours_01             int(1),
   hours_23             int(1),
   hours_02             int(1),
   hours_03             int(1),
   hours_04             int(1),
   hours_05             int(1),
   hours_06             int(1),
   hours_07             int(1),
   hours_08             int(1),
   hours_09             int(1),
   hours_10             int(1),
   hours_11             int(1),
   hours_12             int(1),
   hours_13             int(1),
   hours_14             int(1),
   hours_15             int(1),
   hours_16             int(1),
   hours_17             int(1),
   hours_18             int(1),
   hours_19             int(1),
   hours_20             int(1),
   hours_21             int(1),
   hours_22             int(1),
   hours_24             int(1),
   primary key (dayid)
);

alter table Dayrules comment '日期规则表';
select * from  Dayrules;
select * from  Dayrules 
	where parkid='24534087746194136'
		and 
;
select * from dayrules where  parkid='24534087746194136' and hours_05=1 and hours_06=1
select count(1) from dayrules where parkid='2225b7fd-ac57-4619-975f-9a6453f8187b' and thisDay='2016-4-28' and ( hours_01 is null or hours_01=0 ) and ( hours_02 is null or hours_02=0 ) and ( hours_03 is null or hours_03=0 ) and ( hours_04 is null or hours_04=0 ) and ( hours_05 is null or hours_05=0 ) and ( hours_06 is null or hours_06=0 ) and ( hours_07 is null or hours_07=0 ) and ( hours_08 is null or hours_08=0 ) and ( hours_09 is null or hours_09=0 ) and ( hours_10 is null or hours_10=0 ) and ( hours_11 is null or hours_11=0 ) and ( hours_12 is null or hours_12=0 ) and ( hours_13 is null or hours_13=0 ) and ( hours_14 is null or hours_14=0 ) and ( hours_15 is null or hours_15=0 ) and ( hours_16 is null or hours_16=0 ) and ( hours_17 is null or hours_17=0 ) and ( hours_18 is null or hours_18=0 ) and ( hours_19 is null or hours_19=0 ) and ( hours_20 is null or hours_20=0 ) and ( hours_21 is null or hours_21=0 ) and ( hours_22 is null or hours_22=0 ) and ( hours_23 is null or hours_23=0 ) and ( hours_24 is null or hours_24=0 )
/*==============================================================*/
/* Table: InvitedRecord                                         */
/*==============================================================*/
create table InvitedRecord
(
   invId                varchar(36) not null comment '邀请表主键ID',
   to_phone             varchar(11) not null comment '被邀请人电话',
   inviteTime           datetime comment '邀请时间',
   inviteState          int(1) comment '邀请状态（0：已发送，1：已注册）',
   userid               varchar(36) not null comment '用户ID（外键）',
   parkid               varchar(20) not null,
   primary key (invId)
);

alter table InvitedRecord comment '邀请记录表';

/*==============================================================*/
/* Table: Message                                               */
/*==============================================================*/
create table Message
(
   msgid                varchar(36) not null,
   msgType              int(1) not null comment '消息类型（0：判断消息，1，文字消息）',
   msgContent           varchar(200) not null,
   userid               varchar(36) not null comment '用户id',
   orderid              varchar(36) comment '订单id(查看详情的订单号) 可以为空（为空也可以代表是文字消息）',
   primary key (msgid)
);

alter table Message comment '消息记录表';

/*==============================================================*/
/* Table: OrderInfo                                             */
/*==============================================================*/
create table OrderInfo
(
   orderid              varchar(36) not null,
   ordernum             varchar(18) not null comment '订单号码（规则：年月日时分秒毫秒（13位）+随机数（5））',
   parkid               varchar(36) not null comment '车位ID（外键）',
   placeorde_time       datetime not null comment '下订单时间（生成时间）',
   supplierconfirm_time datetime comment '供方确认时间',
   payment_time         datetime comment '支付时间',
   payType              int(1) comment '支付类型（0“余额支付，1：支付宝，2：微信支付）',
   order_state          int(1) not null comment '订单状态（0：未支付，1：已支付，2：已取消，3：已完成，4.已拒绝',
   vehicleid            varchar(40) not null comment '车牌号',
   money                double(10,2) not null comment '订单金额',
   from_userid          varchar(36) not null comment '用户外键（需方）',
   chargetype           int(1) comment '收费类型（0：按次收费 1：按小时收费）',
	 typemoney 						double(10,2),#类型金额
   primary key (orderid)
);
select * from orderinfo
insert into OrderInfo(orderid,ordernum,parkid,placeorde_time,order_state,vehicleid,from_userid,chargetype,typemoney)
	select '1234565' from DUAL
		WHERE not
	EXISTS 
		(
			select * from ordertime 
					where thisdate>= left(now(),10) 
				and  orderid 
						in 
				(select orderid from orderinfo o where o.parkid='24534087746194136' and (order_state=1 or order_state=0 or order_state=2))#这里查询该车位以后的订单时间
					and( #根据多个时间组去验证是否有交集
						(	thisdate='2016-05-09' and  
								(
									(begin_time>'18:00' and begin_time<'19:00') or 
									(begin_time<'18:00' and end_time>  '19:00') or 
									(end_time>  '18:00' and end_time<  '19:00')
								)
						)
					or
						(	thisdate='2016-05-10' and   		(
									(begin_time>'9:00' and begin_time<'15:00') or 
									(begin_time<'9:00' and end_time>  '15:00') or 
									(end_time>  '9:00' and end_time<  '15:00') ) 	)
			)
		)


alter table OrderInfo comment '订单表';

/*==============================================================*/
/* Table: OrderTime                                             */
/*==============================================================*/
create table OrderTime
(
   otId                 varchar(36) not null comment '订单时间表主键',
   begin_time           time,
   end_time             time,
   thisDate             date comment '当前日期',
   orderid              varchar(36) comment '订单外键（此条数据对应的是哪个订单）',
   primary key (otId)
);

alter table OrderTime comment '订单时间表，已天为单位计算 一个订单的一天的时间段。（对应一个订单是多天本表数据）';
select * from ordertime 
	where 
		orderid in
				(select orderid from orderinfo where order_state=2 and parkid='24534087746194136') 
	and thisDate='2016-05-06'

insert into OrderTime values
('01','10:00','12:00','2016-5-11','24534087746194152'),
('02','10:00','12:00','2016-5-11','24534087746194152')
select * from orderinfo;
select * from OrderTime;
select * from parkingspace where parkid='24534087746194136'
select 1,2,3,4,5 from dual;
select thisdate from ordertime 
		where thisdate>= left(now(),10) 
			and  orderid 
					in 
			(select orderid from orderinfo o where o.parkid='24534087746194136' and (order_state=0 OR order_state=1 or order_state=2))
		and(	(	thisdate='2016-5-10' and  
 ( (begin_time>='9:00' and begin_time<='20:00') or  
(begin_time<='9:00' and end_time>=  '20:00')
 or (end_time>=  '9:00' and end_time<=    '20:00'))))

/*==============================================================*/
/* Table: Parkingspace                                          */
/*==============================================================*/
create table Parkingspace
(
   parkid               varchar(36) not null comment '车位主键ID',
   parkNo               varchar(40) not null comment '车位编号',
   park_instructions    varchar(300) comment '车位具体说明',
   releasedate          datetime not null comment '发布时间',
   update_time         datetime comment '车位更新时间',
   parkstate            int(1) comment '车位状态（缺省）',
   money                double(10,2) not null comment '停车费用',
   chargetype           int(1) not null comment '收费类型（0：按次收费 1：按小时收费）',
   userid               varchar(36),
   communityId          varchar(36) comment '小区外键',
	 entrance             VARCHAR(300) COMMENT '入口',
   is_delete            int(1) comment '是否删除（0：未删除 1：已经删除）',
	 parkType 						varchar(30) COMMENT '车位类型',
   primary key (parkid)
);
 
insert into parkingspace
	(parkid,parkNo,park_instructions,releasedate,update_time,parkstate,money,chargetype,userid,communityId,is_delete,parkType,entrance) 
		VALUES('12345690','%a123','车位说明',NOW(),NOW(),0,10.00,0,'111','1',0,'地面车位','我是入口')

　
#通过停车场id查询

#查询该车位的订单时间  如：2016-05-09 13:00-14:00  已经存在  
select * from ordertime 
		where thisdate>= left(now(),10) 
	and  orderid 
			in 
	(select orderid from orderinfo o where o.parkid='24534087746194136' and (order_state=1 or order_state=0 or order_state=2))#这里查询该车位以后的订单时间
		and( #根据多个时间组去验证是否有交集
			(	thisdate='2016-05-09' and  
					(
						(begin_time>'18:00' and begin_time<'19:00') or 
						(begin_time<'18:00' and end_time>  '19:00') or 
						(end_time>  '18:00' and end_time<    '19:00')
					)
			)
		or
			(	thisdate='2016-05-12' and   		(
						(begin_time>='9:00' and begin_time<='15:00') or 
						(begin_time<='9:00' and end_time>=  '15:00') or 
						(end_time>= '9:00' and end_time<=  '15:00') ) 	)
);
select * from orderinfo
/*select *   
from test_table  
where (startTime > a AND startTime < b) OR  
        (startTime < a AND endTime > b) OR  
        (endTime > a AND endTime < b)  */


alter table Parkingspace comment '车位表';
#alter table Parkingspace add column parkType varchar(30);
#alter table Parkingspace change dapdate_time update_time datetime;
select * from Parkingspace
select p.entrance,p.parkType,p.parkid,p.parkNo,p.park_instructions,money,chargetype,c.communityId,community_name,community_address from parkingspace p,community c where c.communityId=p.communityId and p.parkid='24534087746194136'
select * from community
#小区地址 小区名字 车位
select p.parkNo,p.parkid,c.community_name,c.community_address from parkingspace p,Community c where p.communityId=c.communityId and p.userid='111';
#update parkingspace set parkNo='parkNo',park_instructions='车位说明',update_time=now(),money=200,chargetype=1,parkType='停车场',entrance='入口' where parkid='123456'
/*==============================================================*/
/* Table: Purse                                                 */
/*==============================================================*/
create table Purse
(
   purseid              varchar(36) not null comment '钱包表主键ID',
   balance              double(10,2) not null comment '余额',
   blocked_balances     double(10,2) not null comment '冻结余额',
   userid               varchar(36) UNIQUE not null comment '用户表外键ID',
   primary key (purseid)
);
#select count(1) from Purse where userid=(select userid from user where mobile='18581343206')
#insert into purse values('1aa154569',0.00,0.00,(select userid from user where mobile='18581343206'));
#select * from purse;
alter table Purse comment '钱包表';

/*==============================================================*/
/* Table: TouchBalance                                          */
/*==============================================================*/

create table TouchBalance
(
   tbid                 varchar(36) not null comment '余额明细表Id',
   money                double(10,2) not null comment '金额（正数或者负数；支出或者收入）',
   operation_time       datetime not null comment '时间',
   tradtype              int(1),#'交易类型（0：支付宝，1：微信，2余额支付，3，代理收益，4.，自动退款）',
   purseid              varchar(36) comment '钱包外键',
   primary key (tbid)
);

alter table TouchBalance comment '余额明细表';

/*==============================================================*/
/* Table: Vehicle                                               */
/*==============================================================*/
create table Vehicle
(
   vehicleid            varchar(36) not null comment '车辆主键id',
   vehicleno            varchar(10) not null comment '车牌号',
   userid               varchar(36) not null,
   primary key (vehicleid)
);

alter table Vehicle comment '车辆表';

/*==============================================================*/
/* Table: Weekrules                                             */
/*==============================================================*/
create table Weekrules
(
   weekid               varchar(36) not null,
   monday_begin         time not null comment '星期一（开始）',
   monday_end           time not null comment '星期一（结束）',
   tuesday_begin        time not null comment '星期二（开始）',
   tuesday_end          time not null comment '星期二（结束）',
   wednesday_begin      time not null comment '星期三（开始）',
   wednesday_end        time not null comment '星期三（结束）',
   thursday_begin       time not null comment '星期四（开始）',
   thursday_end         time not null comment '星期四（结束）',
   friday_begin         time not null comment '星期五（开始）',
   friday_end           time not null comment '星期五（结束）',
   saturday_begin       time not null comment '星期六（开始）',
   saturday_end         time not null comment '星期六（结束）',
   sunday_begin         time not null comment '星期日（开始）',
   sunday_end           time not null comment '星期日（结束）',
   update_time          datetime not null,
   parkid               varchar(36),
   primary key (weekid)
);
/*update Weekrules set
		monday_begin={monday_begin},monday_end={monday_end},
		tuesday_begin={tuesday_begin},tuesday_end={tuesday_end},
		wednesday_begin={wednesday_begin},wednesday_end={wednesday_end},
		thursday_begin={thursday_begin},thursday_end={thursday_end},
		friday_begin={friday_begin},friday_end={friday_end},
		saturday_begin={saturday_begin},saturday_end={saturday_end},
		sunday_begin={sunday_begin},sunday_end={sunday_end}
 where weekid='b8e500c6-c6bb-43d4-88de-1ae760938d05'
*/
alter table Weekrules comment '星期规则表';
select * from Weekrules
/*==============================================================*/
/* Table: WithdrawalRecord                                      */
/*==============================================================*/
create table WithdrawalRecord
(
   wrid                 varchar(36) not null comment '提现ID',
   withdrawal_money     double(10,2) comment '提现金额',
   withdrawal_date      datetime comment '提现时间',
   userid               varchar(36) not null,
   withdrawal_state     int(1) comment '提现状态（0：申请中，1：已提现 2:其他）',
   successfultime       datetime comment '提现审核时间。',
   is_delete            int(1) comment '是否删除（0：未删除  1：已删除）',
   payName              varchar(30),
   payNo                varchar(30),
   primary key (wrid)
);

alter table WithdrawalRecord comment '提现记录表';

/*==============================================================*/
/* Table: feedback                                              */
/*==============================================================*/
create table feedback
(
   feedbackid           varchar(36) not null comment '用户反馈主键',
   feedback_content     varchar(300) not null comment '用户反馈内容',
   feedback_img         varchar(200) comment '反馈图片（只支持一张图）',
   userid               varchar(36) not null comment '反馈的用户',
   primary key (feedbackid)
);

alter table feedback comment '用户反馈表';

/*==============================================================*/
/* Table: version                                               */
/*==============================================================*/
create table version
(
   versionid            varchar(36) not null comment '主键ID',
   version              int comment '版本号',
   description          varchar(100) comment '描述',
   url                  varchar(70) comment '路劲',
   os_version           varchar(30) comment '操作系统版本号',
   primary key (versionid)
);

alter table version comment '版本信息';

alter table Certification add constraint FK_Certification_User foreign key (userid)
      references User (userid) on delete restrict on update restrict;

alter table Certification add constraint FK_Certification_Vehicle foreign key (vehicleid)
      references Vehicle (vehicleid) on delete restrict on update restrict;

alter table Collect add constraint FK_Collect_Parkingspace foreign key (parkid)
      references Parkingspace (parkid) on delete restrict on update restrict;

alter table Collect add constraint FK_Collect_User foreign key (userid)
      references User (userid) on delete restrict on update restrict;

alter table Dayrules add constraint FK_Dayrules_ParkInfo foreign key (parkid)
      references Parkingspace (parkid) on delete restrict on update restrict;

alter table InvitedRecord add constraint FK_InvitedRecord_User foreign key (userid)
      references User (userid) on delete restrict on update restrict;

alter table Message add constraint FK_Message_Order foreign key (orderid)
      references OrderInfo (orderid) on delete restrict on update restrict;

alter table OrderInfo add constraint FK_OrderInfo_Park foreign key (parkid)
      references Parkingspace (parkid) on delete restrict on update restrict;

alter table OrderInfo add constraint FK_Order_FromUser foreign key (from_userid)
      references User (userid) on delete restrict on update restrict;

alter table OrderTime add constraint FK_OrderTime_OrderInfo foreign key (orderid)
      references OrderInfo (orderid) on delete restrict on update restrict;

alter table Parkingspace add constraint FK_Parkingspace_Community foreign key (communityId)
      references Community (communityId) on delete restrict on update restrict;

alter table Parkingspace add constraint FK_Parkingspace_User foreign key (userid)
      references User (userid) on delete restrict on update restrict;

alter table Purse add constraint FK_Purse_User foreign key (userid)
      references User (userid) on delete restrict on update restrict;

alter table TouchBalance add constraint FK_TouchBalance_Purse foreign key (purseid)
      references Purse (purseid) on delete restrict on update restrict;

alter table Vehicle add constraint FK_Vehice_User foreign key (userid)
      references User (userid) on delete restrict on update restrict;

alter table Weekrules add constraint FK_Weekrules_Parkingspace foreign key (parkid)
      references Parkingspace (parkid) on delete restrict on update restrict;

alter table WithdrawalRecord add constraint FK_WithdrawalRecord_User foreign key (userid)
      references User (userid) on delete restrict on update restrict;

alter table feedback add constraint FK_Feedback_User foreign key (userid)
      references User (userid) on delete restrict on update restrict;


INSERT INTO `user` VALUES ('111', 'tianjin', '18272163455', '123456', null, null, null, null, null, null, '0', null, null, '2016-04-28 00:00:00', '2833', null, null, null, null, null, null, null, null, null);
SELECT * FROM user;

INSERT INTO advertising VALUES('2','223','13245');
select * FROM advertising;

INSERT INTO purse VALUES('1',10.00,20.00,'111');
select * FROM purse;

INSERT INTO touchbalance VALUES('1',5.00,'2016-04-28 00:00:00',NULL,'1');
select * FROM touchbalance;

INSERT INTO vehicle VALUES('1','123456','111');
select * FROM vehicle;

INSERT INTO certification(certificationId,userid,vehicleid) VALUES('1','111','1');
select  * from certification;

INSERT INTO community VALUES('1','yangpu','世纪大道',NULL,'2016-04-28 00:00:00',0);
select * FROM community;

INSERT INTO parkingspace VALUES('1','123',NULL,'2016-04-28 00:00:00','2016-04-29 00:00:00',0,20.00,0,'111','1',0);/*车位表*/
select * FROM parkingspace;

INSERT INTO orderinfo VALUES('1','20164281736596','1','2016-04-28 17:36:59','2016-04-28 17:38:59','2016-04-28 17:40:59',1,1,'123456',100.00,'111',0);/*订单表*/
select * from orderinfo;

INSERT INTO message VALUES('1',1,'22222','111',NULL);/*消息记录表*/
select * FROM message;

INSERT INTO invitedrecord VALUES('11','1235','2016-04-28 00:00:00',0,'111','1');/*邀请记录表*/
select * FROM invitedrecord;

INSERT INTO collect VALUES('1','111','1','2016-04-28 00:00:00');/*车位收藏表*/
select * from collect;

INSERT INTO ordertime VALUES('1','2016-04-28 00:00:00','2016-04-29 00:00:00','2016-04-28 00:00:00','1');/*订单时间表*/
select * FROM ordertime;

INSERT INTO weekrules VALUES('1','2016-04-28 00:00:00','2016-04-28 00:02:00','2016-04-29 00:00:00','2016-04-29 00:03:00','2016-04-30 00:00:00','2016-04-30 00:02:00','2016-05-01 00:02:00','2016-05-01 00:04:00','2016-05-02 00:01:00','2016-05-02 00:04:00','2016-05-03 00:02:00','2016-05-03 00:04:00','2016-05-04 00:01:00','2016-05-04 00:04:00','2016-04-28 00:00:00','1');/*星期规则表*/
select * FROM weekrules;

INSERT INTO withdrawalrecord VALUES('1',10.00,'2016-05-01 00:04:00','111',0,'2016-05-01 00:06:00',0,NULL,NULL);/*提现记录表*/
select * FROM withdrawalrecord;

INSERT INTO feedback VALUES('1','太好了',NULL,'111');/*用户反馈表*/
select * FROM feedback;

INSERT INTO version(versionid,version) VALUES('1',2);/*版本信息*/
select * FROM version;

DELETE FROM dayrules WHERE dayid='1';

INSERT INTO dayrules VALUES('1','2016-04-28 00:00:00','1',1,0,0,0,0,1,0,0,1,0,1,0,1,1,1,1,0,0,0,0,0,0,0,0);/*日期规则表*/
select * FROM dayrules;

