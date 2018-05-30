-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 30 mai 2018 à 05:13
-- Version du serveur :  10.1.26-MariaDB
-- Version de PHP :  7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestion-scolarite`
--

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `id` int(11) NOT NULL,
  `niveau` varchar(100) NOT NULL,
  `groupe` varchar(255) NOT NULL,
  `id_filiere` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`id`, `niveau`, `groupe`, `id_filiere`) VALUES
(18, '1ère année', 'G_A', 9),
(19, '2ème année', 'G_B', 9),
(26, '2ème année', 'G_A', 9),
(27, '2ème année', 'G_C', 9),
(34, '1ère année', 'G_B', 8),
(35, '1ère année', 'G_C', 8),
(36, '1ère année', 'G_B', 9),
(38, '2ème année', 'G_A', 8),
(39, '2ème année', 'G_B', 8),
(40, '2ème année', 'G_C', 8),
(41, '3ème année', 'G_D', 8),
(42, '3ème année', 'G_B', 8),
(43, '3ème année', 'G_C', 8),
(44, '3ème année', 'G_A', 11),
(45, '3ème année', 'G_B', 11),
(46, '3ème année', 'G_C', 11),
(47, '4ème année', 'G_A', 11),
(48, '4ème année', 'G_B', 11),
(49, '4ème année', 'G_C', 11),
(50, '1ère année', 'G_A', 12),
(51, '5ème année', 'G_B', 11),
(52, '5ème année', 'G_C', 11),
(53, '2ème année', 'G_A', 13),
(55, '3ème année', 'G_A', 8),
(56, '4ème année', 'G_A', 17);

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `id` int(11) NOT NULL,
  `sujet` varchar(100) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `decision` varchar(255) DEFAULT NULL,
  `raison` varchar(255) DEFAULT NULL,
  `id_etudiant1` int(11) NOT NULL,
  `id_etudiant2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id`, `sujet`, `date`, `status`, `decision`, `raison`, `id_etudiant1`, `id_etudiant2`) VALUES
(48, 'certificatPrésence', '2018-01-06 00:55:03', 1, 'Approuvée', NULL, 16, NULL),
(49, 'certificatPrésence', '2018-01-06 00:55:04', 1, 'Ignorée', 'blablabla', 16, NULL),
(50, 'certificatPrésence', '2018-01-06 09:14:11', 1, 'Approuvée', NULL, 18, NULL),
(51, 'certificatPrésence', '2018-01-06 09:14:13', 1, 'Ignorée', 'ze', 18, NULL),
(54, 'certificatPrésence', '2018-01-06 10:08:41', 1, 'Approuvée', NULL, 16, NULL),
(55, 'certificatPrésence', '2018-01-06 10:08:43', 1, 'Approuvée', NULL, 16, NULL),
(56, 'certificatPrésence', '2018-01-06 12:35:03', 1, 'Approuvée', NULL, 20, NULL),
(57, 'certificatPrésence', '2018-01-06 12:35:06', 1, 'Ignorée', 'rouh nayek', 20, NULL),
(58, 'permutation', '2018-01-07 23:41:45', 0, 'En cours', NULL, 18, 20),
(59, 'permutation', '2018-01-07 23:41:46', 0, 'En cours', NULL, 18, 20),
(60, 'permutation', '2018-01-07 23:41:48', 0, 'En cours', NULL, 18, 20),
(61, 'permutation', '2018-01-07 23:41:49', 0, 'En cours', NULL, 18, 20),
(62, 'permutation', '2018-01-07 23:41:49', 0, 'En cours', NULL, 18, 20),
(63, 'permutation', '2018-01-07 23:41:50', 0, 'En cours', NULL, 18, 20),
(64, 'certificatPrésence', '2018-04-14 17:23:22', 0, 'En cours', NULL, 18, NULL),
(65, 'certificatPrésence', '2018-04-14 17:23:24', 0, 'En cours', NULL, 18, NULL),
(66, 'certificatPrésence', '2018-04-14 17:23:25', 0, 'En cours', NULL, 18, NULL),
(67, 'certificatPrésence', '2018-04-14 17:23:26', 0, 'En cours', NULL, 18, NULL),
(68, 'certificatPrésence', '2018-04-14 17:23:26', 0, 'En cours', NULL, 18, NULL),
(69, 'certificatPrésence', '2018-04-14 17:23:26', 0, 'En cours', NULL, 18, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `date_de_naissance` date NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `telephone` int(11) NOT NULL,
  `adresse_mail` varchar(100) NOT NULL,
  `cin` int(11) NOT NULL,
  `ville` varchar(100) NOT NULL,
  `sexe` varchar(100) NOT NULL,
  `matricule` varchar(100) NOT NULL,
  `id_classe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `nom`, `prenom`, `date_de_naissance`, `adresse`, `telephone`, `adresse_mail`, `cin`, `ville`, `sexe`, `matricule`, `id_classe`) VALUES
