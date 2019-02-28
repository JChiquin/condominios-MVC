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

import Modelo.Calle;
import Modelo.Urbanizacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CalleDAO extends ConexionDAO{
    
    public Calle buscarCalle(String nroCalle) {
        Calle calle = new Calle();
        String tiraSQL = "SELECT * FROM calle  where ca_nrocalle = '" + nroCalle +  "'";

        ResultSet resultSet = consulta(tiraSQL);
        try {
            if (resultSet.next()) {
                
                String nombre = resultSet.getString("ca_nombre");
                int cantCasas = resultSet.getInt("ca_cantcasas");                
                char estatus = resultSet.getString("ca_estatus").charAt(0);
                calle.setNombre(nombre);
                calle.setCantCasas(cantCasas);
                calle.setEstatus(estatus);
                
               
            }else{ 
                calle.setNroCalle(null);
              }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calle;
    }
    
    public int totalCallesPorUrb(String codigo) {
        String tiraSQL = "Select count(*) as cant from calle where ca_urbanizacioncodurbanizacion = '" + codigo + "'";
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
    
    public int totalCallesActivasPorUrb(String codigo) {
        String tiraSQL = "Select count(*) as cant from calle where ca_urbanizacioncodurbanizacion = '" + codigo + "' and ca_estatus='A'";
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
    
    public ArrayList<Calle> buscarCallesPorUrb(String codigo) {
      
        ArrayList<Calle> calles = new ArrayList<Calle>();
        String tiraSQL = "SELECT * FROM calle  where ca_urbanizacioncodurbanizacion = '" + codigo +  "' and ca_estatus = 'A' order by ca_nrocalle";

        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                
                String nroCalle = resultSet.getString("ca_nrocalle");
                String nombre = resultSet.getString("ca_nombre");
                int cantCasas = resultSet.getInt("ca_cantcasas");
                char estatus = resultSet.getString("ca_estatus").charAt(0);
                Calle cas = new Calle(nroCalle, nombre, cantCasas, null, estatus);
                calles.add(cas);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calles;
    }
    
    public void agregarCalle(Calle calle, Urbanizacion urba) {
        String tiraSQL = "INSERT INTO calle (ca_nrocalle, ca_nombre, ca_urbanizacioncodurbanizacion,ca_cantcasas, ca_estatus) values('"
                + calle.getNroCalle() + "', '" + calle.getNombre()+ "', '" + urba.getCodigo() + "', '" + calle.getCantCasas()+ "','A')";

        ejecutar(tiraSQL);

    }
    
    public void modificarCalle(Calle calle) {

        String tiraSQL = "UPDATE calle SET ca_nombre = '" + calle.getNombre() + "' , ca_cantcasas = '" + calle.getCantCasas()
                + "'  WHERE ca_nrocalle = '" + calle.getNroCalle() + "' ";
        ejecutar(tiraSQL);

    }
    
    public void eliminarCalle(Calle calle) {
        
        String tiraSQL = "UPDATE calle SET ca_estatus = 'I' "
                + "WHERE ca_nrocalle = '" + calle.getNroCalle() + "' ";
        ejecutar(tiraSQL);
    }

    
}
