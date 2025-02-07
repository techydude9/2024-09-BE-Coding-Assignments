DROP TABLE IF EXISTS dog_breed;
DROP TABLE IF EXISTS breed;
DROP TABLE IF EXISTS dog;
DROP TABLE IF EXISTS location;

CREATE TABLE location (
	location_id int NOT NULL AUTO_INCREMENT,
	business_name varchar(256) NOT NULL,
	street_address varchar(128) NOT NULL,
	city varchar(60),
	state varchar(40),
	zip varchar(20),
	phone varchar(30),
	PRIMARY KEY (location_id)
);

CREATE TABLE dog (
	dog_id int NOT NULL AUTO_INCREMENT,
	location_id int NULL,
	name varchar(60) NOT NULL,
	age int,
	color varchar(128),
	PRIMARY KEY (dog_id),
	FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE CASCADE
);

CREATE TABLE breed (
	breed_id int NOT NULL AUTO_INCREMENT,
	name varchar(128),
	PRIMARY KEY (breed_id)
);

CREATE TABLE dog_breed (
	dog_id int NOT NULL,
	breed_id int NOT NULL,
	FOREIGN KEY (dog_id) REFERENCES dog (dog_id) ON DELETE CASCADE,
	FOREIGN KEY (breed_id) REFERENCES breed (breed_id) ON DELETE CASCADE
);