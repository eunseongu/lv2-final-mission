INSERT INTO member (name, email, password)
VALUES ('하루', 'haru@woowa.com', 'aaa');
INSERT INTO member (name, email, password)
VALUES ('두루', 'duru@woowa.com', 'aaa');

INSERT INTO site (name)
VALUES ('하늘');

INSERT INTO reservation (date, member_id, site_id)
VALUES ('2025-08-15', 1, 1);
INSERT INTO reservation (date, member_id, site_id)
VALUES ('2025-08-16', 1, 1);

INSERT INTO reservation (date, member_id, site_id)
VALUES ('2025-08-17', 2, 1);