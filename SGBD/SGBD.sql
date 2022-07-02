-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : sam. 13 mars 2021 à 17:27
-- Version du serveur :  10.5.5-MariaDB
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `tetu`
--

-- --------------------------------------------------------

--
-- Structure de la table `DJ_Niveau`
--

CREATE TABLE `DJ_Niveau` (
  `cle` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `DJ_Passage`
--

CREATE TABLE `DJ_Passage` (
  `cle` int(11) NOT NULL,
  `p_1` int(11) NOT NULL,
  `p_2` int(11) NOT NULL,
  `cle_niveau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `DJ_Piece`
--

CREATE TABLE `DJ_Piece` (
  `cle` int(11) NOT NULL,
  `pos_x` int(11) NOT NULL,
  `pos_y` int(11) NOT NULL,
  `pas_nord` int(11) NOT NULL DEFAULT 0,
  `pas_sud` int(11) NOT NULL DEFAULT 0,
  `pas_est` int(11) NOT NULL DEFAULT 0,
  `pas_ouest` int(11) NOT NULL DEFAULT 0,
  `cle_niveau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `DJ_Niveau`
--
ALTER TABLE `DJ_Niveau`
  ADD PRIMARY KEY (`cle`);

--
-- Index pour la table `DJ_Passage`
--
ALTER TABLE `DJ_Passage`
  ADD PRIMARY KEY (`cle`);

--
-- Index pour la table `DJ_Piece`
--
ALTER TABLE `DJ_Piece`
  ADD PRIMARY KEY (`cle`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `DJ_Niveau`
--
ALTER TABLE `DJ_Niveau`
  MODIFY `cle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `DJ_Passage`
--
ALTER TABLE `DJ_Passage`
  MODIFY `cle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `DJ_Piece`
--
ALTER TABLE `DJ_Piece`
  MODIFY `cle` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
