DROP TABLE IF EXISTS movie_actor;
DROP TABLE IF EXISTS actor;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS movie;

CREATE TABLE movie (
	movie_id int NOT NULL AUTO_INCREMENT,
	title varchar(128) NOT NULL,
	description varchar(256) NOT NULL,
	release_year int,
	PRIMARY KEY (movie_id)
);

CREATE TABLE review (
	review_id int NOT NULL AUTO_INCREMENT,
	movie_id int NULL,
	name varchar(60) NOT NULL,
	review_text varchar(256),
	stars varchar(5),
	created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (review_id),
	FOREIGN KEY (movie_id) REFERENCES movie (movie_id) ON DELETE CASCADE
);

CREATE TABLE actor (
	actor_id int NOT NULL AUTO_INCREMENT,
	first_name varchar(60),
	last_name varchar(60),
	list_ranking varchar(1),
	PRIMARY KEY (actor_id)
);

CREATE TABLE movie_actor (
	movie_id int NOT NULL,
	actor_id int NOT NULL,
	FOREIGN KEY (movie_id) REFERENCES movie (movie_id) ON DELETE CASCADE,
	FOREIGN KEY (actor_id) REFERENCES actor (actor_id) ON DELETE CASCADE
);