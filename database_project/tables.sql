use investment;

	drop table if exists advises, transaction, investee, client, agent;

create table agent
	(A_ID			numeric(6,0),
	 last_name		varchar(20),
	 first_name		varchar(20),
	 salary			numeric(8,2),
	 primary key (A_ID)
	);

create table client
	(C_ID			numeric(6,0),
	 last_name		varchar(20), 
	 first_name		varchar(15), 
	 tot_balance	numeric(12,2) check (tot_balance > 0),
	 tot_gain		numeric(12,2),
	 primary key (C_ID)
	);

create table investee
	(I_ID			numeric(6,0),
	 company_name	varchar(30), 
	 tot_shares		numeric(12,3), 
	 share_value	numeric(6,2),
	 primary key (I_ID)
	);

create table transaction
	(T_ID			numeric(12,0),
	 A_ID			numeric(6,0),
	 C_ID			numeric(6,0),
	 I_ID			numeric(6,0),
	 num_shares		numeric(12,3), 
	 share_price	numeric(6,2),
	 fee			numeric(6,2), check (fee > 0),
	 day			numeric(2,0), check (0 < day < 32),
	 month			numeric(2,0) check (0 < month < 13),
	 year			numeric(4,0) check (year > 0),
	 primary key (T_ID),
	 foreign key (A_ID) references agent(A_ID)
		on delete cascade,
	 foreign key (C_ID) references client(C_ID)
		on delete cascade,
	 foreign key (I_ID) references investee(I_ID)
		on delete cascade
	);

create table advises
	(A_ID			numeric(6,0), 
     C_ID			numeric(6,0),
	 primary key (A_ID, C_ID),
	 foreign key (A_ID) references agent(A_ID)
		on delete cascade,
	 foreign key (C_ID) references client(C_ID)
		on delete cascade
	);

