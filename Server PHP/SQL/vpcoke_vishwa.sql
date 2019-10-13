-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 13, 2019 at 09:11 AM
-- Server version: 10.2.27-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vpcoke_vishwa`
--

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

CREATE TABLE `articles` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `content` text NOT NULL,
  `category` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `articles`
--

INSERT INTO `articles` (`id`, `title`, `content`, `category`) VALUES
(1, 'A Prayer to get a hassle-free job or business', 'uttama udyogavannu padeyuvadakkagi mattu udyogadallina kirikiri samasyegala pariharakkagi madabekada prarthane.', 'Stotragala Artha'),
(2, 'Is Darvin theory acceptable?', 'uttama udyogavannu padeyuvadakkagi mattu udyogadal...', 'Tatva Surabhi'),
(3, 'A Prayer to get a hassle-free job or business', '\"Matsya jalacara, kurma ubhayacara, varaha sthalac...', 'Navaratri');

-- --------------------------------------------------------

--
-- Table structure for table `info`
--

CREATE TABLE `info` (
  `Id` int(100) NOT NULL,
  `head` varchar(100) NOT NULL,
  `articles` varchar(100) NOT NULL,
  `upanasyas` varchar(100) NOT NULL,
  `prashnauttaras` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `info`
--

INSERT INTO `info` (`Id`, `head`, `articles`, `upanasyas`, `prashnauttaras`) VALUES
(2, 'Stotragala Artha', '1', '8', '9'),
(3, 'Tatva Surabhi', '7', '8', '10'),
(4, 'Navaratri', '11', '12', '13');

-- --------------------------------------------------------

--
-- Table structure for table `notify`
--

CREATE TABLE `notify` (
  `notifyId` int(100) NOT NULL,
  `notifyTitle` varchar(100) NOT NULL,
  `notifyContent` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `prashnauttaras`
--

CREATE TABLE `prashnauttaras` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `content` text NOT NULL,
  `category` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prashnauttaras`
--

INSERT INTO `prashnauttaras` (`id`, `title`, `content`, `category`) VALUES
(1, 'Is Darvin theory acceptable?', 'uttama udyogavannu padeyuvadakkagi mattu udyogadallina kirikiri samasyegala pariharakkagi madabekada prarthane.', 'Stotragala Artha'),
(2, 'A Prayer to get a hassle-free job or business', 'uttama udyogavannu padeyuvadakkagi mattu udyogadal...', 'Tatva Surabhi'),
(3, 'A Prayer to get a hassle-free job or business', '\"Matsya jalacara, kurma ubhayacara, varaha sthalac...', 'Navaratri');

-- --------------------------------------------------------

--
-- Table structure for table `tblcomments`
--

CREATE TABLE `tblcomments` (
  `id` int(11) NOT NULL,
  `postId` char(11) DEFAULT NULL,
  `name` varchar(120) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `comment` mediumtext DEFAULT NULL,
  `postingDate` timestamp NULL DEFAULT current_timestamp(),
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblcomments`
--

INSERT INTO `tblcomments` (`id`, `postId`, `name`, `email`, `comment`, `postingDate`, `status`) VALUES
(1, '12', 'Anuj', 'anuj@gmail.com', 'Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.', '2018-11-21 11:06:22', 0),
(2, '12', 'Test user', 'test@gmail.com', 'This is sample text for testing.', '2018-11-21 11:25:56', 1),
(3, '7', 'ABC', 'abc@test.com', 'This is sample text for testing.', '2018-11-21 11:27:06', 1);

-- --------------------------------------------------------

--
-- Table structure for table `upanasyas`
--

CREATE TABLE `upanasyas` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `content` text NOT NULL,
  `category` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `upanasyas`
--

INSERT INTO `upanasyas` (`id`, `title`, `content`, `category`) VALUES
(1, 'A Prayer to get a hassle-free job or business', 'uttama udyogavannu padeyuvadakkagi mattu udyogadallina kirikiri samasyegala pariharakkagi madabekada prarthane.', 'Stotragala Artha'),
(2, 'Is Darvin theory acceptable?', '\"Matsya jalacara, kurma ubhayacara, varaha sthalacara, narasinha ardhamrga, ardhapurusa, vamana purna beleyada kulla, parasurama oratada manusya, buddha buddhi iruva manusys, kalki ellarannu kondu hakuvavanu\"', 'Tatva Surabhi'),
(3, 'A Prayer to get a hassle-free job or business', 'uttama udyogavannu padeyuvadakkagi mattu udyogadallina kirikiri samasyegala pariharakkagi madabekada prarthane.', 'Navaratri');

-- --------------------------------------------------------

--
-- Table structure for table `user_registration`
--

CREATE TABLE `user_registration` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `mobile` text NOT NULL,
  `city` text NOT NULL,
  `email` text NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_registration`
--

INSERT INTO `user_registration` (`id`, `name`, `mobile`, `city`, `email`, `password`) VALUES
(3, 'Subrata Malik', '8777588314', 'Arambagh', 'subratamalik1997@gmail.com', 'abc'),
(4, 'arnab', '9007294669', 'kolkata', 'sahaarnab@gmail.com', 'def');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `info`
--
ALTER TABLE `info`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `notify`
--
ALTER TABLE `notify`
  ADD PRIMARY KEY (`notifyId`);

--
-- Indexes for table `prashnauttaras`
--
ALTER TABLE `prashnauttaras`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tblcomments`
--
ALTER TABLE `tblcomments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `upanasyas`
--
ALTER TABLE `upanasyas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_registration`
--
ALTER TABLE `user_registration`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articles`
--
ALTER TABLE `articles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `info`
--
ALTER TABLE `info`
  MODIFY `Id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `notify`
--
ALTER TABLE `notify`
  MODIFY `notifyId` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `prashnauttaras`
--
ALTER TABLE `prashnauttaras`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tblcomments`
--
ALTER TABLE `tblcomments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `upanasyas`
--
ALTER TABLE `upanasyas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user_registration`
--
ALTER TABLE `user_registration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
