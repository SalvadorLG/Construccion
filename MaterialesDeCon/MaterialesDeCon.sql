USE [MaterialesDeCon]
GO
/****** Object:  Table [dbo].[Categorias]    Script Date: 30/07/2020 03:41:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categorias](
	[categoriaId] [varchar](10) NOT NULL,
	[nombreCategoria] [nvarchar](20) NULL,
	[descripccion] [nvarchar](50) NULL,
 CONSTRAINT [PK_Categorias] PRIMARY KEY CLUSTERED 
(
	[categoriaId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comprados]    Script Date: 30/07/2020 03:41:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comprados](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[folio] [nchar](50) NOT NULL,
	[producto] [nchar](250) NOT NULL,
	[cantidadIndividual] [int] NOT NULL,
	[precioIndividual] [float] NOT NULL,
 CONSTRAINT [PK_comprados] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CorteCaja]    Script Date: 30/07/2020 03:41:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CorteCaja](
	[ingreso] [decimal](10, 0) NULL,
	[fecha] [varchar](20) NULL,
	[salida] [decimal](10, 0) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrdenEnvio]    Script Date: 30/07/2020 03:41:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrdenEnvio](
	[idVenta] [varchar](10) NULL,
	[idEnvio] [varchar](10) NOT NULL,
	[numeroTelefonico] [varchar](10) NULL,
	[referencia] [nvarchar](50) NULL,
	[numeroInterior] [nvarchar](5) NULL,
	[numeroExterior] [nvarchar](5) NULL,
	[direccion] [nvarchar](100) NULL,
	[nombreCliente] [nvarchar](50) NULL,
	[ciuidad] [nvarchar](25) NULL,
	[codigoPostal] [nvarchar](10) NULL,
 CONSTRAINT [PK_OrdenEnvio] PRIMARY KEY CLUSTERED 
(
	[idEnvio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrdenVenta]    Script Date: 30/07/2020 03:41:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrdenVenta](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fecha] [varchar](20) NULL,
	[totalPagar] [decimal](10, 0) NULL,
 CONSTRAINT [PK_OrdenVenta] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Producto]    Script Date: 30/07/2020 03:41:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Producto](
	[nombreProducto] [nvarchar](30) NULL,
	[idProducto] [varchar](10) NOT NULL,
	[idProveerdor] [varchar](10) NULL,
	[idCategoria] [varchar](10) NULL,
	[precioUnitario] [decimal](10, 0) NULL,
	[existencia] [int] NULL,
 CONSTRAINT [PK_Producto] PRIMARY KEY CLUSTERED 
(
	[idProducto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Proveerdor]    Script Date: 30/07/2020 03:41:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Proveerdor](
	[idProveedor] [nchar](10) NOT NULL,
	[contactoReferencia] [nvarchar](250) NULL,
	[nombreEmpresa] [nvarchar](250) NULL,
	[direccion] [nvarchar](250) NULL,
	[numeroTelefonico] [nvarchar](15) NULL,
	[codigoPostal] [nvarchar](10) NULL,
	[ciudad] [nvarchar](250) NULL,
 CONSTRAINT [PK_Proveerdor] PRIMARY KEY CLUSTERED 
(
	[idProveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ticket]    Script Date: 30/07/2020 03:41:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ticket](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[folio] [nchar](100) NOT NULL,
	[cantidad] [int] NOT NULL,
	[subTotal] [float] NOT NULL,
	[total] [float] NOT NULL,
	[pago] [float] NOT NULL,
	[cambio] [float] NOT NULL,
 CONSTRAINT [PK_Ticket] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 30/07/2020 03:41:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuario](
	[idUsuario] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](30) NULL,
	[apellidoPaterno] [nvarchar](20) NULL,
	[puesto] [nvarchar](20) NULL,
	[password] [nvarchar](50) NULL,
 CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED 
(
	[idUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VentaDetalle]    Script Date: 30/07/2020 03:41:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VentaDetalle](
	[precio] [decimal](10, 0) NULL,
	[cantidad] [int] NULL,
	[nombreProducto] [varchar](50) NOT NULL
) ON [PRIMARY]
GO
