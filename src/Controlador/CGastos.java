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
import DAO.GastosDAO;
import Modelo.Gasto;
import Vista.VGastos;
import lib.Validar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;


public class CGastos implements ActionListener, KeyListener {

    VGastos vgasto;
    Gasto gasto;
    private GastosDAO gastosDAO = new GastosDAO();
    private ArrayList<Gasto> gastos;

    public CGastos() {
        super();
        vgasto = new VGastos(null, true, null);
        vgasto.agregarListener(this, this);
        vgasto.setLocationRelativeTo(null);
        vgasto.Limpiar();
        cargarTablaGasto();
        vgasto.setVisible(true);

    }

    public void eliminarGastos() {

        GastosDAO gastDAO = new GastosDAO();
        gastDAO.eliminarGastos(vgasto.getTxtCodigo1());
        vgasto.Limpiar();
        Validar.mostrarMensaje("Gasto Eliminado con éxito!", "Eliminar Gasto", JOptionPane.INFORMATION_MESSAGE);
        cargarTablaGasto();
    }

    public void registrarGastos() {

        int tipo = 0;
        if (vgasto.isSelectedrdbGeneral()) {
            tipo = 1;
        } else if (vgasto.isSelectedrdbExtraS()) {
            tipo = 2;
        }
        Gasto gast = new Gasto(vgasto.getTxtCodigo1(), vgasto.getTxtNombre1(), vgasto.getTxtDescripcion1(), tipo, 0.00, 'A',null);
        gastosDAO.IncluirGastos(gast);
        Validar.mostrarMensaje("Gasto registrado", "Registrar Gasto", JOptionPane.INFORMATION_MESSAGE);
        cargarTablaGasto();
        vgasto.Limpiar();
    }

    public void ModificarGastos() {
        int tipo = 0;
        if (vgasto.isSelectedrdbGeneral()) {
            tipo = 1;
        } else if (vgasto.isSelectedrdbExtraS()) {
            tipo = 2;
        }
        Gasto gast = new Gasto(vgasto.getTxtCodigo1(), vgasto.getTxtNombre1(), vgasto.getTxtDescripcion1(), tipo, 0.00, 'A',null);
        gastosDAO.modificarGasto(gast);
        Validar.mostrarMensaje("Gasto Modificado con éxito!", "Modificar Gasto", JOptionPane.INFORMATION_MESSAGE);
        cargarTablaGasto();
        vgasto.Limpiar();
    }

    public void BuscarGastos() {

        gasto = gastosDAO.BuscarGastos(vgasto.getTxtCodigo1());
        if (gasto == null) {
            if (JOptionPane.showConfirmDialog(null, "Este Gasto no existe \n¿Desea incluirlo?", "Gasto no existe", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                vgasto.deshablitarCajas(true);
                vgasto.setEnableBtnIncluir(true);
                vgasto.setEnableBtnBuscar(false);
                vgasto.setEditableTxtCodigo(false);
            }
        } else {
            vgasto.setTxtNombre(gasto.getNombre());
            vgasto.setTxtDescripcion(gasto.getDescripcion());

            switch (gasto.getTipo()) {
                case 1:
                    vgasto.setSelectedrdGeneral(true);
                    break;
                case 2:
                    vgasto.setSelectedrdbExtra(true);
                    break;
            }
            vgasto.setEnableBtnModificar(true);
            vgasto.setEnableBtnEliminar(true);
            vgasto.deshablitarCajas(true);
        }
    }

    public void cargarTablaGasto() {
        String[] titulos = {"Codigo", "Nombre", "Tipo", "Descripción"};
        String[] datos = new String[4];
        int[] anchos = {30, 30, 50, 150};
        gastos = gastosDAO.consultarGastos();
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Gasto gast : gastos) {
            datos[0] = gast.getCodigo();
            datos[1] = gast.getNombre();
            datos[2] = (gast.getTipo() == 1 ? "Fijo" : "Extraordinario");
            datos[3] = gast.getDescripcion();
            modelo.addRow(datos);
        }
        Tabla.cargarTabla(titulos, anchos, modelo, vgasto.getjTable1());

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equalsIgnoreCase("Limpiar")) {
            vgasto.Limpiar();
        } else if (ae.getActionCommand().equalsIgnoreCase("Incluir")) {
            if (vgasto.getTxtNombre1().isEmpty() || vgasto.getTxtDescripcion1().isEmpty()) {
                Validar.mostrarMensaje("Debe llenar todos los campos", "Campos vacíos", 1);
            } else {
                registrarGastos();
            }

        } else if (ae.getActionCommand().equalsIgnoreCase("Modificar")) {
            if (vgasto.getTxtNombre1().isEmpty() || vgasto.getTxtDescripcion1().isEmpty()) {
                Validar.mostrarMensaje("Debe llenar todos los campos", "Campos vacíos", 1);
            } else {
                ModificarGastos();
            }

        } else if (ae.getActionCommand().equalsIgnoreCase("Buscar")) {
            BuscarGastos();

        } else if (ae.getActionCommand().equalsIgnoreCase("Eliminar")) {
            eliminarGastos();
        } else if (ae.getActionCommand().equalsIgnoreCase("Regresar")) {
            vgasto.dispose();
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getSource().equals(vgasto.getTxtCodigo())) {
            if (vgasto.lenghtCodigo() > 0) {
                vgasto.setEnableBtnBuscar(true);
            } else {
                vgasto.setEnableBtnBuscar(false);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

}
