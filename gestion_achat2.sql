-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 16 déc. 2023 à 21:30
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_achat2`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `idAdmin` int(11) NOT NULL,
  `login` varchar(254) DEFAULT NULL,
  `password` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`idAdmin`, `login`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `association1`
--

CREATE TABLE `association1` (
  `idFournisseur` int(11) NOT NULL,
  `idProd` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `login` varchar(254) DEFAULT NULL,
  `password` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idClient`, `login`, `password`) VALUES
(58, 'client', 'client');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `idProd` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `idCommande` int(11) NOT NULL,
  `nomCommande` varchar(254) DEFAULT NULL,
  `dateCommande` date DEFAULT NULL,
  `quantitteProdCommande` int(11) DEFAULT NULL,
  `nomClient` varchar(254) DEFAULT NULL,
  `prixTotal` decimal(8,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`idProd`, `idClient`, `idCommande`, `nomCommande`, `dateCommande`, `quantitteProdCommande`, `nomClient`, `prixTotal`) VALUES
(1, 1, 27, 'amineC1', '2022-06-10', 1, 'amine', '4000');

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `idFournisseur` int(11) NOT NULL,
  `nomFournisseur` varchar(254) DEFAULT NULL,
  `prenomFournisseur` varchar(254) DEFAULT NULL,
  `typeProd` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`idFournisseur`, `nomFournisseur`, `prenomFournisseur`, `typeProd`) VALUES
(5, 'fournisseur', 'fournisseur', 'Bureau'),
(6, 'fournisseur2', 'fournisseur2', 'TV ');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `idProd` int(11) NOT NULL,
  `nomProd` varchar(254) DEFAULT NULL,
  `PU` int(11) DEFAULT NULL,
  `quantiteProd` int(11) DEFAULT NULL,
  `dateEntre` date DEFAULT NULL,
  `dateExp` date DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`idProd`, `nomProd`, `PU`, `quantiteProd`, `dateEntre`, `dateExp`, `description`) VALUES
(6, 'pc', 9000, 30, '2022-06-04', '2022-06-16', 'pc protables HP'),
(8, 'Voiture', 80000, 154, '2022-06-08', '2022-06-17', ' Voiture FORD'),
(9, 'Téléphone', 6000, 40, '2022-06-09', '2022-06-22', ' Adnroid'),
(11, 'Bureau', 4000, 99, '2022-06-01', '2022-06-16', ' Bureau professionnel '),
(13, 'ordinateur', 2000, 300, '2022-06-03', '2022-06-11', ' oordi');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idAdmin`) USING BTREE,
  ADD KEY `idAdmin` (`idAdmin`);

--
-- Index pour la table `association1`
--
ALTER TABLE `association1`
  ADD PRIMARY KEY (`idFournisseur`,`idProd`),
  ADD KEY `FK_ASSOCIAT_ASSOCIATI_PRODUIT` (`idProd`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`idCommande`),
  ADD UNIQUE KEY `idClient` (`idClient`),
  ADD UNIQUE KEY `idProd` (`idProd`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`idFournisseur`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`idProd`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `idAdmin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `association1`
--
ALTER TABLE `association1`
  MODIFY `idFournisseur` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `idCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `idFournisseur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `idProd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
