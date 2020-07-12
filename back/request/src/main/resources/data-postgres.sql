insert into bundle values(default);

insert into request(start_date, end_date, status, total_cost, user_id, owner_id, vehicle_id, bundle_id, time_of_creation)
values (DATE('2020-06-10'), DATE('2020-06-13'), 0, 250, 4, 2, 8, 1, ('2020-06-20T11:20:21'));

insert into request(start_date, end_date, status, total_cost, user_id, owner_id, vehicle_id, bundle_id, time_of_creation)
values (DATE('2020-06-10'), DATE('2020-06-13'), 0, 250, 4, 2, 7, 1, ('2020-06-20T11:20:21'));

insert into request(start_date, end_date, status, total_cost, user_id, owner_id, vehicle_id, time_of_creation)
values (DATE('2020-06-12'), DATE('2020-06-20'), 0, 250, 4, 2, 6, ('2020-07-06T11:20:21'));
