CREATE TABLE EVENT(
    id INT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    create_date DATE NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO event (name, create_date, description) VALUES ('name1', '2018-02-09', 'description description description ');
INSERT INTO event (name, create_date, description) VALUES ('name2', '2015-12-12', 'test test test');
INSERT INTO event (name, create_date, description) VALUES ('name3', '2011-10-10', 'query query query');
INSERT INTO event (name, create_date, description) VALUES ('name56', '2011-10-10', 'readme readme');
