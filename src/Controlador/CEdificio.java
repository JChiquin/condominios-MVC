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
import DAO.EdificioDAO;
import DAO.UrbanizacionDAO;
import DAO.ViviendaDAO;
import Modelo.Edificio;
import Modelo.Propietario;
import Modelo.Urbanizacion;
import Modelo.Vivienda;
import Vista.VEdificio;
import lib.Validar;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;


public class CEdificio implements ActionListener {

    private VEdificio ved;
    private ArrayList<Urbanizacion> urbanizaciones;
    ArrayList<Edificio> edificios;
    private ArrayList<Propietario> propietarios;
    UrbanizacionDAO urbDAO;
    EdificioDAO edidao;
    Edificio edi;
    Urbanizacion urba;
    ArrayList<Vivienda> viv;
    ViviendaDAO vidao;
    Tabla tbl;

    public CEdificio() {
        ved = new VEdificio(null, true);
        ved.agregarListener(this);
        ved.setLocationRelativeTo(null);
        cargarCmbUrbanizaciones();

        ved.setVisible(true);

    }

    public void generarCodigo() {
        int cant = edificios.size();
        String valor;
        if (cant < 9) {
            valor = "0";
        } else {
            valor = "";
        }
        valor += cant + 1;
        ved.addCmbEdificio(urbanizaciones.get(ved.getIndexCmbUrbanizacion() - 1).getCodigo() + " - " + valor);

        ved.setIndexCmbEdificio(cant);
        ved.selectedCmbEdificio(cant + 1);

    }

    public void incluirEdificio() {

        Edificio ed = new Edificio(ved.getCmbEdificioSt(), ved.getValorSpnCantPisos(),
                ved.getValorSpnCantAp(), null, 'A');

        EdificioDAO ediDAO = new EdificioDAO();
        ediDAO.agregarEdificio(ed, urbanizaciones.get(ved.getIndexCmbUrbanizacion() - 1));

        Validar.mostrarMensaje("Edificio Incluido con éxito", "Incluir Edificio", 1);
        ved.limpiarCampos();
    }

    public void mostrarEdificio() {

        edidao = new EdificioDAO();

        edi = edidao.buscar(ved.getCmbEdificioSt());
        ved.setValorSpnCantPisos(edi.getCantPiso());
        ved.setValorSpnCantAp(edi.getCantApartamentos());
    }

    public void modificarEdificio() {

        edi.setCodigo(ved.getCmbEdificioSt());
        edi.setCantPiso(ved.getValorSpnCantPisos());
        edi.setCantApartamentos(ved.getValorSpnCantAp());

        edidao.modificarEdificio(edi);

        Validar.mostrarMensaje("Cambios guardados con éxito", "Guardar Cambios", 1);
        ved.limpiarCampos();

    }

    public void eliminarEdificio() {
        vidao = new ViviendaDAO();
        viv = vidao.buscarApartamentos(ved.getCmbEdificioSt());

        if (viv.size() > 0) {

            Validar.mostrarMensaje("Este Edificio tiene apartamentos activos.\nNo se puede eliminar mientras tenga apartamentos activos", "Apartamentos activos", 2);
        } else {
            edi.setCodigo(ved.getCmbEdificioSt());
            edidao.eliminarEdificio(edi);
            Validar.mostrarMensaje("Edificio eliminado exitosamente.", "Eliminar Edificio", 2);
            ved.limpiarCampos();
        }

    }

    public void cargarCmbUrbanizaciones() {
        ved.vaciarCmbUrbanizaciones();
        ved.addCmbUrbanizacion("Seleccione urbanización");
        urbDAO = new UrbanizacionDAO();
        urbanizaciones = urbDAO.consultarUrbanizaciones('e');
        for (Urbanizacion urb : urbanizaciones) {
            ved.addCmbUrbanizacion(urb.getNombre());
        }
    }

