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

import Modelo.FormaPago;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FormaPagoDAO extends ConexionDAO{

    public FormaPagoDAO() {
        super();
    }
     public ArrayList<FormaPago> consultarFormaPago() {
     
        ArrayList<FormaPago> formaPagos = new ArrayList<>();
        String tiraSQL = "Select fp_nombre FROM formapago WHERE fp_estatus='A' order by fp_nombre";
        ResultSet resultSet = consulta(tiraSQL);
        
        try {
            while(resultSet.next()) {
                String nombre= resultSet.getString("fp_nombre");
               
                FormaPago fp = new FormaPago();
                fp.setTipoPago(nombre);
                formaPagos.add(fp);
               }
        } catch (SQLException ex) { 
            Logger.getLogger(FormaPagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formaPagos;
    }
}