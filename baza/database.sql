-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Czas wygenerowania: 13 Gru 2015, 09:39
-- Wersja serwera: 5.6.23
-- Wersja PHP: 5.5.21-1~dotdeb.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `inf_pz`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `assessments`
--

CREATE TABLE IF NOT EXISTS `assessments` (
  `id_assessment` int(11) NOT NULL AUTO_INCREMENT,
  `volume` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL,
  PRIMARY KEY (`id_assessment`),
  KEY `member_id` (`member_id`),
  KEY `recipe_id` (`recipe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `contents` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `recipe_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `recipe_id` (`recipe_id`),
  KEY `member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `components`
--

CREATE TABLE IF NOT EXISTS `components` (
  `id_component` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_component`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `components_volume`
--

CREATE TABLE IF NOT EXISTS `components_volume` (
  `recipe_id` int(11) NOT NULL,
  `component_id` int(11) NOT NULL,
  `volume` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  KEY `recipe_id` (`recipe_id`),
  KEY `component_id` (`component_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `id_group` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `permissions` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `id_member` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `display_name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `password_h` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `from` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `validate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register_date` date NOT NULL,
  `last_visit` date NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id_member`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `photos`
--

CREATE TABLE IF NOT EXISTS `photos` (
  `id_photo` int(11) NOT NULL AUTO_INCREMENT,
  `commenrts` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recipe_id` int(11) NOT NULL,
  PRIMARY KEY (`id_photo`),
  KEY `recipe_id` (`recipe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `recipes`
--

CREATE TABLE IF NOT EXISTS `recipes` (
  `id_recipe` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `text` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id_recipe`),
  KEY `member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reports`
--

CREATE TABLE IF NOT EXISTS `reports` (
  `id_report` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) DEFAULT '0',
  `recipe_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id_report`),
  KEY `recipe_id` (`recipe_id`),
  KEY `member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `assessments`
--
ALTER TABLE `assessments`
  ADD CONSTRAINT `assessments_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`id_member`),
  ADD CONSTRAINT `assessments_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id_recipe`);

--
-- Ograniczenia dla tabeli `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id_recipe`),
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `members` (`id_member`);

--
-- Ograniczenia dla tabeli `components_volume`
--
ALTER TABLE `components_volume`
  ADD CONSTRAINT `components_volume_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id_recipe`),
  ADD CONSTRAINT `components_volume_ibfk_2` FOREIGN KEY (`component_id`) REFERENCES `components` (`id_component`);

--
-- Ograniczenia dla tabeli `members`
--
ALTER TABLE `members`
  ADD CONSTRAINT `members_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id_group`);

--
-- Ograniczenia dla tabeli `photos`
--
ALTER TABLE `photos`
  ADD CONSTRAINT `photos_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id_recipe`);

--
-- Ograniczenia dla tabeli `recipes`
--
ALTER TABLE `recipes`
  ADD CONSTRAINT `recipes_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`id_member`);

--
-- Ograniczenia dla tabeli `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id_recipe`),
  ADD CONSTRAINT `reports_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `members` (`id_member`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
