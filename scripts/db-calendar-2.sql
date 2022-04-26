

select version() as 'mysql version';

INSERT INTO USERS(id, name, password, email, calendar) VALUES(55, null, null, null, null);
select DISTINCT  email, password from users where email='123@email.com' and password='123';


select * from user;
select * from calendar_permit;
select * from task;
select * from calendar;

select * from invitation;


truncate user;
truncate task;
truncate calendar;
truncate calendar_permit;

SET FOREIGN_KEY_CHECKS = 0; 

SET FOREIGN_KEY_CHECKS = 1;

