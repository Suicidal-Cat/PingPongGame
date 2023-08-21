-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 21, 2023 at 10:42 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pingponggame`
--

-- --------------------------------------------------------

--
-- Table structure for table `matchhistory`
--

CREATE TABLE `matchhistory` (
  `matchId` int(40) NOT NULL,
  `UserName1` varchar(40) NOT NULL,
  `UserName2` varchar(40) NOT NULL,
  `Score1` int(40) NOT NULL,
  `Score2` int(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `matchhistory`
--

INSERT INTO `matchhistory` (`matchId`, `UserName1`, `UserName2`, `Score1`, `Score2`) VALUES
(1, 'ana', 'andj712', 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `tblogin`
--

CREATE TABLE `tblogin` (
  `UserName` varchar(10) NOT NULL,
  `Password` varchar(40) DEFAULT NULL,
  `gmail` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tblogin`
--

INSERT INTO `tblogin` (`UserName`, `Password`, `gmail`) VALUES
('aki', 'aki123', 'aki@gmail.com'),
('ana', 'ana123', 'ana@gmail.com'),
('andj712', 'andj712', 'andj712@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `matchhistory`
--
ALTER TABLE `matchhistory`
  ADD PRIMARY KEY (`matchId`,`UserName1`,`UserName2`);

--
-- Indexes for table `tblogin`
--
ALTER TABLE `tblogin`
  ADD PRIMARY KEY (`UserName`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `matchhistory`
--
ALTER TABLE `matchhistory`
  MODIFY `matchId` int(40) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
