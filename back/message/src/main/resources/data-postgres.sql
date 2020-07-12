insert into conversation(user_one_id, user_two_id, last_message, time_of_last_message) values (4, 2, 'Za ovaj komentar imas popust sledeci put!', ('2020-06-17T10:59:21'));
insert into conversation(user_one_id, user_two_id, last_message, time_of_last_message) values (4, 1, 'Poslednja poruka adminu.', ('2020-06-17T10:59:21'));
insert into conversation(user_one_id, user_two_id, last_message, time_of_last_message) values (3, 2, 'Kolega moze li ovaj vas bmw da se proba?', ('2020-06-20T11:20:21'));

insert into message(sender_id, receiver_id, date_and_time, text, conversation_id, message_type) values (3, 2, ('2020-06-20T11:20:21'), 'Kolega moze li ovaj vas bmw da se proba?', 3, 0);
insert into message(sender_id, receiver_id, date_and_time, text, conversation_id, message_type) values(4 , 2, ('2020-06-15T14:20:11'), 'Dobar vam je ovaj BMW!', 1, 0);
insert into message(sender_id, receiver_id, date_and_time, text, conversation_id, message_type) values(2 , 4, ('2020-06-17T10:59:21'), 'Za ovaj komentar imas popust sledeci put!', 1, 0);