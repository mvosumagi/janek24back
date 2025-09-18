-- SCHEMA
CREATE TABLE IF NOT EXISTS country (id serial PRIMARY KEY, name varchar(255) NOT NULL);
CREATE TABLE IF NOT EXISTS city (id serial PRIMARY KEY, country_id int NOT NULL REFERENCES country(id), name varchar(255) NOT NULL);
CREATE TABLE IF NOT EXISTS role (id serial PRIMARY KEY, name varchar(255) NOT NULL);
CREATE TABLE IF NOT EXISTS "user" (id serial PRIMARY KEY, role_id int NOT NULL REFERENCES role(id), username varchar(60) NOT NULL, password varchar(60) NOT NULL, status varchar(1) NOT NULL);
CREATE TABLE IF NOT EXISTS company (id serial PRIMARY KEY, name varchar(255) NOT NULL, number varchar(100) NOT NULL);
CREATE TABLE IF NOT EXISTS user_company (id serial PRIMARY KEY, company_id int NOT NULL REFERENCES company(id), user_id int NOT NULL REFERENCES "user"(id));
CREATE TABLE IF NOT EXISTS currency ("is" int PRIMARY KEY, short_code varchar(3) NOT NULL, description varchar(255) NOT NULL);
CREATE TABLE IF NOT EXISTS service_category (id serial PRIMARY KEY, name varchar(255) NOT NULL, description varchar(255) NOT NULL);
CREATE TABLE IF NOT EXISTS service (
                                       id serial PRIMARY KEY,
                                       user_id int NOT NULL REFERENCES "user"(id),
                                       service_category_id int NOT NULL REFERENCES service_category(id),
                                       name varchar(254) NOT NULL,
                                       description_short varchar(100) NOT NULL,
                                       description_long varchar(1000) NOT NULL,
                                       valid_from date NOT NULL,
                                       valid_to date NOT NULL,
                                       unit_cost decimal(7,2) NOT NULL,
                                       currency_is int NOT NULL REFERENCES currency("is"),
                                       status varchar(1) NOT NULL
);
CREATE TABLE IF NOT EXISTS "order" (
                                       id serial PRIMARY KEY,
                                       user_id int NOT NULL REFERENCES "user"(id),
                                       service_id int NOT NULL REFERENCES service(id),
                                       date date NOT NULL,
                                       user_comment varchar(1000) NOT NULL,
                                       status varchar(1) NOT NULL,
                                       confirmation_comment varchar NOT NULL
);
CREATE TABLE IF NOT EXISTS inbox (
                                     id serial PRIMARY KEY,
                                     receiver_user_id int NOT NULL REFERENCES "user"(id),
                                     sender_user_id int NOT NULL REFERENCES "user"(id),
                                     title varchar(100) NOT NULL,
                                     message varchar(1000) NOT NULL,
                                     status varchar(1) NOT NULL,
                                     created_at timestamp NOT NULL,
                                     service_id int NULL REFERENCES service(id),
                                     order_id int NULL REFERENCES "order"(id)
);
CREATE TABLE IF NOT EXISTS address (
                                       id serial PRIMARY KEY,
                                       user_id int NOT NULL REFERENCES "user"(id),
                                       country_id int NOT NULL REFERENCES country(id),
                                       city_id int NOT NULL REFERENCES city(id),
                                       county varchar(255) NULL,
                                       details varchar(255) NOT NULL,
                                       postal_code varchar(20) NOT NULL,
                                       phone_no varchar(10) NOT NULL,
                                       type varchar(1) NOT NULL,
                                       email varchar(255) NOT NULL
);

-- SEED
INSERT INTO country (name) SELECT 'Estonia' WHERE NOT EXISTS (SELECT 1 FROM country WHERE name='Estonia');

INSERT INTO city (country_id, name)
SELECT c.id, v.name
FROM (VALUES ('Tallinn'), ('Tartu')) AS v(name)
         JOIN country c ON c.name='Estonia'
WHERE NOT EXISTS (SELECT 1 FROM city ci WHERE ci.name=v.name);

INSERT INTO currency ("is", short_code, description) SELECT 1,'EUR','Euro' WHERE NOT EXISTS (SELECT 1 FROM currency WHERE "is"=1);

