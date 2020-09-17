-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2020. Sze 07. 12:09
-- Kiszolgáló verziója: 10.4.11-MariaDB
-- PHP verzió: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `raktar2_float`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `csomagolt`
--

CREATE TABLE `csomagolt` (
  `id` bigint(20) NOT NULL,
  `cikk_szam` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `darab_szam` int(11) NOT NULL,
  `darab_szam_minimum` int(11) NOT NULL,
  `is_selected` bit(1) DEFAULT NULL,
  `nev` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `suly_gramm` int(11) NOT NULL,
  `termek_ara` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `csomagolt`
--

INSERT INTO `csomagolt` (`id`, `cikk_szam`, `darab_szam`, `darab_szam_minimum`, `is_selected`, `nev`, `suly_gramm`, `termek_ara`) VALUES
(3, '1005', 2, 20, b'0', 'Pick Szalámi', 100, 590),
(4, '1044', 365, 50, b'0', 'Kristálycukor ', 1000, 253),
(5, '10499', 771, 100, b'0', 'Sale Marino Jódozott Durva Tengeri Só', 1000, 253),
(6, '3221', 322, 100, b'0', 'Trendavit Himalája Kristálysó', 500, 342),
(7, '3416', 0, 500, b'0', 'Monte Puding', 120, 169);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `gyumolcs`
--

CREATE TABLE `gyumolcs` (
  `id` bigint(20) NOT NULL,
  `kgaraktaron` float NOT NULL,
  `kgaraktaron_minimum` int(11) NOT NULL,
  `cikk_szam` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `is_selected` bit(1) DEFAULT NULL,
  `nev` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `termek_ara` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `gyumolcs`
--

INSERT INTO `gyumolcs` (`id`, `kgaraktaron`, `kgaraktaron_minimum`, `cikk_szam`, `is_selected`, `nev`, `termek_ara`) VALUES
(1, 5390.8, 44, '3333', b'0', 'Eper', 5553),
(2, 55, 20, '6661', b'0', 'Alma', 250);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hus`
--

CREATE TABLE `hus` (
  `id` bigint(20) NOT NULL,
  `kgaraktaron` float NOT NULL,
  `kgaraktaron_minimum` int(11) NOT NULL,
  `cikk_szam` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `is_selected` bit(1) DEFAULT NULL,
  `nev` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `termek_ara` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `hus`
--

INSERT INTO `hus` (`id`, `kgaraktaron`, `kgaraktaron_minimum`, `cikk_szam`, `is_selected`, `nev`, `termek_ara`) VALUES
(1, 5.1, 33, '33355', b'0', 'Csirkemell', 1020),
(2, 40, 30, '34', b'0', 'Darálthús', 780);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ital`
--

CREATE TABLE `ital` (
  `id` bigint(20) NOT NULL,
  `alkohol_tartalom` float NOT NULL,
  `cikk_szam` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `darab_szam` int(11) NOT NULL,
  `darab_szam_minimum` int(11) NOT NULL,
  `is_selected` bit(1) DEFAULT NULL,
  `kiszereles_liter` float NOT NULL,
  `nev` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `termek_ara` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `ital`
--

INSERT INTO `ital` (`id`, `alkohol_tartalom`, `cikk_szam`, `darab_szam`, `darab_szam_minimum`, `is_selected`, `kiszereles_liter`, `nev`, `termek_ara`) VALUES
(1, 0, '2', 556, 33, b'0', 0.5, 'Coca cola', 320),
(2, 0, '3315', 0, 33, b'0', 2.5, 'Fanta', 450),
(3, 0.4, '8585', 5000, 333, b'0', 0.5, 'Borsodi Sör', 270);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `pekaru`
--

CREATE TABLE `pekaru` (
  `id` bigint(20) NOT NULL,
  `cikk_szam` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `darab_szam` int(11) NOT NULL,
  `darab_szam_minimum` int(11) NOT NULL,
  `is_selected` bit(1) DEFAULT NULL,
  `nev` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `suly_gramm` int(11) NOT NULL,
  `termek_ara` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `pekaru`
--

INSERT INTO `pekaru` (`id`, `cikk_szam`, `darab_szam`, `darab_szam_minimum`, `is_selected`, `nev`, `suly_gramm`, `termek_ara`) VALUES
(3, '3111', 442, 30, b'0', 'Kakaós csiga', 100, 90),
(4, '33112', 0, 50, b'0', 'Kenyér', 1000, 250),
(5, '32', 0, 300, b'0', 'Zsömle', 50, 32),
(6, '855', 0, 120, b'0', 'Kifli', 20, 29);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `csomagolt`
--
ALTER TABLE `csomagolt`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `gyumolcs`
--
ALTER TABLE `gyumolcs`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `hus`
--
ALTER TABLE `hus`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `ital`
--
ALTER TABLE `ital`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `pekaru`
--
ALTER TABLE `pekaru`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `csomagolt`
--
ALTER TABLE `csomagolt`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT a táblához `gyumolcs`
--
ALTER TABLE `gyumolcs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT a táblához `hus`
--
ALTER TABLE `hus`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT a táblához `ital`
--
ALTER TABLE `ital`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `pekaru`
--
ALTER TABLE `pekaru`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
