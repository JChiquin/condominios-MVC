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
package Controlador;

import lib.Tabla;
import DAO.CuotaDAO;
import DAO.GastosDAO;
import DAO.UrbanizacionDAO;
import Modelo.Cuota;
import Modelo.Gasto;
import Modelo.Urbanizacion;
import Vista.VReporteGastoCuota;
import lib.Validar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public final class CReporteGastoCuota implements ActionListener{
    
    VReporteGastoCuota vrd;
    private ArrayList<Urbanizacion> urbanizaciones;
    private ArrayList<Cuota> cuotas;
    public CReporteGastoCuota(){
        super();
        vrd = new VReporteGastoCuota(null, true, null);
        vrd.agregarListener(this);
        cargarUrbanizacionesCombo();
        vrd.setLocationRelativeTo(null);
        vrd.setVisible(true);
    }
    
   public void cargarUrbanizacionesCombo() {
        vrd.vaciarCmbUrbanizacione();
        vrd.addCmbUrbanizacion("Seleccione urbanización");
        UrbanizacionDAO urbDAO = new UrbanizacionDAO();
        ArrayList<Urbanizacion> urbdanizaciones ;
        urbanizaciones = urbDAO.consultarUrbanizaciones(' ');
        for (Urbanizacion urb : urbanizaciones) {
            vrd.addCmbUrbanizacion(urb.getNombre());
        }
    }
    
    public void cargarCodCuotaCombo(){
        vrd.vaciarcmbCuotas();
        vrd.addcmbCocCuota("Seleccione una cuota");
        CuotaDAO cuDAO = new CuotaDAO();
        cuotas = cuDAO.ConsultarCuotas(urbanizaciones.get(vrd.getSelectedIndexCmbCodigoUrb() - 1).getCodigo());
        for (Cuota cu : cuotas) {
            vrd.addcmbCocCuota(cu.getCodigoCuota());
        }
        
        }
    
        public void CargarTablaGastos(){
        String[] titulos = {"Codigo Gasto", "Nombre Gasto", "Tipo Gasto","Fecha Gasto", "Monto Gasto" };
        
		String[] datos = new String[5];
      
		int[] anchos = { 100, 140, 140, 100,100};

		DefaultTableModel modelo = new DefaultTableModel(null, titulos) {

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		          GastosDAO gaDAO = new GastosDAO();
                 ArrayList<Gasto> lisGasto = gaDAO.GastoPorCuota(vrd.getSelectedItemCmbCodCuota());
		
		for (int i = 0; i < lisGasto.size(); i++) {

			Gasto ga = lisGasto.get(i);
	                        datos[0] = ga.getCodigo();
				datos[1] = ga.getNombre();
				datos[2] = (ga.getTipo() == 1 ? "Fijo" : "Extraordinario");
                                datos[3] = String.valueOf(ga.getFecha());
				datos[4] = String.valueOf(ga.getMonto());
				modelo.addRow(datos);
		
		}
                Tabla.cargarTabla(titulos, anchos, modelo,vrd.getjTable1());

    }
@Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(vrd.getCmbUrbanizaciones())){
       if(vrd.getSelectedIndexCmbCodigoUrb() > 0){
             vrd.enablecmbCodCuota(true);
             cargarCodCuotaCombo();
            }           
        }
      else if(e.getSource().equals(vrd.getCmbCodCuota())){
           if(vrd.getSelectedIndexCmbCodCuota() > 0){
               CargarTablaGastos();
           }
       }
    
   }
}   
    
   
    


    

