INSERT INTO janek24.country (id, name) VALUES (default, 'Estonia');
INSERT INTO janek24.country (id, name) VALUES (default, 'Latvia');
INSERT INTO janek24.country (id, name) VALUES (default, 'Sweden');


INSERT INTO janek24.city (id, country_id, name) VALUES (default, 1, 'Tallinn');
INSERT INTO janek24.city (id, country_id, name) VALUES (default, 1, 'Tartu');
INSERT INTO janek24.city (id, country_id, name) VALUES (default, 2, 'Riga');
INSERT INTO janek24.city (id, country_id, name) VALUES (default, 3, 'Stockholm');

INSERT INTO currency (id, short_code, description) VALUES (default, 'EUR', 'Euro');

INSERT INTO role (id, name) VALUES (default, 'ADMIN');
INSERT INTO role (id, name) VALUES (default, 'USER');

INSERT INTO company (id, name, number) VALUES (default, 'HomeCare OÜ', '12345678');
INSERT INTO company (id, name, number) VALUES (default, 'Lauri Auto OÜ', '87654321');
INSERT INTO company (id, name, number) VALUES (default, 'Mart Services OÜ', '11223344');

INSERT INTO "user" (id, role_id, username, password, status, first_name, last_name, phone_no, email) VALUES (default, (SELECT id FROM role WHERE name='ADMIN'), 'admin', '123', 'A', '', '', '','');
INSERT INTO "user" (id, role_id, username, password, status, first_name, last_name, phone_no, email) VALUES (default, (SELECT id FROM role WHERE name='USER'), 'rauno', '123', 'A', '', '', '','');
INSERT INTO "user" (id, role_id, username, password, status, first_name, last_name, phone_no, email) VALUES (default, (SELECT id FROM role WHERE name='USER'), 'lauri', '123', 'A', '', '', '','');
INSERT INTO "user" (id, role_id, username, password, status, first_name, last_name, phone_no, email) VALUES (default, (SELECT id FROM role WHERE name='USER'), 'mart', '123', 'A', '', '', '','');

INSERT INTO user_company (id, company_id, user_id) VALUES (default, (SELECT id FROM company WHERE name='HomeCare OÜ'), (SELECT id FROM "user" WHERE username='admin'));
INSERT INTO user_company (id, company_id, user_id) VALUES (default, (SELECT id FROM company WHERE name='Lauri Auto OÜ'), (SELECT id FROM "user" WHERE username='lauri'));
INSERT INTO user_company (id, company_id, user_id) VALUES (default, (SELECT id FROM company WHERE name='Mart Services OÜ'), (SELECT id FROM "user" WHERE username='mart'));

INSERT INTO address (id, user_id, country_id, city_id, county, details, postal_code, type)
VALUES (default, (SELECT id FROM "user" WHERE username='admin'), (SELECT id FROM country WHERE name='Estonia'), (SELECT id FROM city WHERE name='Tallinn'), 'Harju County', 'Kalasadama 4-12', '10145', 'H');

INSERT INTO address (id, user_id, country_id, city_id, county, details, postal_code, type)
VALUES (default, (SELECT id FROM "user" WHERE username='rauno'), (SELECT id FROM country WHERE name='Estonia'), (SELECT id FROM city WHERE name='Tartu'), 'Tartu County', 'Rüütli 7-5', '51007', 'H');

INSERT INTO address (id, user_id, country_id, city_id, county, details, postal_code, type)
VALUES (default, (SELECT id FROM "user" WHERE username='lauri'), (SELECT id FROM country WHERE name='Estonia'), (SELECT id FROM city WHERE name='Tallinn'), 'Harju County', 'Telliskivi 60', '10412', 'H');

INSERT INTO address (id, user_id, country_id, city_id, county, details, postal_code, type)
VALUES (default, (SELECT id FROM "user" WHERE username='mart'), (SELECT id FROM country WHERE name='Estonia'), (SELECT id FROM city WHERE name='Tallinn'), 'Harju County', 'Pärnu mnt 67', '10134', 'H');

INSERT INTO service_category (id, name, description)
VALUES (default, 'Unlisted', 'User-requested service not yet in catalog.');

INSERT INTO service_category (id, name, description)
VALUES (default, 'Home Cleaning', 'Professional house cleaning providerServices to keep your home spotless and organized.');

INSERT INTO service_category (id, name, description)
VALUES (default, 'Lawn Care', 'Landscaping, lawn maintenance, and garden providerServices to beautify your outdoor space.');

INSERT INTO service_category (id, name, description)
VALUES (default, 'Tech Support', 'Computer repair, smartphone help, and technology assistance from certified experts.');

INSERT INTO service_category (id, name, description)
VALUES (default, 'Handyman', 'General repairs, installations, and home improvements by skilled professionals.');

INSERT INTO service_category (id, name, description)
VALUES (default, 'Pet Care', 'Pet sitting, dog walking, grooming, and veterinary transport providerServices.');

INSERT INTO service_category (id, name, description)
VALUES (default, 'Tutoring', 'Academic support and educational tutoring for students of all ages and subjects.');

