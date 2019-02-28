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

import Modelo.Propietario;
import lib.Validar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PropietarioDAO extends ConexionDAO {

    public PropietarioDAO() {
        super();
    }

    public ArrayList<Propietario> consultarPropietarios() {
        ArrayList<Propietario> propietarios = new ArrayList<Propietario>();
        String tiraSQL = "Select * FROM propietario where pr_estatus='A' order by pr_cedula";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                String cedula = resultSet.getString("pr_cedula");
                String nombre = resultSet.getString("pr_nombre");
                String apellido = resultSet.getString("pr_apellido");
                String direccion = resultSet.getString("pr_direccion");
                String telefono = resultSet.getString("pr_telefono");
                Date fecNac = new Date();
                fecNac = resultSet.getDate("pr_fechanacimiento");
                char estatus = resultSet.getString("pr_estatus").charAt(0);
                Propietario pr = new Propietario(cedula, nombre, apellido, direccion, telefono, fecNac, null, estatus);
                propietarios.add(pr);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return propietarios;
    }

    public void agregarPropietario(Propietario propietario) {
        String tiraSQL = "INSERT INTO propietario (pr_cedula, pr_nombre,pr_apellido, pr_direccion, pr_telefono, pr_fechaNacimiento, pr_estatus) values('"
                + propietario.getCedula() + "', '" + propietario.getNombre() + "', '" + propietario.getApellido() + "', '" + propietario.getDireccion() + "', '"
                + propietario.getTelefono() + "', '" + propietario.getFechaNac() + "','A')";

        ejecutar(tiraSQL);

    }

    public Propietario buscarPropietario(String cedulaPropietario) {
        Propietario propietario = new Propietario();
        String tiraSQL = "SELECT * FROM propietario "
                + "WHERE pr_cedula = '" + cedulaPropietario + "'";

        ResultSet resultSet = consulta(tiraSQL);
        try {
            if (resultSet.next()) {

                String nombre = resultSet.getString("pr_nombre");
                String apellido = resultSet.getString("pr_apellido");
                String direccion = resultSet.getString("pr_direccion");
                String telefono = resultSet.getString("pr_telefono");
                Date fecNac = new Date();
                fecNac = resultSet.getDate("pr_fechanacimiento");
                char estatus = resultSet.getString("pr_estatus").charAt(0);
                propietario.setNombre(nombre);
                propietario.setApellido(apellido);
                propietario.setDireccion(direccion);
                propietario.setTelefono(telefono);
                propietario.setFechaNac(fecNac);
                propietario.setEstatus(estatus);

            } else {
                propietario.setCedula(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return propietario;
    }

    public void modificarPropietario(Propietario propietario) {

        String tiraSQL = "UPDATE propietario SET pr_nombre = '" + propietario.getNombre() + "', pr_apellido = "
                + "'" + propietario.getApellido() + "' , pr_direccion = '" + propietario.getDireccion()
                + "',  pr_telefono= '" + propietario.getTelefono() + "'  "
                + "  WHERE pr_cedula = '" + propietario.getCedula() + "' ";
        ejecutar(tiraSQL);

    }

    public void reactivarPropietario(Propietario propietario) {
        String tiraSQL = "UPDATE propietario SET pr_estatus = '" + propietario.getEstatus() + "'  WHERE pr_cedula = '" + propietario.getCedula() + "' ";
        ejecutar(tiraSQL);
    }

    public void eliminarPropietario(Propietario propietario) {

        String tiraSQL = "UPDATE propietario SET pr_estatus = 'I' "
                + "WHERE pr_cedula = '" + propietario.getCedula() + "' ";
        ejecutar(tiraSQL);
    }

    public ArrayList<Propietario> consultarPropietariosUrbanizacion(String codigo) {
        ArrayList<Propietario> propietarios = new ArrayList<Propietario>();
        String tiraSQL = "select distinct pr_cedula, pr_nombre, pr_apellido, pr_direccion, pr_telefono, pr_fechanacimiento, u_codurbanizacion from urbanizacion, propietario, calle, casa where u_codurbanizacion = ca_urbanizacioncodurbanizacion and ca_nrocalle = cas_callenrocalle and cas_propietariocedula = pr_cedula and cas_estatus='A' and ca_estatus='A' and u_estatus='A' and pr_estatus='A' and u_codurbanizacion='"+codigo+"'\n"
                + "UNION ALL\n"
                + "select distinct pr_cedula, pr_nombre, pr_apellido, pr_direccion, pr_telefono, pr_fechanacimiento, u_codurbanizacion from urbanizacion, propietario, edificio, apartamento where u_codurbanizacion = ed_urbanizacioncodurbanizacion and ed_codedificio = ap_edificiocodedificio and ap_propietariocedula = pr_cedula and u_estatus='A' and pr_estatus='A' and ap_estatus='A' and ed_estatus='A' and u_codurbanizacion='"+codigo+"' order by u_codurbanizacion";
        ResultSet resultSet = consulta(tiraSQL);
        
        try {
            while (resultSet.next()) {
                String cedula = resultSet.getString("pr_cedula");
                String nombre = resultSet.getString("pr_nombre");
                String apellido = resultSet.getString("pr_apellido");
                String direccion = resultSet.getString("pr_direccion");
                String telefono = resultSet.getString("pr_telefono");
                Date fecNac = new Date();
                fecNac = resultSet.getDate("pr_fechanacimiento");
                Propietario pr = new Propietario(cedula, nombre, apellido, direccion, telefono, fecNac, null, 'A');
                propietarios.add(pr);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return propietarios;
    }
}
