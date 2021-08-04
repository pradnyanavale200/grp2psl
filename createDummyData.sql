/*Following queries create dummy data, Running learningMgmtDDL file is prerequisite for running this file. */

use learningMgmtDB;

INSERT INTO learner (learnerid, name, department, phonenumber, email, password) VALUES 
(1001, 'Adams', 'Marketing', '2084748755', 'adams@gmail.com', 'adamspass'),
(1002, 'Baker', 'Finance', '2165462308', 'bakers@gmail.com', 'bakerspass'),
(1003, 'Clark', 'IT', '2015625120', 'clark@gmail.com', 'clarkpass'),
(1004, 'Davis', 'Marketing', '2099645369', 'davis@gmail.com', 'davispass'),
(1005, 'Evans', 'Finance', '2169359933', 'evans@gmail.com', 'evanspass'),
(1006, 'Frank', 'IT', '2249156251', 'frank@gmail.com', 'frankpass'),
(1007, 'Ghosh', 'Marketing', '2132978767', 'ghosh@gmail.com', 'ghoshpass'),
(1008, 'Hills', 'Finance', '6195269471', 'hills@gmail.com', 'hillspass'),
(1009, 'Irwin', 'IT', '5076097206', 'irwin@gmail.com', 'irwinpass'),
(1010, 'Jones', 'IT', '2765521246', 'jones@gmail.com', 'jonespass');

INSERT INTO trainer (trainerid, name, department, phonenumber, email, password) VALUES 
(2001, 'Klein', 'HR', '2189714300', 'klein@gmail.com', 'kleinpass'),
(2002, 'Lopez', 'HR', '2096124483', 'lopez@gmail.com', 'lopezpass');

INSERT INTO manager (managerid, name, phonenumber, email, password)
VALUES (3001, 'Zafar', '2189714300', 'zafar@gmail.com', 'zafarpass');

INSERT INTO course (courseid, coursename, prerequisite, syllabus, duration) VALUES 
(1, 'Introduction to OOPs', 'Basic Programming', 'Principles of OOPs, Classes and Objects, Constructors and Destructors, Polymorphism, Program development using Inheritance, Exception Handling', 12),
(2, 'Finance 101', 'Basic Mathematics', 'Accounting, Financial Analysis, Corporate Finance', 8);

INSERT INTO TeacherCourseMapping(tcid, trainerid, courseid) VALUES 
(1, 2001, 2),
(2, 2002, 1);