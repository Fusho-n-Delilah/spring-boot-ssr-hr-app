use employee;

CREATE TABLE pto_requests(
	id			int				NOT NULL auto_increment,
	emp_no      INT             NOT NULL,
    from_date   DATE            NOT NULL,
    to_date     DATE			NOT NULL,
    total_hours	FLOAT			NOT NULL,
    approved	tinyint			NOT NULL,
    FOREIGN KEY (emp_no) REFERENCES employee (emp_no) ON DELETE CASCADE,
    PRIMARY KEY (id, emp_no, from_date)
);

