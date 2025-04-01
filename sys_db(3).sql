-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 01, 2025 at 11:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sys_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `dailytimerecords`
--

CREATE TABLE `dailytimerecords` (
  `record_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `entry_date` date NOT NULL,
  `time_in` time NOT NULL,
  `time_out` time NOT NULL,
  `month` varchar(20) NOT NULL,
  `hours_worked` decimal(10,0) NOT NULL,
  `overtime_hrs` decimal(10,0) NOT NULL,
  `absent` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dailytimerecords`
--

INSERT INTO `dailytimerecords` (`record_id`, `employee_id`, `entry_date`, `time_in`, `time_out`, `month`, `hours_worked`, `overtime_hrs`, `absent`) VALUES
(1, 1001, '2024-08-01', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(2, 1002, '2024-08-01', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(3, 1003, '2024-08-01', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(4, 1004, '2024-08-01', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(6, 1001, '2024-08-02', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(7, 1002, '2024-08-02', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(8, 1003, '2024-08-02', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(9, 1004, '2024-08-02', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(11, 1001, '2024-08-05', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(12, 1002, '2024-08-05', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(13, 1003, '2024-08-05', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(14, 1004, '2024-08-05', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(16, 1001, '2024-08-06', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(17, 1002, '2024-08-06', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(18, 1003, '2024-08-06', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(19, 1004, '2024-08-06', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(21, 1001, '2024-08-07', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(22, 1002, '2024-08-07', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(23, 1003, '2024-08-07', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(24, 1004, '2024-08-07', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(26, 1001, '2024-08-08', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(27, 1002, '2024-08-08', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(28, 1003, '2024-08-08', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(29, 1004, '2024-08-08', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(31, 1001, '2024-08-09', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(32, 1002, '2024-08-09', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(33, 1003, '2024-08-09', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(34, 1004, '2024-08-09', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(36, 1001, '2024-08-12', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(37, 1002, '2024-08-12', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(38, 1003, '2024-08-12', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(39, 1004, '2024-08-12', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(41, 1001, '2024-08-13', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(42, 1002, '2024-08-13', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(43, 1003, '2024-08-13', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(44, 1004, '2024-08-13', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(46, 1001, '2024-08-14', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(47, 1002, '2024-08-14', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(48, 1003, '2024-08-14', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(49, 1004, '2024-08-14', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(51, 1001, '2024-08-15', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(52, 1002, '2024-08-15', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(53, 1003, '2024-08-15', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(54, 1004, '2024-08-15', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(56, 1001, '2024-08-16', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(57, 1002, '2024-08-16', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(58, 1003, '2024-08-16', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(59, 1004, '2024-08-16', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(61, 1001, '2024-08-19', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(62, 1002, '2024-08-19', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(63, 1003, '2024-08-19', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(64, 1004, '2024-08-19', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(66, 1001, '2024-08-20', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(67, 1002, '2024-08-20', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(68, 1003, '2024-08-20', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(69, 1004, '2024-08-20', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(71, 1001, '2024-08-21', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(72, 1002, '2024-08-21', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(73, 1003, '2024-08-21', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(74, 1004, '2024-08-21', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(76, 1001, '2024-08-22', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(77, 1002, '2024-08-22', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(78, 1003, '2024-08-22', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(79, 1004, '2024-08-22', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(81, 1001, '2024-08-23', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(82, 1002, '2024-08-23', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(83, 1003, '2024-08-23', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(84, 1004, '2024-08-23', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(86, 1001, '2024-08-26', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(87, 1002, '2024-08-26', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(88, 1003, '2024-08-26', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(89, 1004, '2024-08-26', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(91, 1001, '2024-08-27', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(92, 1002, '2024-08-27', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(93, 1003, '2024-08-27', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(94, 1004, '2024-08-27', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(96, 1001, '2024-08-28', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(97, 1002, '2024-08-28', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(98, 1003, '2024-08-28', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(99, 1004, '2024-08-28', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(101, 1001, '2024-08-29', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(102, 1002, '2024-08-29', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(103, 1003, '2024-08-29', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(104, 1004, '2024-08-29', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(106, 1001, '2024-08-30', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(107, 1002, '2024-08-30', '08:15:00', '17:15:00', 'AUGUST', 8, 0, 'No'),
(108, 1003, '2024-08-30', '08:05:00', '17:05:00', 'AUGUST', 8, 0, 'No'),
(109, 1004, '2024-08-30', '08:10:00', '17:10:00', 'AUGUST', 8, 0, 'No'),
(111, 1001, '2024-09-02', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(112, 1002, '2024-09-02', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(113, 1003, '2024-09-02', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(114, 1004, '2024-09-02', '08:05:00', '17:35:00', 'SEPTEMBER', 8, 0, 'No'),
(116, 1001, '2024-09-03', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(117, 1002, '2024-09-03', '08:10:00', '17:40:00', 'SEPTEMBER', 8, 0, 'No'),
(118, 1003, '2024-09-03', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(119, 1004, '2024-09-03', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(121, 1001, '2024-09-04', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(122, 1002, '2024-09-04', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(123, 1003, '2024-09-04', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(124, 1004, '2024-09-04', '08:10:00', '17:40:00', 'SEPTEMBER', 8, 0, 'No'),
(126, 1001, '2024-09-05', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(127, 1002, '2024-09-05', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(128, 1003, '2024-09-05', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(129, 1004, '2024-09-05', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(131, 1001, '2024-09-06', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(132, 1002, '2024-09-06', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(133, 1003, '2024-09-06', '08:05:00', '17:35:00', 'SEPTEMBER', 8, 0, 'No'),
(134, 1004, '2024-09-06', '08:10:00', '17:40:00', 'SEPTEMBER', 8, 0, 'No'),
(136, 1001, '2024-09-09', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(137, 1002, '2024-09-09', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(138, 1003, '2024-09-09', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(139, 1004, '2024-09-09', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(141, 1001, '2024-09-10', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(142, 1002, '2024-09-10', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(143, 1003, '2024-09-10', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(144, 1004, '2024-09-10', '08:05:00', '17:35:00', 'SEPTEMBER', 8, 0, 'No'),
(146, 1001, '2024-09-11', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(147, 1002, '2024-09-11', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(148, 1003, '2024-09-11', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(149, 1004, '2024-09-11', '08:10:00', '17:40:00', 'SEPTEMBER', 8, 0, 'No'),
(151, 1001, '2024-09-12', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(152, 1002, '2024-09-12', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(153, 1003, '2024-09-12', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(154, 1004, '2024-09-12', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(156, 1001, '2024-09-13', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(157, 1002, '2024-09-13', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(158, 1003, '2024-09-13', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(159, 1004, '2024-09-13', '08:10:00', '17:40:00', 'SEPTEMBER', 8, 0, 'No'),
(161, 1001, '2024-09-16', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(162, 1002, '2024-09-16', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(163, 1003, '2024-09-16', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(164, 1004, '2024-09-16', '08:00:00', '17:15:00', 'SEPTEMBER', 8, 0, 'No'),
(166, 1001, '2024-09-17', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(167, 1002, '2024-09-17', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(168, 1003, '2024-09-17', '08:20:00', '17:50:00', 'SEPTEMBER', 8, 0, 'No'),
(169, 1004, '2024-09-17', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(171, 1001, '2024-09-18', '08:15:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(172, 1002, '2024-09-18', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(173, 1003, '2024-09-18', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(174, 1004, '2024-09-18', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(176, 1001, '2024-09-19', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(177, 1002, '2024-09-19', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(178, 1003, '2024-09-19', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(179, 1004, '2024-09-19', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(181, 1001, '2024-09-20', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(182, 1002, '2024-09-20', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(183, 1003, '2024-09-20', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(184, 1004, '2024-09-20', '08:10:00', '17:40:00', 'SEPTEMBER', 8, 0, 'No'),
(186, 1001, '2024-09-23', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(187, 1002, '2024-09-23', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(188, 1003, '2024-09-23', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(189, 1004, '2024-09-23', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(191, 1001, '2024-09-24', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(192, 1002, '2024-09-24', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(193, 1003, '2024-09-24', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(194, 1004, '2024-09-24', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(196, 1001, '2024-09-25', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(197, 1002, '2024-09-25', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(198, 1003, '2024-09-25', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(199, 1004, '2024-09-25', '08:05:00', '17:35:00', 'SEPTEMBER', 8, 0, 'No'),
(201, 1001, '2024-09-26', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(202, 1002, '2024-09-26', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(203, 1003, '2024-09-26', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(204, 1004, '2024-09-26', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(206, 1001, '2024-09-27', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(207, 1002, '2024-09-27', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(208, 1003, '2024-09-27', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(209, 1004, '2024-09-27', '08:10:00', '17:40:00', 'SEPTEMBER', 8, 0, 'No'),
(211, 1001, '2024-09-30', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(212, 1002, '2024-09-30', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(213, 1003, '2024-09-30', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(214, 1004, '2024-09-30', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(216, 1001, '2024-10-01', '08:10:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(217, 1002, '2024-10-01', '08:05:00', '18:00:00', 'OCTOBER', 8, 1, 'No'),
(218, 1003, '2024-10-01', '08:20:00', '20:00:00', 'OCTOBER', 8, 3, 'No'),
(219, 1004, '2024-10-01', '08:00:00', '16:45:00', 'OCTOBER', 7, 0, 'No'),
(221, 1001, '2024-10-02', '09:00:00', '19:00:00', 'OCTOBER', 8, 2, 'No'),
(222, 1002, '2024-10-02', '08:25:00', '22:00:00', 'OCTOBER', 8, 5, 'No'),
(223, 1003, '2024-10-02', '08:05:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(224, 1004, '2024-10-02', '08:10:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(225, 1001, '2024-10-03', '08:00:00', '17:00:00', 'OCTOBER', 8, 0, 'No'),
(226, 1003, '2024-10-03', '08:30:00', '18:00:00', 'OCTOBER', 8, 0, 'No'),
(227, 1004, '2024-10-03', '08:45:00', '20:15:00', 'OCTOBER', 8, 3, 'No'),
(229, 1001, '2024-10-04', '08:25:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(230, 1002, '2024-10-04', '08:00:00', '16:45:00', 'OCTOBER', 7, 0, 'No'),
(231, 1003, '2024-10-04', '09:00:00', '20:30:00', 'OCTOBER', 8, 3, 'No'),
(233, 1002, '2024-10-07', '08:15:00', '18:45:00', 'OCTOBER', 8, 1, 'No'),
(234, 1003, '2024-10-07', '08:30:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(235, 1004, '2024-10-07', '09:00:00', '21:00:00', 'OCTOBER', 8, 4, 'No'),
(237, 1001, '2024-10-08', '08:00:00', '16:30:00', 'OCTOBER', 7, 0, 'No'),
(238, 1002, '2024-10-08', '08:35:00', '20:15:00', 'OCTOBER', 8, 2, 'No'),
(239, 1004, '2024-10-08', '08:10:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(241, 1001, '2024-10-09', '08:10:00', '19:00:00', 'OCTOBER', 8, 2, 'No'),
(242, 1002, '2024-10-09', '08:00:00', '17:00:00', 'OCTOBER', 8, 0, 'No'),
(243, 1003, '2024-10-09', '09:00:00', '18:30:00', 'OCTOBER', 8, 1, 'No'),
(244, 1004, '2024-10-09', '08:20:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(245, 1001, '2024-10-10', '08:15:00', '20:00:00', 'OCTOBER', 8, 3, 'No'),
(246, 1002, '2024-10-10', '08:25:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(247, 1003, '2024-10-10', '08:10:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(249, 1001, '2024-10-11', '08:30:00', '18:00:00', 'OCTOBER', 8, 0, 'No'),
(250, 1002, '2024-10-11', '08:00:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(251, 1003, '2024-10-11', '08:10:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(252, 1004, '2024-10-11', '08:00:00', '16:30:00', 'OCTOBER', 7, 0, 'No'),
(254, 1001, '2024-10-14', '08:00:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(255, 1002, '2024-10-14', '08:20:00', '17:50:00', 'OCTOBER', 8, 0, 'No'),
(256, 1003, '2024-10-14', '08:00:00', '17:00:00', 'OCTOBER', 8, 0, 'No'),
(257, 1004, '2024-10-14', '08:15:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(259, 1001, '2024-10-15', '08:25:00', '17:15:00', 'OCTOBER', 8, 0, 'No'),
(260, 1002, '2024-10-15', '08:15:00', '18:00:00', 'OCTOBER', 8, 1, 'No'),
(261, 1003, '2024-10-15', '08:30:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(262, 1004, '2024-10-15', '09:00:00', '19:00:00', 'OCTOBER', 8, 2, 'No'),
(263, 1001, '2024-10-16', '08:10:00', '19:00:00', 'OCTOBER', 8, 2, 'No'),
(264, 1002, '2024-10-16', '08:05:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(265, 1003, '2024-10-16', '08:15:00', '18:30:00', 'OCTOBER', 8, 1, 'No'),
(266, 1004, '2024-10-16', '08:20:00', '17:50:00', 'OCTOBER', 8, 0, 'No'),
(268, 1001, '2024-10-17', '08:05:00', '16:50:00', 'OCTOBER', 7, 0, 'No'),
(269, 1002, '2024-10-17', '08:15:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(270, 1003, '2024-10-17', '08:00:00', '17:15:00', 'OCTOBER', 8, 0, 'No'),
(271, 1004, '2024-10-17', '08:30:00', '19:00:00', 'OCTOBER', 8, 3, 'No'),
(273, 1001, '2024-10-18', '08:00:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(274, 1002, '2024-10-18', '08:25:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(275, 1003, '2024-10-18', '08:15:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(277, 1001, '2024-10-21', '08:05:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(278, 1002, '2024-10-21', '08:00:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(279, 1003, '2024-10-21', '08:20:00', '18:00:00', 'OCTOBER', 8, 0, 'No'),
(280, 1004, '2024-10-21', '08:15:00', '19:00:00', 'OCTOBER', 8, 1, 'No'),
(282, 1001, '2024-10-22', '08:15:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(283, 1002, '2024-10-22', '08:00:00', '18:00:00', 'OCTOBER', 8, 0, 'No'),
(284, 1003, '2024-10-22', '08:30:00', '18:30:00', 'OCTOBER', 8, 0, 'No'),
(285, 1004, '2024-10-22', '08:05:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(286, 1001, '2024-10-23', '08:00:00', '17:15:00', 'OCTOBER', 8, 0, 'No'),
(287, 1002, '2024-10-23', '08:05:00', '19:00:00', 'OCTOBER', 8, 3, 'No'),
(288, 1003, '2024-10-23', '08:10:00', '17:40:00', 'OCTOBER', 8, 0, 'No'),
(289, 1004, '2024-10-23', '08:25:00', '17:50:00', 'OCTOBER', 8, 0, 'No'),
(291, 1001, '2024-10-24', '08:20:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(292, 1002, '2024-10-24', '08:15:00', '18:00:00', 'OCTOBER', 8, 1, 'No'),
(293, 1003, '2024-10-24', '08:10:00', '17:50:00', 'OCTOBER', 8, 0, 'No'),
(294, 1004, '2024-10-24', '08:30:00', '19:00:00', 'OCTOBER', 8, 3, 'No'),
(296, 1001, '2024-10-25', '08:30:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(297, 1002, '2024-10-25', '08:00:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(298, 1003, '2024-10-25', '08:15:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(299, 1004, '2024-10-25', '08:00:00', '17:15:00', 'OCTOBER', 8, 0, 'No'),
(301, 1001, '2024-10-28', '08:15:00', '18:00:00', 'OCTOBER', 8, 1, 'No'),
(302, 1002, '2024-10-28', '08:00:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(303, 1003, '2024-10-28', '08:30:00', '18:00:00', 'OCTOBER', 8, 0, 'No'),
(304, 1004, '2024-10-28', '08:20:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(305, 1001, '2024-10-29', '08:00:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(306, 1002, '2024-10-29', '08:15:00', '18:30:00', 'OCTOBER', 8, 1, 'No'),
(307, 1003, '2024-10-29', '08:25:00', '17:50:00', 'OCTOBER', 8, 0, 'No'),
(308, 1004, '2024-10-29', '08:10:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(310, 1001, '2024-10-30', '08:15:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(311, 1002, '2024-10-30', '08:30:00', '19:00:00', 'OCTOBER', 8, 3, 'No'),
(312, 1003, '2024-10-30', '08:20:00', '18:00:00', 'OCTOBER', 8, 0, 'No'),
(313, 1004, '2024-10-30', '08:15:00', '18:00:00', 'OCTOBER', 8, 0, 'No'),
(315, 1001, '2024-10-31', '08:10:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(316, 1002, '2024-10-31', '08:05:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(317, 1003, '2024-10-31', '08:20:00', '18:00:00', 'OCTOBER', 8, 1, 'No'),
(318, 1004, '2024-10-31', '08:30:00', '17:30:00', 'OCTOBER', 8, 0, 'No');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL,
  `emp_fname` varchar(50) NOT NULL,
  `emp_middle` varchar(255) DEFAULT NULL,
  `emp_lname` varchar(50) NOT NULL,
  `emp_age` int(11) NOT NULL,
  `emp_sex` varchar(255) NOT NULL,
  `emp_add` varchar(50) NOT NULL,
  `emp_email` varchar(50) NOT NULL,
  `emp_contact` varchar(50) NOT NULL,
  `emp_hdate` date NOT NULL,
  `emp_dept` varchar(50) NOT NULL,
  `emp_position` varchar(50) NOT NULL,
  `filePath` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`emp_id`, `emp_fname`, `emp_middle`, `emp_lname`, `emp_age`, `emp_sex`, `emp_add`, `emp_email`, `emp_contact`, `emp_hdate`, `emp_dept`, `emp_position`, `filePath`) VALUES
(1001, 'Rasheed', '', 'Tapales', 20, 'Male', 'Tinubdan, San Fernando, Cebu', 'tapales@gmail.com', '09456989966', '2024-11-01', 'Human Resources', 'Finance Manager', 'src/GUI/images/Emp/09c0eddb-1373-422b-b709-ef871a17eb5d.jpg'),
(1002, 'Rodeliza', 'La Rosa', 'Tapales', 20, 'Female', 'Ward 4, Minglanilla, Cebu', 'rode@gmail.com', '09456231563', '2025-02-08', 'Front Office', 'Front Office Manager', 'src/GUI/images/Emp/b537b962-fb69-4a53-8dff-8a71c0e79cd6.jpg'),
(1003, 'Christhel', '', 'Tapao', 20, 'Female', 'Naga City, Cebu', 'marie@gmail.com', '09236578986', '2025-01-04', 'IT', 'IT Manager', 'src/GUI/images/Emp/fff30141-df3d-4748-a82d-f3e4312d3f68.jpg'),
(1004, 'Allyna', '', 'Manatad', 20, 'Female', 'Naga City, Cebu', 'allyna@gmail.com', '09231567425', '2025-01-15', 'Maintenance', 'Chief Engineer', 'src/GUI/images/Emp/485490886_1600104924037517_3051937344010869938_n-removebg-preview.png');

-- --------------------------------------------------------

--
-- Table structure for table `m_reports`
--

CREATE TABLE `m_reports` (
  `m_report_id` int(11) NOT NULL,
  `report_type` varchar(255) NOT NULL,
  `month` varchar(20) NOT NULL,
  `year` int(11) NOT NULL,
  `total_salary_cost` decimal(15,2) NOT NULL,
  `total_contri_employee` decimal(15,2) NOT NULL,
  `total_contri_employer` decimal(15,2) NOT NULL,
  `date_generated` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `report_id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `month` varchar(10) NOT NULL,
  `year` int(11) NOT NULL,
  `total_hours` decimal(10,0) NOT NULL,
  `total_overtime` decimal(10,0) NOT NULL,
  `sss` decimal(10,0) NOT NULL,
  `phil_health` decimal(10,0) NOT NULL,
  `pag_ibig` decimal(10,0) NOT NULL,
  `t_deductions` decimal(10,0) NOT NULL,
  `overtime_pay` decimal(10,0) NOT NULL,
  `net_pay` decimal(10,0) NOT NULL,
  `status` varchar(20) NOT NULL,
  `date_generated` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `role_name`) VALUES
(1, 'Super_Admin'),
(2, 'HR_Admin'),
(3, 'Employee'),
(4, 'Staff');

-- --------------------------------------------------------

--
-- Table structure for table `system_logs`
--

CREATE TABLE `system_logs` (
  `log_id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `details` text DEFAULT NULL,
  `timestamp` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `system_logs`
--

INSERT INTO `system_logs` (`log_id`, `username`, `action`, `details`, `timestamp`) VALUES
(1, 'test_user', 'test_action', 'test_details', '2025-03-25 23:36:51'),
(2, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 08:26:16'),
(3, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 08:28:05'),
(4, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 08:28:33'),
(5, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 08:29:09'),
(6, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 08:32:01'),
(7, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 08:33:10'),
(8, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 08:41:10'),
(9, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 08:44:31'),
(10, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 08:51:42'),
(11, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 08:57:30'),
(12, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 09:02:29'),
(13, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 09:17:09'),
(14, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-27 09:25:51'),
(15, 'superadmin', 'Login Failed', 'Invalid username or password.', '2025-03-27 09:26:01'),
(16, 'superadmin', 'Login Failed', 'Invalid username or password.', '2025-03-27 09:26:06'),
(17, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 09:26:13'),
(18, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 09:28:15'),
(19, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 09:31:08'),
(20, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 09:38:05'),
(21, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-03-27 09:46:46'),
(22, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 09:28:23'),
(23, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 09:38:02'),
(24, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 09:40:36'),
(25, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 09:41:19'),
(26, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 09:43:59'),
(27, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 09:44:46'),
(28, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 09:53:27'),
(29, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 09:57:29'),
(30, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 10:02:57'),
(31, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 10:03:20'),
(32, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 10:07:15'),
(33, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 10:07:56'),
(34, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 10:10:22'),
(35, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 10:11:00'),
(36, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 10:11:49'),
(37, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 10:13:50'),
(38, 'admim', 'Login Failed', 'Invalid username or password.', '2025-03-29 21:10:07'),
(39, 'admim', 'Login Failed', 'Invalid username or password.', '2025-03-29 21:10:12'),
(40, 'admim', 'Login Failed', 'Invalid username or password.', '2025-03-29 21:10:17'),
(41, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 21:10:20'),
(42, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 21:39:18'),
(43, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 21:40:13'),
(44, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 21:46:46'),
(45, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 22:08:08'),
(46, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 22:45:32'),
(47, 'admim', 'Login Failed', 'Invalid username or password.', '2025-03-29 22:47:35'),
(48, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 22:47:41'),
(49, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:08:23'),
(50, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:25:05'),
(51, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:26:24'),
(52, 'admin', 'Login Failed', 'Password mismatch.', '2025-03-29 23:29:43'),
(53, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:29:49'),
(54, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:34:14'),
(55, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:35:58'),
(56, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:36:38'),
(57, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:37:13'),
(58, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:38:11'),
(59, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:39:02'),
(60, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:39:46'),
(61, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:40:31'),
(62, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-29 23:51:47'),
(63, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 00:16:27'),
(64, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 00:23:29'),
(65, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 00:30:35'),
(66, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 08:28:22'),
(67, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 08:32:28'),
(68, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 08:53:24'),
(69, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 09:35:45'),
(70, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 09:47:01'),
(71, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 09:50:23'),
(72, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 11:42:31'),
(73, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 11:43:43'),
(74, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 12:04:36'),
(75, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 12:06:34'),
(76, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 12:29:15'),
(77, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 12:56:34'),
(78, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 13:00:27'),
(79, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 13:02:26'),
(80, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 13:09:28'),
(81, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 13:16:51'),
(82, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 13:20:43'),
(83, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 13:30:05'),
(84, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 13:47:53'),
(85, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 14:00:12'),
(86, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 14:12:35'),
(87, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 14:15:11'),
(88, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 14:56:58'),
(89, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 18:05:59'),
(90, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 18:13:10'),
(91, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 18:22:32'),
(92, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:05:02'),
(93, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:06:53'),
(94, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:08:57'),
(95, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:14:21'),
(96, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:17:02'),
(97, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:17:58'),
(98, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:19:59'),
(99, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:23:15'),
(100, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:35:22'),
(101, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:38:29'),
(102, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 20:54:58'),
(103, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 21:18:23'),
(104, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 21:30:43'),
(105, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 22:35:20'),
(106, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 22:41:13'),
(107, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 22:44:30'),
(108, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 23:16:40'),
(109, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 23:18:04'),
(110, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 23:21:10'),
(111, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-30 23:24:18'),
(112, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 00:09:48'),
(113, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 00:13:49'),
(114, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 00:29:04'),
(115, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 00:31:28'),
(116, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 00:34:03'),
(117, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 09:03:36'),
(118, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 09:29:52'),
(119, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 09:40:17'),
(120, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 09:46:05'),
(121, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 09:48:35'),
(122, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 09:52:13'),
(123, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 09:55:13'),
(124, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 09:59:24'),
(125, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 10:00:41'),
(126, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 10:04:01'),
(127, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 10:06:09'),
(128, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 10:07:22'),
(129, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 10:24:32'),
(130, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 10:27:58'),
(131, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 10:30:48'),
(132, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 13:32:35'),
(133, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 13:33:59'),
(134, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 13:37:21'),
(135, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 13:38:11'),
(136, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 15:13:21'),
(137, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 15:18:51'),
(138, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 15:37:49'),
(139, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 15:43:04'),
(140, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 16:15:26'),
(141, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 16:47:03'),
(142, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 20:27:30'),
(143, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 21:03:46'),
(144, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 21:27:49'),
(145, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 21:31:42');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_email` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_pass` varchar(255) NOT NULL,
  `status` varchar(20) NOT NULL,
  `role_id` int(20) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_email`, `user_name`, `user_pass`, `status`, `role_id`, `emp_id`) VALUES
(33, 'oki@gmail.com', 'okay', 'ZZMh7by6cFWzNU4WXdzKTPcOxc6OvAGd5hAt2pLBfZw=', 'Active', NULL, NULL),
(34, 'hi@gmail.com', 'hello', 'ZZMh7by6cFWzNU4WXdzKTPcOxc6OvAGd5hAt2pLBfZw=', 'Active', NULL, NULL),
(38, 'admin@gmail.com', 'admin', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', 'Active', 2, NULL),
(39, 'super@gmail.com', 'super', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', 'Active', 1, NULL),
(40, 'dmakldm@gmail.com', 'diaskdao', 'oqeqwdmoksma', 'Active', NULL, NULL),
(41, 'admini@gmail.com', 'adminni', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', 'Active', 1, NULL),
(42, 'ireo@gmail.com', 'ireo', 'AK4k/lL3MAiVZvNWQs+JQws5OXjvvqBihY5+Bc5fsgw=', 'Newly Registered', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dailytimerecords`
--
ALTER TABLE `dailytimerecords`
  ADD PRIMARY KEY (`record_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`emp_id`);

--
-- Indexes for table `m_reports`
--
ALTER TABLE `m_reports`
  ADD PRIMARY KEY (`m_report_id`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`report_id`),
  ADD KEY `emp_id` (`emp_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `system_logs`
--
ALTER TABLE `system_logs`
  ADD PRIMARY KEY (`log_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `role_id` (`role_id`),
  ADD KEY `emp_id` (`emp_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dailytimerecords`
--
ALTER TABLE `dailytimerecords`
  MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=320;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `emp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1005;

--
-- AUTO_INCREMENT for table `m_reports`
--
ALTER TABLE `m_reports`
  MODIFY `m_report_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `system_logs`
--
ALTER TABLE `system_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=146;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dailytimerecords`
--
ALTER TABLE `dailytimerecords`
  ADD CONSTRAINT `dailytimerecords_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`emp_id`);

--
-- Constraints for table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
