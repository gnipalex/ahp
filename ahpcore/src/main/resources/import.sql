
-- add unique constraint for AlternativeComparisonTable entity

ALTER TABLE ComparisonTable ADD CONSTRAINT UC_user_criteria_decision UNIQUE (user_id, criteria_id, projectDecision_id);

-- add unique constraint for AlternativeComparisonTable entity

ALTER TABLE ComparisonTable ADD CONSTRAINT UC_name_decision UNIQUE (name, projectDecision_id);


-- sample data

insert into role(name) values('ROLE_USER');

insert into user(id,firstName, lastName, email, password) value (1, 'Test', 'User', 'test@email.com', '123123');
insert into user(id,firstName, lastName, email, password) value (2, 'Test2', 'User2', 'test2@email.com', '123123');

insert into user_role(user_id, roles_id) value (1, 1);

insert into project (id, name, description, owner_id) value (1, 'Test Project', 'This is fully test project, created for test user', 1);

insert into projectdecision (id, description, goal, project_id, status) value (1, 'We want to select some sweets but to be sure that we select the best option among available options', 'Select sweet', 1, 'CREATED');
insert into projectdecision (id, description, goal, project_id, status) value (2, 'We have limited budget, but we would like to get some special gadget', 'Select new gadget', 1, 'CREATED');
insert into projectdecision (id, description, goal, project_id, status) value (3, 'I can''t choose my startup direction', 'Select startup direction', 1, 'CREATED');


-- alternatives
insert into comparableitem (id, name, description, type, projectDecision_id) value (1,'Cookie Maria', 'This is Maria cookie, it can be bought in ATB market, it contains low lewel of sugar, also its cost is low', 'alternative', 1);
insert into comparableitem (id, name, description, type, projectDecision_id) value (2,'Wafles Artek', 'These wafles are tasty, everyone remembers it, it''s crispy and with chocolate, tastes well with a cup of coffee', 'alternative', 1);

-- criterias
insert into comparableitem (id,name, description, type, projectDecision_id) value (3,'Price', 'How much alternative costs, the less is the better', 'criteria', 1);
insert into comparableitem (id,name, description, type, projectDecision_id) value (4,'Taste', 'Compare taste of these alternatives', 'criteria', 1);
insert into comparableitem (id,name, description, type, projectDecision_id) value (5,'Best for children', 'Which one better suits for children, for their health', 'criteria', 1);

-- vote requests
insert into voterequest (id, email, token, projectDecision_id, registeredUser_id, status) value (1, 'test@email.com', '1234#gdgdg#89121', 1, 1, 'CONFIRMED');
insert into voterequest (id, email, token, projectDecision_id, registeredUser_id, status) value (2, 'test2@email.com', 'uuu1234#gdgdg#89121', 1, null, 'CREATED');

-- criteria comparison table for Child
insert into comparisontable (id, type, name, description, projectDecision_id) value (1, 'criteria', 'Child', 'How little child treats priorities', 1);

insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (1, 'EQUAL', 1, 3, 3);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (2, 'SLIGHTLY_CONCEDE', 1, 3, 4);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (3, 'EQUAL', 1, 3, 5);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (4, 'SLIGHTLY_FAVORS', 1, 4, 3);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (5, 'EQUAL', 1, 4, 4);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (6, 'EXTREME_FAVORS', 1, 4, 5);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (7, 'EQUAL', 1, 5, 3);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (8, 'EXTREME_CONCEDE', 1, 5, 4);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (9, 'EQUAL', 1, 5, 5);

-- criteria comparison table for Mother
insert into comparisontable (id, type, name, description, projectDecision_id) value (2, 'criteria', 'Mother', 'How mother treats priorities', 1);

insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (11, 'EQUAL', 2, 3, 3);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (12, 'SLIGHTLY_CONCEDE', 2, 3, 4);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (13, 'EQUAL', 2, 3, 5);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (14, 'SLIGHTLY_FAVORS', 2, 4, 3);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (15, 'EQUAL', 2, 4, 4);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (16, 'STRONGLY_CONCEDE', 2, 4, 5);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (17, 'EQUAL', 2, 5, 3);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (18, 'STRONGLY_FAVORS', 2, 5, 4);
insert into comparisonpair (id, value, comparisonTable_id, itemA_id, itemB_id) value (19, 'EQUAL', 2, 5, 5);


