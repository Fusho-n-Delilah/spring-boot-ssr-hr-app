use employee;

drop table if exists work_hours;
drop table if exists time_sheet;

Create Table time_sheet(
	id				int			NOT NULL auto_increment,
    emp_no			int			NOT NULL,
    pay_period_id	int			NOT NULL,
    submitted_date	date				,
    approver_id		int					,
    approved_date	date				,
    FOREIGN KEY (emp_no) REFERENCES employee (emp_no),
    FOREIGN KEY (pay_period_id) REFERENCES pay_periods (id),
    PRIMARY KEY (id)
);

Create Table work_hours (
	id			int			NOT NULL auto_increment,
    emp_no		int			NOT NULL,
    date		date		NOT NULL,
    hours		float		NOT NULL,
    pay_period_id	int		NOT NULL,
    time_sheet_id	int		NOT NULL,
    FOREIGN KEY (emp_no) REFERENCES employee (emp_no),
    FOREIGN KEY (pay_period_id) REFERENCES pay_periods (id),
    FOREIGN KEY (time_sheet_id) REFERENCES time_sheet (id),
    PRIMARY KEY (id)
);



