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

import Modelo.Empresa;
import Modelo.Urbanizacion;
import lib.Validar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UrbanizacionDAO extends ConexionDAO {

    public UrbanizacionDAO() {
        super();
    }

    public ArrayList<Urbanizacion> consultarUrbanizaciones(char tipo) {
        ArrayList<Urbanizacion> urbanizaciones = new ArrayList<Urbanizacion>();
        String tiraSQL = "Select * FROM urbanizacion where u_estatus='A'";
        switch (tipo) {
            case 'c':
                tiraSQL += " and u_tipourbanizacion='c'";
                break;
            case 'e':
                tiraSQL += " and u_tipourbanizacion='e'";
                break;
        }
        tiraSQL += " order by u_codurbanizacion";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                String codigo = resultSet.getString("u_codurbanizacion");
                String nombre = resultSet.getString("u_nombre");
                String direccion = resultSet.getString("u_direccion");
                int cantcalle_edificio = resultSet.getInt("u_cantcalle_edificio");
                char tipoUrb = resultSet.getString("u_tipourbanizacion").charAt(0);
                Urbanizacion urb = new Urbanizacion(codigo, nombre, direccion, cantcalle_edificio, tipoUrb, null, null);
                urbanizaciones.add(urb);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return urbanizaciones;
    }

    public int cantUrbanizaciones() {
        int cant = 0;
        String tiraSQL = "SELECT count(*) FROM urbanizacion ";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            resultSet.next();
            cant = resultSet.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cant;

    }

    public void agregarUrb(Urbanizacion urbanizacion, Empresa empresa) {
        String tiraSQL = "INSERT INTO urbanizacion (u_codurbanizacion, u_empresarif, u_nombre, u_direccion, u_cantcalle_edificio,u_tipourbanizacion,u_estatus) values('"
                + urbanizacion.getCodigo() + "', '" + empresa.getRif() + "', '" + urbanizacion.getNombre() + "', '" + urbanizacion.getDireccion() + "','" + urbanizacion.getCantCalle_Edificio() + "','" + urbanizacion.getTipoUrbanizacion() + "','A')";
        ejecutar(tiraSQL);

    }

    public void modificarUrb(Urbanizacion urbanizacion, Empresa empresa) {

        String tiraSQL = "UPDATE urbanizacion SET u_empresarif = '" + empresa.getRif() + "', u_nombre = '" + urbanizacion.getNombre() + "', u_direccion = '" + urbanizacion.getDireccion() + "', u_cantcalle_edificio = " + urbanizacion.getCantCalle_Edificio() + ",u_tipourbanizacion = '" + urbanizacion.getTipoUrbanizacion() + "' where u_codurbanizacion = '" + urbanizacion.getCodigo() + "'  ";
        ejecutar(tiraSQL);

    }

    public void eliminarUrb(String codigo) {
        String tiraSQL = "UPDATE urbanizacion SET u_estatus = 'I' "
                + "WHERE u_codurbanizacion = '" + codigo + "' ";
        ejecutar(tiraSQL);
    }
}
