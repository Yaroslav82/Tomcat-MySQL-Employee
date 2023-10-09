-- Створення бд
CREATE DATABASE demo_db;


-- Створення таблиці
CREATE TABLE IF NOT EXISTS employees
( id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  position VARCHAR(128) NOT NULL,
  phone VARCHAR(128) NOT NULL,
  PRIMARY KEY (id)
);


-- Створення нового employee
INSERT INTO employees (name, position, phone) VALUES (?, ?, ?)

-- Читання всіх employees
SELECT * FROM employees

-- Читання employee по id
SELECT * FROM employees WHERE id == ?

-- Оновлення employee
UPDATE employees SET name = ?, position = ?, phone = ? WHERE id = ?

-- Видалення employee
DELETE FROM employees WHERE id = ?
