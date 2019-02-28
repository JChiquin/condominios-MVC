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

import DAO.ConexionDAO;
import Modelo.Edificio;
import Modelo.Urbanizacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class EdificioDAO extends ConexionDAO {

    public EdificioDAO() {
        super();
    }

    public ArrayList<Edificio> buscarEdificiosPorUrbanizacion(String codigo) {
        ArrayList<Edificio> edificios = new ArrayList<Edificio>();
        String tiraSQL = "Select * FROM edificio where ed_urbanizacioncodurbanizacion='" + codigo + "' and ed_estatus='A' order by ed_codedificio";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                String codedificio = resultSet.getString("ed_codedificio");
                int cantpisos = resultSet.getInt("ed_cantpisos");
                int cantapar = resultSet.getInt("ed_cantapartamentos");
                char estatus = resultSet.getString("ed_estatus").charAt(0);
                Edificio ed = new Edificio(codedificio, cantpisos, cantapar, null, estatus);
                edificios.add(ed);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return edificios;
    }
    
    public void agregarEdificio(Edificio edificio, Urbanizacion urba ) {
        String tiraSQL = "INSERT INTO edificio (ed_codedificio, ed_urbanizacioncodurbanizacion,ed_cantpisos, ed_cantapartamentos, ed_estatus) values('"
                + edificio.getCodigo() + "', '" + urba.getCodigo() + "', '" + edificio.getCantPiso()+ "', '" +edificio.getCantApartamentos()+ "','A')";

        ejecutar(tiraSQL);

    }
    
     public Edificio buscar(String codigo)
    {
        
        	Edificio edi = new Edificio();
			String tiraSQL = "SELECT * FROM edificio  where ed_codedificio = '" + codigo + "' and ed_estatus= 'A' ";
		
			ResultSet resultSet = consulta(tiraSQL);
			try {
				if (resultSet.next()){
					
					int cantPisos = resultSet.getInt("ed_cantpisos");
					int cantAparts = resultSet.getInt("ed_cantapartamentos");
                                        char estatus = resultSet.getString("ed_estatus").charAt(0);
                                       edi.setCantPiso(cantPisos);
                                       edi.setCantApartamentos(cantAparts);
                                       edi.setEstatus(estatus);
		
					
                                }
			} catch (SQLException e){
				e.printStackTrace();
			}
        return edi;
    }
     
     public void modificarEdificio(Edificio edificio) {

        String tiraSQL = "UPDATE edificio SET ed_cantpisos = '" + edificio.getCantPiso() + "' , ed_cantapartamentos = '" + edificio.getCantApartamentos()
                + "'  WHERE ed_codedificio = '" + edificio.getCodigo()+ "' ";
        ejecutar(tiraSQL);

    }
     
     public void eliminarEdificio(Edificio edificio) {
        
        String tiraSQL = "UPDATE edificio SET ed_estatus = 'I' "
                + "WHERE ed_codedificio = '" + edificio.getCodigo() + "' ";
        ejecutar(tiraSQL);
    }
     
      public int totalEdificiosPorUrb(String codigo) {
        String tiraSQL = "Select count(*) as cant from edificio where ed_urbanizacioncodurbanizacion = '" + codigo + "'";
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
      
      public int totalEdificiosActivosPorUrb(String codigo) {
        String tiraSQL = "Select count(*) as cant from edificio where ed_urbanizacioncodurbanizacion = '" + codigo + "' and ed_estatus='A'";
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
     
     public ArrayList<Edificio> buscarEdificios() {
        ArrayList<Edificio> edificios = new ArrayList<Edificio>();
        String tiraSQL = "Select * FROM edificio where ed_estatus='A' order by ed_codedificio";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                String codedificio = resultSet.getString("ed_codedificio");
                int cantpisos = resultSet.getInt("ed_cantpisos");
                int cantapar = resultSet.getInt("ed_cantapartamentos");
                char estatus = resultSet.getString("ed_estatus").charAt(0);
                Edificio ed = new Edificio(codedificio, cantpisos, cantapar, null, estatus);
                edificios.add(ed);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return edificios;
    }
         
}