INSERT INTO role (name)
SELECT x.name FROM (VALUES ('ADMIN'),('USER')) AS x(name)
WHERE NOT EXISTS (SELECT 1 FROM role r WHERE r.name=x.name);

INSERT INTO company (name, number)
SELECT x.name, x.num FROM (VALUES
                               ('HomeCare OÜ','12345678'),
                               ('Lauri Auto OÜ','87654321'),
                               ('Mart Services OÜ','11223344')
                          ) AS x(name,num)
WHERE NOT EXISTS (SELECT 1 FROM company c WHERE c.name=x.name);

INSERT INTO "user" (role_id, username, password, status)
SELECT (SELECT id FROM role WHERE name='ADMIN'),'admin','123','A'
WHERE NOT EXISTS (SELECT 1 FROM "user" WHERE username='admin');

INSERT INTO "user" (role_id, username, password, status)
SELECT (SELECT id FROM role WHERE name='USER'),'rauno','123','A'
WHERE NOT EXISTS (SELECT 1 FROM "user" WHERE username='rauno');

INSERT INTO "user" (role_id, username, password, status)
SELECT (SELECT id FROM role WHERE name='USER'),'lauri','123','A'
WHERE NOT EXISTS (SELECT 1 FROM "user" WHERE username='lauri');

INSERT INTO "user" (role_id, username, password, status)
SELECT (SELECT id FROM role WHERE name='USER'),'mart','123','A'
WHERE NOT EXISTS (SELECT 1 FROM "user" WHERE username='mart');

INSERT INTO user_company (company_id, user_id)
SELECT (SELECT id FROM company WHERE name='HomeCare OÜ'),
       (SELECT id FROM "user" WHERE username='admin')
WHERE NOT EXISTS (
    SELECT 1 FROM user_company
    WHERE company_id=(SELECT id FROM company WHERE name='HomeCare OÜ')
      AND user_id=(SELECT id FROM "user" WHERE username='admin')
);

INSERT INTO user_company (company_id, user_id)
SELECT (SELECT id FROM company WHERE name='Lauri Auto OÜ'),
       (SELECT id FROM "user" WHERE username='lauri')
WHERE NOT EXISTS (
    SELECT 1 FROM user_company
    WHERE company_id=(SELECT id FROM company WHERE name='Lauri Auto OÜ')
      AND user_id=(SELECT id FROM "user" WHERE username='lauri')
);

INSERT INTO user_company (company_id, user_id)
SELECT (SELECT id FROM company WHERE name='Mart Services OÜ'),
       (SELECT id FROM "user" WHERE username='mart')
WHERE NOT EXISTS (
    SELECT 1 FROM user_company
    WHERE company_id=(SELECT id FROM company WHERE name='Mart Services OÜ')
      AND user_id=(SELECT id FROM "user" WHERE username='mart')
);

INSERT INTO address (user_id,country_id,city_id,county,details,postal_code,phone_no,type,email)
SELECT u.id,c.id,ci.id,'Harju County','Kalasadama 4-12','10145','51234567','H','admin@homecare.ee'
FROM "user" u, country c, city ci
WHERE u.username='admin' AND c.name='Estonia' AND ci.name='Tallinn'
  AND NOT EXISTS (SELECT 1 FROM address a WHERE a.user_id=u.id);

INSERT INTO address (user_id,country_id,city_id,county,details,postal_code,phone_no,type,email)
SELECT u.id,c.id,ci.id,'Tartu County','Rüütli 7-5','51007','52345678','H','rauno@example.ee'
FROM "user" u, country c, city ci
WHERE u.username='rauno' AND c.name='Estonia' AND ci.name='Tartu'
  AND NOT EXISTS (SELECT 1 FROM address a WHERE a.user_id=u.id);

INSERT INTO address (user_id,country_id,city_id,county,details,postal_code,phone_no,type,email)
SELECT u.id,c.id,ci.id,'Harju County','Telliskivi 60','10412','53456789','H','lauri@example.ee'
FROM "user" u, country c, city ci
WHERE u.username='lauri' AND c.name='Estonia' AND ci.name='Tallinn'
  AND NOT EXISTS (SELECT 1 FROM address a WHERE a.user_id=u.id);

