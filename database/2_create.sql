CREATE TABLE address (
                         id serial  NOT NULL,
                         user_id int  NOT NULL,
                         country_id int  NOT NULL,
                         city_id int  NOT NULL,
                         county varchar(255)  NULL,
                         details varchar(255)  NOT NULL,
                         postal_code varchar(20)  NOT NULL,
                         phone_no varchar(10)  NOT NULL,
                         type varchar(1)  NOT NULL,
                         email varchar(255)  NOT NULL,
                         CONSTRAINT address_pk PRIMARY KEY (id)
);


CREATE TABLE city (
                      id serial  NOT NULL,
                      country_id int  NOT NULL,
                      name varchar(255)  NOT NULL,
                      CONSTRAINT city_pk PRIMARY KEY (id)
);


CREATE TABLE company (
                         id serial  NOT NULL,
                         name varchar(255)  NOT NULL,
                         number varchar(100)  NOT NULL,
                         CONSTRAINT company_pk PRIMARY KEY (id)
);


CREATE TABLE country (
                         id serial  NOT NULL,
                         name varchar(255)  NOT NULL,
                         CONSTRAINT country_pk PRIMARY KEY (id)
);


CREATE TABLE currency (
                          "is" int  NOT NULL,
                          short_code varchar(3)  NOT NULL,
                          description varchar(255)  NOT NULL,
                          CONSTRAINT currency_pk PRIMARY KEY ("is")
);


CREATE TABLE inbox (
                       id serial  NOT NULL,
                       receiver_user_id int  NOT NULL,
                       sender_user_id int  NOT NULL,
                       title varchar(100)  NOT NULL,
                       message varchar(1000)  NOT NULL,
                       status varchar(1)  NOT NULL,
                       created_at timestamp  NOT NULL,
                       service_id int  NULL,
                       order_id int  NULL,
                       CONSTRAINT inbox_pk PRIMARY KEY (id)
);


CREATE TABLE "order" (
                         id serial  NOT NULL,
                         user_id int  NOT NULL,
                         service_id int  NOT NULL,
                         date date  NOT NULL,
                         user_comment varchar(1000)  NOT NULL,
                         status varchar(1)  NOT NULL,
                         confirmation_comment varchar  NOT NULL,
                         CONSTRAINT order_pk PRIMARY KEY (id)
);


CREATE TABLE role (
                      id serial  NOT NULL,
                      name varchar(255)  NOT NULL,
                      CONSTRAINT role_pk PRIMARY KEY (id)
);


CREATE TABLE service (
                         id serial  NOT NULL,
                         user_id int  NOT NULL,
                         service_category_id int  NOT NULL,
                         name varchar(254)  NOT NULL,
                         description_short varchar(100)  NOT NULL,
                         description_long varchar(1000)  NOT NULL,
                         valid_from date  NOT NULL,
                         valid_to date  NOT NULL,
                         unit_cost decimal(7,2)  NOT NULL,
                         currency_is int  NOT NULL,
                         status varchar(1)  NOT NULL,
                         CONSTRAINT service_pk PRIMARY KEY (id)
);


CREATE TABLE service_category (
                                  id serial  NOT NULL,
                                  name varchar(255)  NOT NULL,
                                  description varchar(255)  NOT NULL,
                                  CONSTRAINT service_category_pk PRIMARY KEY (id)
);


CREATE TABLE "user" (
                        id serial  NOT NULL,
                        role_id int  NOT NULL,
                        username varchar(60)  NOT NULL,
                        password varchar(60)  NOT NULL,
                        status varchar(1)  NOT NULL,
                        CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE user_company (
                              id serial  NOT NULL,
                              company_id int  NOT NULL,
                              user_id int  NOT NULL,
                              CONSTRAINT user_company_pk PRIMARY KEY (id)
);

ALTER TABLE address ADD CONSTRAINT address_city
    FOREIGN KEY (city_id)
        REFERENCES city (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE address ADD CONSTRAINT address_country
    FOREIGN KEY (country_id)
        REFERENCES country (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE address ADD CONSTRAINT address_user
    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE city ADD CONSTRAINT city_country
    FOREIGN KEY (country_id)
        REFERENCES country (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE inbox ADD CONSTRAINT inbox_user_receiver
    FOREIGN KEY (receiver_user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE inbox ADD CONSTRAINT inbox_user_sender
    FOREIGN KEY (sender_user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE "order" ADD CONSTRAINT order_service
    FOREIGN KEY (service_id)
        REFERENCES service (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE "order" ADD CONSTRAINT order_user
    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE service ADD CONSTRAINT service_currency
    FOREIGN KEY (currency_is)
        REFERENCES currency ("is")
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE service ADD CONSTRAINT service_service_category
    FOREIGN KEY (service_category_id)
        REFERENCES service_category (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE service ADD CONSTRAINT service_user
    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE user_company ADD CONSTRAINT user_company_company
    FOREIGN KEY (company_id)
        REFERENCES company (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;


ALTER TABLE user_company ADD CONSTRAINT user_company_user
    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE "user" ADD CONSTRAINT user_role
    FOREIGN KEY (role_id)
        REFERENCES role (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;


