insert into tb_user(id,username,password,phone,email,created,updated)
values
(2,'admin01','e10adc3949ba59abbe56e057f20f883e','123456789',null,sysdate(),sysdate());

INSERT INTO tb_admin_user VALUES
(1,'admin','e10adc3949ba59abbe56e057f20f883e','123456789','kevin@163.com','2017-04-05','2017-04-05');

insert into tb_item
(id, title, sell_point, price, num, barcode, image, cid, status, created, updated)
values
(1,'123t','ppp',110,10,'bell',null,11,1,sysdate(),sysdate());

insert into tb_content (id,category_id,title,sub_title,title_desc,url,pic,pic2,created,updated,content)
VALUES
(1,1,'NewContent','h','category test','http://123.com',null,null,sysdate(),sysdate(),'hello cat!');