INSERT INTO address (user_id,country_id,city_id,county,details,postal_code,phone_no,type,email)
SELECT u.id,c.id,ci.id,'Harju County','Pärnu mnt 67','10134','54555666','H','mart@martservices.ee'
FROM "user" u, country c, city ci
WHERE u.username='mart' AND c.name='Estonia' AND ci.name='Tallinn'
  AND NOT EXISTS (SELECT 1 FROM address a WHERE a.user_id=u.id);

INSERT INTO service_category (name, description)
SELECT x.name, x.descr
FROM (VALUES
          ('Home Cleaning','Professional house cleaning services to keep your home spotless and organized.'),
          ('Lawn Care','Landscaping, lawn maintenance, and garden services to beautify your outdoor space.'),
          ('Tech Support','Computer repair, smartphone help, and technology assistance from certified experts.'),
          ('Handyman','General repairs, installations, and home improvements by skilled professionals.'),
          ('Pet Care','Pet sitting, dog walking, grooming, and veterinary transport services.'),
          ('Tutoring','Academic support and educational tutoring for students of all ages and subjects.'),
          ('Car Repair','Vehicle maintenance and repair services.'),
          ('Sales','Sales assistance, lead generation, and customer outreach.')
     ) AS x(name, descr)
WHERE NOT EXISTS (SELECT 1 FROM service_category sc WHERE sc.name=x.name);

INSERT INTO service (user_id,service_category_id,name,description_short,description_long,valid_from,valid_to,unit_cost,currency_is,status)
SELECT (SELECT id FROM "user" WHERE username='admin'),
       (SELECT id FROM service_category WHERE name='Home Cleaning'),
       'Apartment Deep Clean','Deep cleaning for apartments up to 60 m²',
       'Thorough cleaning including kitchen, bathroom, floors, and windows (inside). Supplies included.',
       CURRENT_DATE, DATE '2099-12-31', 65.00, 1, 'A'
WHERE NOT EXISTS (SELECT 1 FROM service s WHERE s.name='Apartment Deep Clean' AND s.user_id=(SELECT id FROM "user" WHERE username='admin'));

INSERT INTO service (user_id,service_category_id,name,description_short,description_long,valid_from,valid_to,unit_cost,currency_is,status)
SELECT (SELECT id FROM "user" WHERE username='admin'),
       (SELECT id FROM service_category WHERE name='Tutoring'),
       'Math Tutoring (Grades 5–9)','Focused lessons and homework help',
       'One-on-one tutoring in English. Sessions available online or in Tallinn.',
       CURRENT_DATE, DATE '2099-12-31', 22.00, 1, 'A'
WHERE NOT EXISTS (SELECT 1 FROM service s WHERE s.name='Math Tutoring (Grades 5–9)' AND s.user_id=(SELECT id FROM "user" WHERE username='admin'));

INSERT INTO service (user_id,service_category_id,name,description_short,description_long,valid_from,valid_to,unit_cost,currency_is,status)
SELECT (SELECT id FROM "user" WHERE username='rauno'),
       (SELECT id FROM service_category WHERE name='Lawn Care'),
       'Lawn Mowing','Quick lawn mowing for private yards',
       'Professional lawn mowing in Tallinn and Tartu. Includes trimming and edge finishing.',
       CURRENT_DATE, DATE '2099-12-31', 18.00, 1, 'A'
WHERE NOT EXISTS (SELECT 1 FROM service s WHERE s.name='Lawn Mowing' AND s.user_id=(SELECT id FROM "user" WHERE username='rauno'));

INSERT INTO service (user_id,service_category_id,name,description_short,description_long,valid_from,valid_to,unit_cost,currency_is,status)
SELECT (SELECT id FROM "user" WHERE username='rauno'),
       (SELECT id FROM service_category WHERE name='Car Repair'),
       'Car Repair - Basic Service','Oil change and safety check',
       'Oil & filter change, fluids top-up, brake check, and basic diagnostics. Appointment required.',
       CURRENT_DATE, DATE '2099-12-31', 59.00, 1, 'A'
WHERE NOT EXISTS (SELECT 1 FROM service s WHERE s.name='Car Repair - Basic Service' AND s.user_id=(SELECT id FROM "user" WHERE username='rauno'));

