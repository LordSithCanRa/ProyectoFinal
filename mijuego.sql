-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 24-04-2022 a las 10:34:08
-- Versión del servidor: 5.7.31
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mijuego`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
CREATE TABLE IF NOT EXISTS `comentarios` (
  `usuarioRemitente` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `idComentario` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuarioDestinatario` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `fecha` timestamp NOT NULL,
  `meGustas` bigint(20) NOT NULL,
  `comentario` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idComentario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fondos`
--

DROP TABLE IF EXISTS `fondos`;
CREATE TABLE IF NOT EXISTS `fondos` (
  `id` int(11) NOT NULL,
  `src` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `precio` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventariofondos`
--

DROP TABLE IF EXISTS `inventariofondos`;
CREATE TABLE IF NOT EXISTS `inventariofondos` (
  `usuario` int(11) NOT NULL,
  `idFondo` int(11) NOT NULL,
  `fechaObtenido` timestamp NOT NULL,
  PRIMARY KEY (`usuario`,`idFondo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventarioskins`
--

DROP TABLE IF EXISTS `inventarioskins`;
CREATE TABLE IF NOT EXISTS `inventarioskins` (
  `usuario` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `idSkin` int(11) NOT NULL,
  `fechaObtenido` timestamp NOT NULL,
  PRIMARY KEY (`usuario`,`idSkin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sistemaniveles`
--

DROP TABLE IF EXISTS `sistemaniveles`;
CREATE TABLE IF NOT EXISTS `sistemaniveles` (
  `nivel` int(11) NOT NULL,
  `experienciaNecesaria` bigint(20) NOT NULL,
  `recompensa` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`nivel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `skins`
--

DROP TABLE IF EXISTS `skins`;
CREATE TABLE IF NOT EXISTS `skins` (
  `id` int(11) NOT NULL,
  `src` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `precio` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `usuario` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `gmail` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `admin` int(11) NOT NULL,
  `experienciaAct` bigint(20) NOT NULL,
  `monedas` bigint(20) NOT NULL,
  `nivel` int(11) NOT NULL,
  `recordExp` bigint(20) NOT NULL,
  `partidasJugadas` bigint(20) NOT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario`, `clave`, `gmail`, `admin`, `experienciaAct`, `monedas`, `nivel`, `recordExp`, `partidasJugadas`) VALUES
('admin', 'admin', 'admin@a', 1, 0, 0, 1, 0, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