INSERT INTO service_category (id, name, description)
VALUES (default, 'Car Repair', 'Vehicle maintenance and repair providerServices.');

INSERT INTO service_category (id, name, description)
VALUES (default, 'Sales', 'Sales assistance, lead generation, and customer outreach.');


INSERT INTO provider_service (id, user_id, service_category_id, name, description_short, description_long, valid_from, valid_to, unit_cost, currency_is, status)
VALUES (default, (SELECT id FROM "user" WHERE username='admin'), (SELECT id FROM service_category WHERE name='Home Cleaning'),
        'Apartment Deep Clean','Deep cleaning for apartments up to 60 m²',
        'Thorough cleaning including kitchen, bathroom, floors, and windows (inside). Supplies included.',
        CURRENT_DATE, DATE '2099-12-31', 65.00, 1, 'A');

INSERT INTO provider_service (id, user_id, service_category_id, name, description_short, description_long, valid_from, valid_to, unit_cost, currency_is, status)
VALUES (default, (SELECT id FROM "user" WHERE username='admin'), (SELECT id FROM service_category WHERE name='Tutoring'),
        'Math Tutoring (Grades 5–9)','Focused lessons and homework help',
        'One-on-one tutoring in English. Sessions available online or in Tallinn.',
        CURRENT_DATE, DATE '2099-12-31', 22.00, 1, 'A');

INSERT INTO provider_service (id, user_id, service_category_id, name, description_short, description_long, valid_from, valid_to, unit_cost, currency_is, status)
VALUES (default, (SELECT id FROM "user" WHERE username='rauno'), (SELECT id FROM service_category WHERE name='Lawn Care'),
        'Lawn Mowing','Quick lawn mowing for private yards',
        'Professional lawn mowing in Tallinn and Tartu. Includes trimming and edge finishing.',
        CURRENT_DATE, DATE '2099-12-31', 18.00, 1, 'A');

INSERT INTO provider_service (id, user_id, service_category_id, name, description_short, description_long, valid_from, valid_to, unit_cost, currency_is, status)
VALUES (default, (SELECT id FROM "user" WHERE username='rauno'), (SELECT id FROM service_category WHERE name='Car Repair'),
        'Car Repair - Basic Service','Oil change and safety check',
        'Oil & filter change, fluids top-up, brake check, and basic diagnostics. Appointment required.',
        CURRENT_DATE, DATE '2099-12-31', 59.00, 1, 'A');

INSERT INTO provider_service (id, user_id, service_category_id, name, description_short, description_long, valid_from, valid_to, unit_cost, currency_is, status)
VALUES (default, (SELECT id FROM "user" WHERE username='lauri'), (SELECT id FROM service_category WHERE name='Sales'),
        'B2B Sales Outreach','Prospecting and email/phone outreach',
        'Lead list building, cold outreach, CRM updates, and appointment setting for Estonian/English markets.',
        CURRENT_DATE, DATE '2099-12-31', 25.00, 1, 'A');

INSERT INTO provider_service (id, user_id, service_category_id, name, description_short, description_long, valid_from, valid_to, unit_cost, currency_is, status)
VALUES (default, (SELECT id FROM "user" WHERE username='lauri'), (SELECT id FROM service_category WHERE name='Sales'),
        'Sales Pipeline Setup','CRM and process setup',
        'Set up CRM stages, templates, and reporting; train team on day-to-day usage.',
        CURRENT_DATE, DATE '2099-12-31', 40.00, 1, 'A');

INSERT INTO provider_service (id, user_id, service_category_id, name, description_short, description_long, valid_from, valid_to, unit_cost, currency_is, status)
VALUES (default, (SELECT id FROM "user" WHERE username='mart'), (SELECT id FROM service_category WHERE name='Tech Support'),
        'PC & Phone Support','Diagnostics, cleanup, and setup',
        'Help with Windows/Mac, smartphones, backups, malware removal, and new device setup in Tallinn/Tartu.',
        CURRENT_DATE, DATE '2099-12-31', 39.00, 1, 'A');

INSERT INTO provider_service (id, user_id, service_category_id, name, description_short, description_long, valid_from, valid_to, unit_cost, currency_is, status)
VALUES (default, (SELECT id FROM "user" WHERE username='mart'), (SELECT id FROM service_category WHERE name='Pet Care'),
        'Dog Walking (30 min)','Friendly dog walking in city center',
        'Reliable dog walking in Tallinn city center. Pickup and drop-off included within 1 km.',
        CURRENT_DATE, DATE '2099-12-31', 12.00, 1, 'A');

INSERT INTO provider_service (id, user_id, service_category_id, name, description_short, description_long, valid_from, valid_to, unit_cost, currency_is, status)
VALUES (default, (SELECT id FROM "user" WHERE username='mart'), (SELECT id FROM service_category WHERE name='Handyman'),
        'Small Repairs & Installations','Shelves, curtain rods, minor fixes',
        'Skilled handyman for quick home jobs: mounting, sealing, small carpentry. Tools included.',
        CURRENT_DATE, DATE '2099-12-31', 35.00, 1, 'A');