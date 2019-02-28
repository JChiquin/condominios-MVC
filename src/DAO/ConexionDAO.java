/**
 * @author Chiquín, Jorge
 * @author Vargas, Alexander
 * @author Bonilla, José
 * @author Fernández, Ysidro
 * @author Rodríguez, Ronald
 */

package DAO;


import lib.Conexion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class ConexionDAO extends Conexion {
    
    public ConexionDAO() {
		super();
		establecerPropiedadesDeConexion("org.postgresql.Driver","condominio",
				"jdbc:postgresql://localhost:5432/", "postgres", "021195");
	}
    
}