DELETE FROM service
WHERE user_id=(SELECT id FROM "user" WHERE username='lauri')
  AND service_category_id=(SELECT id FROM service_category WHERE name='Tech Support');

INSERT INTO service (user_id,service_category_id,name,description_short,description_long,valid_from,valid_to,unit_cost,currency_is,status)
SELECT (SELECT id FROM "user" WHERE username='lauri'),
       (SELECT id FROM service_category WHERE name='Sales'),
       'B2B Sales Outreach','Prospecting and email/phone outreach',
       'Lead list building, cold outreach, CRM updates, and appointment setting for Estonian/English markets.',
       CURRENT_DATE, DATE '2099-12-31', 25.00, 1, 'A'
WHERE NOT EXISTS (SELECT 1 FROM service s WHERE s.name='B2B Sales Outreach' AND s.user_id=(SELECT id FROM "user" WHERE username='lauri'));

INSERT INTO service (user_id,service_category_id,name,description_short,description_long,valid_from,valid_to,unit_cost,currency_is,status)
SELECT (SELECT id FROM "user" WHERE username='lauri'),
       (SELECT id FROM service_category WHERE name='Sales'),
       'Sales Pipeline Setup','CRM and process setup',
       'Set up CRM stages, templates, and reporting; train team on day-to-day usage.',
       CURRENT_DATE, DATE '2099-12-31', 40.00, 1, 'A'
WHERE NOT EXISTS (SELECT 1 FROM service s WHERE s.name='Sales Pipeline Setup' AND s.user_id=(SELECT id FROM "user" WHERE username='lauri'));

INSERT INTO service (user_id,service_category_id,name,description_short,description_long,valid_from,valid_to,unit_cost,currency_is,status)
SELECT (SELECT id FROM "user" WHERE username='mart'),
       (SELECT id FROM service_category WHERE name='Tech Support'),
       'PC & Phone Support','Diagnostics, cleanup, and setup',
       'Help with Windows/Mac, smartphones, backups, malware removal, and new device setup in Tallinn/Tartu.',
       CURRENT_DATE, DATE '2099-12-31', 39.00, 1, 'A'
WHERE NOT EXISTS (SELECT 1 FROM service s WHERE s.name='PC & Phone Support' AND s.user_id=(SELECT id FROM "user" WHERE username='mart'));

INSERT INTO service (user_id,service_category_id,name,description_short,description_long,valid_from,valid_to,unit_cost,currency_is,status)
SELECT (SELECT id FROM "user" WHERE username='mart'),
       (SELECT id FROM service_category WHERE name='Pet Care'),
       'Dog Walking (30 min)','Friendly dog walking in city center',
       'Reliable dog walking in Tallinn city center. Pickup and drop-off included within 1 km.',
       CURRENT_DATE, DATE '2099-12-31', 12.00, 1, 'A'
WHERE NOT EXISTS (SELECT 1 FROM service s WHERE s.name='Dog Walking (30 min)' AND s.user_id=(SELECT id FROM "user" WHERE username='mart'));

INSERT INTO service (user_id,service_category_id,name,description_short,description_long,valid_from,valid_to,unit_cost,currency_is,status)
SELECT (SELECT id FROM "user" WHERE username='mart'),
       (SELECT id FROM service_category WHERE name='Handyman'),
       'Small Repairs & Installations','Shelves, curtain rods, minor fixes',
       'Skilled handyman for quick home jobs: mounting, sealing, small carpentry. Tools included.',
       CURRENT_DATE, DATE '2099-12-31', 35.00, 1, 'A'
WHERE NOT EXISTS (SELECT 1 FROM service s WHERE s.name='Small Repairs & Installations' AND s.user_id=(SELECT id FROM "user" WHERE username='mart'));

WITH ids AS (
    SELECT
        (SELECT id FROM "user" WHERE username='admin') AS admin_id,
        (SELECT id FROM "user" WHERE username='rauno') AS rauno_id,
        (SELECT id FROM service WHERE name='Car Repair - Basic Service' AND user_id=(SELECT id FROM "user" WHERE username='rauno')) AS car_service_id
)
INSERT INTO "order" (user_id, service_id, date, user_comment, status, confirmation_comment)
SELECT admin_id, car_service_id, CURRENT_DATE + INTERVAL '2 days',
       'Please change oil and check brakes.', 'A', 'Confirmed – see you Thursday.'
