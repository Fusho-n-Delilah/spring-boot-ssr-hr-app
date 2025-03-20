use employee;

drop table if exists pay_periods;

CREATE TABLE pay_periods(
	id			int							NOT NULL AUTO_INCREMENT,
    schedule	ENUM('Bi-Weekly','Weekly')	NOT NULL,
    status		tinyint						NOT NULL DEFAULT 0,
    from_date	date						NOT NULL,
    to_date		date						NOT NULL,
    chk_date	date						NOT NULL,
    year		int							NOT NULL,
    PRIMARY KEY	(id)
);

INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-03-08","2025-03-21","2025-03-28",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-03-22","2025-04-04","2025-04-11",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-04-05","2025-04-18","2025-04-25",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-04-19","2025-05-02","2025-05-09",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-05-03","2025-05-16","2025-05-23",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-05-17","2025-05-30","2025-06-06",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-05-31","2025-06-13","2025-06-20",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-06-14","2025-06-27","2025-07-04",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-06-28","2025-07-11","2025-07-18",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-07-12","2025-07-25","2025-08-01",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-07-26","2025-08-08","2025-08-15",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-08-09","2025-08-22","2025-08-29",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-08-23","2025-09-05","2025-09-12",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-09-06","2025-09-19","2025-09-26",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-09-20","2025-10-03","2025-10-10",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-10-04","2025-10-17","2025-10-24",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-10-18","2025-10-31","2025-11-07",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-11-01","2025-11-14","2025-11-21",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-11-15","2025-11-28","2025-12-05",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-11-29","2025-12-12","2025-12-19",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-12-13","2025-12-26","2026-01-02",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2025-12-27","2026-01-09","2026-01-16",2025);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-01-10","2026-01-23","2026-01-30",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-01-24","2026-02-06","2026-02-13",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-02-07","2026-02-20","2026-02-27",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-02-21","2026-03-06","2026-03-13",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-03-07","2026-03-20","2026-03-27",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-03-21","2026-04-03","2026-04-10",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-04-04","2026-04-17","2026-04-24",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-04-18","2026-05-01","2026-05-08",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-05-02","2026-05-15","2026-05-22",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-05-16","2026-05-29","2026-06-05",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-05-30","2026-06-12","2026-06-19",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-06-13","2026-06-26","2026-07-03",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-06-27","2026-07-10","2026-07-17",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-07-11","2026-07-24","2026-07-31",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-07-25","2026-08-07","2026-08-14",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-08-08","2026-08-21","2026-08-28",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-08-22","2026-09-04","2026-09-11",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-09-05","2026-09-18","2026-09-25",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-09-19","2026-10-02","2026-10-09",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-10-03","2026-10-16","2026-10-23",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-10-17","2026-10-30","2026-11-06",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-10-31","2026-11-13","2026-11-20",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-11-14","2026-11-27","2026-12-04",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-11-28","2026-12-11","2026-12-18",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-12-12","2026-12-25","2027-01-01",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2026-12-26","2027-01-08","2027-01-15",2026);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-01-09","2027-01-22","2027-01-29",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-01-23","2027-02-05","2027-02-12",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-02-06","2027-02-19","2027-02-26",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-02-20","2027-03-05","2027-03-12",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-03-06","2027-03-19","2027-03-26",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-03-20","2027-04-02","2027-04-09",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-04-03","2027-04-16","2027-04-23",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-04-17","2027-04-30","2027-05-07",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-05-01","2027-05-14","2027-05-21",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-05-15","2027-05-28","2027-06-04",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-05-29","2027-06-11","2027-06-18",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-06-12","2027-06-25","2027-07-02",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-06-26","2027-07-09","2027-07-16",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-07-10","2027-07-23","2027-07-30",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-07-24","2027-08-06","2027-08-13",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-08-07","2027-08-20","2027-08-27",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-08-21","2027-09-03","2027-09-10",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-09-04","2027-09-17","2027-09-24",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-09-18","2027-10-01","2027-10-08",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-10-02","2027-10-15","2027-10-22",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-10-16","2027-10-29","2027-11-05",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-10-30","2027-11-12","2027-11-19",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-11-13","2027-11-26","2027-12-03",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-11-27","2027-12-10","2027-12-17",2027);
INSERT INTO pay_periods (schedule,status,from_date,to_date,chk_date,year)  VALUES("Bi-Weekly",0,"2027-12-11","2027-12-24","2027-12-31",2027);

select * from pay_periods;