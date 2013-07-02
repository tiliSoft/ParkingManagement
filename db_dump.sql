-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 02, 2013 at 01:30 AM
-- Server version: 5.1.44
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `parking302`
--

-- --------------------------------------------------------

--
-- Table structure for table `calc_fut`
--

CREATE TABLE IF NOT EXISTS `calc_fut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `media` double(11,2) DEFAULT NULL,
  `top` double(11,2) NOT NULL DEFAULT '0.00',
  `d_from` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `d_to` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `calc_fut`
--

INSERT INTO `calc_fut` (`id`, `media`, `top`, `d_from`, `d_to`) VALUES
(1, 80.00, 43333.00, '01/04/2013', '30/04/2013');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `phone` varchar(128) COLLATE utf8_unicode_ci DEFAULT '',
  `mobile` varchar(128) COLLATE utf8_unicode_ci DEFAULT '',
  `afm` varchar(128) COLLATE utf8_unicode_ci DEFAULT '',
  `doy` varchar(128) COLLATE utf8_unicode_ci DEFAULT '',
  `address` varchar(256) COLLATE utf8_unicode_ci DEFAULT '',
  `type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  UNIQUE KEY `name` (`name`,`phone`,`mobile`),
  UNIQUE KEY `name_2` (`name`,`phone`,`mobile`),
  UNIQUE KEY `name_3` (`name`,`phone`,`mobile`),
  UNIQUE KEY `name_4` (`name`,`phone`,`mobile`,`afm`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `client`
--


-- --------------------------------------------------------

--
-- Table structure for table `contract`
--

CREATE TABLE IF NOT EXISTS `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_posto` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `prezzo` double(11,2) NOT NULL DEFAULT '0.00',
  `data_ricevutta` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `aa` int(11) NOT NULL DEFAULT '0',
  `user` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dal` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `al` timestamp NULL DEFAULT NULL,
  `extra` int(11) NOT NULL DEFAULT '0',
  `why` varchar(1024) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  KEY `id_client` (`id_client`),
  KEY `id_posto_2` (`id_posto`),
  KEY `user` (`user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `contract`
--


-- --------------------------------------------------------

--
-- Table structure for table `contract_vehicles`
--

CREATE TABLE IF NOT EXISTS `contract_vehicles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_contract` int(11) NOT NULL,
  `id_vehicle` int(11) NOT NULL,
  `first` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  UNIQUE KEY `id_contract` (`id_contract`,`id_vehicle`),
  UNIQUE KEY `id_contract_3` (`id_contract`,`id_vehicle`),
  KEY `id_contract_2` (`id_contract`),
  KEY `id_vehicle` (`id_vehicle`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `contract_vehicles`
--


-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `log` varchar(4096) COLLATE utf8_unicode_ci NOT NULL,
  `sessionid` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=13 ;

--
-- Dumping data for table `log`
--


-- --------------------------------------------------------

--
-- Table structure for table `log_session`
--

CREATE TABLE IF NOT EXISTS `log_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sessionid` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enter_datatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `exit_datatime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ip` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `info` varchar(2048) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`sessionid`),
  UNIQUE KEY `sessionid` (`sessionid`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  KEY `id` (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `log_session`
--


-- --------------------------------------------------------

--
-- Table structure for table `posto`
--

CREATE TABLE IF NOT EXISTS `posto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `piano` int(128) NOT NULL,
  `number` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `space` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  UNIQUE KEY `piano` (`piano`,`number`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=296 ;

--
-- Dumping data for table `posto`
--

INSERT INTO `posto` (`id`, `piano`, `number`, `space`) VALUES
(0, -1, 'UNDERGROUND', 48),
(1, 0, 'GROUND', 6),
(2, 1, '101', 1),
(3, 1, '102', 1),
(4, 1, '103', 1),
(5, 1, '104', 1),
(6, 1, '105', 1),
(7, 1, '106', 1),
(8, 1, '107A', 1),
(9, 1, '107B', 1),
(10, 1, '108', 1),
(11, 1, '109', 1),
(12, 1, '110', 1),
(13, 1, '111', 1),
(14, 1, '112', 1),
(15, 1, '113', 1),
(16, 1, '114', 1),
(17, 1, '115', 1),
(18, 1, '116', 1),
(19, 1, '117', 1),
(20, 1, '118', 1),
(21, 1, '119', 1),
(22, 1, '120', 1),
(23, 1, '121', 1),
(24, 1, '122', 1),
(25, 1, '123', 1),
(26, 1, '124', 1),
(27, 1, '125', 1),
(28, 1, '126', 1),
(29, 1, '127', 1),
(30, 1, '128', 1),
(31, 1, '129', 1),
(32, 1, '130', 1),
(33, 1, '131', 1),
(34, 1, '132', 1),
(35, 1, '133', 1),
(36, 1, '134', 1),
(37, 1, '135', 1),
(38, 1, '136', 1),
(39, 1, '137', 1),
(40, 1, '138', 1),
(41, 1, '139', 1),
(42, 1, '140', 1),
(43, 2, '201', 1),
(44, 2, '202', 1),
(45, 2, '203', 1),
(46, 2, '204', 1),
(47, 2, '205', 1),
(48, 2, '206', 1),
(49, 2, '207A', 1),
(50, 2, '207B', 1),
(51, 2, '208', 1),
(52, 2, '209', 1),
(53, 2, '210', 1),
(54, 2, '211', 1),
(55, 2, '212', 1),
(56, 2, '213', 1),
(57, 2, '214', 1),
(58, 2, '215', 1),
(59, 2, '216', 1),
(60, 2, '217', 1),
(61, 2, '218', 1),
(62, 2, '219', 1),
(63, 2, '220', 1),
(64, 2, '221', 1),
(65, 2, '222', 1),
(66, 2, '223', 1),
(67, 2, '224', 1),
(68, 2, '225', 1),
(69, 2, '226', 1),
(70, 2, '227', 1),
(71, 2, '228', 1),
(72, 2, '229', 1),
(73, 2, '230', 1),
(74, 2, '231', 1),
(75, 2, '232', 1),
(76, 2, '233', 1),
(77, 2, '234', 1),
(78, 2, '235', 1),
(79, 2, '236', 1),
(80, 2, '237', 1),
(81, 2, '238', 1),
(82, 2, '239', 1),
(83, 2, '240', 1),
(84, 2, '241', 1),
(85, 2, '242', 1),
(86, 3, '301', 1),
(87, 3, '302', 1),
(88, 3, '303', 1),
(89, 3, '304', 1),
(90, 3, '305', 1),
(91, 3, '306', 1),
(92, 3, '307A', 1),
(93, 3, '307B', 1),
(94, 3, '308', 1),
(95, 3, '309', 1),
(96, 3, '310', 1),
(97, 3, '311', 1),
(98, 3, '312', 1),
(99, 3, '313', 1),
(100, 3, '314', 1),
(101, 3, '315', 1),
(102, 3, '316', 1),
(103, 3, '317', 1),
(104, 3, '318', 1),
(105, 3, '319', 1),
(106, 3, '320', 1),
(107, 3, '321', 1),
(108, 3, '322', 1),
(109, 3, '323', 1),
(110, 3, '324', 1),
(111, 3, '325', 1),
(112, 3, '326', 1),
(113, 3, '327', 1),
(114, 3, '328', 1),
(115, 3, '329', 1),
(116, 3, '330', 1),
(117, 3, '331', 1),
(118, 3, '332', 1),
(119, 3, '333', 1),
(120, 3, '334', 1),
(121, 3, '335', 1),
(122, 3, '336', 1),
(123, 3, '337', 1),
(124, 3, '338', 1),
(125, 3, '339', 1),
(126, 3, '340', 1),
(127, 3, '341', 1),
(128, 3, '342', 1),
(129, 4, '401', 1),
(130, 4, '402', 1),
(131, 4, '403', 1),
(132, 4, '404', 1),
(133, 4, '405', 1),
(134, 4, '406', 1),
(135, 4, '407A', 1),
(136, 4, '407B', 1),
(137, 4, '408', 1),
(138, 4, '409', 1),
(139, 4, '410', 1),
(140, 4, '411', 1),
(141, 4, '412', 1),
(142, 4, '413', 1),
(143, 4, '414', 1),
(144, 4, '415', 1),
(145, 4, '416', 1),
(146, 4, '417', 1),
(147, 4, '418', 1),
(148, 4, '419', 1),
(149, 4, '420', 1),
(150, 4, '421', 1),
(151, 4, '422', 1),
(152, 4, '423', 1),
(153, 4, '424', 1),
(154, 4, '425', 1),
(155, 4, '426', 1),
(156, 4, '427', 1),
(157, 4, '428', 1),
(158, 4, '429', 1),
(159, 4, '430', 1),
(160, 4, '431', 1),
(161, 4, '432', 1),
(162, 4, '433', 1),
(163, 4, '434', 1),
(164, 4, '435', 1),
(165, 4, '436', 1),
(166, 4, '437', 1),
(167, 4, '438', 1),
(168, 4, '439', 1),
(169, 4, '440', 1),
(170, 4, '441', 1),
(171, 4, '442', 1),
(172, 5, '501', 1),
(173, 5, '502', 1),
(174, 5, '503', 1),
(175, 5, '504', 1),
(176, 5, '505', 1),
(177, 5, '506', 1),
(178, 5, '507A', 1),
(179, 5, '507B', 1),
(180, 5, '508', 1),
(181, 5, '509', 1),
(182, 5, '510', 1),
(183, 5, '511', 1),
(184, 5, '512', 1),
(185, 5, '513', 1),
(186, 5, '514', 1),
(187, 5, '515', 1),
(188, 5, '516', 1),
(189, 5, '517', 1),
(190, 5, '518', 1),
(191, 5, '519', 1),
(192, 5, '520', 1),
(193, 5, '521', 1),
(194, 5, '522', 1),
(195, 5, '523', 1),
(196, 5, '524', 1),
(197, 5, '525', 1),
(198, 5, '526', 1),
(199, 5, '527', 1),
(200, 5, '528', 1),
(201, 5, '529', 1),
(202, 5, '530', 1),
(203, 5, '531', 1),
(204, 5, '532', 1),
(205, 5, '533', 1),
(206, 5, '534', 1),
(207, 5, '535', 1),
(208, 5, '536', 1),
(209, 5, '537', 1),
(210, 5, '538', 1),
(211, 5, '539', 1),
(212, 5, '540', 1),
(213, 5, '541', 1),
(214, 5, '542', 1),
(215, 6, '601', 1),
(216, 6, '602', 1),
(217, 6, '603', 1),
(218, 6, '604', 1),
(219, 6, '605', 1),
(220, 6, '606', 1),
(221, 6, '607A', 1),
(222, 6, '607B', 1),
(223, 6, '608', 1),
(224, 6, '609', 1),
(225, 6, '610', 1),
(226, 6, '611', 1),
(227, 6, '612', 1),
(228, 6, '613', 1),
(229, 6, '614', 1),
(230, 6, '615', 1),
(231, 6, '616', 1),
(232, 6, '617', 1),
(233, 6, '618', 1),
(234, 6, '619', 1),
(235, 6, '620', 1),
(236, 6, '621', 1),
(237, 6, '622', 1),
(238, 6, '623', 1),
(239, 6, '624', 1),
(240, 6, '625', 1),
(241, 6, '626', 1),
(242, 6, '627', 1),
(243, 6, '628', 1),
(244, 6, '629', 1),
(245, 6, '630', 1),
(246, 6, '631', 1),
(247, 6, '632', 1),
(248, 6, '633', 1),
(249, 6, '634', 1),
(250, 6, '635', 1),
(251, 6, '636', 1),
(252, 6, '637', 1),
(253, 6, '638', 1),
(254, 6, '639', 1),
(255, 6, '640', 1),
(256, 6, '641', 1),
(257, 6, '642', 1),
(258, 7, '701', 1),
(259, 7, '702', 1),
(260, 7, '703', 1),
(261, 7, '704', 1),
(262, 7, '705', 1),
(263, 7, '706', 1),
(264, 7, '707', 1),
(265, 7, '708', 1),
(266, 7, '709', 1),
(267, 7, '710', 1),
(268, 7, '711', 1),
(269, 7, '712', 1),
(270, 7, '713', 1),
(271, 7, '714', 1),
(272, 7, '715', 1),
(273, 7, '716', 1),
(274, 7, '717', 1),
(275, 7, '718', 1),
(276, 7, '719', 1),
(277, 7, '720', 1),
(278, 7, '721A', 1),
(279, 7, '721B', 1),
(280, 7, '722', 1),
(281, 7, '723', 1),
(282, 7, '724', 1),
(283, 7, '725', 1),
(284, 7, '726', 1),
(285, 7, '727', 1),
(286, 7, '728', 1),
(287, 7, '729', 1),
(288, 7, '730', 1),
(289, 7, '731', 1),
(290, 7, '732', 1),
(291, 7, '733', 1),
(292, 7, '734', 1),
(293, 7, '735', 1),
(294, 7, 'RAMP UP B', 1),
(295, 7, 'RAMP DN A', 1);

-- --------------------------------------------------------

--
-- Table structure for table `scontrino`
--

CREATE TABLE IF NOT EXISTS `scontrino` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_contract` int(11) DEFAULT NULL,
  `prezzo` double(11,2) NOT NULL DEFAULT '0.00',
  `start` date NOT NULL,
  `end` date NOT NULL,
  `data_pagamento` date DEFAULT NULL,
  `user` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `aa` int(11) NOT NULL DEFAULT '0',
  `af` int(11) NOT NULL DEFAULT '0',
  `ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `bank` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  KEY `id_contract` (`id_contract`),
  KEY `user` (`user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `scontrino`
--


-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` int(11) NOT NULL DEFAULT '0',
  `admin` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=31 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `name`, `surname`, `enabled`, `admin`) VALUES
(21, 'root', 'toor', 'Sotiraki', 'Sima', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `vehicles`
--

CREATE TABLE IF NOT EXISTS `vehicles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number_plate` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `model` varchar(256) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `type` int(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_plate` (`number_plate`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  KEY `id` (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `vehicles`
--


-- --------------------------------------------------------

--
-- Table structure for table `vehicles_type`
--

CREATE TABLE IF NOT EXISTS `vehicles_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_2` (`id`),
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `vehicles_type`
--

INSERT INTO `vehicles_type` (`id`, `type`) VALUES
(1, 'Auto'),
(2, 'Moto'),
(3, 'Bike');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `contract_ibfk_3` FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `contract_ibfk_4` FOREIGN KEY (`id_posto`) REFERENCES `posto` (`id`),
  ADD CONSTRAINT `contract_ibfk_5` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`);

--
-- Constraints for table `contract_vehicles`
--
ALTER TABLE `contract_vehicles`
  ADD CONSTRAINT `contract_vehicles_ibfk_3` FOREIGN KEY (`id_contract`) REFERENCES `contract` (`id`),
  ADD CONSTRAINT `contract_vehicles_ibfk_4` FOREIGN KEY (`id_vehicle`) REFERENCES `vehicles` (`id`);

--
-- Constraints for table `log_session`
--
ALTER TABLE `log_session`
  ADD CONSTRAINT `log_session_ibfk_4` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `scontrino`
--
ALTER TABLE `scontrino`
  ADD CONSTRAINT `scontrino_ibfk_4` FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE SET NULL ON UPDATE NO ACTION,
  ADD CONSTRAINT `scontrino_ibfk_5` FOREIGN KEY (`id_contract`) REFERENCES `contract` (`id`);

--
-- Constraints for table `vehicles`
--
ALTER TABLE `vehicles`
  ADD CONSTRAINT `vehicles_ibfk_2` FOREIGN KEY (`type`) REFERENCES `vehicles_type` (`id`) ON UPDATE NO ACTION;