FROM ids
WHERE NOT EXISTS (SELECT 1 FROM "order" o WHERE o.user_id=ids.admin_id AND o.service_id=ids.car_service_id);

INSERT INTO inbox (receiver_user_id, sender_user_id, title, message, status, created_at, service_id, order_id)
SELECT rauno_id, admin_id, 'Car Repair Booking',
       'Hi! I booked a basic service for Thursday. Is 10:00 okay?', 'U', NOW(), car_service_id,
       (SELECT id FROM "order" WHERE user_id=admin_id AND service_id=car_service_id ORDER BY id DESC LIMIT 1)
FROM (
         SELECT
             (SELECT id FROM "user" WHERE username='admin') AS admin_id,
             (SELECT id FROM "user" WHERE username='rauno') AS rauno_id,
             (SELECT id FROM service WHERE name='Car Repair - Basic Service' AND user_id=(SELECT id FROM "user" WHERE username='rauno')) AS car_service_id
     ) x
WHERE NOT EXISTS (
    SELECT 1 FROM inbox i
    WHERE i.receiver_user_id=x.rauno_id AND i.sender_user_id=x.admin_id
      AND i.service_id=x.car_service_id AND i.title='Car Repair Booking'
);

WITH ids AS (
    SELECT
        (SELECT id FROM "user" WHERE username='admin') AS admin_id,
        (SELECT id FROM "user" WHERE username='mart') AS mart_id,
        (SELECT id FROM service WHERE name='PC & Phone Support' AND user_id=(SELECT id FROM "user" WHERE username='mart')) AS ts_service_id
)
INSERT INTO "order" (user_id, service_id, date, user_comment, status, confirmation_comment)
SELECT admin_id, ts_service_id, CURRENT_DATE + INTERVAL '1 day',
       'Laptop is slow and full of popups.', 'A', 'Confirmed – remote session tomorrow.'
FROM ids
WHERE NOT EXISTS (SELECT 1 FROM "order" o WHERE o.user_id=ids.admin_id AND o.service_id=ids.ts_service_id);

INSERT INTO inbox (receiver_user_id, sender_user_id, title, message, status, created_at, service_id, order_id)
SELECT mart_id, admin_id, 'Tech Support Request',
       'Could you also set up automatic backups?', 'U', NOW(), ts_service_id,
       (SELECT id FROM "order" WHERE user_id=admin_id AND service_id=ts_service_id ORDER BY id DESC LIMIT 1)
FROM (
         SELECT
             (SELECT id FROM "user" WHERE username='admin') AS admin_id,
             (SELECT id FROM "user" WHERE username='mart') AS mart_id,
             (SELECT id FROM service WHERE name='PC & Phone Support' AND user_id=(SELECT id FROM "user" WHERE username='mart')) AS ts_service_id
     ) x
WHERE NOT EXISTS (
    SELECT 1 FROM inbox i
    WHERE i.receiver_user_id=x.mart_id AND i.sender_user_id=x.admin_id
      AND i.service_id=x.ts_service_id AND i.title='Tech Support Request'
);

INSERT INTO inbox (receiver_user_id, sender_user_id, title, message, status, created_at, service_id, order_id)
SELECT lauri_id, admin_id, 'Sales Outreach Inquiry',
       'What volume can you handle weekly for English market leads?', 'U', NOW(), sales_service_id, NULL
FROM (
         SELECT
             (SELECT id FROM "user" WHERE username='admin') AS admin_id,
             (SELECT id FROM "user" WHERE username='lauri') AS lauri_id,
             (SELECT id FROM service WHERE name='B2B Sales Outreach' AND user_id=(SELECT id FROM "user" WHERE username='lauri')) AS sales_service_id
     ) x
WHERE NOT EXISTS (
    SELECT 1 FROM inbox i
    WHERE i.receiver_user_id=x.lauri_id AND i.sender_user_id=x.admin_id
      AND i.service_id=x.sales_service_id AND i.title='Sales Outreach Inquiry'
);
