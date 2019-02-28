CREATE TABLE Apartamento (
  Ap_CodApartamento      varchar(30) NOT NULL, 
  Ap_EdificiocodEdificio varchar(30) NOT NULL, 
  Ap_Propietariocedula   varchar(20) NOT NULL, 
  Ap_CantHabitaciones    int4 NOT NULL, 
  Ap_CantBanios          int4 NOT NULL, 
  Ap_NroPiso             int4 NOT NULL, 
  Ap_Estatus             char(1) NOT NULL, 
  PRIMARY KEY (Ap_CodApartamento));
CREATE TABLE Calle (
  Ca_NroCalle                    varchar(15) NOT NULL, 
  Ca_UrbanizacioncodUrbanizacion varchar(15) NOT NULL, 
  Ca_Nombre                      varchar(50) NOT NULL, 
  Ca_CantCasas                   int4 NOT NULL, 
  Ca_Estatus                     char(1) NOT NULL, 
  PRIMARY KEY (Ca_NroCalle));
CREATE TABLE Casa (
  Cas_CodCasa           varchar(30) NOT NULL, 
  Cas_CallenroCalle     varchar(15) NOT NULL, 
  Cas_Propietariocedula varchar(20) NOT NULL, 
  Cas_CantHabitaciones  int4 NOT NULL, 
  Cas_CantBanios        int4 NOT NULL, 
  Cas_CantPisos         int4 NOT NULL, 
  Cas_Estatus           char(1) NOT NULL, 
  PRIMARY KEY (Cas_CodCasa));
CREATE TABLE Cuota (
  C_CodCuota                    varchar(15) NOT NULL, 
  C_UrbanizacioncodUrbanizacion varchar(15) NOT NULL, 
  C_MontoTotal                  float8 NOT NULL, 
  C_NroMes                      int4 NOT NULL, 
  C_NroAnio                     int4 NOT NULL, 
  C_Estatus                     char(1) NOT NULL, 
  PRIMARY KEY (C_CodCuota));
CREATE TABLE Edificio (
  Ed_CodEdificio                 varchar(30) NOT NULL, 
  Ed_UrbanizacioncodUrbanizacion varchar(15) NOT NULL, 
  Ed_CantPisos                   int4 NOT NULL, 
  Ed_CantApartamentos            int4 NOT NULL, 
  Ed_Estatus                     char(1) NOT NULL, 
  PRIMARY KEY (Ed_CodEdificio));
CREATE TABLE Empresa (
  E_Rif       varchar(20) NOT NULL, 
  E_Nombre    varchar(50) NOT NULL, 
  E_Direccion varchar(50) NOT NULL, 
  E_Telefono  varchar(20) NOT NULL, 
  E_Estatus   char(1) NOT NULL, 
  PRIMARY KEY (E_Rif));
CREATE TABLE FormaPago (
  FP_CodFormaPago varchar(30) NOT NULL, 
  FP_Nombre       varchar(30) NOT NULL, 
  FP_Estatus      char(1) NOT NULL, 
  PRIMARY KEY (FP_CodFormaPago));
CREATE TABLE FormaPagoPorPago (
  FPPP_PagocodPago           varchar(30) NOT NULL, 
  FPPP_FormaPagocodFormaPago varchar(30) NOT NULL, 
  FPPP_Monto                 float8 NOT NULL, 
  FPPP_Estatus               char(1) NOT NULL);
CREATE TABLE Gasto (
  G_CodGasto         varchar(15) NOT NULL, 
  G_TipoGastocodTipo int4 NOT NULL, 
  G_Nombre           varchar(50) NOT NULL, 
  G_Descripcion      varchar(50) NOT NULL, 
  G_Estatus          char(1) NOT NULL, 
  PRIMARY KEY (G_CodGasto));
CREATE TABLE GastoPorCuota (
  GPC_CuotacodCuota varchar(15) NOT NULL, 
  GPC_GastocodGasto varchar(15) NOT NULL, 
  GPC_Monto         float8 NOT NULL, 
  GPC_Fecha         date NOT NULL, 
  GPC_Estatus       char(1) NOT NULL);
CREATE TABLE Pago (
  Pa_CodPago                   varchar(30) NOT NULL, 
  Pa_CasacodCasa               varchar(30), 
  Pa_ApartamentocodApartamento varchar(30), 
  Pa_CuotacodCuota             varchar(15) NOT NULL, 
  Pa_Fecha                     date NOT NULL, 
  Pa_Estatus                   char(1) NOT NULL, 
  PRIMARY KEY (Pa_CodPago));
CREATE TABLE Propietario (
  Pr_Cedula          varchar(20) NOT NULL, 
  Pr_Nombre          varchar(30) NOT NULL, 
  Pr_Apellido        varchar(30) NOT NULL, 
  Pr_Direccion       varchar(30) NOT NULL, 
  Pr_Telefono        varchar(20) NOT NULL, 
  Pr_FechaNacimiento date NOT NULL, 
  Pr_Estatus         char(1) NOT NULL, 
  PRIMARY KEY (Pr_Cedula));
CREATE TABLE TipoGasto (
  TG_CodTipo SERIAL NOT NULL, 
  TG_Nombre  varchar(30) NOT NULL, 
  TG_Estatus char(1) NOT NULL, 
  PRIMARY KEY (TG_CodTipo));
CREATE TABLE Urbanizacion (
  U_CodUrbanizacion    varchar(15) NOT NULL, 
  U_Empresarif         varchar(20) NOT NULL, 
  U_Nombre             varchar(50) NOT NULL, 
  U_Direccion          varchar(50) NOT NULL, 
  U_CantCalle_Edificio int4 NOT NULL, 
  U_TipoUrbanización   char(1) NOT NULL, 
  U_Estatus            char(1) NOT NULL, 
  PRIMARY KEY (U_CodUrbanizacion));