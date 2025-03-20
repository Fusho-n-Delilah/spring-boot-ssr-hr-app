use employee;

drop table if exists pay_check;

Create Table pay_check(
	id				int			NOT NULL AUTO_INCREMENT,
    emp_no			int			NOT NULL,
    hrs_wrk			FLOAT		NOT NULL DEFAULT 0.0,
    hrs_pto			FLOAT		NOT NULL DEFAULT 0.0,
    gross_wage		float		NOT NULL DEFAULT 0.0,
	fed_tax			FLOAT		NOT NULL,
    med_tax			FLOAT		NOT NULL,
    soc_tax			float		NOT NULL,
    state_tax		float		NOT NULL DEFAULT 0.0,
    tax_total		float		NOT NULL DEFAULT 0.0,
    dppo			float				,
    ppo				float				,
    vision			float				,
    deduct_total	float		NOT NULL DEFAULT 0.0,
    net_wage		float		NOT NULL DEFAULT 0.0,
    chk_date		date		NOT NULL,
    year			int			NOT NULL,
	PRIMARY KEY (`id`,`emp_no`)			,
	CONSTRAINT `check_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employee` (`emp_no`)
);

Alter Table pay_check AUTO_INCREMENT = 1000;
