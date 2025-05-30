-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2025 at 11:19 PM
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
  `absent` tinyint(1) NOT NULL
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
  `filePath` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`emp_id`, `emp_fname`, `emp_middle`, `emp_lname`, `emp_age`, `emp_sex`, `emp_add`, `emp_email`, `emp_contact`, `emp_hdate`, `emp_dept`, `emp_position`, `filePath`) VALUES
(1001, 'Rasheed', '', 'Tapales', 20, 'Male', 'Tinubdan, San Fernando, Cebu', 'tapalesrasheed123@gmail.com', '09456989966', '2025-03-01', 'House Keeping', 'Room Attendant', NULL),
(1002, 'Rodeliza', '', 'La Rosa', 20, 'Female', 'Ward IV, Minglanilla, Cebu', 'rodeliza@gmail.com', '09432517890', '2025-03-02', 'Human Resources', 'Finance Manager', NULL),
(1003, 'John', '', 'Doe', 20, 'Male', 'Naga City, Cebu', 'doejohn@gmail.com', '09458697201', '2025-03-03', 'Front Office', 'Receptionist', NULL),
(1004, 'Ellen', '', 'Shark', 20, 'Female', 'Zenless Zone City', 'tapalesrasheed123@gmail.com', '09347689167', '2025-03-15', 'IT', 'IT Manager', NULL),
(1005, 'Raiden', '', 'Shogun', 20, 'Female', 'Inazuma City', 'raiden@gmail.com', '09872819281', '2025-03-10', 'Maintenance', 'Chief Engineer', NULL),
(1006, 'Erl Bryan', '', 'Cabizares', 20, 'Male', 'Langtad, Naga City, Cebu', 'bryan@gmail.com', '09235128898', '2025-03-15', 'IT', 'System Administrator', NULL),
(1007, 'wa na', '', 'ko', 20, 'Male', 'Naga City', 'wana@gmail.com', '09748295981', '2025-03-01', 'Front Office', 'Front Office Manager', 'src/GUI/images/Emp/09c0eddb-1373-422b-b709-ef871a17eb5d.jpg');

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
(1, 'test_user', 'test_action', 'test_details', '2025-03-25 23:36:51');

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
-- Table structure for table `password_reset_tokens`
--

CREATE TABLE `password_reset_tokens` (
  `token_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `token` varchar(6) NOT NULL,
  `expiry_time` datetime NOT NULL,
  `is_used` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`token_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `password_reset_tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dailytimerecords`
--
ALTER TABLE `