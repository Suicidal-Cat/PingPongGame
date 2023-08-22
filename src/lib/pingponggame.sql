-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2023 at 11:14 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

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
  `Score2` int(40) NOT NULL,
  `GameMode` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `matchhistory`
--

INSERT INTO `matchhistory` (`matchId`, `UserName1`, `UserName2`, `Score1`, `Score2`, `GameMode`) VALUES
(4, 'ana', 'aki', 1, 6, 'Classic'),
(8, 'guest', 'ana', 6, 5, 'Advanced'),
(9, 'aki', 'ana', 7, 2, 'Advanced');

-- --------------------------------------------------------

--
-- Table structure for table `tblogin`
--

CREATE TABLE `tblogin` (
  `UserName` varchar(10) NOT NULL,
  `Password` varchar(40) DEFAULT NULL,
  `gmail` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblogin`
--

INSERT INTO `tblogin` (`UserName`, `Password`, `gmail`) VALUES
('aki', 'aki123', 'aki@gmail.com'),
('ana', 'ana123', 'ana@gmail.com'),
('andj712', 'andj712', 'andj712@gmail.com'),
('guest', 'guest', 'gues@guest.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `matchhistory`
--
ALTER TABLE `matchhistory`
  ADD PRIMARY KEY (`matchId`,`UserName1`,`UserName2`),
  ADD KEY `UserName1` (`UserName1`),
  ADD KEY `UserName2` (`UserName2`);

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
  MODIFY `matchId` int(40) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `matchhistory`
--
ALTER TABLE `matchhistory`
  ADD CONSTRAINT `matchhistory_ibfk_1` FOREIGN KEY (`UserName1`) REFERENCES `tblogin` (`UserName`) ON UPDATE CASCADE,
  ADD CONSTRAINT `matchhistory_ibfk_2` FOREIGN KEY (`UserName2`) REFERENCES `tblogin` (`UserName`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
