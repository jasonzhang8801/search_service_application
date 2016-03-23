-- create business table
CREATE TABLE BUSINESS (
	business_id 	VARCHAR(100),
	name			VARCHAR(150) NOT NULL,
	city			VARCHAR(50),
	state			VARCHAR(30),
	stars 			NUMBER,
	PRIMARY KEY (business_id)
);
-- create business category table
CREATE TABLE CATEGORY (
	business_id		VARCHAR(100),
	name 			VARCHAR(150),
	PRIMARY KEY (business_id, name),
	FOREIGN KEY (business_id) REFERENCES BUSINESS 
);
-- create business subcategory table
CREATE TABLE SUBCATEGORY (
	business_id		VARCHAR(100),
	name 			VARCHAR(150),
	PRIMARY KEY (business_id, name),
	FOREIGN KEY (business_id) REFERENCES BUSINESS 
);
-- create yelp_user table 
CREATE TABLE YELP_USER (
	user_id			VARCHAR(50),
	yelping_since	VARCHAR(20),
	name 			VARCHAR(150) NOT NULL,
	review_count	INTEGER,
	friend_count	INTEGER,
	average_stars	NUMBER,
	PRIMARY KEY (user_id)
);
-- create review table
CREATE TABLE REVIEW(
	review_id		VARCHAR(100),
	business_id		VARCHAR(100) NOT NULL,
	review_date		VARCHAR(20),
	stars 			INTEGER,
	votes			INTEGER,
	PRIMARY KEY (review_id),
	FOREIGN KEY (business_id) REFERENCES BUSINESS 
);
-- create checkin table
CREATE TABLE CHECKIN (
	business_id 	VARCHAR(100),
	time_slot		VARCHAR(30),
	count 			INTEGER,
	PRIMARY KEY (business_id, time_slot),
	FOREIGN KEY (business_id) REFERENCES BUSINESS
);