(16, 'Bouhaha', 'Anas', '1970-01-01', 'Rue Salah Eddine Al ayoubi', 55949877, 'bouhahaanas@gmail.com', 11856627, 'Kairouan', 'homme', 'mat-BOUHAHA.ANAS', 43),
(18, 'Allani', 'Amine', '1970-01-01', 'Saad city', 29043844, 'amine.allani@gmail.com', 21346870, 'Kairouan', 'homme', 'mat-ALLANI.AMINE', 41),
(20, 'Sbai', 'Hamed', '1970-01-01', 'Cité Sidi Saad', 21188871, 'sbai.hamed109@gmail.com', 11889264, 'Kairouan', 'homme', 'mat-SBAI.HAMED', 55);

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

CREATE TABLE `filiere` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `filiere`
--

INSERT INTO `filiere` (`id`, `nom`) VALUES
(8, 'Licence--Informatique'),
(9, 'Préparatoire--MP'),
(10, 'Master--Pilotage SI'),
(11, 'Génie--Civil'),
(12, 'Licence--Energétique'),
(13, 'Licence--Management'),
(15, 'Préparatoire--PC'),
(16, 'Génie--Mécanique'),
(17, 'Génie--Logiciel');

-- --------------------------------------------------------

--
-- Structure de la table `filiere_matiere`
--

