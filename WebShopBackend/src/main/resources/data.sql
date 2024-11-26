INSERT INTO public.users (role,id,name,password,username) VALUES
    (0,'a1b13cd7-85cf-44c2-b5e1-98420d7e543a'::uuid,'miki','$2a$10$1IYhlxIbX61pZk9gncOp2.E3v2HYizSolFk.idano69pLGpPGNcrm','miki@gmail.com'),
    (1,'2b3861fb-bd75-4ae5-a395-b05eeb028f5a'::uuid,'jecka','$2a$10$FjI/MQO0/4znyONygj3US.j/yyGwLSaR1ZppYMYP/Mh.WCfDQ7vFC','jecka@gmail.com');
--password is encoded 'test' for both users
INSERT INTO package (name,price) VALUES
    ('Basic Bundle',29.99),
    ('Standard Bundle',49.99),
    ('Premium Bundle',79.99);
INSERT INTO section (name,package_id) VALUES
    ('TV',1),
    ('Net',1),
    ('Mobile',1),
    ('TV',2),
    ('Net',2),
    ('Mobile',2),
    ('TV',3),
    ('Net',3),
    ('Mobile',3);
INSERT INTO section_options (section_id,options) VALUES
    (1,'Basic Channels'),
    (1,'HD Channels'),
    (1,'Sports Package'),
    (2,'50 Mbps'),
    (2,'100 Mbps'),
    (2,'Unlimited Data'),
    (3,'5GB Data'),
    (3,'10GB Data'),
    (3,'Unlimited Calls'),
    (4,'Basic Channels');
INSERT INTO section_options (section_id,options) VALUES
    (4,'HD Channels'),
    (4,'Premium Channels'),
    (4,'Sports Package'),
    (5,'100 Mbps'),
    (5,'200 Mbps'),
    (5,'Unlimited Data'),
    (5,'Free Router'),
    (6,'10GB Data'),
    (6,'20GB Data'),
    (6,'Unlimited Calls & Texts');
INSERT INTO section_options (section_id,options) VALUES
    (7,'Basic Channels'),
    (7,'HD Channels'),
    (7,'Premium Channels'),
    (7,'Sports Package'),
    (7,'On-demand Movies'),
    (8,'200 Mbps'),
    (8,'500 Mbps'),
    (8,'Unlimited Data'),
    (8,'Free Router'),
    (8,'5G Connectivity');
INSERT INTO section_options (section_id,options) VALUES
    (9,'20GB Data'),
    (9,'50GB Data'),
    (9,'Unlimited Calls & Texts'),
    (9,'International Roaming');
