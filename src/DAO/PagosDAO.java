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
import Modelo.Pago;
import Modelo.Vivienda;
import Modelo.Propietario;
import lib.Validar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PagosDAO extends ConexionDAO{
    
     public PagosDAO() {
        super();
}
  public ArrayList<Pago> consultarPagos() {
     
        ArrayList<Pago> pagos = new ArrayList<Pago>();
        String tiraSQL = "Select * FROM pago WHERE pa_estatus='A' order by pa_codpago";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while(resultSet.next()) {
                String codigo= resultSet.getString("pa_codpago");
               
                Pago pag = new Pago();
                pag.setCodigo(codigo);
                pagos.add(pag);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pagos;
    
    } 
     
   public void eliminarPago(String codigo) {
        String tiraSQL = "UPDATE pago SET pa_estatus = 'I' "
                + "WHERE pa_codpago = '" + codigo + "' ";
        ejecutar(tiraSQL);
    }
  
  
     public ArrayList<Pago> buscarPago(String codigo)
     {
          ArrayList<Pago> pagos = new ArrayList<Pago>();
        String tiraSQL = "select pr_cedula,cas_codcasa,c_codcuota,c_montototal from pago,propietario,casa,cuota where pr_cedula=cas_propietariocedula and cas_codcasa=pa_casacodcasa and c_codcuota=pa_cuotacodcuota  and pa_codpago='"+codigo+"' and pa_estatus='A'"
                +" union select pr_cedula,ap_codapartamento,c_codcuota,c_montototal from pago,propietario,apartamento,cuota where pr_cedula=ap_propietariocedula and ap_codapartamento=pa_apartamentocodapartamento and c_codcuota=pa_cuotacodcuota  and pa_codpago='"+codigo+"' and pa_estatus='A'";
        ResultSet resultSet = consulta(tiraSQL);
        try {
            while (resultSet.next()) {
                String cedula = resultSet.getString("pr_cedula");
                if(resultSet.getString("cas_codcasa") != "" )
                {
                    String vivienda = resultSet.getString("cas_casa");
                }else
                {
                    String vivienda= resultSet.getString("ap_codapartamento");
                }
                
                double cuota = resultSet.getInt("c_codcuota");
                Pago pg = new Pago(null, cuota, null, null, null);
                pagos.add(pg);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pagos;
    }
     
   public ArrayList<Vivienda> consultarViviendasPorPro(String cedulaPro) {

        ArrayList<Vivienda> viv = new ArrayList<Vivienda>();

        String tiraSQL = "SELECT * from apartamento, propietario where ap_propietariocedula = '" + cedulaPro
                + "' and ap_propietariocedula = pr_cedula and ap_estatus = 'A'";
        String tiraSQL2 = "SELECT * from casa, propietario where cas_propietariocedula = '" + cedulaPro
                + "' and cas_propietariocedula = pr_cedula and cas_estatus = 'A'";

        ResultSet resultSet = consulta(tiraSQL);
        ResultSet resultSet2 = consulta(tiraSQL2);
        try {
            while (resultSet.next()) {

                int nroPiso = resultSet.getInt("ap_nropiso");
                String codapartamento = resultSet.getString("ap_codapartamento");
                int cantHab = resultSet.getInt("ap_canthabitaciones");
                int cantBanios = resultSet.getInt("ap_cantbanios");
                char estatus = resultSet.getString("ap_estatus").charAt(0);
                Propietario pr = new Propietario();
                pr.setCedula(resultSet.getString("ap_propietariocedula"));
                Vivienda vi = new Vivienda(nroPiso, pr, codapartamento, cantHab, cantBanios, 'A', 'A', null);
                viv.add(vi);
            }
            while (resultSet2.next()) {

                int nroPisos = resultSet2.getInt("cas_cantpisos");
                String codCasa = resultSet2.getString("cas_codcasa");
                int cantHab = resultSet2.getInt("cas_canthabitaciones");
                int cantBanios = resultSet2.getInt("cas_cantbanios");
                char estatus = resultSet2.getString("cas_estatus").charAt(0);
                Propietario pr = new Propietario();
                pr.setCedula(resultSet2.getString("cas_propietariocedula"));
                Vivienda vi = new Vivienda(nroPisos, pr, codCasa, cantHab, cantBanios, 'A', 'C', null);
                viv.add(vi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viv;
    }  
     
     
    public int cantPago() {
        int cant = 0;
        String tiraSQL = "SELECT count(*) as cantidad FROM pago ";
        ResultSet resultSet = consulta(tiraSQL);
        
         try {
             resultSet.next();
             cant = resultSet.getInt("cantidad");
         } catch (SQLException ex) {
             Logger.getLogger(PagosDAO.class.getName()).log(Level.SEVERE, null, ex);
         }

        return cant;

    }
     public void efectuarPago(Pago pago,Cuota cuota,Vivienda vivienda) {
        String tiraSQL = "INSERT INTO pago (pa_codpago, pa_casacodcasa, pa_apartamentocodapartamento, pa_cuotacodcuota, pa_fecha,pa_estatus) values('"
                + pago.getCodigo() + "', '" + vivienda.getCodigo() + "', '" + vivienda.getCodigo() + "', '" + cuota.getCodigoCuota() + "','" + pago.getFecha() + "','A')";
        ejecutar(tiraSQL);
         Validar.mostrarMensaje("Pago Efectuado con Exito", "Pago", 1);
    }
}

