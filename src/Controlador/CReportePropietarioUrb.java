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
import DAO.PropietarioDAO;
import DAO.UrbanizacionDAO;
import Modelo.Propietario;
import Modelo.Urbanizacion;
import Vista.VReportePropietarioUrb;
import lib.Validar;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class CReportePropietarioUrb implements ActionListener, ChangeListener {

    VReportePropietarioUrb vrp;
    ArrayList<Urbanizacion> listUrb;

    public CReportePropietarioUrb() {
        super();
        vrp = VReportePropietarioUrb.getInstancia(null, true, null);
        vrp.agregarListener(this, this);
        vrp.vaciarComboUrb();
        cargarUrbanizacionesCombo();
        vrp.setVisible(true);
    }

    public void cargarUrbanizacionesCombo() {
        vrp.addCmbUrbanizacion("Seleccione urbanización");
        UrbanizacionDAO urbDAO = new UrbanizacionDAO();
        listUrb = urbDAO.consultarUrbanizaciones(' ');
        for (Urbanizacion urb : listUrb) {
            vrp.addCmbUrbanizacion(urb.getNombre());
        }
    }

    public void CargarTablaPropietarios() {

        String[] titulos = {"Cedula", "Nombre", "Apellido", "Dirección", "Telefono", "Fecha Nacimiento"};
        String[] datos = new String[6];
        int[] anchos = {40, 40, 50, 150, 100, 100};

        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        PropietarioDAO urbDAO = new PropietarioDAO();
        ArrayList<Propietario> lisProp = urbDAO.consultarPropietariosUrbanizacion(listUrb.get(vrp.getSelectedIndexcmbUrb()-1).getCodigo());

        for (Propietario prop : lisProp) {
            datos[0] = prop.getCedula();
            datos[1] = prop.getNombre();
            datos[2] = prop.getApellido();
            datos[3] = prop.getDireccion();
            datos[4] = prop.getTelefono();
            datos[5] = String.valueOf(prop.getFechaNac());
            modelo.addRow(datos);

        }
        vrp.cargarTabla(anchos, modelo);
        vrp.maximoFilas();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Consultar")) {
            if (vrp.getSelectedIndexcmbUrb() > 0) {
                CargarTablaPropietarios();
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Limpiar")) {
            vrp.limpiarTabla();
        } else if (e.getActionCommand().equalsIgnoreCase("Alinear centro")) {
            vrp.alinearTexto(SwingConstants.CENTER);
        } else if (e.getActionCommand().equalsIgnoreCase("Alinear derecha")) {
            vrp.alinearTexto(SwingConstants.RIGHT);
        } else if (e.getActionCommand().equalsIgnoreCase("Alinear izquierda")) {
            vrp.alinearTexto(SwingConstants.LEFT);
        } else if (e.getActionCommand().equalsIgnoreCase("Cambiar color de letra")) {
            vrp.colorLetras(JColorChooser.showDialog(null, "Elige un color", Color.BLACK));
        } else if (e.getActionCommand().equalsIgnoreCase("Restaurar tabla por defecto")) {
            vrp.resturarTabla();
        } else if (e.getActionCommand().equalsIgnoreCase("Cambiar color fondo")) {
            vrp.colorFondo(JColorChooser.showDialog(null, "Elige un color", Color.BLACK));
        } else if (e.getActionCommand().equalsIgnoreCase("Cambiar color de bordes")) {
            Color colorBorde = JColorChooser.showDialog(null, "Elige un color", Color.BLACK);
            vrp.cambiarColorBordes(colorBorde);
        } else if (e.getActionCommand().equalsIgnoreCase("Cambiar color fila")) {
            Color color = (JColorChooser.showDialog(null, "Elige un color", Color.BLACK));
            vrp.cambiarColorFila(color);
        }

    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        if(ce.getSource().equals(vrp.getSpnDerecho()) || ce.getSource().equals(vrp.getSpnIzquierdo()) || ce.getSource().equals(vrp.getSpnInferior()) || ce.getSource().equals(vrp.getSpnSuperior())){
            vrp.cambiarGrosorBorde();
        }

    }
}
