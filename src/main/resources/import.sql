insert into user_profile_table(permission,username,password,salt) values ('admin','testtest','wKpbQvahOtbT2ZfKrB8AzDqH+uUSSWZMrDRiZ8LQm1TfF2xhOMt3eeVxXr7O5ghFI6B8uJTbgrB40g/8S8HlXw==','na9lMAPHtLI=');
insert into employee_profile_table(user_id,employee_nr,role,first_name,last_name,phone_nr,email) values (1,'123','Dealer','Nils','Karlsson','073-123456','nilsk@test.com');
insert into customer_profile_table(user_id,company_name,org_nr,first_name,last_name,address,phone_nr,email) values (1,'Sporten','550011','Nils','Karlsson','Stockholm','073-123456','nilsk@test.com');

insert into employee_profile_table(user_id,employee_nr,role,first_name,last_name,phone_nr,email) values (2,'123','Dealer','Pelle','Svanslös','073-123456','nilsk@test.com');
insert into customer_profile_table(user_id,company_name,org_nr,first_name,last_name,address,phone_nr,email) values (3,'Sporten','550011','Johan','Falk','Stockholm','073-123456','nilsk@test.com');
insert into user_profile_table(permission,username,password,salt) values ('customer','test123','wKpbQvahOtbT2ZfKrB8AzDqH+uUSSWZMrDRiZ8LQm1TfF2xhOMt3eeVxXr7O5ghFI6B8uJTbgrB40g/8S8HlXw==','na9lMAPHtLI=');

insert into report_table(id,game_table_name,location,report_nr,employee_sign,customer_sign,digital_cash_flow,cash_flow,revenue,payment,info_field,status) values (1,'BlackJack','Stockholm','1','123','073-123456',1000.00,1000.00,700.00,1200.00,'Bra kväll','Avslutad');