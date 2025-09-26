CREATE TABLE country (
                         id serial PRIMARY KEY,
                         name varchar(255) NOT NULL
);

CREATE TABLE city (
                      id serial PRIMARY KEY,
                      country_id int NOT NULL REFERENCES country(id),
                      name varchar(255) NOT NULL
);

CREATE TABLE role (
                      id serial PRIMARY KEY,
                      name varchar(255) NOT NULL
);

CREATE TABLE "user" (
                        id serial PRIMARY KEY,
                        role_id int NOT NULL REFERENCES role(id),
                        username varchar(60) NOT NULL,
                        password varchar(60) NOT NULL,
                        status varchar(1) NOT NULL,
                        first_name varchar(255) NOT NULL,
                        last_name varchar(255) NOT NULL,
                        phone_no varchar(10) NOT NULL,
                        email varchar(255) NOT NULL
);

CREATE TABLE company (
                         id serial PRIMARY KEY,
                         name varchar(255) NOT NULL,
                         number varchar(100) NOT NULL
);

CREATE TABLE user_company (
                              id serial PRIMARY KEY,
                              company_id int NOT NULL REFERENCES company(id),
                              user_id int NOT NULL REFERENCES "user"(id)
);

CREATE TABLE currency (
                          id serial PRIMARY KEY,
                          short_code varchar(3) NOT NULL,
                          description varchar(255) NOT NULL
);

CREATE TABLE service_category (
                                  id serial PRIMARY KEY,
                                  name varchar(255) NOT NULL,
                                  description varchar(255) NOT NULL
);

CREATE TABLE provider_service (
                         id serial PRIMARY KEY,
                         user_id int NOT NULL REFERENCES "user"(id),
                         service_category_id int NOT NULL REFERENCES service_category(id),
                         name varchar(254) NOT NULL,
                         description_short varchar(100) NOT NULL,
                         description_long varchar(1000) NOT NULL,
                         valid_from date NOT NULL,
                         valid_to date NOT NULL,
                         unit_cost decimal(7,2) NOT NULL,
                         currency_is int NOT NULL REFERENCES currency(id),
                         status varchar(1) NOT NULL
);

CREATE TABLE "order" (
                         id serial PRIMARY KEY,
                         user_id int NOT NULL REFERENCES "user"(id),
                         provider_service_id int NOT NULL REFERENCES provider_service(id),
                         date date NOT NULL,
                         user_comment varchar(1000) NOT NULL,
                         status varchar(1) NOT NULL,
                         confirmation_comment varchar NOT NULL
);

CREATE TABLE inbox (
                       id serial PRIMARY KEY,
                       receiver_user_id int NOT NULL REFERENCES "user"(id),
                       sender_user_id int NOT NULL REFERENCES "user"(id),
                       title varchar(100) NOT NULL,
                       message varchar(1000) NOT NULL,
                       status varchar(1) NOT NULL,
                       created_at timestamp NOT NULL,
                       provider_service_id int NULL REFERENCES provider_service(id),
                       order_id int NULL REFERENCES "order"(id)
);

CREATE TABLE address (
                         id serial PRIMARY KEY,
                         user_id int NOT NULL REFERENCES "user"(id),
                         country_id int NOT NULL REFERENCES country(id),
                         city_id int NOT NULL REFERENCES city(id),
                         county varchar(255) NULL,
                         details varchar(255) NOT NULL,
                         postal_code varchar(20) NOT NULL,
                         type varchar(1) NOT NULL
);
