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
import Modelo.Gasto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Vista.VGastos;
import java.text.SimpleDateFormat;
import java.util.Date;
import lib.Validar;

public class GastosDAO extends ConexionDAO {

    public GastosDAO() {
        super();
    }

    public void IncluirGastos(Gasto gt) {
        String tiraSQL = " INSERT INTO gasto( g_codgasto, g_tipogastocodtipo, g_nombre, g_descripcion, g_estatus) VALUES('"
                + gt.getCodigo() + "', '" + gt.getTipo() + "', '" + gt.getNombre() + "', '" + gt.getDescripcion() + "','A')";
        ejecutar(tiraSQL);

    }

    public boolean IncluirGastoPorCuota(String codCuota, Gasto gt) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String tiraSQL = "insert into gastoporcuota (gpc_cuotacodcuota, gpc_gastocodgasto, gpc_monto, gpc_fecha, gpc_estatus) "
                + "values('" + codCuota + "', '" + gt.getCodigo() + "', '" + gt.getMonto() + "', '" + formato.format(gt.getFecha()) + "', 'A')";
        return ejecutar(tiraSQL);

    }

    public void eliminarGastosPorCuota(String codCuota) {
        String tiraSQL = "DELETE FROM gastoporcuota where gpc_cuotacodcuota='" + codCuota + "'";
        ejecutar(tiraSQL);
    }

    public ArrayList<Gasto> consultarGastos() {
        ArrayList<Gasto> arregloG = new ArrayList<Gasto>();
        VGastos vg = null;
        String tiraSQL = "Select * FROM gasto WHERE g_estatus='A' order by g_codgasto";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {

                String codigo = resultSet.getString("g_codgasto");
                String nombre = resultSet.getString("g_nombre");
                String descripcion = resultSet.getString("g_descripcion");
                int tipo = resultSet.getInt("g_tipogastocodtipo");
                char estatus = resultSet.getString("g_estatus").charAt(0);
                Gasto gt = new Gasto(codigo, nombre, descripcion, tipo, 0.00, estatus, null);
                arregloG.add(gt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arregloG;
    }

    public Gasto BuscarGastos(String codigoG) {
        String tiraSQL = "Select * FROM gasto where g_codgasto='" + codigoG + "' and g_estatus='A'";
        Gasto gasto = null;
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                String codigo = resultSet.getString("g_codgasto");
                String nombre = resultSet.getString("g_nombre");
                int tipo = resultSet.getInt("g_tipogastocodtipo");
                String descripcion = resultSet.getString("g_descripcion");
                char estatus = resultSet.getString("g_estatus").charAt(0);
                gasto = new Gasto(codigo, nombre, descripcion, tipo, 0.00, estatus, null);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return gasto;

    }

    public void modificarGasto(Gasto gasto) {
        String tiraSQL = "UPDATE gasto SET g_tipogastocodtipo = '" + gasto.getTipo() + "', g_nombre='" + gasto.getNombre() + "', g_descripcion = '" + gasto.getDescripcion() + "' where g_codgasto = '" + gasto.getCodigo() + "'";
        ejecutar(tiraSQL);

    }

    public void eliminarGastos(String codigo) {
        String tiraSQL = "UPDATE gasto SET g_estatus = 'I' "
                + "WHERE g_codgasto = '" + codigo + "' ";
        ejecutar(tiraSQL);
    }

    public ArrayList<Gasto> GastoPorCuota(String codigoC) {
        ArrayList<Gasto> arregloG = new ArrayList<Gasto>();
        String tiraSQL = "Select g_codgasto,g_tipogastocodtipo,g_nombre,gpc_monto,g_estatus,gpc_fecha FROM gasto,gastoporcuota where g_codgasto=gpc_gastocodgasto and gpc_cuotacodcuota ='" + codigoC + "' and g_estatus='A' and gpc_estatus='A'";
        Gasto gasto = null;
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                String codigo = resultSet.getString("g_codgasto");
                String nombre = resultSet.getString("g_nombre");
                int tipo = resultSet.getInt("g_tipogastocodtipo");
                float monto = resultSet.getFloat("gpc_monto");
                char estatus = resultSet.getString("g_estatus").charAt(0);
                Date fecha = resultSet.getDate("gpc_fecha");
                gasto = new Gasto(codigo, nombre, "", tipo, monto, estatus, fecha);
                arregloG.add(gasto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arregloG;

    }

}
