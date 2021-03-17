DROP TABLE `empleados`;
CREATE TABLE `empleados`(
	`id` int(11) NOT NULL primary key auto_increment,
	`name` varchar(100) NOT NULL,
	`job`  varchar(100) ,
	`salary`  double (11)
);


INSERT INTO `empleados` (`id`, `name`, `job`, `salary`) VALUES 
(1, "Jorge", "developper", "20000"),
(2, "Maria", "rrhh", "2200"),
(3, "Ryan", "developper", "24000"),
