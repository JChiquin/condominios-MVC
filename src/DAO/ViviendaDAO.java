/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Chiquín, Jorge
 * @author Vargas, Alexander
 * @author Bonilla, José
 * @author Fernández, Ysidro
 * @author Rodríguez, Ronald
 */
package DAO;

import Modelo.Vivienda;
import Modelo.Propietario;
import Modelo.Urbanizacion;
import lib.Validar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViviendaDAO extends ConexionDAO {

    public ViviendaDAO() {
        super();
    }

    public ArrayList<Vivienda> buscarApartamentos(String codigo) {
        ArrayList<Vivienda> apartamentos = new ArrayList<Vivienda>();
        String tiraSQL = "Select * FROM apartamento where ap_edificiocodedificio='" + codigo + "' and ap_estatus='A' order by ap_codapartamento";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                int nroPiso = resultSet.getInt("ap_nropiso");
                String codapartamento = resultSet.getString("ap_codapartamento");
                int cantHab = resultSet.getInt("ap_canthabitaciones");
                int cantBanios = resultSet.getInt("ap_cantbanios");
                Propietario pr = new Propietario();
                pr.setCedula(resultSet.getString("ap_propietariocedula"));
                Vivienda ap = new Vivienda(nroPiso, pr, codapartamento, cantHab, cantBanios, 'A', 'A', null);
                apartamentos.add(ap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return apartamentos;
    }

    public boolean modificarApartamento(Vivienda ap) {
        String tiraSQL = "UPDATE apartamento SET ap_propietariocedula = '" + ap.getPropietario().getCedula() + "'"
                + ", ap_canthabitaciones = " + "'" + ap.getCantHabitaciones() + "' "
                + ", ap_cantbanios = '" + ap.getCantBanios() + "'"
                + " WHERE ap_codapartamento = '" + ap.getCodigo() + "'";
        return ejecutar(tiraSQL);
    }

    public int totalApartamentosPorEdificio(String codigo) {
        String tiraSQL = "Select count(*) as cant from apartamento where ap_edificiocodedificio = '" + codigo + "'";
        ResultSet resultSet = consulta(tiraSQL);
        int cant = 0;
        try {
            if (resultSet.next()) {
                cant = resultSet.getInt("cant");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cant;

    }

    public boolean incluirApartamento(Vivienda ap) {
        String tiraSQL = "INSERT INTO apartamento(ap_codapartamento,ap_edificiocodedificio,ap_propietariocedula,ap_canthabitaciones,ap_cantbanios,ap_nropiso,ap_estatus) "
                + "Values('" + ap.getCodigo() + "','" + ap.getCodigo().substring(0, 9) + "','" + ap.getPropietario().getCedula() + "','" + ap.getCantHabitaciones() + "','" + ap.getCantBanios() + "','" + ap.getNumPiso() + "','A')";
        return ejecutar(tiraSQL);
    }

    public boolean eliminarApartamento(Vivienda ap) {
        String tiraSQL = "UPDATE apartamento SET ap_estatus = 'I' where ap_codapartamento = '" + ap.getCodigo() + "'";
        return ejecutar(tiraSQL);
    }

    public ArrayList<Vivienda> buscarViviendasPorPro(String cedulaPro) {

        ArrayList<Vivienda> viv = new ArrayList<Vivienda>();

        String tiraSQL = "SELECT * from apartamento, propietario where ap_propietariocedula = '" + cedulaPro
                + "' and ap_propietariocedula = pr_cedula and ap_estatus = 'A'";
        String tiraSQL2 = "SELECT * from casa, propietario where cas_propietariocedula = '" + cedulaPro
                + "' and cas_propietariocedula = pr_cedula and cas_estatus = 'A'";

        ResultSet resultSet = consulta(tiraSQL);
        ResultSet resultSet2 = consulta(tiraSQL2);
        try {
            while (resultSet.next()) {

                int nroPiso = resultSet.getInt("ap_nropiso");
                String codapartamento = resultSet.getString("ap_codapartamento");
                int cantHab = resultSet.getInt("ap_canthabitaciones");
                int cantBanios = resultSet.getInt("ap_cantbanios");
                char estatus = resultSet.getString("ap_estatus").charAt(0);
                Propietario pr = new Propietario();
                pr.setCedula(resultSet.getString("ap_propietariocedula"));
                Vivienda vi = new Vivienda(nroPiso, pr, codapartamento, cantHab, cantBanios, 'A', 'A', null);
                viv.add(vi);
            }
            while (resultSet2.next()) {

                int nroPisos = resultSet2.getInt("cas_cantpisos");
                String codCasa = resultSet2.getString("cas_codcasa");
                int cantHab = resultSet2.getInt("cas_canthabitaciones");
                int cantBanios = resultSet2.getInt("cas_cantbanios");
                char estatus = resultSet2.getString("cas_estatus").charAt(0);
                Propietario pr = new Propietario();
                pr.setCedula(resultSet2.getString("cas_propietariocedula"));
                Vivienda vi = new Vivienda(nroPisos, pr, codCasa, cantHab, cantBanios, 'A', 'C', null);
                viv.add(vi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viv;
    }

    public ArrayList<Vivienda> buscarCasasPorPro(String cedulaPro) {
        ArrayList<Vivienda> viv = new ArrayList<Vivienda>();

        String tiraSQL = "SELECT * from casa, propietario where cas_propietariocedula = '" + cedulaPro
                + "' and cas_propietariocedula = pr_cedula and cas_estatus = 'A'";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {

                int nroCalle = resultSet.getInt("cas_nrocalle");
                String codCasa = resultSet.getString("cas_codcasa");
                int cantHab = resultSet.getInt("cas_canthabitaciones");
                int cantBanios = resultSet.getInt("cas_cantbanios");
                char estatus = resultSet.getString("cas_estatus").charAt(0);
                Propietario pr = new Propietario();
                pr.setCedula(resultSet.getString("cas_propietariocedula"));
                Vivienda vi = new Vivienda(nroCalle, pr, codCasa, cantHab, cantBanios, 'A', 'C', null);
                viv.add(vi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return viv;
    }

    public ArrayList<Vivienda> buscarCasas(String codigo) {
        ArrayList<Vivienda> casas = new ArrayList<Vivienda>();
        String tiraSQL = "Select * FROM casa where cas_callenrocalle = '" + codigo + "' and cas_estatus = 'A' order by cas_callenrocalle";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                int nroPisos = resultSet.getInt("cas_cantpisos");
                String codCasa = resultSet.getString("cas_codcasa");
                int cantHab = resultSet.getInt("cas_canthabitaciones");
                int cantBanios = resultSet.getInt("cas_cantbanios");
                char estatus = resultSet.getString("cas_estatus").charAt(0);
                Propietario pr = new Propietario();
                pr.setCedula(resultSet.getString("cas_propietariocedula"));
                Vivienda vi = new Vivienda(nroPisos, pr, codCasa, cantHab, cantBanios, 'A', 'C', null);
                casas.add(vi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return casas;
    }

    public int totalViviendasPorProp(String cedulaPro) {
        String tiraSQL = "SELECT count(*) as total from apartamento where ap_propietariocedula='" + cedulaPro + "' and ap_estatus='A'";
        int cant = 0;
        ResultSet resultSet = consulta(tiraSQL);
        try {
            if (resultSet.next()) {
                cant = resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        tiraSQL = "SELECT count(*) as total from casa where cas_propietariocedula='" + cedulaPro + "' and cas_estatus='A'";
        try {
            if (resultSet.next()) {
                cant += resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cant;
    }

    public boolean eliminarCasa(Vivienda ca) {
        String tiraSQL = "UPDATE casa SET cas_estatus = 'I' where cas_codcasa = '" + ca.getCodigo() + "'";
        return ejecutar(tiraSQL);
    }

    public boolean incluirCasa(Vivienda ca) {
        String tiraSQL = "INSERT INTO casa(cas_codcasa,cas_callenrocalle,cas_propietariocedula,cas_canthabitaciones,cas_cantbanios,cas_cantpisos,cas_estatus) "
                + "Values('" + ca.getCodigo() + "','" + ca.getCodigo().substring(0, ca.getCodigo().lastIndexOf("-")-1) + "','" + ca.getPropietario().getCedula() + "','" + ca.getCantHabitaciones() + "','" + ca.getCantBanios() + "','" + ca.getNumPiso() + "','A')";
        return ejecutar(tiraSQL);
    }

    public int totalCasasPorCalle(String nroCalle) {
        String tiraSQL = "Select count(*) as cant from casa where cas_callenrocalle = '" + nroCalle + "'";
        ResultSet resultSet = consulta(tiraSQL);
        int cant = 0;
        try {
            if (resultSet.next()) {
                cant = resultSet.getInt("cant");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cant;
    }

    public boolean modificarCasa(Vivienda ca) {
        String tiraSQL = "UPDATE casa SET cas_propietariocedula = '" + ca.getPropietario().getCedula() + "'"
                + ", cas_canthabitaciones = " + "'" + ca.getCantHabitaciones() + "' "
                + ", cas_cantbanios = '" + ca.getCantBanios() + "'"
                + ", cas_cantpisos = '" + ca.getNumPiso() + "'"
                + " WHERE cas_codcasa = '" + ca.getCodigo() + "'";
        return ejecutar(tiraSQL);
    }
    
    public ArrayList<Vivienda> buscarViviendasPorPro2(String cedulaPro,String urbanizacion) {

        ArrayList<Vivienda> viv = new ArrayList<Vivienda>();

        String tiraSQL = "SELECT ap_codapartamento from apartamento, propietario,edificio,urbanizacion where ap_propietariocedula = '" + cedulaPro
                + "' and ap_propietariocedula = pr_cedula and ap_edificiocodedificio=ed_codedificio and ed_urbanizacioncodurbanizacion=u_codurbanizacion and u_nombre='"+urbanizacion+"' and ap_estatus = 'A'";
        String tiraSQL2 = "SELECT cas_codcasa from casa, propietario,calle,urbanizacion where cas_propietariocedula = '" + cedulaPro
                + "' and cas_propietariocedula = pr_cedula and cas_callenrocalle=ca_nrocalle and ca_urbanizacioncodurbanizacion=u_codurbanizacion and u_nombre='"+urbanizacion+"' and cas_estatus = 'A'";

        ResultSet resultSet = consulta(tiraSQL);
        ResultSet resultSet2 = consulta(tiraSQL2);
        try {
            while (resultSet.next()) {
                String codapartamento = resultSet.getString("ap_codapartamento");
                Vivienda vi = new Vivienda();
                vi.setCodigo(codapartamento);
                viv.add(vi);
            }
            while (resultSet2.next()) {
                String codCasa = resultSet2.getString("cas_codcasa");
                Vivienda vi = new Vivienda();
                vi.setCodigo(codCasa);
                viv.add(vi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viv;
    }
    
    public int totalViviendasPorUrb(String codUrb) {
        String tiraSQL = "Select count(*) as cant from casa where left(cas_codcasa,4) = '" + codUrb + "' and cas_estatus='A'";
        ResultSet resultSet = consulta(tiraSQL);
        String tiraSQL2 = "Select count(*) as cant from apartamento where left(ap_codapartamento,4) = '" + codUrb + "' and ap_estatus='A'";
        ResultSet resultSet2 = consulta(tiraSQL2);
        int cant = 0;
        try {
            if (resultSet.next()) {
                cant += resultSet.getInt("cant");
            }
            if (resultSet2.next()) {
                cant += resultSet2.getInt("cant");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cant;
    }
    


}
