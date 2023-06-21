-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 17, 2023 at 02:23 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mental_health`
--

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `name` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `contact` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`name`, `email`, `password`, `contact`) VALUES
('wahid', 'wahid@gmail.com', '123456', '11'),
('Pinky', 'pinky@gmail.com', '789456123', '01402104895');

-- --------------------------------------------------------

--
-- Table structure for table `userthoughts`
--

CREATE TABLE `userthoughts` (
  `email` varchar(200) DEFAULT NULL,
  `thoughts` varchar(200) DEFAULT NULL,
  `time` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `userthoughts`
--

INSERT INTO `userthoughts` (`email`, `thoughts`, `time`) VALUES
('wahid@gmail.com', 'Checking ', '1671087030'),
('wahid@gmail.com', '', '1671087736'),
('wahid@gmail.com', '', '1671087740'),
('wahid@gmail.com', 'Still trying', '2022/12/15'),
('wahid@gmail.com', 'final', '15-12-22 08:07:51'),
('wahid@gmail.com', '', '22-12-22 06:42:40');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
