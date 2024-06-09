CREATE DATABASE IF NOT EXISTS conference_manager;

USE conference_manager;

CREATE TABLE IF NOT EXISTS conferences (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS meetings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    conference_id INT NOT NULL,
    topic VARCHAR(255) NOT NULL,
    date VARCHAR(255) NOT NULL,
    participants INT NOT NULL,
    FOREIGN KEY (conference_id) REFERENCES conferences(id)
);
