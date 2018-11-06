-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-09-2018 a las 06:42:40
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_ferreteria`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscaPxApellidos` (IN `papellidos` VARCHAR(50))  BEGIN
select*from persona where apellidos like concat(papellidos,'%');
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscaPxApellidovendedor` (IN `PAPELLIDO` INT(50))  BEGIN
select*from vendedores where APELLIDO like concat(PAPELLIDO,'%');
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscaPxNOMBREPRODUCTO` (IN `PNOMBREPRODUCTO` VARCHAR(50))  BEGIN
select * from productos where NOMBREPRODUCTO like  concat(PNOMBREPRODUCTO,'%');
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deletePersona` (IN `pdni` VARCHAR(10))  BEGIN
delete from  persona where dni = pdni;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deletePRODUCTO` (IN `PCODIGO` VARCHAR(100))  BEGIN
delete from  productos where CODIGO = PCODIGO;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deleteVendedores` (IN `PDPI` VARCHAR(10))  BEGIN
delete from  vendedores where DPI = PDPI;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_editPersona` (IN `pdni` VARCHAR(10), IN `papellidos` VARCHAR(50), IN `pnombres` INT(50), IN `pfecha` INT(30), IN `plugar` INT(50))  BEGIN
update persona set apellidos = papellidos, nombres= pnombres, fechaNacimiento=pfecha, lugarNacimiento=plugar
where dni = pdni;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_editPRODUCTO` (IN `PCODIGO` VARCHAR(100), IN `PNOMBREPRODUCTO` VARCHAR(50), IN `PDESCRIPCION` VARCHAR(50), IN `PCANTIDAD` VARCHAR(1000), IN `PPRECIO` VARCHAR(1000))  BEGIN
update productos set NOMBREPRODUCTO = PNOMBREPRODUCTO, DESCRIPCION= PDESCRIPCION, CANTIDAD=PCANTIDAD, PRECIO=PPRECIO
where CODIGO = PCODIGO;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_editvendedores` (IN `PDPI` VARCHAR(10), IN `PAPELLIDO` VARCHAR(50), IN `PNOMBRE` VARCHAR(50), IN `PTELEFONO` VARCHAR(10), IN `PDIRECCION` VARCHAR(50))  BEGIN
update vendedores set APELLIDO = PAPELLIDO, NOMBRE= PNOMBRE, TELEFONO=PTELEFONO, DIRECCION=PDIRECCION
where DPI = PDPI;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertPersona` (IN `pdni` VARCHAR(10), IN `papellido` VARCHAR(50), IN `pnombres` VARCHAR(50), IN `pfechas` VARCHAR(30), IN `plugar` INT(50))  BEGIN
insert into persona(dni,apellidos,nombres,fechaNacimiento,lugarNacimiento)
values (pdni,papellidos,pnombres,pfechas,plugar);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertproductos` (IN `PCODIGO` VARCHAR(100), IN `PNOMBREPRODUCTO` VARCHAR(50), IN `PDESCRIPCION` VARCHAR(50), IN `PCANTIDAD` VARCHAR(1000), IN `PPRECIO` VARCHAR(1000))  BEGIN
insert into productos(CODIGO,NOMBREPRODUCTO,DESCRIPCION,CANTIDAD,PRECIO)
values (PCODIGO,PNOMBREPRODUCTO,PDESCRIPCION,PCANTIDAD,PPRECIO);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertVendedores` (IN `PDPI` VARCHAR(10), IN `PAPELLIDO` VARCHAR(50), IN `PNOMBRE` VARCHAR(50), IN `PTELEFONO` VARCHAR(10), IN `PDIRECCION` VARCHAR(50))  BEGIN
insert into vendedores(DPI,APELLIDO,NOMBRE,TELEFONO,DIRECCION)
values (PDPI,PAPELLIDO,PNOMBRE,PTELEFONO,PDIRECCION);

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `numfactura` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `dni` varchar(10) CHARACTER SET utf8 NOT NULL,
  `CODIGO` varchar(100) CHARACTER SET utf8 NOT NULL,
  `DPI` varchar(10) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `dni` varchar(10) CHARACTER SET utf8 NOT NULL,
  `apellidos` varchar(50) CHARACTER SET utf8 NOT NULL,
  `nombres` varchar(50) CHARACTER SET utf8 NOT NULL,
  `fechaNacimiento` varchar(30) CHARACTER SET utf8 NOT NULL,
  `lugarNacimiento` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`dni`, `apellidos`, `nombres`, `fechaNacimiento`, `lugarNacimiento`) VALUES
('1', 'valenzuela', 'luis', '11-07-2018', 'guatemala');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `CODIGO` varchar(100) CHARACTER SET utf8 NOT NULL,
  `NOMBREPRODUCTO` varchar(50) CHARACTER SET utf8 NOT NULL,
  `DESCRIPCION` varchar(50) CHARACTER SET utf8 NOT NULL,
  `CANTIDAD` varchar(1000) CHARACTER SET utf8 NOT NULL,
  `PRECIO` varchar(1000) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`CODIGO`, `NOMBREPRODUCTO`, `DESCRIPCION`, `CANTIDAD`, `PRECIO`) VALUES
('1', 'papel', 'asdfasdf', '5', '5'),
('2', 'mrtillo de thor', 'saca rayos', '10', '50');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedores`
--

CREATE TABLE `vendedores` (
  `DPI` varchar(10) CHARACTER SET utf8 NOT NULL,
  `NOMBRE` varchar(50) CHARACTER SET utf8 NOT NULL,
  `APELLIDO` varchar(50) CHARACTER SET utf32 NOT NULL,
  `TELEFONO` varchar(10) CHARACTER SET utf8 NOT NULL,
  `DIRECCION` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vendedores`
--

INSERT INTO `vendedores` (`DPI`, `NOMBRE`, `APELLIDO`, `TELEFONO`, `DIRECCION`) VALUES
('1', 'asdf', 'dafs', '1234', 'asdfasdf'),
('2', 'asdfas', 'asdfasdf', '6545', 'sdafasdf');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`numfactura`),
  ADD KEY `dni` (`dni`),
  ADD KEY `CODIGO` (`CODIGO`),
  ADD KEY `DPI` (`DPI`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`CODIGO`);

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`DPI`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`CODIGO`) REFERENCES `productos` (`CODIGO`),
  ADD CONSTRAINT `facturas_ibfk_2` FOREIGN KEY (`dni`) REFERENCES `persona` (`dni`),
  ADD CONSTRAINT `facturas_ibfk_3` FOREIGN KEY (`DPI`) REFERENCES `vendedores` (`DPI`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
