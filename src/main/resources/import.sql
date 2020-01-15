insert into user_profile_table(permission,username,password,salt) values ('admin','testtest','wKpbQvahOtbT2ZfKrB8AzDqH+uUSSWZMrDRiZ8LQm1TfF2xhOMt3eeVxXr7O5ghFI6B8uJTbgrB40g/8S8HlXw==','na9lMAPHtLI=');
insert into employee_profile_table(user_id,employee_nr,role,first_name,last_name,phone_nr,email) values (1,'123','Dealer','Nils','Karlsson','073-123456','nilsk@test.com');

insert into customer_profile_table(user_id,company_name,org_nr,first_name,last_name,address,phone_nr,email) values (2,'Sporten','550011','Nils','Karlsson','Stockholm','073-123456','nilsk@test.com');
insert into user_profile_table(permission,username,password,salt) values ('customer','test123','wKpbQvahOtbT2ZfKrB8AzDqH+uUSSWZMrDRiZ8LQm1TfF2xhOMt3eeVxXr7O5ghFI6B8uJTbgrB40g/8S8HlXw==','na9lMAPHtLI=');

insert into employee_profile_table(user_id,employee_nr,role,first_name,last_name,phone_nr,email) values (3,'0','Owner','Pelle','Svanslös','073-1234456','nilsk@ser.com');
insert into user_profile_table(permission,username,password,salt) values ('employee','test','wKpbQvahOtbT2ZfKrB8AzDqH+uUSSWZMrDRiZ8LQm1TfF2xhOMt3eeVxXr7O5ghFI6B8uJTbgrB40g/8S8HlXw==','na9lMAPHtLI=');

insert into employee_profile_table(user_id,employee_nr,role,first_name,last_name,phone_nr,email) values (4,'123','Dealer','Pelle','Svanslös','073-123456','nilsk@test.com');

insert into report_table(id,game_table_name,location,employee_sign,customer_sign,digital_cash_flow,cash_flow,revenue,payment,info_field,status,END_CHANGE,END_FIFTIES,END_FIVES,END_HUNDREDS,END_SUM,END_TENS,END_TWENTIES,START_CHANGE,START_FIFTIES,START_FIVES,START_HUNDREDS,START_SUM,START_TENS,START_TWENTIES) values (1,'BlackJack','Stockholm','123','073-123456',1000.00,1000.00,700.00,1200.00,'Bra kväll','Avslutad',0,0,0,0,0,0,0,0,0,0,0,0,0,0);