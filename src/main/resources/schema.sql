DROP BILLING;

CREATE TABLE BILLING {
    min_hours Number(2,0),
    max_hours Number(2,0),
    vehicle_type varchar2(10),
    dollar_amount Number(2,1)
}

DROP PARKING_FLOOR;

CREATE TABLE PARKING_FLOOR {
    level Number(2,0),
    available_four_wheeler_parking_spots Number(3, 0),
    available_four_wheeler_parking_spots Number(3, 0),
    PRIMARY KEY level
}

DROP TICKET;

CREATE TABLE TICKET {
    id Number(10,0),
    issue_time timestamp,
    status varchar2(10),
    vehicle_type varchar2(10),
    PRIMARY KEY id
}