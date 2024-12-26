DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS project;

CREATE TABLE project (
	project_id INT AUTO_INCREMENT NOT NULL,
	project_name VARCHAR(128) NOT NULL,
	estimated_hours DECIMAL(7,2),
	actual_hours DECIMAL(7,2),
	difficulty INT,
	notes TEXT,
	PRIMARY KEY (project_id)
);

CREATE TABLE material (
	material_id INT AUTO_INCREMENT NOT NULL,
	project_id INT NOT NULL,
	material_name VARCHAR(128) NOT NULL,
	num_required INT,
	cost DECIMAL(7,2),
	PRIMARY KEY (material_id),
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE TABLE step (
	step_id INT AUTO_INCREMENT NOT NULL,
	project_id INT NOT NULL,
	step_text TEXT NOT NULL,
	step_order INT NOT NULL,
	PRIMARY KEY (step_id),
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE TABLE category (
	category_id INT AUTO_INCREMENT NOT NULL,
	category_name VARCHAR(128) NOT NULL,
	PRIMARY KEY (category_id)
);

CREATE TABLE project_category (
	project_id INT NOT NULL,
	category_id INT NOT NULL,
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE,
	FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
	UNIQUE KEY (project_id, category_id)
);

INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) 
			VALUES 
			('Put up guest room window blinds', 3.25, 2.15, 3, 'Got the blinds from Home depot'),
			('Install garbage disposal', 3.00, 2.00, 3, 'Buy the disposal from Lowes'),
			('Test Project', 9.99, 8.99, 5, 'Test data');
INSERT INTO material (project_id, material_name, num_required, cost) 
			VALUES 
			(1, '1-inch screws', 8, 1.23),
			(1, 'Hunter Douglas shades', 1, 78.55),
			(2, 'InSinkerator', 1, 89.99),
			(2, '12-2 Romex wire w ground', 8, 21.50),
			(3, 'Testing material 1', 10, 99.99);
INSERT INTO step (project_id, step_text, step_order) 
			VALUES 
			(1, 'Measure for brackets', 1),
			(1, 'Drill hole for brackets', 2),
			(1, 'Install brackets with screws', 3),
			(2, 'Remove existing drain plumbing', 1),
			(2, 'Install new disposal under sink', 2),
			(3, 'Test step 1', 1);
INSERT INTO category (category_name)
			VALUES 
			('Doors and Windows'),
			('Kitchen'),
			('Electrical'),
			('Test category');
INSERT INTO project_category (project_id, category_id) 
			VALUES 
			(1, 1),
			(2, 2),
			(2, 3),
			(3, 4);
			
				