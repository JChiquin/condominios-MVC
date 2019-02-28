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

import Modelo.Cuota;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CuotaDAO extends ConexionDAO {

    public CuotaDAO() {
        super();
    }

    public ArrayList<Cuota> ConsultarCuotas(String codUrb) {
        ArrayList<Cuota> cuotas = new ArrayList<Cuota>();
        String tiraSQL = "Select * FROM cuota where c_estatus='A' and c_urbanizacioncodurbanizacion = '" + codUrb + "' order by c_codcuota";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                String codigo = resultSet.getString("c_codcuota");
                float monto = resultSet.getFloat("c_montototal");
                int numeroMes = resultSet.getInt("c_nromes");
                int numeroAnio = resultSet.getInt("c_nroanio");
                char estatus = resultSet.getString("c_estatus").charAt(0);
                Cuota cu = new Cuota(monto, null, null, estatus, numeroMes, numeroAnio, codigo);
                cuotas.add(cu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cuotas;
    }

    public boolean modificarCuota(String codCuota, double monto) {
        String tiraSQL = "UPDATE cuota set c_montototal =" + monto + " where c_codcuota='" + codCuota + "'";
        return ejecutar(tiraSQL);
    }

    public boolean incluirCuota(Cuota cu) {
        String tiraSQL = "INSERT INTO cuota(c_codcuota, c_urbanizacioncodurbanizacion, c_montototal, c_nromes, c_nroanio, c_estatus) "
                + "Values('" + cu.getCodigoCuota() + "','" + cu.getCodigoCuota().substring(0, cu.getCodigoCuota().lastIndexOf("-") - 1) + "','" + cu.getMonto() + "','" + cu.getNroMes() + "','" + cu.getNroAnio() + "','A')";
        return ejecutar(tiraSQL);
    }

    public boolean eliminarCuota(Cuota codigo) {
        String tiraSQL = "UPDATE cuota SET c_estatus = 'I' "
                + "WHERE c_codcuota = '" + codigo.getCodigoCuota() + "' ";
        return ejecutar(tiraSQL);

    }

    public int totalCuotas(String codUrb) {
        String tiraSQL = "Select count(*) as cant from cuota where c_urbanizacioncodurbanizacion = '" + codUrb + "'";
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
}
