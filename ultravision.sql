-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2019 at 02:49 AM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ultravision`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `subscription` varchar(30) NOT NULL,
  `cardNumber` varchar(30) NOT NULL,
  `LayoutPoint` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `surname`, `email`, `phone`, `subscription`, `cardNumber`, `LayoutPoint`) VALUES
(36, 'Suelen', 'Fiorelo', 'suelen@hotmail.com', '0838599634', 'ML', '111111', 160),
(38, 'Andree', 'Campos', 'andree@campos.com', '888888888', 'PR', '999999999', 30),
(39, 'Cleiton', 'Ferraz', 'cleiton@ferrax.com', '89859856', 'VL', '65989695', 0),
(40, 'pedro', 'ferraz', 'pedro@ferrax.com', '88858585', 'VL', '8858985', 0),
(41, 'Felipe', 'SOnic', 'felie@sonic.com', '8958959', 'TV', '69856985', 10);

-- --------------------------------------------------------

--
-- Table structure for table `rent`
--

CREATE TABLE `rent` (
  `idRent` int(10) NOT NULL,
  `FK_idCustomer` int(10) DEFAULT NULL,
  `FK_idTitle` int(10) DEFAULT NULL,
  `rentDate` varchar(240) DEFAULT NULL,
  `returnDate` varchar(220) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rent`
--

INSERT INTO `rent` (`idRent`, `FK_idCustomer`, `FK_idTitle`, `rentDate`, `returnDate`) VALUES
(94, 38, 52, '2019-05-01', '2019-05-11'),
(97, 38, 49, '2019-05-11', '2019-05-11'),
(98, 41, 56, '2019-05-12', '2019-05-12'),
(99, 36, 57, ' 2019.05.12', '');

-- --------------------------------------------------------

--
-- Table structure for table `title`
--

CREATE TABLE `title` (
  `id` int(10) NOT NULL,
  `titleTypes` varchar(40) NOT NULL,
  `title` varchar(30) NOT NULL,
  `year` varchar(4) NOT NULL,
  `genre` varchar(30) NOT NULL,
  `director` varchar(30) NOT NULL,
  `band` varchar(30) NOT NULL,
  `formats` varchar(30) NOT NULL,
  `venue` varchar(30) NOT NULL,
  `ageRating` int(4) NOT NULL,
  `actors` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `title`
--

INSERT INTO `title` (`id`, `titleTypes`, `title`, `year`, `genre`, `director`, `band`, `formats`, `venue`, `ageRating`, `actors`) VALUES
(48, 'MUSIC', 'What is about us', '2016', 'rock', 'NOT REQUIRED', 'pink', 'BLU-RAY', 'NOT REQUIRED', 0, 'NOT REQUIRED'),
(49, 'LIVE CONCERT VIDEOS', 'Laura Michaels', '2000', ' Vocals', 'NOT REQUIRED', 'NOT REQUIRED', 'DVD', 'Ireland', 0, 'NOT REQUIRED'),
(52, 'MUSIC', 'Hurt', '202', 'country', 'NOT REQUIRED', 'Johnny Cash', 'BLU-RAY', 'NOT REQUIRED', 0, 'NOT REQUIRED'),
(54, 'MOVIE', 'Home Alone', '1990', 'comedia', 'Chris Columbus', 'NOT REQUIRED', 'CD', 'NOT REQUIRED', 15, 'Macaulay Culkin'),
(56, 'BOXSET', 'Modern Family', '2009', 'Comedy', 'Gail Mancuso', 'NOT REQUIRED', 'DVD', 'NOT REQUIRED', 18, 'NOT REQUIRED'),
(57, 'MUSIC', 'Perfet Duet', '2017', 'Pop', 'NOT REQUIRED', 'Ed Sheeran', 'CD', 'NOT REQUIRED', 0, 'NOT REQUIRED'),
(62, 'MUSIC', 'Africa', '2016', 'Pop', 'NOT REQUIRED', 'Shakira', 'BLU-RAY', 'NOT REQUIRED', 0, 'NOT REQUIRED'),
(63, 'MUSIC', 'You are my sunshine', '1969', 'romantic', 'NOT REQUIRED', 'Johnny Cash', 'BLU-RAY', 'NOT REQUIRED', 0, 'NOT REQUIRED'),
(64, 'LIVE CONCERT VIDEOS', 'Scott Raines', '2003', 'Guitar', 'NOT REQUIRED', 'NOT REQUIRED', 'DVD', 'London', 0, 'NOT REQUIRED'),
(65, 'MOVIE', 'Avegeres', '2019', 'Action', 'Joss Whedon', 'NOT REQUIRED', 'DVD', 'NOT REQUIRED', 12, 'Chris Evans'),
(66, 'MOVIE', 'The walking Dead', '2010', 'terror', 'Greg Nicotero', 'NOT REQUIRED', 'DVD', 'NOT REQUIRED', 15, 'ddd'),
(67, 'MUSIC', 'Teste', '1990', 'Pop', 'NOT REQUIRED', 'Teste', 'CD', 'NOT REQUIRED', 0, 'NOT REQUIRED'),
(68, 'LIVE CONCERT VIDEOS', 'Teste', '2000', 'teste', 'NOT REQUIRED', 'NOT REQUIRED', 'BLU-RAY', 'teste', 0, 'NOT REQUIRED'),
(69, 'MOVIE', 'teste', '2010', 'teste', 'teste', 'NOT REQUIRED', 'CD', 'NOT REQUIRED', 15, 'teste'),
(70, 'BOXSET', 'teste', '2016', 'teste', 'teste', 'NOT REQUIRED', 'DVD', 'NOT REQUIRED', 15, 'NOT REQUIRED');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`idRent`),
  ADD KEY `FK_idCustomer` (`FK_idCustomer`),
  ADD KEY `FK_idTitle` (`FK_idTitle`);

--
-- Indexes for table `title`
--
ALTER TABLE `title`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `rent`
--
ALTER TABLE `rent`
  MODIFY `idRent` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT for table `title`
--
ALTER TABLE `title`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `rent`
--
ALTER TABLE `rent`
  ADD CONSTRAINT `rent_ibfk_1` FOREIGN KEY (`FK_idCustomer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `rent_ibfk_2` FOREIGN KEY (`FK_idTitle`) REFERENCES `title` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
