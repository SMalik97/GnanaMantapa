-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 06, 2019 at 01:01 PM
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
-- Indexes for table `prashnauttaras`
--
ALTER TABLE `prashnauttaras`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `upanasyas`
--
ALTER TABLE `upanasyas`
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
-- AUTO_INCREMENT for table `prashnauttaras`
--
ALTER TABLE `prashnauttaras`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `upanasyas`
--
ALTER TABLE `upanasyas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
