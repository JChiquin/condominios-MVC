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
import DAO.ViviendaDAO;
import DAO.EdificioDAO;
import DAO.PropietarioDAO;
import DAO.UrbanizacionDAO;
import Modelo.Vivienda;
import Modelo.Edificio;
import Modelo.Propietario;
import Modelo.Urbanizacion;
import Vista.VApartamento;
import lib.Validar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CApartamento implements ActionListener {

    private VApartamento vap;
    private ArrayList<Urbanizacion> urbanizaciones;
    private ArrayList<Edificio> edificios;
    private ArrayList<Vivienda> apartamentos;
    private ArrayList<Propietario> propietarios;

    public CApartamento() {
        vap = new VApartamento(null, true);
        vap.agregarListener(this);
        vap.setLocationRelativeTo(null);
        cargarUrbanizaciones();
        cargarPropietarios();
        vap.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("Limpiar")) {
            vap.limpiarCajas();
        } else if (ae.getActionCommand().equalsIgnoreCase("Regresar")) {
            vap.dispose();
        } else if (ae.getActionCommand().equalsIgnoreCase("Eliminar")) {
            eliminarApartamento();
        } else if (ae.getActionCommand().equalsIgnoreCase("Modificar")) {
            if (vap.getIndexCmbPropietario() == 0) {
                Validar.mostrarMensaje("Debe llenar todos los campos", "Apartamento", 0);
            } else {
                modificarApartamento(vap.getIndexCmbApartamento() - 1);
            }
        } else if (ae.getActionCommand().equalsIgnoreCase("Incluir")) {
            if (esPosibleIncluir()) {
                habilitarParaIncluirApartamento();
            } else {
                Validar.mostrarMensaje("No es posible incluir más apartamentos a ese edificio", "Apartamento", 0);
            }
        } else if (ae.getActionCommand().equalsIgnoreCase("Guardar")) {
            if (vap.getIndexCmbPropietario() == 0) {
                Validar.mostrarMensaje("Debe llenar todos los campos", "Apartamento", 0);
            } else {
                guardarApartamento();
            }
        } else if (ae.getSource().equals(vap.getCmbUrbanizacion())) {
            if (vap.getIndexCmbUrbanizacion() > 0) {
                vap.enableCmbEdificio(true);
                cargarEdificios();

            } else if (vap.getIndexCmbUrbanizacion() == 0) {
                vap.limpiarCampos();
                vap.enableCmbEdificio(false);
                vap.enableCmbApartamento(false);
                vap.vaciarCmbApartamento();
                vap.vaciarCmbEdificio();
            }
        } else if (ae.getSource().equals(vap.getCmbEdificio())) {
            if (vap.getIndexCmbEdificio() > 0) {
                cargarApartamentos();
                vap.enableCmbApartamento(true);
                vap.enableBtnIncluir(true);
            } else if (vap.getIndexCmbEdificio() == 0) {
                vap.limpiarCampos();
                vap.enableCmbApartamento(false);
                vap.enableBtnIncluir(false);
                vap.vaciarCmbApartamento();
            }
        } else if (ae.getSource().equals(vap.getCmbApartamento())) {
            if (vap.getIndexCmbApartamento() > 0 && vap.getIndexCmbApartamento() <= apartamentos.size()) {
                buscarApartamento(vap.getIndexCmbApartamento() - 1);
                vap.enableParaModificar(true);
            } else if (vap.getIndexCmbApartamento() == 0) {
                vap.limpiarCampos();
            }
        }

    }

    public void cargarUrbanizaciones() {
        vap.vaciarCmbUrbanizacione();
        vap.addCmbUrbanizacion("Seleccione urbanización");
        UrbanizacionDAO urbDAO = new UrbanizacionDAO();
        urbanizaciones = urbDAO.consultarUrbanizaciones('e');
        for (Urbanizacion urb : urbanizaciones) {
            vap.addCmbUrbanizacion(urb.getNombre());
        }
    }

    public void cargarEdificios() {
        vap.vaciarCmbEdificio();
        EdificioDAO edDAO = new EdificioDAO();
        edificios = edDAO.buscarEdificiosPorUrbanizacion(urbanizaciones.get(vap.getIndexCmbUrbanizacion() - 1).getCodigo());
        for (Edificio ed : edificios) {
            vap.addCmbEdificio(ed.getCodigo());
        }

    }

    public void cargarTablaApart() {
        String[] titulos = {"Nombre Urb.", "Código Edif.", "Código Apto.", "Cédula Prop.", "Nro. Piso", "Cant. Habitaciones", "Cant. Baños"};
        String[] datos = new String[7];
        int[] anchos = {50, 20, 20, 30, 30, 15, 15, 15};

        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Vivienda ap : apartamentos) {
            datos[0] = urbanizaciones.get(vap.getIndexCmbUrbanizacion() - 1).getNombre();
            datos[1] = edificios.get(vap.getIndexCmbEdificio() - 1).getCodigo();
            datos[2] = ap.getCodigo();
            datos[3] = ap.getPropietario().getCedula();
            datos[4] = String.valueOf(ap.getNumPiso());
            datos[5] = String.valueOf(ap.getCantHabitaciones());
            datos[6] = String.valueOf(ap.getCantBanios());
            modelo.addRow(datos);
        }
        Tabla.cargarTabla(titulos, anchos, modelo, vap.getjTable1());
    }

    public void cargarApartamentos() {
        vap.vaciarCmbApartamento();
        ViviendaDAO apDAO = new ViviendaDAO();
        apartamentos = apDAO.buscarApartamentos(edificios.get(vap.getIndexCmbEdificio() - 1).getCodigo());
        for (Vivienda ap : apartamentos) {
            vap.addCmbApartamento(ap.getCodigo());
        }

        cargarTablaApart();
    }

    public void cargarPropietarios() {

        PropietarioDAO prDAO = new PropietarioDAO();
        propietarios = prDAO.consultarPropietarios();
        for (Propietario pr : propietarios) {
            vap.addCmbPropietario(pr.getCedula());
        }
    }

    public void buscarApartamento(int i) {
        Vivienda aux = apartamentos.get(i);
        vap.setValorCmbPropietario(aux.getPropietario().getCedula());
        vap.setValorSpnPiso(aux.getNumPiso());
        vap.setValorSpnBaños(aux.getCantBanios());
        vap.setValorSpnHabitaciones(aux.getCantHabitaciones());
    }

    public void modificarApartamento(int i) {
        Vivienda aux = apartamentos.get(i);
        aux.getPropietario().setCedula(vap.getValorCmbPropietario());
        aux.setCantBanios(vap.getValorSpnBaños());
        aux.setCantHabitaciones(vap.getValorSpnHabitaciones());
        ViviendaDAO apDAO = new ViviendaDAO();
        if (apDAO.modificarApartamento(aux)) {
            Validar.mostrarMensaje("Modificado con exito!", "Apartamento", 1);
        } else {
            Validar.mostrarMensaje("¡Error!", "Apartamento", 0);
        }
        vap.enableParaModificar(false);
        vap.limpiarCajas();
    }

    public void habilitarParaIncluirApartamento() {
        vap.enableParaIncluir(true);
        ViviendaDAO viDAO = new ViviendaDAO();
        int cant = viDAO.totalApartamentosPorEdificio(edificios.get(vap.getIndexCmbEdificio() - 1).getCodigo());
        vap.setMaximoPisos(edificios.get(vap.getIndexCmbEdificio() - 1).getCantPiso());

        String valor = "";
        if (cant < 9) {
            valor = "0";
        }
        valor += cant + 1;
        vap.addCmbApartamento(edificios.get(vap.getIndexCmbEdificio() - 1).getCodigo() + " - " + valor);
        vap.setIndexCmbApartamento(cant + 1);
    }

    public boolean esPosibleIncluir() {
        int maxApartamentosEdificio = edificios.get(vap.getIndexCmbEdificio() - 1).getCantApartamentos();
        ViviendaDAO viDAO = new ViviendaDAO();
        int cantApartamentos = viDAO.totalApartamentosPorEdificio(edificios.get(vap.getIndexCmbEdificio() - 1).getCodigo());
        if (cantApartamentos < maxApartamentosEdificio) {
            return true;
        } else {
            return false;
        }
    }

    public void guardarApartamento() {
        Vivienda ap = new Vivienda(vap.getValorSpnPiso(), propietarios.get(vap.getIndexCmbPropietario() - 1),
                vap.getValorCmbApartamento(), vap.getValorSpnHabitaciones(), vap.getValorSpnBaños(), 'A', 'A', null);
        ViviendaDAO viDAO = new ViviendaDAO();
        if (viDAO.incluirApartamento(ap)) {
            Validar.mostrarMensaje("Apartamento incluido con éxito!", "Apartamento", 1);
        } else {
            Validar.mostrarMensaje("¡Error!", "Apartamento", 0);
        }
        vap.limpiarCajas();
    }

    public void eliminarApartamento() {
        ViviendaDAO viDAO = new ViviendaDAO();
        if (viDAO.eliminarApartamento(apartamentos.get(vap.getIndexCmbApartamento() - 1))) {
            Validar.mostrarMensaje("Apartamento eliminado con éxito!", "Apartamento", 1);
        } else {
            Validar.mostrarMensaje("Error!", "Apartamento", 0);
        }
        vap.limpiarCajas();

    }

}
