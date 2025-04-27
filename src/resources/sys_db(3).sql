-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2025 at 06:31 PM
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
(1001, 'Rasheed', '', 'Tapales', 20, 'Male', 'Tinubda, San Fernando, Cebu', 'tapales@gmail.com', '09456989966', '2024-04-11', 'IT', 'IT Manager', 'src/GUI/images/Emp/09c0eddb-1373-422b-b709-ef871a17eb5d.jpg', 17),
(1002, 'Rodeliza', '', 'Tapales', 20, 'Female', 'Ward 3, Minglanilla, Cebu', 'rode@gmail.com', '09213567891', '2025-03-06', 'House Keeping', 'Housekeeping Supervisor', 'src/GUI/images/Emp/b537b962-fb69-4a53-8dff-8a71c0e79cd6.jpg', 10),
(1003, 'Allyna', 'Bonghanoy', 'Manatad', 20, 'Female', 'Inoburan, Naga City, Cebu', 'allyna@gmail.com', '09123567890', '2024-08-22', 'Front Office', 'Receptionist', 'src/GUI/images/Emp/485490886_1600104924037517_3051937344010869938_n-removebg-preview.png', 6),
(1004, 'Vince', 'Cuyno', 'Tapales', 20, 'Male', 'San Isidro, San Fernando, Cebu', 'vince@gmail.com', '09345234123', '2024-10-16', 'Maintenance', 'Chief Engineer', 'src/GUI/images/Emp/Vince.jpg', 13),
(1005, 'Christhel Marie', '', 'Tapao', 21, 'Female', 'Inoburan, San Fernando, Cebu', 'mariethel@gmail.com', '09124567891', '2024-03-07', 'Human Resources', 'Finance Clerk', 'src/GUI/images/Emp/fff30141-df3d-4748-a82d-f3e4312d3f68.jpg', 4);

-- --------------------------------------------------------

--
-- Table structure for table `password_reset_tokens`
--