    public void cargarCmbEdificios() {
        ved.vaciarCmdEdificio();
        ved.addCmbEdificio("Seleccione edificio");
        edidao = new EdificioDAO();
        edificios = edidao.buscarEdificiosPorUrbanizacion(urbanizaciones.get(ved.getIndexCmbUrbanizacion() - 1).getCodigo());

        for (Edificio ed : edificios) {
            ved.addCmbEdificio(ed.getCodigo());
        }
        cargarTablaEdificio();

    }

    public void cargarTablaEdificio() {

        String[] titulos = {"Nombre Urb.", "Código Edif.", "Cant. Pisos", "Cant. Aparts."};
        String[] datos = new String[4];
        int[] anchos = {50, 20, 20, 30};

        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Edificio ed : edificios) {
            datos[0] = urbanizaciones.get(ved.getIndexCmbUrbanizacion() - 1).getNombre();
            datos[1] = ed.getCodigo();
            datos[2] = String.valueOf(ed.getCantPiso());
            datos[3] = String.valueOf(ed.getCantApartamentos());
            modelo.addRow(datos);
        }
        Tabla.cargarTabla(titulos, anchos, modelo, ved.getTablaEdif());
    }

    public boolean esPosibleIncluir() {
        int maxEdificioUrb = urbanizaciones.get(ved.getIndexCmbUrbanizacion() - 1).getCantCalle_Edificio();
        edidao = new EdificioDAO();
        int cantEdificios = edidao.totalEdificiosPorUrb(urbanizaciones.get(ved.getIndexCmbUrbanizacion() - 1).getCodigo());
        if (cantEdificios < maxEdificioUrb) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(ved.getCmbUrbanizacion())) {
            if (ved.getIndexCmbUrbanizacion() > 0) {
                ved.enableCmbEdificio(true);
                ved.setEnabledBtnIncluir(true);
                ved.setEnabledBtnLimpiar(true);
                cargarCmbEdificios();

            } else if (ved.getIndexCmbUrbanizacion() == 0) {
                ved.limpiarCampos();
                ved.enableCmbEdificio(false);
                ved.vaciarCmdEdificio();
            }

        } else if (e.getSource().equals(ved.getCmbEdificio())) {
            if (ved.getIndexCmbEdificio() > 0) {
                mostrarEdificio();

                ved.setEnabledBtnIncluir(false);
                ved.enableCmbEdificio(false);
                ved.enableCmbUrba(false);
                ved.enabledBotones(true);
                ved.enabledSpn(true);
            } else if (ved.getIndexCmbEdificio() == 0) {
                ved.setValorSpnCantAp(0);
                ved.setValorSpnCantPisos(0);
                ved.setEnabledBtnIncluir(true);
                ved.enabledBotones(false);
                ved.enabledSpn(false);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Incluir")) {
            if (esPosibleIncluir()) {
                generarCodigo();
                ved.setEnabledBtnIncluir(true);
                ved.enableCmbEdificio(false);
                ved.enableCmbUrba(false);
                ved.enabledBotones(false);
                ved.setEnabledBtnLimpiar(true);
                ved.enabledSpn(true);
                ved.setValorSpnCantPisos(1);
                ved.setValorSpnCantAp(1);
                ved.setTxtIncluirEm("Guardar");
            } else {
                Validar.mostrarMensaje("No es posible agregar más edificios a esa urbanización", "Incluir edificio", 0);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("Guardar")) {
            incluirEdificio();
            ved.setTxtIncluirEm("Incluir");

        } else if (e.getActionCommand().equalsIgnoreCase("Modificar")) {
            modificarEdificio();

        } else if (e.getActionCommand().equalsIgnoreCase("Eliminar")) {
            eliminarEdificio();
        } else if (e.getActionCommand().equalsIgnoreCase("Limpiar")) {
            ved.limpiarCampos();
            ved.vaciarCmdEdificio();
            tbl.vaciarTabla(ved.getTablaEdif());
        } else if (e.getActionCommand().equalsIgnoreCase("Regresar")) {
            ved.dispose();
        }
    }
}
