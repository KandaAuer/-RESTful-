-- Запрос 1: Получить всех студентов, возраст которых между 17 и 25
SELECT * FROM student WHERE age BETWEEN 17 AND 25;

-- Запрос 2: Получить всех студентов, но только имена
SELECT name FROM student;

-- Запрос 3: Получить всех студентов, у которых в имени есть буква "О"
SELECT * FROM student WHERE name ILIKE '%о%';

-- Запрос 4: Получить всех студентов, у которых возраст меньше идентификатора
SELECT * FROM student WHERE age < id;

-- Запрос 5: Получить всех студентов, упорядоченных по возрасту
SELECT * FROM student ORDER BY age;

-- Запрос 6: Получить студентов, у которых имя начинается с буквы "А"
SELECT * FROM student WHERE name ILIKE 'А%';

-- Запрос 7: Получить студентов, у которых возраст больше 19 лет:
SELECT * FROM student WHERE age > 19;

-- Запрос 8: Получить студентов, у которых возраст меньше 23 лет, но больше 20 лет:
SELECT * FROM student WHERE age > 20 AND age < 23;

-- Запрос 9: Получить всех студентов и их факультеты (с использованием JOIN):
SELECT student.name, student.age, faculty.name AS faculty_name
FROM student
JOIN faculty ON student.faculty_id = faculty.id;

 Получить студентов, которые учатся на факультетах, начинающихся с "Инженер":
SELECT * FROM student
JOIN faculty ON student.faculty_id = faculty.id
WHERE faculty.name ILIKE 'Инженер%';

-- Запрос 11: Получить студентов, которые учат на факультетах, в которых цвет красный:
SELECT * FROM student
JOIN faculty ON student.faculty_id = faculty.id
WHERE faculty.color ILIKE 'красный';

-- Запрос 12: Получить студентов с самым наибольшим возрастом:
SELECT * FROM student ORDER BY age DESC LIMIT 1;

-- Запрос 13: Получить студентов, которые учатся на факультете с определенным id:
SELECT * FROM student WHERE faculty_id = 3;

-- Запрос 14: Посчитать количество студентов на каждом факультете:
SELECT faculty.name, COUNT(student.id) AS student_count
FROM faculty
LEFT JOIN student ON faculty.id = student.faculty_id
GROUP BY faculty.name;

-- Запрос 15: Получить студентов, которые учатся на факультете, название которого содержит "Технология":
SELECT * FROM student
JOIN faculty ON student.faculty_id = faculty.id
WHERE faculty.name ILIKE '%Технология%';

-- Запрос 16: Получить студентов, у которых возраст равен идентификатору факультета:
SELECT * FROM student
JOIN faculty ON student.faculty_id = faculty.id
WHERE student.age = faculty.id;

-- Запрос 17: Получить студентов, у которых возраст в диапазоне от 17 до 25 лет, и они учатся на факультетах с цветом "Синий"
SELECT * FROM student
JOIN faculty ON student.faculty_id = faculty.id
WHERE student.age BETWEEN 17 AND 25
AND faculty.color ILIKE 'синий';

-- Запрос 18: Получить факультеты, на которых больше 5 студентов
SELECT faculty.name, COUNT(student.id) AS student_count
FROM faculty
LEFT JOIN student ON faculty.id = student.faculty_id
GROUP BY faculty.name
HAVING COUNT(student.id) > 5;
