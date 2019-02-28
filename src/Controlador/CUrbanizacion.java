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
import DAO.EdificioDAO;
import DAO.EmpresaDAO;
import DAO.UrbanizacionDAO;
import Modelo.Empresa;
import Modelo.Urbanizacion;
import Vista.VUrbanizacion;
import lib.Validar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CUrbanizacion implements ActionListener, KeyListener {

    VUrbanizacion ve;
    public int cant = 0;
    private ArrayList<Urbanizacion> urbanizaciones;

    public CUrbanizacion() {
        super();
        ve = new VUrbanizacion(null, true, null);
        ve.agregarListener(this, this);
        cargarEmpresa();
        cargarUrbanizacionesCombo();
        cargarTablaUrb();
        ve.bloquearCampos(false);
        ve.bloquearcamposUrb(false);
        UrbanizacionDAO urbDAO = new UrbanizacionDAO();
        cant = urbDAO.cantUrbanizaciones();
        ve.setSelectedrdbCasa(true);
        ve.setVisible(true);
    }

    public void cargarEmpresa() {

        EmpresaDAO empDAO = new EmpresaDAO();
        Empresa emp = empDAO.buscar();
        ve.setTxtRifSt(emp.getRif());
        ve.setTxtNombreSt(emp.getNombre());
        ve.setTxtDireccionSt(emp.getDireccion());
        ve.setTxtTelefonoSt(emp.getTelefono());

    }

    public void modificarEmpresa() {

        Empresa emp = new Empresa(ve.getTxtRifSt(), ve.getTxtNombreSt(), ve.getTxtDirSt(), ve.getTxtTelfSt(), null, 'A');

        EmpresaDAO empDAO = new EmpresaDAO();
        empDAO.modificarEmpresa(emp);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String valor = "";
        if (ae.getActionCommand().equalsIgnoreCase("Limpiar")) {
            ve.Limpiar();
            cargarUrbanizacionesCombo();
            ve.bloquearcamposUrb(false);
            ve.setTxtIncluir("Incluir");
        } else if (ae.getActionCommand().equalsIgnoreCase("Regresar")) {
            ve.dispose();
        } else if (ae.getActionCommand().equalsIgnoreCase("Modificar")) {

            ve.bloquearCampos(true);
            ve.setTxtModicar("Guardar");
        } else if (ae.getActionCommand().equalsIgnoreCase("Guardar")) {
            ve.bloquearCampos(false);
            ve.setTxtModicar("Modificar");
            modificarEmpresa();
            Validar.mostrarMensaje("Empresa Modoficada con éxito", "Modificar", JOptionPane.INFORMATION_MESSAGE);
        } else if (ae.getActionCommand().equalsIgnoreCase("Incluir")) {
            UrbanizacionDAO udao = new UrbanizacionDAO();
            cant = udao.cantUrbanizaciones();
            if (cant < 9) {
                valor = "000";
            } else if (cant < 99) {
                valor = "00";
            } else if (cant < 999) {
                valor = "0";
            } else {
                valor = "";
            }
            valor += cant + 1;
            ve.addCmbUrbanizacion(valor);
            ve.Limpiar();
            ve.setValorCmbCodUrb(valor);
            ve.setEnabledComboCod(false);
            ve.bloquearcamposUrb(true);
            ve.setTxtIncluir("Guardar Urb");
        } else if (ae.getActionCommand().equalsIgnoreCase("Guardar Urb")) {
            if (ve.getTxtNombreSt().isEmpty() || ve.getTxtDirUrbSt().isEmpty() || ve.getValuespnCantCE() == 0) {
                Validar.mostrarMensaje("Campos Vacios", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                registrarUrb();
                ve.bloquearcamposUrb(false);
                ve.setTxtIncluir("Incluir");
                modificarEmpresa();
                ve.setEnabledComboCod(true);
                ve.selectedComboCod(0);
                cargarUrbanizacionesCombo();
                Validar.mostrarMensaje("Urbanización Incluida con éxito", "Incluir", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (ae.getSource().equals(ve.getCmbCodigoUrb())) {
            if (ve.getIndexCmbCodUrb() > 0 && ve.getIndexCmbCodUrb() <= urbanizaciones.size()) {
                ve.setenabledBtnModificarU(true);
                ve.setenabledEliminarU(true);
                ve.setEnabledIncluir(false);
                buscarUrb(ve.getIndexCmbCodUrb() - 1);
                ve.BloquearCamposModificarUrb(true);
            } else if (ve.getIndexCmbCodUrb() == 0) {
                ve.Limpiar();
            }
        } else if (ae.getActionCommand().equalsIgnoreCase("ModificarUrb")) {
            if (ve.getTxtNombreSt().isEmpty() || ve.getTxtDirUrbSt().isEmpty() || ve.getValuespnCantCE() == 0) {
                Validar.mostrarMensaje("Campos Vacios", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                modificarUrb();
                ve.Limpiar();
                ve.bloquearcamposUrb(false);
                ve.setEnabledIncluir(true);
                cargarUrbanizacionesCombo();
                Validar.mostrarMensaje("Urbanización Modoficada con éxito", "Modificar", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (ae.getActionCommand().equalsIgnoreCase("Eliminar")) {
            if (JOptionPane.showConfirmDialog(null, "¿Está seguro de ELIMINAR la Urbanización? \n\t Se perderan los datos.", "ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                eliminarUrb();
            }

        }
    }

    public void cargarUrbanizacionesCombo() {
        ve.vaciarCmbUrbanizacione();
        ve.addCmbUrbanizacion("Seleccione urbanización");
        UrbanizacionDAO urbDAO = new UrbanizacionDAO();
        urbanizaciones = urbDAO.consultarUrbanizaciones(' ');
        for (Urbanizacion urb : urbanizaciones) {
            ve.addCmbUrbanizacion(urb.getCodigo());
        }
        cargarTablaUrb();
    }

    public void registrarUrb() {

        Empresa emp = new Empresa();
        emp.setRif(ve.getTxtRifSt());
        char tipo = 0;
        if (ve.isSelectedrdbCasa()) {
            tipo = 'c';
        } else if (ve.isSelectedrdbEdificio()) {
            tipo = 'e';
        }
        Urbanizacion urbanizacion = new Urbanizacion(ve.getSelectedItemcmbCodigoUrb(), ve.getTxtNombreUrbSt(), ve.getTxtDirUrbSt(), ve.getValuespnCantCE(), tipo, null, null);
        UrbanizacionDAO urbDAO = new UrbanizacionDAO();
        urbDAO.agregarUrb(urbanizacion, emp);
    }

    public void modificarUrb() {

        Empresa emp = new Empresa();
        emp.setRif(ve.getTxtRifSt());
        char tipo = 0;
        if (ve.isSelectedrdbCasa()) {
            tipo = 'c';
        } else if (ve.isSelectedrdbEdificio()) {
            tipo = 'e';
        }
        Urbanizacion urbanizacion = new Urbanizacion(ve.getSelectedItemcmbCodigoUrb(), ve.getTxtNombreUrbSt(), ve.getTxtDirUrbSt(), ve.getValuespnCantCE(), tipo, null, null);
        UrbanizacionDAO urbDAO = new UrbanizacionDAO();
        urbDAO.modificarUrb(urbanizacion, emp);
    }

    public void eliminarUrb() {
        Urbanizacion urbEliminar = urbanizaciones.get(ve.getIndexCmbCodUrb() - 1);

        int total = 0;
        switch (urbEliminar.getTipoUrbanizacion()) {
            case 'c':
                CalleDAO caDAO = new CalleDAO();
                total = caDAO.totalCallesActivasPorUrb(urbEliminar.getCodigo());
                break;
            case 'e':
                EdificioDAO ediDAO = new EdificioDAO();
                total = ediDAO.totalEdificiosActivosPorUrb(urbEliminar.getCodigo());
                break;
        }
        if (total > 0) {
           Validar.mostrarMensaje("Esta Urbanizacion tiene calles/edificios activos.\nNo se puede eliminar mientras tenga calles/edificios activos", "Eliminar", 2);
        } else {
             UrbanizacionDAO urbDAO = new UrbanizacionDAO();
            urbDAO.eliminarUrb(urbEliminar.getCodigo());
            ve.Limpiar();
            cargarUrbanizacionesCombo();
            ve.bloquearcamposUrb(false);
            ve.setEnabledIncluir(true);
            Validar.mostrarMensaje("Urbanización Eliminada con éxito", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (ke.getSource().equals(ve.getTxtNombre())) {
            Validar.ValidarLetras(ke);
        } else if (ke.getSource().equals(ve.getTxtTelefono())) {
            Validar.ValidarNumeros(ke);
            Validar.ValidarCajasTexto(ke, 11, ve.getTxtTelefono());

        }

    }

    public void buscarUrb(int i) {
        Urbanizacion aux = urbanizaciones.get(i);
        ve.setTxtNombreUrbSt(aux.getNombre());
        ve.setTxtDirUrbSt(aux.getDireccion());
        ve.setTxtCantCESt(aux.getCantCalle_Edificio());
        switch (aux.getTipoUrbanizacion()) {
            case 'c':
                ve.setSelectedrdbCasa(true);
                break;
            case 'e':
                ve.setSelectedrdbEdificio(true);
                break;
        }
    }

    public void cargarTablaUrb() {
        String[] titulos = {"Código", "Nombre", "Dirección", "Tipo", "Cantidad Calles/Edficios"};
        String[] datos = new String[5];
        int[] anchos = {30, 60, 150, 30, 80};

        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Urbanizacion urb : urbanizaciones) {
            datos[0] = urb.getCodigo();
            datos[1] = urb.getNombre();
            datos[2] = urb.getDireccion();
            datos[3] = (urb.getTipoUrbanizacion() == 'c' ? "Casas" : "Edificios");
            datos[4] = String.valueOf(urb.getCantCalle_Edificio());
            modelo.addRow(datos);
        }
        Tabla.cargarTabla(titulos, anchos, modelo, ve.getjTable1());

    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }
}
