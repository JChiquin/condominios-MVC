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
import DAO.PropietarioDAO;
import DAO.UrbanizacionDAO;
import Modelo.Cuota;
import Modelo.Propietario;
import Modelo.Urbanizacion;
import Vista.VReporteCuotaporUrbanizacion;
import Vista.VReportePropietarioUrb;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class CreporteCuotaporUrbanizacion implements ActionListener{
    VReporteCuotaporUrbanizacion vrc;
    ArrayList<Urbanizacion> listUrb ;
    public CreporteCuotaporUrbanizacion(){
        super();
        vrc = new VReporteCuotaporUrbanizacion(null, true, null);
        vrc.agregarListener(this);
         cargarUrbanizacionesCombo();
        vrc.setVisible(true);
    }
    
     public void cargarUrbanizacionesCombo() {
        vrc.addCmbUrbanizacion("Seleccione urbanización");
        UrbanizacionDAO urbDAO = new UrbanizacionDAO();
        
        listUrb = urbDAO.consultarUrbanizaciones(' ');
        for (Urbanizacion urb : listUrb) {
            vrc.addCmbUrbanizacion(urb.getNombre());
        }
    }
     
     
      public void CargarTablaCuotas(){
        String[] titulos = {"Codigo", "Monto", "Numero de mes", "Año" };
        
		String[] datos = new String[4];
      
		int[] anchos = { 100, 40, 40,40};

		DefaultTableModel modelo = new DefaultTableModel(null, titulos) {

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		 CuotaDAO cuDAO = new CuotaDAO();
                 ArrayList<Cuota> lisCuota = cuDAO.ConsultarCuotas(listUrb.get(vrc.getSelectedIndexCmbCodigoUrb()-1).getCodigo());
		
		for (int i = 0; i < lisCuota.size(); i++) {

			Cuota cu = lisCuota.get(i);
	                        datos[0] = cu.getCodigoCuota();
				datos[1] = String.valueOf(cu.getMonto());
				datos[2] = String.valueOf(cu.getNroMes());
				datos[3] = String.valueOf(cu.getNroAnio());
				modelo.addRow(datos);
		
		}
                Tabla.cargarTabla(titulos, anchos, modelo,vrc.getjTable1());

    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource().equals(vrc.getCmbCodigoUrb())){
            if(vrc.getSelectedIndexCmbCodigoUrb()>0)
            CargarTablaCuotas();
       }
    }
    
}

  