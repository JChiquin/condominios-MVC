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
import DAO.UrbanizacionDAO;
import DAO.ViviendaDAO;
import Modelo.Calle;
import Modelo.Edificio;
import Modelo.Urbanizacion;
import Modelo.Vivienda;
import Vista.VCalle;
import lib.Validar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CCalle implements ActionListener, KeyListener {

    VCalle vca;
    UrbanizacionDAO urbDAO;
    private ArrayList<Urbanizacion> urbanizaciones;
    private ArrayList<Calle> calles;
    Urbanizacion urba;
    CalleDAO cadao;
    Calle ca;
    ArrayList<Vivienda> viv;
    ViviendaDAO vidao;
    Tabla tbl;

    public CCalle() {
        vca = new VCalle(null, true, null);
        vca.agregarListener(this, this);
        cargarCmbUrbanizaciones();
        vca.limpiar();
        vca.setEnabledBtnBuscar(false);
        vca.setLocationRelativeTo(null);
        vca.setVisible(true);
    }

    public void cargarTablaCalle() {
        cadao = new CalleDAO();

        calles = cadao.buscarCallesPorUrb(vca.getCmbUrbaSt().substring(0, 4));
        String[] titulos = {"Nombre Urb.", "Nro. Calle", "Nombre", " Cant. Casas",};
        String[] datos = new String[4];
        int[] anchos = {50, 20, 20, 20};

        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Calle ca : calles) {
            datos[0] = urbanizaciones.get(vca.getIndexCmbUrbanizacion() - 1).getNombre();
            datos[1] = ca.getNroCalle();
            datos[2] = ca.getNombre();
            datos[3] = String.valueOf(ca.getCantCasas());
            modelo.addRow(datos);
        }
        Tabla.cargarTabla(titulos, anchos, modelo, vca.getTablaCalle());
    }

    public void cargarCmbUrbanizaciones() {
        vca.vaciarCmbUrbanizaciones();
        vca.addCmbUrbanizacion("Seleccione urbanización");
        urbDAO = new UrbanizacionDAO();
        urbanizaciones = urbDAO.consultarUrbanizaciones('c');
        for (Urbanizacion urb : urbanizaciones) {
            vca.addCmbUrbanizacion(urb.getCodigo() + " - " + urb.getNombre());
        }
    }

    public void mostrarCalle() {

        cadao = new CalleDAO();

        ca = cadao.buscarCalle(vca.getCmbUrbaSt().substring(0, 4) + " - " + vca.getTxtNroCalleSt());
        if (ca.getEstatus() == 'A') {
            vca.setTxtNombre(ca.getNombre());
            vca.setValorSpnCantCasas(ca.getCantCasas());
            vca.setEnabledBtnIncluir(false);
            vca.setEnabledBtnBuscar(false);
            vca.setEnabledCmbCodUrba(false);
            vca.enabledBotones(true);
            vca.enabledCampos(true);
            vca.setEditableTxtNroCalle(false);
        } else if (ca.getEstatus() == 'I') {
            Validar.mostrarMensaje("Esta Calle se encuentra en estado 'Inactivo'", "Calle inactiva", 1);
        } else if (ca.getNombre() == null) {
            if (JOptionPane.showConfirmDialog(null, "Esta Calle no existe \n¿Desea incluirla?", "Calle no encontrada", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                if (esPosibleIncluir()) {
                    vca.setEnabledBtnIncluir(true);
                    vca.setEnabledCmbCodUrba(false);
                    vca.setEnabledBtnBuscar(false);
                    vca.enabledBotones(false);
                    vca.setEnabledBtnLimpiar(true);
                    vca.enabledCampos(true);
                    vca.setEditableTxtNroCalle(false);
                } else {
                    Validar.mostrarMensaje("No es posible añadir más calles a esa urbanización", "Incluir Calle", 0);
                }
            }
        }

    }

    public boolean esPosibleIncluir() {
        int maxCallesUrb = urbanizaciones.get(vca.getIndexCmbUrbanizacion() - 1).getCantCalle_Edificio();
        cadao = new CalleDAO();
        int cantCalles = cadao.totalCallesPorUrb(urbanizaciones.get(vca.getIndexCmbUrbanizacion() - 1).getCodigo());
        if (cantCalles < maxCallesUrb) {
            return true;
        } else {
            return false;
        }
    }

    public void incluirCalle() {

        Calle ca = new Calle(urbanizaciones.get(vca.getIndexCmbUrbanizacion() - 1).getCodigo() + " - " + vca.getTxtNroCalleSt(), vca.getTxtNombreSt(),
                vca.getValorSpnCantCasas(), null, 'A');

        CalleDAO caDAO = new CalleDAO();
        caDAO.agregarCalle(ca, urbanizaciones.get(vca.getIndexCmbUrbanizacion() - 1));

        Validar.mostrarMensaje("Calle Incluida con éxito", "Incluir Calle", 1);
        vca.limpiar();
    }

    public void modificarCalle() {

        ca.setNroCalle(urbanizaciones.get(vca.getIndexCmbUrbanizacion() - 1).getCodigo() + " - " + vca.getTxtNroCalleSt());
        ca.setNombre(vca.getTxtNombreSt());
        ca.setCantCasas(vca.getValorSpnCantCasas());

        cadao.modificarCalle(ca);

        Validar.mostrarMensaje("Cambios guardados con éxito", "Guardar Cambios", 1);
        vca.limpiar();

    }

    public void eliminarCalle() {
        vidao = new ViviendaDAO();
        viv = vidao.buscarCasas(urbanizaciones.get(vca.getIndexCmbUrbanizacion() - 1).getCodigo() + " - " + vca.getTxtNroCalleSt());

        if (viv.size() > 0) {

            Validar.mostrarMensaje("Esta Calle tiene casas activas.\nNo se puede eliminar mientras tenga casas activas", "Casas activos", 2);
        } else {
            ca.setNroCalle(urbanizaciones.get(vca.getIndexCmbUrbanizacion() - 1).getCodigo() + " - " + vca.getTxtNroCalleSt());
            cadao.eliminarCalle(ca);
            Validar.mostrarMensaje("Calle eliminada exitosamente.", "Eliminar Calle", 2);
            vca.limpiar();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vca.getCmbCodUrba())) {
            if (vca.getIndexCmbUrbanizacion() > 0) {

                vca.setEnabledBtnLimpiar(true);
                vca.setEditableTxtNroCalle(true);
                cargarTablaCalle();
            } else if (vca.getIndexCmbUrbanizacion() == 0) {
                vca.limpiar();
                vca.setEnabledBtnBuscar(false);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("Buscar")) {
            mostrarCalle();
        } else if (e.getActionCommand().equalsIgnoreCase("Incluir")) {
            if (vca.getTxtNombreSt().isEmpty()) {
                vca.setTxtNombre("Sin nombre");
            }
            incluirCalle();

        } else if (e.getActionCommand().equalsIgnoreCase("Modificar")) {
            if (vca.getTxtNombreSt().isEmpty()) {
                vca.setTxtNombre("Sin nombre");
            }
            modificarCalle();

        } else if (e.getActionCommand().equalsIgnoreCase("Eliminar")) {
            eliminarCalle();
        } else if (e.getActionCommand().equalsIgnoreCase("Limpiar")) {
            vca.limpiar();
            tbl.vaciarTabla(vca.getTablaCalle());
        } else if (e.getActionCommand().equalsIgnoreCase("Regresar")) {
            vca.dispose();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(vca.getTxtNroCalle())) {
            if (vca.lenghtNroCalle() > 0) {
                vca.setEnabledBtnBuscar(true);
            } else {
                vca.setEnabledBtnBuscar(false);
            }
        }

    }

}