CREATE TABLE `password_reset_tokens` (
  `token_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `token` varchar(6) NOT NULL,
  `expiry_time` datetime NOT NULL,
  `is_used` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `password_reset_tokens`
--

INSERT INTO `password_reset_tokens` (`token_id`, `user_id`, `token`, `expiry_time`, `is_used`, `created_at`) VALUES
(1, 48, '131152', '2025-04-26 13:32:02', 0, '2025-04-26 05:22:02'),
(2, 48, '376135', '2025-04-26 13:32:34', 0, '2025-04-26 05:22:34'),
(3, 48, '289606', '2025-04-26 13:40:06', 0, '2025-04-26 05:30:06'),
(4, 51, '919177', '2025-04-26 13:41:52', 0, '2025-04-26 05:31:52'),
(5, 48, '166508', '2025-04-26 14:06:57', 0, '2025-04-26 05:56:57'),
(6, 48, '310602', '2025-04-26 14:07:57', 0, '2025-04-26 05:57:57'),
(7, 48, '643197', '2025-04-26 14:08:55', 0, '2025-04-26 05:58:55'),
(8, 51, '257850', '2025-04-26 14:13:36', 0, '2025-04-26 06:03:36'),
(9, 51, '057351', '2025-04-26 14:14:47', 0, '2025-04-26 06:04:47'),
(10, 51, '972199', '2025-04-26 14:17:54', 0, '2025-04-26 06:07:54'),
(11, 51, '160655', '2025-04-26 14:19:48', 0, '2025-04-26 06:09:48'),
(12, 51, '953659', '2025-04-26 14:27:11', 0, '2025-04-26 06:17:11'),
(13, 51, '650244', '2025-04-26 15:33:19', 0, '2025-04-26 07:23:19'),
(14, 51, '592924', '2025-04-26 15:41:14', 1, '2025-04-26 07:31:14'),
(15, 51, '493376', '2025-04-26 15:44:12', 1, '2025-04-26 07:34:12'),
(16, 51, '662337', '2025-04-26 17:35:01', 1, '2025-04-26 09:25:01'),
(17, 51, '060160', '2025-04-26 17:40:12', 1, '2025-04-26 09:30:12'),
(18, 48, '930776', '2025-04-26 17:50:40', 1, '2025-04-26 09:40:40'),
(19, 51, '707022', '2025-04-27 09:06:03', 1, '2025-04-27 00:56:03'),
(20, 51, '466414', '2025-04-28 00:37:41', 1, '2025-04-27 16:27:41');

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
(297, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-22 00:49:43'),
(298, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-22 00:52:57'),
(299, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-23 05:05:01'),
(300, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-23 05:15:38'),
(301, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-23 05:19:33'),
(302, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-23 05:37:16'),
(303, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-24 21:54:16'),
(304, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-24 21:54:46'),
(305, 'wiggle', 'Login Failed', 'Access denied due to role restriction.', '2025-04-24 21:55:11'),
(306, 'Wiggle', 'Login Failed', 'Access denied due to role restriction.', '2025-04-24 21:55:35'),
(307, 'dasdasdasdas', 'Login Failed', 'Invalid username or password.', '2025-04-24 21:55:41'),
(308, 'Vince', 'Login Failed', 'Password mismatch.', '2025-04-24 21:57:37'),
(309, 'Vince', 'Login Failed', 'Access denied due to role restriction.', '2025-04-24 21:57:42'),
(310, 'Wiggle', 'Login Failed', 'Access denied due to role restriction.', '2025-04-24 21:59:03'),
(311, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 22:00:25'),
(312, 'Wiggle', 'Login Failed', 'Password mismatch.', '2025-04-24 22:17:23'),
(313, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 22:17:27'),
(314, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 22:18:20'),
(315, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 22:21:11'),
(316, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-24 22:21:46'),
(317, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 22:21:56'),
(318, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 22:23:16'),
(319, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 22:24:55'),
(320, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 22:59:07'),
(321, 'Wiggle', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run1514565210/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Employees/Employee.fxml\n', '2025-04-24 22:59:11'),
(322, 'Wiggle', 'Login Attempt', 'Employee ID is missing, awaiting binding to session.', '2025-04-24 23:13:03'),
(323, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:13:04'),
(324, 'Wiggle', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run1574716315/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Employees/Employee.fxml\n', '2025-04-24 23:13:34'),
(325, 'Wiggle', 'Login Attempt', 'Employee ID is missing, awaiting binding to session.', '2025-04-24 23:13:35'),
(326, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:13:35'),
(327, 'Wiggle', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run1574716315/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Employees/Employee.fxml\n', '2025-04-24 23:13:37'),
(328, 'admin', 'Login Attempt', 'Employee ID is missing, awaiting binding to session.', '2025-04-24 23:15:12'),
(329, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-24 23:15:13'),
(330, 'Wiggle', 'Login Attempt', 'Employee ID is missing, awaiting binding to session.', '2025-04-24 23:16:43'),
(331, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:16:43'),
(332, 'Wiggle', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run448467676/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Employees/Employee.fxml\n', '2025-04-24 23:19:13'),
(333, 'Wiggle', 'Login Attempt', 'Employee ID is missing, awaiting binding to session.', '2025-04-24 23:19:35'),
(334, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:19:35'),
(335, 'Wiggle', 'Login Attempt', 'Employee ID is missing, awaiting binding to session.', '2025-04-24 23:20:46'),
(336, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:20:47'),
(337, 'Wiggle', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run1552257347/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Employees/Employee.fxml\n', '2025-04-24 23:20:48'),
(338, 'Wiggle', 'Login Attempt', 'Employee ID is missing, awaiting binding to session.', '2025-04-24 23:22:08'),
(339, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:22:09'),
(340, 'Wiggle', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run33004188/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Employees/Employee.fxml\n', '2025-04-24 23:22:10'),
(341, 'Wiggle', 'Login Attempt', 'Employee ID is missing, awaiting binding to session.', '2025-04-24 23:25:27'),
(342, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:25:28'),
(343, 'Wiggle', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run20942956/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Employees/Employee.fxml\n', '2025-04-24 23:25:29'),
(344, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:27:57'),
(345, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:28:43'),
(346, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:39:15'),
(347, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:42:24'),
(348, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:43:35'),
(349, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:44:29'),
(350, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:47:31'),
(351, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:47:58'),
(352, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:49:29'),
(353, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:50:31'),
(354, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:52:38'),
(355, 'Wiggle', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run1670423066/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Employees/Employee.fxml\n', '2025-04-24 23:52:41'),
(356, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:53:37'),
(357, 'Wiggle', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run26697281/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Employees/Employee.fxml\n', '2025-04-24 23:53:38'),
(358, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:55:16'),
(359, 'Wiggle', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run1699360408/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Employees/Employee.fxml\n', '2025-04-24 23:55:17'),
(360, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:56:52'),
(361, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:57:36'),
(362, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-24 23:58:19'),
(363, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:07:00'),
(364, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:08:55'),
(365, 'Wiggle', 'Login Failed', 'Password mismatch.', '2025-04-25 00:14:29'),
(366, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:14:32'),
(367, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:14:53'),
(368, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:17:17'),
(369, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:24:57'),
(370, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:30:18'),
(371, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:31:42'),
(372, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:33:16'),
(373, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:39:52'),
(374, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:40:59'),
(375, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:43:25'),
(376, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:44:14'),
(377, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:46:19'),
(378, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:48:26'),
(379, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:50:15'),
(380, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:53:05'),
(381, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 00:54:30'),
(382, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 01:00:42'),
(383, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 01:01:25'),
(384, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 01:03:47'),
(385, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 09:16:23'),
(386, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 09:17:37'),
(387, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 09:23:49'),
(388, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 09:30:02'),
(389, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 09:32:58'),
(390, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 10:18:47'),
(391, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 10:19:39'),
(392, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 11:30:52'),
(393, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 11:35:31'),
(394, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 11:57:40'),
(395, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 12:22:36'),
(396, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 12:23:13'),
(397, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 12:26:57'),
(398, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 13:04:12'),
(399, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 13:05:27'),
(400, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 13:07:42'),
(401, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 13:32:03'),
(402, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 13:37:25'),
(403, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 13:38:19'),
(404, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 13:47:36'),
(405, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 13:55:35'),
(406, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:09:13'),
(407, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:12:21'),
(408, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:24:12'),
(409, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:26:12'),
(410, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:29:03'),
(411, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:32:03'),
(412, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:39:24'),
(413, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:41:11'),
(414, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:53:08'),
(415, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:54:22'),
(416, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 14:58:15'),
(417, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 15:00:30'),
(418, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 15:04:55'),
(419, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 17:47:10'),
(420, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 17:58:25'),
(421, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 17:59:08'),
(422, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:03:34'),
(423, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:05:02'),
(424, 'Wiggle', 'Login Failed', 'Password mismatch.', '2025-04-25 18:06:08'),
(425, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:06:11'),
(426, 'Wiggle', 'Login Failed', 'Password mismatch.', '2025-04-25 18:08:08'),
(427, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:08:12'),
(428, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:16:14'),
(429, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:17:13'),
(430, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:19:14'),
(431, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:21:01'),
(432, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:23:10'),
(433, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:25:06'),
(434, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:26:45'),
(435, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:27:52'),
(436, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:32:56'),
(437, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 18:38:12'),
(438, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:01:56'),
(439, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:02:53'),
(440, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:03:42'),
(441, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:04:38'),
(442, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:06:15'),
(443, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 19:06:43'),
(444, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:07:00'),
(445, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:23:38'),
(446, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 19:27:41'),
(447, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:37:15'),
(448, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:38:48'),
(449, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 19:38:57'),
(450, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:42:39'),
(451, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 19:42:52'),
(452, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 19:43:53'),
(453, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 19:44:02'),
(454, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 20:02:46'),
(455, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 20:04:06'),
(456, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 20:05:24'),
(457, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 20:07:44'),
(458, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 20:10:15'),
(459, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 20:10:55'),
(460, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 20:14:09'),
(461, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-25 21:09:39'),
(462, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-25 21:14:10'),
(463, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-25 21:55:15'),
(464, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-25 22:17:18'),
(465, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-25 22:18:41'),
(466, 'staff', 'Login Failed', 'Password mismatch.', '2025-04-25 22:20:03'),
(467, 'staff', 'Login Failed', 'Password mismatch.', '2025-04-25 22:20:07'),
(468, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-25 22:20:13'),
(469, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-25 22:42:46'),
(470, 'staffni', 'Login Failed', 'Password mismatch.', '2025-04-25 22:45:18'),
(471, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-25 22:45:54'),
(472, 'rodez', 'Login Failed', 'Access denied due to role restriction.', '2025-04-25 22:46:59'),
(473, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-25 22:50:37'),
(474, 'staff', 'Login Failed', 'Password mismatch.', '2025-04-25 22:50:45'),
(475, 'staffni', 'Login Failed', 'Password mismatch.', '2025-04-25 22:50:47'),
(476, 'staffni', 'Login Failed', 'Password mismatch.', '2025-04-25 22:50:53'),
(477, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-25 22:51:03'),
(478, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-25 23:16:31'),
(479, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-25 23:51:21'),
(480, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-25 23:52:01'),
(481, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-26 07:09:18'),
(482, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-26 07:14:50'),
(483, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-26 07:16:30'),
(484, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 07:17:11'),
(485, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:11:18'),
(486, 'rodez', 'Navigation Error', 'Error resolving onMouseClicked=\'#ExportButton\', either the event handler is not in the Namespace or there is an error in the script.\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run1104172483/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Staff/PDFGEN.fxml:115\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run1104172483/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Staff/Staff.fxml:39\n', '2025-04-26 08:11:20'),
(487, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:12:27'),
(488, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:14:59'),
(489, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:18:53'),
(490, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:35:11'),
(491, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:44:11'),
(492, 'rodez', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run69658818/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Staff/PDFGEN.fxml\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run69658818/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Staff/Staff.fxml:39\n', '2025-04-26 08:44:12'),
(493, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:44:41'),
(494, 'rodez', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run69658818/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Staff/PDFGEN.fxml\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run69658818/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Staff/Staff.fxml:39\n', '2025-04-26 08:44:43'),
(495, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:47:18'),
(496, 'rodez', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run300045918/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Staff/PDFGEN.fxml\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run300045918/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Staff/Staff.fxml:39\n', '2025-04-26 08:47:20'),
(497, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:48:50'),
(498, 'rodez', 'Navigation Error', '\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run300045918/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Staff/PDFGEN.fxml\nfile:/D:/temp/PRELIM/SLIDERS/my-system/dist/run300045918/GUI-TAPALES-IT2A.jar!/GUI/SysUI/Staff/Staff.fxml:39\n', '2025-04-26 08:48:50'),
(499, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:49:08'),
(500, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 08:53:50'),
(501, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 09:07:55'),
(502, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 10:19:43'),
(503, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 10:30:42'),
(504, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 10:37:16'),
(505, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 10:39:50'),
(506, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 10:45:43'),
(507, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 10:47:41'),
(508, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 10:53:34'),
(509, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 10:57:54'),
(510, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 11:02:42'),
(511, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 11:09:14'),
(512, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 11:11:38'),
(513, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 11:15:27'),
(514, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 11:20:38'),
(515, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 11:21:46'),
(516, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 11:22:55'),
(517, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 11:24:00'),
(518, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-26 16:57:26'),
(519, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-26 17:03:00'),
(520, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 17:11:05'),
(521, 'rodez', 'Login Failed', 'Password mismatch.', '2025-04-26 17:31:52'),
(522, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 17:31:56'),
(523, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-26 18:47:31'),
(524, 'Wiggle', 'Login Failed', 'Password mismatch.', '2025-04-26 18:57:53'),
(525, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-26 18:57:58'),
(526, 'Wiggle', 'Login Failed', 'Password mismatch.', '2025-04-26 18:58:14'),
(527, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-26 18:58:17'),
(528, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 18:59:24'),
(529, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-26 19:01:03'),
(530, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-26 20:25:59'),
(531, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-26 20:28:06'),
(532, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-26 20:52:41'),
(533, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:04:28'),
(534, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:10:43'),
(535, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:13:58'),
(536, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:19:09'),
(537, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:20:21'),
(538, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:28:00'),
(539, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:30:13'),
(540, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:32:14'),
(541, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:41:40'),
(542, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:42:47'),
(543, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:44:31'),
(544, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:46:43'),
(545, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:48:46'),
(546, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 22:55:37'),
(547, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:01:03'),
(548, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:02:40'),
(549, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:03:44'),
(550, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:05:41'),
(551, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:08:14'),
(552, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:10:42'),
(553, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:11:32'),
(554, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:12:49'),
(555, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:14:37'),
(556, 'admin', 'Login Attempt', 'Attempting login', '2025-04-26 23:34:10'),
(557, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-26 23:34:11'),
(558, 'admin', 'Initialize', 'Initializing HR Admin dashboard', '2025-04-26 23:34:11'),
(559, 'admin', 'Initialize', 'HR Admin dashboard initialized successfully', '2025-04-26 23:34:11'),
(560, 'admin', 'UI', 'Opened drawer menu', '2025-04-26 23:34:15'),
(561, 'admin', 'Navigation', 'Navigating to HR Dashboard', '2025-04-26 23:34:15'),
(562, 'admin', 'Page Load', 'Loading page: /GUI/SysUI/Admin/HR_Dashboard.fxml', '2025-04-26 23:34:15'),
(563, 'admin', 'Page Load', 'Successfully loaded page: /GUI/SysUI/Admin/HR_Dashboard.fxml', '2025-04-26 23:34:15'),
(564, 'admin', 'Navigation', 'Navigating to Employee Management', '2025-04-26 23:34:16'),
(565, 'admin', 'Page Load', 'Loading page: /GUI/SysUI/Admin/HR_EmployeeManagement.fxml', '2025-04-26 23:34:16'),
(566, 'admin', 'Load Data', 'Executing query: SELECT emp_id, emp_fname, emp_lname, emp_email, emp_hdate, emp_contact, emp_dept, emp_position FROM employee', '2025-04-26 23:34:16'),
(567, 'admin', 'Load Data', 'Successfully loaded 2 employees', '2025-04-26 23:34:16'),
(568, 'admin', 'Page Load', 'Successfully loaded page: /GUI/SysUI/Admin/HR_EmployeeManagement.fxml', '2025-04-26 23:34:16'),
(569, 'admin', 'Navigation', 'Navigating to Daily Time Records', '2025-04-26 23:34:16'),
(570, 'admin', 'Page Load', 'Loading page: /GUI/SysUI/Admin/DailyTimeRecords.fxml', '2025-04-26 23:34:16'),
(571, 'admin', 'Page Load', 'Successfully loaded page: /GUI/SysUI/Admin/DailyTimeRecords.fxml', '2025-04-26 23:34:17'),
(572, 'admin', 'Navigation', 'Navigating to Payslip Management', '2025-04-26 23:34:17'),
(573, 'admin', 'Page Load', 'Loading page: /GUI/SysUI/Admin/PaySlipManagement.fxml', '2025-04-26 23:34:17'),
(574, 'admin', 'Page Load', 'Successfully loaded page: /GUI/SysUI/Admin/PaySlipManagement.fxml', '2025-04-26 23:34:17'),
(575, 'admin', 'Navigation', 'Navigating to Payslip Management', '2025-04-26 23:34:18');
INSERT INTO `system_logs` (`log_id`, `username`, `action`, `details`, `timestamp`) VALUES
(576, 'admin', 'Page Load', 'Loading page: /GUI/SysUI/Admin/PaySlipManagement.fxml', '2025-04-26 23:34:18'),
(577, 'admin', 'Page Load', 'Successfully loaded page: /GUI/SysUI/Admin/PaySlipManagement.fxml', '2025-04-26 23:34:18'),
(578, 'admin', 'Navigation', 'Navigating to Daily Time Records', '2025-04-26 23:34:19'),
(579, 'admin', 'Page Load', 'Loading page: /GUI/SysUI/Admin/DailyTimeRecords.fxml', '2025-04-26 23:34:19'),
(580, 'admin', 'Page Load', 'Successfully loaded page: /GUI/SysUI/Admin/DailyTimeRecords.fxml', '2025-04-26 23:34:19'),
(581, 'admin', 'Navigation', 'Navigating to Employee Management', '2025-04-26 23:34:19'),
(582, 'admin', 'Page Load', 'Loading page: /GUI/SysUI/Admin/HR_EmployeeManagement.fxml', '2025-04-26 23:34:19'),
(583, 'admin', 'Load Data', 'Executing query: SELECT emp_id, emp_fname, emp_lname, emp_email, emp_hdate, emp_contact, emp_dept, emp_position FROM employee', '2025-04-26 23:34:19'),
(584, 'admin', 'Load Data', 'Successfully loaded 2 employees', '2025-04-26 23:34:19'),
(585, 'admin', 'Page Load', 'Successfully loaded page: /GUI/SysUI/Admin/HR_EmployeeManagement.fxml', '2025-04-26 23:34:19'),
(586, 'admin', 'Navigation', 'Navigating to HR Dashboard', '2025-04-26 23:34:19'),
(587, 'admin', 'Page Load', 'Loading page: /GUI/SysUI/Admin/HR_Dashboard.fxml', '2025-04-26 23:34:19'),
(588, 'admin', 'Page Load', 'Successfully loaded page: /GUI/SysUI/Admin/HR_Dashboard.fxml', '2025-04-26 23:34:19'),
(589, 'admin', 'UI', 'Closed drawer menu', '2025-04-26 23:34:21'),
(590, 'super', 'Login Attempt', 'Attempting login', '2025-04-26 23:35:13'),
(591, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:35:13'),
(592, 'super', 'Initialize', 'Initializing Super Admin dashboard', '2025-04-26 23:35:13'),
(593, 'super', 'Initialize', 'Super Admin dashboard initialized successfully', '2025-04-26 23:35:14'),
(594, 'super', 'UI', 'Opened drawer menu', '2025-04-26 23:35:15'),
(595, 'super', 'Navigation', 'Navigating to Super Admin Dashboard', '2025-04-26 23:35:16'),
(596, 'super', 'Page Load', 'Loading page: /GUI/SysUI/SuperAdmin/SU_Dashboard.fxml', '2025-04-26 23:35:16'),
(597, 'super', 'Page Load', 'Successfully loaded page: /GUI/SysUI/SuperAdmin/SU_Dashboard.fxml', '2025-04-26 23:35:16'),
(598, 'super', 'UI', 'Closed drawer menu', '2025-04-26 23:35:18'),
(599, 'super', 'UI', 'Opened drawer menu', '2025-04-26 23:35:19'),
(600, 'super', 'Navigation', 'Navigating to User Management', '2025-04-26 23:35:20'),
(601, 'super', 'Page Load', 'Loading page: /GUI/SysUI/SuperAdmin/SU_UserManagement.fxml', '2025-04-26 23:35:20'),
(602, 'super', 'Page Load', 'Successfully loaded page: /GUI/SysUI/SuperAdmin/SU_UserManagement.fxml', '2025-04-26 23:35:20'),
(603, 'super', 'Login Attempt', 'Attempting login', '2025-04-26 23:36:45'),
(604, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:36:45'),
(605, 'super', 'Initialize', 'Initializing Super Admin dashboard', '2025-04-26 23:36:45'),
(606, 'super', 'Initialize', 'Super Admin dashboard initialized successfully', '2025-04-26 23:36:45'),
(607, 'super', 'UI', 'Opened drawer menu', '2025-04-26 23:36:50'),
(608, 'super', 'Navigation', 'Navigating to Super Admin Dashboard', '2025-04-26 23:36:51'),
(609, 'super', 'Page Load', 'Loading page: /GUI/SysUI/SuperAdmin/SU_Dashboard.fxml', '2025-04-26 23:36:51'),
(610, 'super', 'Page Load', 'Successfully loaded page: /GUI/SysUI/SuperAdmin/SU_Dashboard.fxml', '2025-04-26 23:36:51'),
(611, 'super', 'Navigation', 'Navigating to User Management', '2025-04-26 23:36:53'),
(612, 'super', 'Page Load', 'Loading page: /GUI/SysUI/SuperAdmin/SU_UserManagement.fxml', '2025-04-26 23:36:53'),
(613, 'super', 'Page Load', 'Successfully loaded page: /GUI/SysUI/SuperAdmin/SU_UserManagement.fxml', '2025-04-26 23:36:53'),
(614, 'super', 'Navigation', 'Navigating to System Logs', '2025-04-26 23:36:53'),
(615, 'super', 'Page Load', 'Loading page: /GUI/SysUI/SuperAdmin/System.fxml', '2025-04-26 23:36:53'),
(616, 'super', 'Page Load', 'Successfully loaded page: /GUI/SysUI/SuperAdmin/System.fxml', '2025-04-26 23:36:53'),
(617, 'super', 'Navigation', 'Navigating to Scalability Settings', '2025-04-26 23:36:54'),
(618, 'super', 'Page Load', 'Loading page: /GUI/SysUI/SuperAdmin/Scalability.fxml', '2025-04-26 23:36:54'),
(619, 'super', 'Page Load', 'Successfully loaded page: /GUI/SysUI/SuperAdmin/Scalability.fxml', '2025-04-26 23:36:54'),
(620, 'super', 'Navigation', 'Navigating to System Logs', '2025-04-26 23:36:54'),
(621, 'super', 'Page Load', 'Loading page: /GUI/SysUI/SuperAdmin/System.fxml', '2025-04-26 23:36:54'),
(622, 'super', 'Page Load', 'Successfully loaded page: /GUI/SysUI/SuperAdmin/System.fxml', '2025-04-26 23:36:54'),
(623, 'super', 'UI', 'Closed drawer menu', '2025-04-26 23:36:56'),
(624, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-26 23:42:32'),
(625, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-27 13:38:21'),
(626, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-27 13:39:25'),
(627, 'Wiggle', 'Login Successful', 'Logged in as Employee', '2025-04-27 17:14:52'),
(628, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-28 00:08:00'),
(629, '', 'Login Attempt', 'Failed - Empty fields', '2025-04-28 00:23:49'),
(630, 'thyl', 'Login Failed', 'Account marked as \'Newly Registered\'.', '2025-04-28 00:24:26'),
(631, 'super', 'Login Failed', 'Password mismatch.', '2025-04-28 00:24:32'),
(632, 'super', 'Login Successful', 'Logged in as Super_Admin', '2025-04-28 00:24:39'),
(633, 'thyl', 'Login Successful', 'Logged in as Employee', '2025-04-28 00:24:53'),
(634, 'thyl', 'Login Failed', 'Password mismatch.', '2025-04-28 00:25:10'),
(635, 'thyl', 'Login Successful', 'Logged in as Employee', '2025-04-28 00:25:14'),
(636, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-28 00:28:20'),
(637, 'admin', 'Login Successful', 'Logged in as HR_Admin', '2025-04-28 00:28:34'),
(638, 'rodez', 'Login Successful', 'Logged in as Staff', '2025-04-28 00:28:55');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_email` varchar(50) NOT NULL,
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
(47, 'oasd@gmail.com', 'aOha', 'qwe123!@#', 'Active', 2, NULL),
(48, 'tapalesrasheed123@gmail.com', 'Wiggle', 'LINbqJZtkCEg+0UEA3+tNO/6S5Rh6YjkxNoHOtUNroI=', 'Active', 3, 1001),
(49, 'staff@gmail.com', 'staff', '12345678', 'Active', 4, NULL),
(50, 'staffni@gmail.com', 'staffni', '12345678', 'Active', 4, NULL),
(51, 'snezhyohaha@gmail.com', 'rodez', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', 'Active', 4, NULL),
(52, 'thyl@gmail.com', 'thyl', 'LINbqJZtkCEg+0UEA3+tNO/6S5Rh6YjkxNoHOtUNroI=', 'Active', 3, 1005);

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
-- Indexes for table `password_reset_tokens`
--
ALTER TABLE `password_reset_tokens`
  ADD PRIMARY KEY (`token_id`),
  ADD KEY `user_id` (`user_id`);

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
  MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1347;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `emp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1006;

--
-- AUTO_INCREMENT for table `password_reset_tokens`
--
ALTER TABLE `password_reset_tokens`
  MODIFY `token_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `rates`
--
ALTER TABLE `rates`
  MODIFY `rates_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `system_logs`
--
ALTER TABLE `system_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=639;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

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
-- Constraints for table `password_reset_tokens`
--
ALTER TABLE `password_reset_tokens`
  ADD CONSTRAINT `password_reset_tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;

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
