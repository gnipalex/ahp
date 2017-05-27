insert into role(name) values('ROLE_USER');

insert into user(id,firstName, lastName, email, password) value (1, 'Test', 'User', 'test@email.com', '123123');
insert into user(id,firstName, lastName, email, password) value (2, 'Test2', 'User2', 'test2@email.com', '123123');

insert into user_role(user_id, roles_id) value (1, 1);

insert into project (id, name, description, owner_id) value (1, 'Test Project', 'This is fully test project, created for test user', 1);

insert into projectdecision (id, description, goal, project_id) value (1, 'We want to select some sweets but to be sure that we select the best option among available options', 'Select sweet', 1);
																	 
insert into comparableitem (id, name, description, type, projectDecision_id) value (1,'Cookie Maria', 'This is Maria cookie, it can be bought in ATB market, it contains low lewel of sugar, also its cost is low', 'alternative', 1);
insert into comparableitem (id, name, description, type, projectDecision_id) value (2,'Wafles Artek', 'These wafles are tasty, everyone remembers it, it''s crispy and with chocolate, tastes well with a cup of coffee', 'alternative', 1);

insert into comparableitem (id,name, description, type, projectDecision_id) value (3,'Price', 'How much alternative costs, the less is the better', 'criteria', 1);
insert into comparableitem (id,name, description, type, projectDecision_id) value (4,'Taste', 'Compare taste of these alternatives', 'criteria', 1);
insert into comparableitem (id,name, description, type, projectDecision_id) value (5,'Best for children', 'Which one better suits for children, for their health', 'criteria', 1);

insert into voterequest (id, email, token, projectDecision_id, registeredUser_id, status) value (1, 'test@email.com', '1234#gdgdg#89121', 1, 1, 'CONFIRMED');
insert into voterequest (id, email, token, projectDecision_id, registeredUser_id, status) value (2, 'test2@email.com', 'uuu1234#gdgdg#89121', 1, null, 'CREATED');