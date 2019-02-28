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
import DAO.CalleDAO;
import DAO.PropietarioDAO;
import DAO.UrbanizacionDAO;
import DAO.ViviendaDAO;
import Modelo.Calle;
import Modelo.Propietario;
import Modelo.Urbanizacion;
import Modelo.Vivienda;
import Vista.VCasa;
import lib.Validar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class CCasa implements ActionListener {
    private VCasa vca;
    private ArrayList<Urbanizacion> urbanizaciones;
    private ArrayList<Calle> calles;
    private ArrayList<Vivienda> casas;
    private ArrayList<Propietario> propietarios;

    public CCasa() {
        vca = new VCasa(null, true);
        vca.agregarListener(this);
        vca.setLocationRelativeTo(null);
        cargarUrbanizaciones();
        cargarPropietarios();
        vca.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("Limpiar")) {
            vca.limpiarCajas();
        } else if (ae.getActionCommand().equalsIgnoreCase("Regresar")) {
            vca.dispose();
        } else if (ae.getActionCommand().equalsIgnoreCase("Eliminar")) {
            eliminarCasa();
        } else if (ae.getActionCommand().equalsIgnoreCase("Modificar")) {
            if (vca.getIndexCmbPropietario() == 0) {
                Validar.mostrarMensaje("Debe llenar todos los campos", "Casa", 0);
            } else {
                modificarCasa(vca.getIndexCmbCasa() - 1);
            }
        } else if (ae.getActionCommand().equalsIgnoreCase("Incluir")) {
            if (esPosibleIncluir()) {
                habilitarParaIncluirCasa();
            } else {
                Validar.mostrarMensaje("No es posible incluir más casas a esa calle", "Casa", 0);
            }
        } else if (ae.getActionCommand().equalsIgnoreCase("Guardar")) {
            if (vca.getIndexCmbPropietario() == 0) {
                Validar.mostrarMensaje("Debe llenar todos los campos", "Casa", 0);
            } else {
                guardarCasa();
            }
        } else if (ae.getSource().equals(vca.getCmbUrbanizacion())) {
            if (vca.getIndexCmbUrbanizacion() > 0) {
                vca.enableCmbCalle(true);
                cargarCalles();

            } else if (vca.getIndexCmbUrbanizacion() == 0) {
                vca.limpiarCampos();
                vca.enableCmbCalle(false);
                vca.enableCmbCasa(false);
                vca.vaciarCmbCasa();
                vca.vaciarCmbCalle();
            }
        } else if (ae.getSource().equals(vca.getCmbCalle())) {
            if (vca.getIndexCmbCalle() > 0) {
                cargarCasas();
                vca.enableCmbCasa(true);
                vca.enableBtnIncluir(true);
            } else if (vca.getIndexCmbCalle() == 0) {
                vca.limpiarCampos();
                vca.enableCmbCasa(false);
                vca.enableBtnIncluir(false);
                vca.vaciarCmbCasa();
            }
        } else if (ae.getSource().equals(vca.getCmbCasa())) {
            if (vca.getIndexCmbCasa() > 0 && vca.getIndexCmbCasa() <= casas.size()) {
                buscarCasa(vca.getIndexCmbCasa() - 1);
                vca.enableParaModificar(true);
            } else if (vca.getIndexCmbCasa() == 0) {
                vca.limpiarCampos();
            }
        }

    }

    public void cargarUrbanizaciones() {
        vca.vaciarCmbUrbanizacione();
        vca.addCmbUrbanizacion("Seleccione urbanización");
        UrbanizacionDAO urbDAO = new UrbanizacionDAO();
        urbanizaciones = urbDAO.consultarUrbanizaciones('c');
        for (Urbanizacion urb : urbanizaciones) {
            vca.addCmbUrbanizacion(urb.getNombre());
        }
    }

    public void cargarCalles() {
        vca.vaciarCmbCalle();
        CalleDAO caDAO = new CalleDAO();
        calles = caDAO.buscarCallesPorUrb(urbanizaciones.get(vca.getIndexCmbUrbanizacion() - 1).getCodigo());
        for (Calle ca : calles) {
            vca.addCmbCalle(ca.getNroCalle());
        }

    }

    public void cargarTablaCasa() {
        String[] titulos = {"Nombre Urb.", "Nro. Calle", "Código Casa", "Cédula Prop.", "Cant. Pisos", "Cant. Habitaciones", "Cant. Baños"};
        String[] datos = new String[7];
        int[] anchos = {50, 20, 20, 30, 30, 15, 15, 15};

        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Vivienda ca : casas) {
            datos[0] = urbanizaciones.get(vca.getIndexCmbUrbanizacion() - 1).getNombre();
            datos[1] = calles.get(vca.getIndexCmbCalle() - 1).getNroCalle();
            datos[2] = ca.getCodigo();
            datos[3] = ca.getPropietario().getCedula();
            datos[4] = String.valueOf(ca.getNumPiso());
            datos[5] = String.valueOf(ca.getCantHabitaciones());
            datos[6] = String.valueOf(ca.getCantBanios());
            modelo.addRow(datos);
        }
        Tabla.cargarTabla(titulos, anchos, modelo, vca.getjTable1());
    }

    public void cargarCasas() {
        vca.vaciarCmbCasa();
        ViviendaDAO caDAO = new ViviendaDAO();
        casas = caDAO.buscarCasas(calles.get(vca.getIndexCmbCalle() - 1).getNroCalle());
        for (Vivienda ca : casas) {
            vca.addCmbCasa(ca.getCodigo());
        }

        cargarTablaCasa();
    }

    public void cargarPropietarios() {

        PropietarioDAO prDAO = new PropietarioDAO();
        propietarios = prDAO.consultarPropietarios();
        for (Propietario pr : propietarios) {
            vca.addCmbPropietario(pr.getCedula());
        }
    }

    public void buscarCasa(int i) {
        Vivienda aux = casas.get(i);
        vca.setValorCmbPropietario(aux.getPropietario().getCedula());
        vca.setValorSpnPiso(aux.getNumPiso());
        vca.setValorSpnBaños(aux.getCantBanios());
        vca.setValorSpnHabitaciones(aux.getCantHabitaciones());
    }

    public void modificarCasa(int i) {
        Vivienda aux = casas.get(i);
        aux.getPropietario().setCedula(vca.getValorCmbPropietario());
        aux.setCantBanios(vca.getValorSpnBaños());
        aux.setCantHabitaciones(vca.getValorSpnHabitaciones());
        aux.setNumPiso(vca.getValorSpnPiso());
        ViviendaDAO caDAO = new ViviendaDAO();
        if (caDAO.modificarCasa(aux)) {
            Validar.mostrarMensaje("Modificado con exito!", "Casa", 1);
        } else {
            Validar.mostrarMensaje("¡Error!", "Casa", 0);
        }
        vca.enableParaModificar(false);
        vca.limpiarCajas();
    }

    public void habilitarParaIncluirCasa() {
        vca.enableParaIncluir(true);
        ViviendaDAO viDAO = new ViviendaDAO();
        int cant = viDAO.totalCasasPorCalle(calles.get(vca.getIndexCmbCalle() - 1).getNroCalle());

        String valor = "";
        if (cant < 9) {
            valor = "0";
        }
        valor += cant + 1;
        vca.addCmbCasa(calles.get(vca.getIndexCmbCalle() - 1).getNroCalle() + " - " + valor);
        vca.setIndexCmbCasa(cant + 1);
    }

    public boolean esPosibleIncluir() {
        int maxCasasCalle = calles.get(vca.getIndexCmbCalle() - 1).getCantCasas();
        ViviendaDAO viDAO = new ViviendaDAO();
        int cantCasas = viDAO.totalCasasPorCalle(calles.get(vca.getIndexCmbCalle() - 1).getNroCalle());
        if (cantCasas < maxCasasCalle) {
            return true;
        } else {
            return false;
        }
    }

    public void guardarCasa() {
        Vivienda ca = new Vivienda(vca.getValorSpnPiso(), propietarios.get(vca.getIndexCmbPropietario() - 1),
                vca.getValorCmbCasa(), vca.getValorSpnHabitaciones(), vca.getValorSpnBaños(), 'A', 'A', null);
        ViviendaDAO viDAO = new ViviendaDAO();
        if (viDAO.incluirCasa(ca)) {
            Validar.mostrarMensaje("Casa incluida con éxito!", "Casa", 1);
        } else {
            Validar.mostrarMensaje("¡Error!", "Casa", 0);
        }
        vca.limpiarCajas();
    }

    public void eliminarCasa() {
        ViviendaDAO viDAO = new ViviendaDAO();
        if (viDAO.eliminarCasa(casas.get(vca.getIndexCmbCasa() - 1))) {
            Validar.mostrarMensaje("Casa eliminada con éxito!", "Casa", 1);
        } else {
            Validar.mostrarMensaje("Error!", "Casa", 0);
        }
        vca.limpiarCajas();

    }

}
