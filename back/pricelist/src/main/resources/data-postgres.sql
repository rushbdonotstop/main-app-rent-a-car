insert into vehicle_discount(num_days, discount) values (20, 10);
insert into vehicle_discount(num_days, discount) values (15, 8);
insert into vehicle_discount(num_days, discount) values (30, 20);
--vehicle 1
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-06-08'), DATE('2020-07-23'), 70, 2, 0, 1, 2);
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-07-24'), DATE('2020-08-23'), 80, 3, 0, 1, 2);
--vehicle 2
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-06-29'), DATE('2020-08-23'), 60, 3, 0, 2, 2);
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-08-24'), DATE('2020-10-23'), 55, 3, 0, 2, 2);
--vehicle 3
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-06-23'), DATE('2020-09-23'), 90, 2, 120, 3, 3);
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-09-24'), DATE('2020-12-23'), 110, 3, 120, 3, 3);
--vehicle 4
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-06-08'), DATE('2020-06-29'), 100, 3, 0, 4, 2);
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-06-30'), DATE('2020-08-23'), 130, 3, 0, 4, 1);
--vehicle 5
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-07-11'), DATE('2020-07-28'), 100, 3, 0, 5, 1);
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-07-29'), DATE('2020-09-23'), 130, 3, 0, 5, 1);
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-09-24'), DATE('2020-12-12'), 130, 3, 0, 5, 1);
--vehicle 6
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-06-08'), DATE('2020-08-08'), 140, 4, 150, 6, 1);
--vehicle 7
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-06-23'), DATE('2020-09-13'), 60, 3, 80, 7, 1);
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-09-14'), DATE('2020-12-23'), 65, 3, 80, 7, 2);
--vehicle 8
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
values (DATE('2020-06-08'), DATE('2020-08-26'), 80, 2, 0, 8, 3);
insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id)
values (DATE('2020-08-27'), DATE('2020-11-11'), 90, 2, 0, 8);







