-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Már 23. 09:31
-- Kiszolgáló verziója: 10.4.17-MariaDB
-- PHP verzió: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `icms`
--
CREATE DATABASE IF NOT EXISTS `icms` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;
USE `icms`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `address_table`
--

CREATE TABLE `address_table` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `other` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `postal_code` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `address_table`
--

INSERT INTO `address_table` (`id`, `address`, `other`, `postal_code`) VALUES
(9, 'Minta utca 956.', '', '4400'),
(22, 'hrsz. 124', '', '1101'),
(25, 'Ipari park 1/d', '', '4400'),
(30, 'Keskeny utca 56/a II emelet. 12. lakás', '', '3801'),
(33, 'Minta utca 12.', '', '9364');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `city_table`
--

CREATE TABLE `city_table` (
  `postal_code` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `city_name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `city_table`
--

INSERT INTO `city_table` (`postal_code`, `city_name`) VALUES
('1101', 'Budapest'),
('3801', 'Város'),
('4400', 'Nyíregyháza'),
('9364', 'Cirák');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `colleague_table`
--

CREATE TABLE `colleague_table` (
  `id` bigint(20) NOT NULL,
  `custom_id` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `other` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `position` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `qualifications` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `registration_date` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `working_area` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `colleague_table`
--

INSERT INTO `colleague_table` (`id`, `custom_id`, `email`, `name`, `other`, `phone_number`, `position`, `qualifications`, `registration_date`, `working_area`) VALUES
(10, 'ME124', 'togemud@mailinator.com', 'Preston Key', '-', '+1 (901) 361-5171', 'szervíztechnikus', 'elektronikai mérnök', '2023-03-14', '-'),
(11, 'B956', 'rukorot@mailinator.com', 'Angela Todd', '-', '+1 (326) 675-6008', 'szervízvezető', 'informatikai mérnök, közgazdasági ismeretek', '2020-12-10', '-');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `company_table`
--

CREATE TABLE `company_table` (
  `id` bigint(20) NOT NULL,
  `address_id` int(11) NOT NULL,
  `address_id2` int(11) DEFAULT NULL,
  `address_id3` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `finance_id` int(11) DEFAULT NULL,
  `modification_date` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `other` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `company_table`
--

INSERT INTO `company_table` (`id`, `address_id`, `address_id2`, `address_id3`, `name`, `email`, `finance_id`, `modification_date`, `other`, `phone_number`) VALUES
(1, 9, 0, 0, 'ICMS Limited Company', 'email@icms.hu', 8, '2023-03-22', '-', '06 42 / 111 - 111');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `customers_table`
--

CREATE TABLE `customers_table` (
  `id` bigint(20) NOT NULL,
  `address_id` int(11) NOT NULL,
  `address_id2` int(11) DEFAULT NULL,
  `address_id3` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `finance_id` int(11) DEFAULT NULL,
  `other` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `registration_date` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `customers_table`
--

INSERT INTO `customers_table` (`id`, `address_id`, `address_id2`, `address_id3`, `name`, `email`, `finance_id`, `other`, `phone_number`, `registration_date`) VALUES
(29, 30, 0, 0, 'Ina Kirby', 'cupugasiq@mailinator.com', 28, 'telefonon értesítendő', '+1 (706) 608-8484', '2023-03-22'),
(32, 33, 0, 0, 'Autóbolt Bt.', 'gukecoj@mailinator.com', 31, 'Az elkészült terméket mindig postázni kell.', '+1 (577) 332-2108', '2023-03-22');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `dealer_table`
--

CREATE TABLE `dealer_table` (
  `id` bigint(20) NOT NULL,
  `address_id` int(11) NOT NULL,
  `address_id2` int(11) DEFAULT NULL,
  `address_id3` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `finance_id` int(11) DEFAULT NULL,
  `other` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `dealer_table`
--

INSERT INTO `dealer_table` (`id`, `address_id`, `address_id2`, `address_id3`, `name`, `email`, `finance_id`, `other`, `phone_number`) VALUES
(21, 22, 0, 0, 'Elektromos Nagykereskedelem', 'rycupyrime@mailinator.com', 20, 'csak 25 darab felett', '+1 (838) 533-8494'),
(24, 25, 0, 0, 'Alkatrész Bolt Kft.', 'dowid@mailinator.com', 23, '-', '+1 (162) 433-7704');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `fault_table`
--

CREATE TABLE `fault_table` (
  `id` bigint(20) NOT NULL,
  `fault_name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `fault_other` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `fault_table`
--

INSERT INTO `fault_table` (`id`, `fault_name`, `fault_other`) VALUES
(12, 'bevizsgálásra beszálítva', ''),
(13, 'törött alkatrész', ''),
(14, 'nem kapcsol be', ''),
(15, 'müködés során recsegő hangot ad ki', '');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `finance_table`
--

CREATE TABLE `finance_table` (
  `id` bigint(20) NOT NULL,
  `bank_account_number` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `bank_account_number_international` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL,
  `eu_vat_number` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `vat_number` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `finance_table`
--

INSERT INTO `finance_table` (`id`, `bank_account_number`, `bank_account_number_international`, `eu_vat_number`, `vat_number`) VALUES
(8, '00000000-00000000-00000000', 'HU52-00000000-00000000-00000000', 'HU55555555', '12345678-2-10'),
(20, '00000000-00000000-00000000', 'HU52-00000000-00000000-00000000', 'HU55555555', '12345678-2-10'),
(23, '00000000-00000000-00000000', 'HU52-00000000-00000000-00000000', 'HU55555555', '12345678-2-10'),
(28, '', '', '', ''),
(31, '00000000-00000000-00000000', 'HU52-00000000-00000000-00000000', 'HU55555555', '12345678-2-10');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) UNSIGNED NOT NULL,
  `cycle_option` tinyint(1) UNSIGNED NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB;

--
-- A tábla adatainak kiíratása `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
(1001, 1, 9223372036854775806, 1, 1, 1000, 0, 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `objects_table`
--

CREATE TABLE `objects_table` (
  `id` bigint(20) NOT NULL,
  `customerid` int(11) NOT NULL,
  `item_brand` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `item_name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `item_serial` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `item_type` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `other` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `objects_table`
--

INSERT INTO `objects_table` (`id`, `customerid`, `item_brand`, `item_name`, `item_serial`, `item_type`, `other`) VALUES
(34, 29, 'DELL', 'aio számítógép', '56648465456', 'PC', ''),
(35, 32, 'Kyocera', 'Kyocera FS2135', '254545584', 'Fénymásoló', '');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `products_table`
--

CREATE TABLE `products_table` (
  `id` bigint(20) NOT NULL,
  `custom_id` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `dealer_id` int(11) NOT NULL,
  `other` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `price_b` double NOT NULL,
  `price_n` double NOT NULL,
  `price_sell_b` double NOT NULL,
  `price_sell_n` double NOT NULL,
  `product_name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `vat` int(11) NOT NULL,
  `vat_sell` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `products_table`
--

INSERT INTO `products_table` (`id`, `custom_id`, `dealer_id`, `other`, `price_b`, `price_n`, `price_sell_b`, `price_sell_n`, `product_name`, `vat`, `vat_sell`) VALUES
(26, '11050505056', 21, '-', 2500, 1969, 4500, 3543, 'ventilátor 12v ', 27, 27),
(27, '69596525', 24, '', 7399, 5826, 8599, 6771, 'külső burkolat', 27, 27);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `services_table`
--

CREATE TABLE `services_table` (
  `id` bigint(20) NOT NULL,
  `custom_id` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `price_b` double NOT NULL,
  `price_n` double NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `vat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `services_table`
--

INSERT INTO `services_table` (`id`, `custom_id`, `price_b`, `price_n`, `name`, `vat`) VALUES
(16, 'AB00', 0, 0, 'bevizsgálás-javítás kérve', 0),
(17, 'AB01', 6500, 5118, 'bevizsgálás-javítás nélkül', 27),
(18, 'ZXV3', 5500, 4331, 'általános munkadíj (1 óra)', 27),
(19, 'HAA12', 13500, 10630, 'elektromos egység cseréje', 27);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `users_table`
--

CREATE TABLE `users_table` (
  `id` bigint(20) NOT NULL,
  `account_credentials_non_expired` bit(1) NOT NULL,
  `account_enabled` bit(1) NOT NULL,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `password_changed` bit(1) NOT NULL,
  `registration_date` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `role` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `users_table`
--

INSERT INTO `users_table` (`id`, `account_credentials_non_expired`, `account_enabled`, `account_non_expired`, `account_non_locked`, `email`, `first_name`, `last_name`, `password`, `password_changed`, `registration_date`, `role`, `user_name`) VALUES
(2, b'1', b'1', b'1', b'1', 'admin@admin.admin', 'CONFIG', 'ADMIN', '$2a$10$dG4ZcnooYv6GDJhFIwBjEeeM7h0Sv9CfVDeje/wr.MRCljsJOLXMu', b'1', 'config-admin', 'ADMIN', 'conf-admin'),
(5, b'1', b'1', b'1', b'1', 'super@user.icms', 'Super', 'User', '$2a$10$4Bb3Ve08TcBKvefXBZhJTexnQRZhRrRLUBkUVhvCR6SrIgnMIMQsq', b'1', '2023-03-22', 'SUPER_USER', 'frank'),
(7, b'1', b'1', b'1', b'1', 'simple@user.icms', 'Simple', 'User', '$2a$10$hD0KWsDwr3VTlenungN8B.MhPdE73kna.I1VrZfpzWt0klRtL0vPy', b'1', '2023-03-22', 'SIMPLE_USER', 'west96');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `worksheets_table`
--

CREATE TABLE `worksheets_table` (
  `id` bigint(20) NOT NULL,
  `create_date` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `engineer_code` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL,
  `faults_id` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL,
  `finish_date` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL,
  `object_id` int(11) DEFAULT NULL,
  `other` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL,
  `products_id` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL,
  `services_id` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL,
  `state` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `worksheets_table`
--

INSERT INTO `worksheets_table` (`id`, `create_date`, `customer_id`, `engineer_code`, `faults_id`, `finish_date`, `object_id`, `other`, `products_id`, `services_id`, `state`) VALUES
(36, '2023-03-22', 29, 'ME124', '0;14', '2023-03-22', 34, '', '0', '0;17', '1'),
(37, '2023-03-22', 32, 'B956', '0;15;13;12', '2023-03-22', 35, 'jelenlegi számláló: 124 012  FF', '0;27;26', '0;19;16', '1');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `address_table`
--
ALTER TABLE `address_table`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `city_table`
--
ALTER TABLE `city_table`
  ADD PRIMARY KEY (`postal_code`);

--
-- A tábla indexei `colleague_table`
--
ALTER TABLE `colleague_table`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `company_table`
--
ALTER TABLE `company_table`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `customers_table`
--
ALTER TABLE `customers_table`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `dealer_table`
--
ALTER TABLE `dealer_table`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `fault_table`
--
ALTER TABLE `fault_table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_rfrj4i0ltmo23osj6ol3mu2p5` (`fault_name`);

--
-- A tábla indexei `finance_table`
--
ALTER TABLE `finance_table`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `objects_table`
--
ALTER TABLE `objects_table`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `products_table`
--
ALTER TABLE `products_table`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `services_table`
--
ALTER TABLE `services_table`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `users_table`
--
ALTER TABLE `users_table`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `worksheets_table`
--
ALTER TABLE `worksheets_table`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
