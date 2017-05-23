use ahp;

insert into user(id,email,firstName,lastName,password) value (2, 'user2@email.com', 'User 2', 'Last name', '123123');

insert into role(id,name) value(1, 'ROLE_USER');

insert into user_role(user_id,roles_id) value(2,1);

insert into project(id,name,description,user_id) value(1, 'Project 1', 'Choose desert', 2);

insert into criteria(id, name, project_id) value(1, 'Degree of sweetness', 1);
insert into criteria(id, name, project_id) value(2, 'Cost', 1);
insert into criteria(id, name, project_id) value(3, 'Amount', 1);

insert into criteriacomparisonversion(id, name, project_id) value(1, 'For children', 1);
insert into criteriacomparisonversion(id, name, project_id) value(2, 'For adult', 1);

insert into criteriacomparison(id, criteria_a_id, criteria_b_id, value, project_id, version_id)
	values (1, 1 ,1 , 'EQUAL', 1, 1), 
			(2, 1 ,2 , 'SLIGHTLY_FAVORS', 1, 1),
            (3, 1 ,3 , 'STRONGLY_CONCEDE', 1, 1),
            (4, 2 ,1 , 'SLIGHTLY_CONCEDE', 1, 1),
            (5, 2 ,2 , 'EQUAL', 1, 1),
            (6, 2 ,3 , 'VERY_STRONGLY_CONCEDE', 1, 1),
            (7, 3 ,1 , 'STRONGLY_FAVORS', 1, 1),
            (8, 3 ,2 , 'VERY_STRONGLY_FAVORS', 1, 1),
            (9, 3 ,3 , 'EQUAL', 1, 1);

insert into alternative(id,name,description,project_id) value(1,'Ice cream', '', 1);
insert into alternative(id,name,description,project_id) value(2,'Cake', '', 1);
insert into alternative(id,name,description,project_id) value(3,'Pie', '', 1);

insert into alternativecomparison(id,alternative_a_id, alternative_b_id, value,criteria_id, user_id)
	values (1, 1, 1, 'EQUAL', 1, 2),
			(2, 1, 2, 'VERY_STRONGLY_FAVORS', 1, 2),
            (3, 1, 3, 'EXTREME_FAVORS', 1, 2),
            (4, 2, 1, 'VERY_STRONGLY_CONCEDE', 1, 2),
            (5, 2, 2, 'EQUAL', 1, 2),
            (6, 2, 3, 'STRONGLY_FAVORS', 1, 2),
            (7, 3, 1, 'EXTREME_CONCEDE', 1, 2),
            (8, 3, 2, 'STRONGLY_CONCEDE', 1, 2),
            (9, 3, 3, 'EQUAL', 1, 2);





