-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2025 at 06:51 PM
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
(6, 1001, '2024-08-02', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(11, 1001, '2024-08-05', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(16, 1001, '2024-08-06', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(21, 1001, '2024-08-07', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(26, 1001, '2024-08-08', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(31, 1001, '2024-08-09', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(36, 1001, '2024-08-12', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(41, 1001, '2024-08-13', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(46, 1001, '2024-08-14', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(51, 1001, '2024-08-15', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(56, 1001, '2024-08-16', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(61, 1001, '2024-08-19', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(66, 1001, '2024-08-20', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(71, 1001, '2024-08-21', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(76, 1001, '2024-08-22', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(81, 1001, '2024-08-23', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(86, 1001, '2024-08-26', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(91, 1001, '2024-08-27', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(96, 1001, '2024-08-28', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(101, 1001, '2024-08-29', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(106, 1001, '2024-08-30', '08:00:00', '17:00:00', 'AUGUST', 8, 0, 'No'),
(111, 1001, '2024-09-02', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(116, 1001, '2024-09-03', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(121, 1001, '2024-09-04', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(126, 1001, '2024-09-05', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(131, 1001, '2024-09-06', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(136, 1001, '2024-09-09', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(141, 1001, '2024-09-10', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(146, 1001, '2024-09-11', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(151, 1001, '2024-09-12', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(156, 1001, '2024-09-13', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(161, 1001, '2024-09-16', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(166, 1001, '2024-09-17', '08:05:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(171, 1001, '2024-09-18', '08:15:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(176, 1001, '2024-09-19', '08:00:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(181, 1001, '2024-09-20', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(186, 1001, '2024-09-23', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(191, 1001, '2024-09-24', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(196, 1001, '2024-09-25', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(201, 1001, '2024-09-26', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(206, 1001, '2024-09-27', '08:15:00', '17:45:00', 'SEPTEMBER', 8, 0, 'No'),
(211, 1001, '2024-09-30', '08:10:00', '17:30:00', 'SEPTEMBER', 8, 0, 'No'),
(216, 1001, '2024-10-01', '08:10:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(221, 1001, '2024-10-02', '09:00:00', '19:00:00', 'OCTOBER', 8, 2, 'No'),
(225, 1001, '2024-10-03', '08:00:00', '17:00:00', 'OCTOBER', 8, 0, 'No'),
(229, 1001, '2024-10-04', '08:25:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(237, 1001, '2024-10-08', '08:00:00', '16:30:00', 'OCTOBER', 7, 0, 'No'),
(241, 1001, '2024-10-09', '08:10:00', '19:00:00', 'OCTOBER', 8, 2, 'No'),
(245, 1001, '2024-10-10', '08:15:00', '20:00:00', 'OCTOBER', 8, 3, 'No'),
(249, 1001, '2024-10-11', '08:30:00', '18:00:00', 'OCTOBER', 8, 0, 'No'),
(254, 1001, '2024-10-14', '08:00:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(259, 1001, '2024-10-15', '08:25:00', '17:15:00', 'OCTOBER', 8, 0, 'No'),
(263, 1001, '2024-10-16', '08:10:00', '19:00:00', 'OCTOBER', 8, 2, 'No'),
(268, 1001, '2024-10-17', '08:05:00', '16:50:00', 'OCTOBER', 7, 0, 'No'),
(273, 1001, '2024-10-18', '08:00:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(277, 1001, '2024-10-21', '08:05:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(282, 1001, '2024-10-22', '08:15:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(286, 1001, '2024-10-23', '08:00:00', '17:15:00', 'OCTOBER', 8, 0, 'No'),
(291, 1001, '2024-10-24', '08:20:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(296, 1001, '2024-10-25', '08:30:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(301, 1001, '2024-10-28', '08:15:00', '18:00:00', 'OCTOBER', 8, 1, 'No'),
(305, 1001, '2024-10-29', '08:00:00', '17:30:00', 'OCTOBER', 8, 0, 'No'),
(310, 1001, '2024-10-30', '08:15:00', '17:45:00', 'OCTOBER', 8, 0, 'No'),
(315, 1001, '2024-10-31', '08:10:00', '17:30:00', 'OCTOBER', 8, 0, 'No');

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
  `filePath` varchar(255) DEFAULT NULL,
  `rates_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`emp_id`, `emp_fname`, `emp_middle`, `emp_lname`, `emp_age`, `emp_sex`, `emp_add`, `emp_email`, `emp_contact`, `emp_hdate`, `emp_dept`, `emp_position`, `filePath`, `rates_id`) VALUES
(1001, 'Rasheed', '', 'Tapales', 20, 'Male', 'Tinubda, San Fernando, Cebu', 'tapales@gmail.com', '09456989966', '2024-04-11', 'IT', 'IT Manager', 'src/GUI/images/Emp/09c0eddb-1373-422b-b709-ef871a17eb5d.jpg', 17);

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
-- Table structure for table `rates`
--

CREATE TABLE `rates` (
  `rates_id` int(11) NOT NULL,
  `position` varchar(255) NOT NULL,
  `hourly_rate` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rates`
--

INSERT INTO `rates` (`rates_id`, `position`, `hourly_rate`) VALUES
(1, 'General Manager', 120.00),
(2, 'HR Manager', 150.00),
(3, 'Finance Manager', 110.00),
(4, 'Finance Clerk', 80.00),
(5, 'Front Office Manager', 100.00),
(6, 'Receptionist', 95.00),
(7, 'Porter', 50.00),
(8, 'Reservation Clerk', 45.00),
(9, 'Executive Housekeeper', 130.00),
(10, 'Housekeeping Supervisor', 80.00),
(11, 'Room Attendant', 50.00),
(12, 'Public Area Cleaner', 40.00),
(13, 'Chief Engineer', 180.00),
(14, 'Maintenance Supervisor', 160.00),
(15, 'Maintenance Technician', 170.00),
(16, 'Groundskeeper', 50.00),
(17, 'IT Manager', 200.00),
(18, 'IT Support Specialist', 120.00),
(19, 'Network Administrator', 110.00),
(20, 'System Administrator', 130.00);

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
  `gross_salary` decimal(10,2) NOT NULL,
  `sss` decimal(10,0) NOT NULL,
  `phil_health` decimal(10,0) NOT NULL,
  `pag_ibig` decimal(10,0) NOT NULL,
  `t_deductions` decimal(10,0) NOT NULL,
  `overtime_pay` decimal(10,0) NOT NULL,
  `net_pay` decimal(10,0) NOT NULL,
  `status` varchar(20) NOT NULL,
  `date_generated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`report_id`, `emp_id`, `month`, `year`, `total_hours`, `total_overtime`, `gross_salary`, `sss`, `phil_health`, `pag_ibig`, `t_deductions`, `overtime_pay`, `net_pay`, `status`, `date_generated`) VALUES
(2, 1001, 'September', 2024, 168, 0, 33600.00, 1512, 840, 200, 2552, 0, 31048, 'Approved', '2025-04-18 01:58:17'),
(3, 1001, 'October', 2024, 174, 10, 34800.00, 1566, 870, 200, 2636, 3000, 35164, 'Approved', '2025-04-18 02:04:00');

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
(145, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-03-31 21:31:42'),
(146, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 06:20:29'),
(147, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 10:48:44'),
(148, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 10:49:30'),
(149, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 11:12:28'),
(150, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 11:13:18'),
(151, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 11:14:50'),
(152, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 11:15:46'),
(153, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 11:30:06'),
(154, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 12:48:40'),
(155, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 12:58:22'),
(156, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 15:56:30'),
(157, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 16:35:55'),
(158, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 16:45:14'),
(159, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 16:52:22'),
(160, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 16:56:49'),
(161, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 17:51:38'),
(162, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 17:53:43'),
(163, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 18:50:13'),
(164, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 19:56:29'),
(165, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 21:38:35'),
(166, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-03 23:29:51'),
(167, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-15 20:59:23'),
(168, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-17 11:54:14'),
(169, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-17 12:04:57'),
(170, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-17 12:07:34'),
(171, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-17 12:17:13'),
(172, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-17 15:07:41'),
(173, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-17 19:18:22'),
(174, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-17 19:23:40'),
(175, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-17 19:29:49'),
(176, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-17 19:59:05'),
(177, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-17 20:51:42'),
(178, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 09:27:22'),
(179, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 09:30:05'),
(180, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 09:31:47'),
(181, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 09:36:03'),
(182, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 09:47:42'),
(183, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 09:58:00'),
(184, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 10:03:51'),
(185, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 20:53:40'),
(186, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 21:00:18'),
(187, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 21:01:55'),
(188, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 23:13:10'),
(189, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-18 23:14:29'),
(190, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 07:47:29'),
(191, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 08:16:43'),
(192, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 08:17:58'),
(193, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 08:20:53'),
(194, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 09:12:00'),
(195, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 11:25:33'),
(196, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 11:36:47'),
(197, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 11:39:00'),
(198, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 12:36:49'),
(199, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 15:32:43'),
(200, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 15:34:20'),
(201, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 16:05:01'),
(202, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 16:05:51'),
(203, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 16:06:51'),
(204, 'admin', 'Login Failed', 'Password mismatch.', '2025-04-19 16:09:56'),
(205, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 16:09:59'),
(206, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 16:57:33'),
(207, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 16:59:18'),
(208, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 17:24:49'),
(209, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 17:36:04'),
(210, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 17:37:18'),
(211, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 17:39:03'),
(212, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-19 22:37:42'),
(213, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:06:55'),
(214, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:08:48'),
(215, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:13:51'),
(216, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:16:35'),
(217, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:18:39'),
(218, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:20:34'),
(219, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:22:19'),
(220, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:25:17'),
(221, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:26:14'),
(222, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:36:51'),
(223, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:41:56'),
(224, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:57:42'),
(225, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 00:59:07'),
(226, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 01:00:34'),
(227, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 01:01:37'),
(228, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 01:04:39'),
(229, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 01:13:45'),
(230, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:41:46'),
(231, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:44:30'),
(232, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:46:22'),
(233, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:47:15'),
(234, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:48:35'),
(235, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:49:08'),
(236, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:50:30'),
(237, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:50:42'),
(238, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:53:58'),
(239, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:54:43'),
(240, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:55:02'),
(241, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:57:48'),
(242, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:58:42'),
(243, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 20:59:32'),
(244, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 21:11:12'),
(245, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 21:15:54'),
(246, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 21:29:28'),
(247, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 21:30:27'),
(248, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-20 21:49:16'),
(249, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 20:23:40'),
(250, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 20:46:22'),
(251, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 20:48:24'),
(252, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 21:01:28'),
(253, '', 'Login Attempt', 'Failed - Empty fields', '2025-04-21 21:03:19'),
(254, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 21:03:41'),
(255, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 21:04:56'),
(256, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 21:09:09'),
(257, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 21:23:17'),
(258, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 21:25:04'),
(259, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 21:35:45'),
(260, 'admin', 'Login Failed', 'Password mismatch.', '2025-04-21 21:37:23'),
(261, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 21:37:27'),
(262, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 21:52:21'),
(263, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:15:48'),
(264, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:19:31'),
(265, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:22:11'),
(266, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:24:04'),
(267, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:29:15'),
(268, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:44:02'),
(269, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:45:30'),
(270, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:47:14'),
(271, 'admin', 'Login Failed', 'Password mismatch.', '2025-04-21 22:49:29'),
(272, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:49:33'),
(273, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:54:04'),
(274, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:55:21'),
(275, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 22:59:03'),
(276, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 23:00:25'),
(277, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 23:06:10'),
(278, 'super', 'Login Failed', 'Password mismatch.', '2025-04-21 23:06:47'),
(279, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-21 23:06:51'),
(280, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-21 23:11:33'),
(281, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 23:12:43'),
(282, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-21 23:12:53'),
(283, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-21 23:30:33'),
(284, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-21 23:34:04'),
(285, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-21 23:34:12'),
(286, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-21 23:40:03'),
(287, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-22 00:05:29'),
(288, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-22 00:05:42'),
(289, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-22 00:18:48'),
(290, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-22 00:33:21'),
(291, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-22 00:41:16'),
(292, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-22 00:42:03'),
(293, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-22 00:43:33'),
(294, 'admin', 'Login Failed', 'Password mismatch.', '2025-04-22 00:44:21'),
(295, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-22 00:44:24'),
(296, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-22 00:47:39'),
(297, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-22 00:49:43');

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
(42, 'ireo@gmail.com', 'ireo', 'AK4k/lL3MAiVZvNWQs+JQws5OXjvvqBihY5+Bc5fsgw=', 'Active', NULL, NULL),
(43, 'vince@gmail.com', 'Vince', 'LINbqJZtkCEg+0UEA3+tNO/6S5Rh6YjkxNoHOtUNroI=', 'Active', 3, NULL),
(44, 'kyle@gmail.com', 'kyle123', 'LINbqJZtkCEg+0UEA3+tNO/6S5Rh6YjkxNoHOtUNroI=', 'Newly Registered', 3, NULL),
(45, 'asdhad@gmail.com', 'Hehu', 'LINbqJZtkCEg+0UEA3+tNO/6S5Rh6YjkxNoHOtUNroI=', 'Newly Registered', 3, NULL),
(46, 'eawdawda@gmail.com', 'dasdas', 'V/MDAD8w3FmCj0f418M6Qvw+3C/JRIEomI5sEtUR0dk=', 'Newly Registered', 3, NULL),
(47, 'oasd@gmail.com', 'aOha', 'qwe123!@#', 'Active', 2, NULL);

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
  ADD PRIMARY KEY (`emp_id`),
  ADD KEY `rates_id` (`rates_id`);

--
-- Indexes for table `m_reports`
--
ALTER TABLE `m_reports`
  ADD PRIMARY KEY (`m_report_id`);

--
-- Indexes for table `rates`
--
ALTER TABLE `rates`
  ADD PRIMARY KEY (`rates_id`);

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
  MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=574;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `emp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1002;

--
-- AUTO_INCREMENT for table `m_reports`
--
ALTER TABLE `m_reports`
  MODIFY `m_report_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rates`
--
ALTER TABLE `rates`
  MODIFY `rates_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `system_logs`
--
ALTER TABLE `system_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=298;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dailytimerecords`
--
ALTER TABLE `dailytimerecords`
  ADD CONSTRAINT `dailytimerecords_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`emp_id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`rates_id`) REFERENCES `rates` (`rates_id`);

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