CREATE TABLE `filiere_matiere` (
  `id` int(11) NOT NULL,
  `id_filiere` int(11) NOT NULL,
  `id_matiere` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `filiere_matiere`
--

INSERT INTO `filiere_matiere` (`id`, `id_filiere`, `id_matiere`) VALUES
(5, 17, 6),
(6, 8, 6),
(7, 8, 8),
(8, 8, 9),
(9, 8, 10),
(10, 8, 11),
(11, 8, 12),
(12, 8, 13),
(13, 9, 12);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `coefficient` double NOT NULL,
  `semestre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`id`, `nom`, `coefficient`, `semestre`) VALUES
(6, 'JAVA', 4.5, '1ère semestre'),
(7, 'UML', 3.5, '1ère semestre'),
(8, 'Recherche Opérationnelle', 1.5, '1ère semestre'),
(9, 'FrameworkSymfony', 3, '1ère semestre'),
(10, 'FrameworkAngular', 3.5, '1ère semestre'),
(11, 'Systèmes Réparties', 3, '1ère semestre'),
(12, 'Anglais', 1.5, '1ère semestre'),
(13, 'Français1', 1.5, '1ère semestre'),
(14, 'Intelligence artificielle', 3.5, '1ère semestre'),
(15, 'Génie Logiciel', 3.5, '1ère semestre'),
(16, 'Systèmes & architectures avancées', 3.5, '1ère semestre');

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `id_etudiant` int(11) NOT NULL,
  `id_matiere` int(11) NOT NULL,
  `ds` double NOT NULL,
  `exm` double NOT NULL,
  `cc` double NOT NULL,
  `tp` double DEFAULT NULL,
  `moyenne` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`id`, `id_etudiant`, `id_matiere`, `ds`, `exm`, `cc`, `tp`, `moyenne`) VALUES
(1, 18, 6, 12.5, 13, 15, 15.5, 13.35),
(2, 16, 6, 15, 20, 15.2, 10, 17.52);

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

CREATE TABLE `professeur` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `date_de_naissance` date NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `telephone` int(11) NOT NULL,
  `adresse_mail` varchar(100) NOT NULL,
  `cin` int(11) NOT NULL,
  `ville` varchar(100) NOT NULL,
  `sexe` varchar(100) NOT NULL,
  `grade` varchar(100) NOT NULL,
  `salaire` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `professeur`
--

INSERT INTO `professeur` (`id`, `nom`, `prenom`, `date_de_naissance`, `adresse`, `telephone`, `adresse_mail`, `cin`, `ville`, `sexe`, `grade`, `salaire`) VALUES
(1, 'Elleuch', 'Zied', '1970-01-01', 'Sekyét Ezzit', 98989898, 'elleuch.zied@gmail.com', 1010101, 'Sfax', 'homme', 'maitreAassistant', 2.5),
(2, 'Saidane', 'Mohamed', '1975-06-07', 'Hamam Sousse', 12345678, '@gmail.com', 12345678, 'Sousse', 'homme', 'maitreDoctorant', 1800),
(3, 'Chtioui', 'Houssem', '1970-01-01', 'bla', 12345678, '@gmail.com', 12345678, 'Kairouan', 'homme', 'maitreAassistant', 12345),
(4, 'Salem', 'Safa', '1970-01-01', 'bla', 12345678, '@gmail.com', 12345678, 'Kairouan', 'femme', 'maitreAassistant', 12345),
(5, 'Maalel', 'Ahmed', '1970-01-01', 'bla', 12345678, '@gmail.com', 12345678, 'Kairouan', 'homme', 'maitreAassistant', 12345);

-- --------------------------------------------------------

--
-- Structure de la table `professeur_matiere`
--

CREATE TABLE `professeur_matiere` (
  `id` int(11) NOT NULL,
  `id_professeur` int(11) NOT NULL,
  `id_matiere` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `professeur_matiere`
--

INSERT INTO `professeur_matiere` (`id`, `id_professeur`, `id_matiere`) VALUES
(6, 2, 7),
(8, 5, 13),
(9, 3, 7),
(10, 5, 7),
(11, 1, 6),
(12, 3, 6);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `contenu` varchar(100) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_etudiant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id`, `contenu`, `date`, `id_etudiant`) VALUES
(2, 'L\'enseignant est abscent', '2018-01-06 09:03:45', 16),
(3, 'L\'enseignant est abscent', '2018-01-06 09:03:50', 16),
(4, 'sfzef', '2018-01-06 10:07:47', 20),
(5, 'bla bla bla', '2018-01-07 23:41:22', 20);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom_utilisateur` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom_utilisateur`, `mot_de_passe`, `type`) VALUES
(1, 'anas', '11856627', 'agent'),
(3, 'directeur', '12345678', 'directeur');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_filiere` (`id_filiere`);

--
-- Index pour la table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_etudiant` (`id_etudiant1`),
  ADD KEY `id_etudiant2` (`id_etudiant2`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `matricule` (`matricule`),
  ADD KEY `etudiant_ibfk_1` (`id_classe`);

--
-- Index pour la table `filiere`
--
ALTER TABLE `filiere`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `filiere_matiere`
--
ALTER TABLE `filiere_matiere`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_filiere` (`id_filiere`),
  ADD KEY `id_matiere` (`id_matiere`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_etudiant` (`id_etudiant`),
  ADD KEY `note_ibfk_2` (`id_matiere`);

--
-- Index pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `professeur_matiere`
--
ALTER TABLE `professeur_matiere`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_professeur` (`id_professeur`),
  ADD KEY `id_matiere` (`id_matiere`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_etudiant` (`id_etudiant`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `classe`
--
ALTER TABLE `classe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT pour la table `demande`
--
ALTER TABLE `demande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `filiere`
--
ALTER TABLE `filiere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `filiere_matiere`
--
ALTER TABLE `filiere_matiere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `professeur`
--
ALTER TABLE `professeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `professeur_matiere`
--
ALTER TABLE `professeur_matiere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `classe_ibfk_1` FOREIGN KEY (`id_filiere`) REFERENCES `filiere` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `demande_ibfk_1` FOREIGN KEY (`id_etudiant1`) REFERENCES `etudiant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `demande_ibfk_2` FOREIGN KEY (`id_etudiant2`) REFERENCES `etudiant` (`id`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `filiere_matiere`
--
ALTER TABLE `filiere_matiere`
  ADD CONSTRAINT `filiere_matiere_ibfk_1` FOREIGN KEY (`id_filiere`) REFERENCES `filiere` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `filiere_matiere_ibfk_2` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_1` FOREIGN KEY (`id_etudiant`) REFERENCES `etudiant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `note_ibfk_2` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `professeur_matiere`
--
ALTER TABLE `professeur_matiere`
  ADD CONSTRAINT `professeur_matiere_ibfk_1` FOREIGN KEY (`id_professeur`) REFERENCES `professeur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `professeur_matiere_ibfk_2` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`id_etudiant`) REFERENCES `etudiant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
