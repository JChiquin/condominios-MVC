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
import DAO.ConexionDAO;
import lib.Validar;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmpresaDAO extends ConexionDAO {

    public EmpresaDAO() {
        super();
    }

    public void agregarEmpresa(Empresa empresa) {
        String tiraSQL = "INSERT INTO empresa (e_rif, e_nombre, e_direccion, e_telefono, e_estatus) values('" +
                empresa.getRif() + "', '" + empresa.getNombre() + "', '" + empresa.getDireccion() + "', '" + empresa.getTelefono() + "','A')";
          
        ejecutar(tiraSQL);

    }
   public void modificarEmpresa(Empresa empresa) {
       
		String tiraSQL = "UPDATE empresa SET e_rif = '"+ empresa.getRif() + "', e_nombre = '" + empresa.getNombre() + "', e_direccion = '" + empresa.getDireccion() + "', e_telefono = '" + empresa.getTelefono() + "' ";
		ejecutar(tiraSQL);
                
	}
    public Empresa buscar()
    {
        
        	Empresa emp = null;
			String tiraSQL = "SELECT * FROM empresa";
		
			ResultSet resultSet = consulta(tiraSQL);
			try {
				if (resultSet.next()){
					String codigo = resultSet.getString("e_rif");
					String nombre = resultSet.getString("e_nombre");
					String direccion = resultSet.getString("e_direccion");
					String telefono  = resultSet.getString("e_telefono");
                                        
                                        emp=new Empresa(codigo, nombre, direccion, telefono, null,'A');
		
					
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
        return emp;
    }
            
}
