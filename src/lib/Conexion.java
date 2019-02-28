/*
 * Conexion.java 
 * 
 * Esta clase contiene los metodos para conectarse a la base de datos,
 * ejecutar consultas y tiras SQL
 /**
 * @author Chiquín, Jorge
 * @author Vargas, Alexander
 * @author Bonilla, José
 * @author Fernández, Ysidro
 * @author Rodríguez, Ronald
 */
package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @version 1.0
 *
 * @author Bracho, Jose
 * @author Salazar, Pedro
 * @author Sanchez, Jose
 * @author Suarez, Tomas
 * @author Montalban, Wilfredo
 *
 */
public class Conexion {

    // CONSTRUCTOR VACIO
    public Conexion() {
        super();
    }

    // ATRIBUTOS
    private String driver = "";
    private String url = "";
    private String usuario = "";
    private String contrasena = "";
    private Connection conexion = null;

    // ESTABLECER LAS PROPIEDADES DE LA CONEXION A LA BASE DE DATOS
    public void establecerPropiedadesDeConexion(String driver,
            String nombreBaseDeDatos, String url, String usuario,
            String contrasena) {
        this.driver = driver;
        this.url = url + nombreBaseDeDatos;
        this.usuario = usuario;
        this.contrasena = contrasena;

    }

    //CONECTARSE A LA BASE DE DATOS
    public void conectar() {

        try {
            // Cargamos el Driver
            Class.forName(this.driver);
            // Creamos un enlace hacia la base de datos
            conexion = (Connection) DriverManager.getConnection(this.url,
                    this.usuario, this.contrasena);         
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error con la conexi�n a la "
                    + "base de datos. \n" + e);
        }
    }

    //REALIZAR UNA CONSULTA SQL
    public ResultSet consulta(String tiraSQL) {
        conectar();
        ResultSet resultSet = null;
        if (conexion != null) {
            try {
                Statement st = conexion.createStatement();
                resultSet = (ResultSet) st.executeQuery(tiraSQL);
                conexion.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

        return resultSet;
    }

    // EJECUTAR UNA INSTRUCCION SQL
    public boolean ejecutar(String tiraSQL) {
        boolean instruccionEjecutada = false;
        Statement statement = null;
        conectar();
        try {
            statement = conexion.createStatement();
            statement.executeUpdate(tiraSQL);
            if (statement != null) {
                statement.close();
            }
            instruccionEjecutada = true;
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
        return instruccionEjecutada;
    }
}
