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

import DAO.EmpresaDAO;
import Modelo.Empresa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.VInicio;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import lib.Validar;
import javax.swing.JOptionPane;

public class CInicio implements ActionListener, KeyListener {

    VInicio vre;

    public CInicio() {
        super();
        if (primeraVez()) {
            vre = new VInicio();
            vre.setLocationRelativeTo(null);
            vre.setBtnModificarEm(false);
            vre.agregarListener(this, this);
            vre.setVisible(true);
        } else {
            new CMenu();
        }
    }

    public boolean primeraVez() {
        EmpresaDAO empDAO = new EmpresaDAO();
        Empresa emp = empDAO.buscar();
        if (emp == null) {
            return true;
        } else {
            return false;
        }

    }

    public void registrarEmpresa() {

        Empresa emp = new Empresa(vre.getTxtRifSt(), vre.getTxtNombreSt(), vre.getTxtDireccionSt(), vre.getTxtTelefonoSt(), null, 'A');

        EmpresaDAO empDAO = new EmpresaDAO();
        empDAO.agregarEmpresa(emp);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("Limpiar")) {
            vre.limpiarCampos();
        } else if (ae.getActionCommand().equalsIgnoreCase("Iniciar")) {
            vre.dispose();
        } else if (ae.getActionCommand().equalsIgnoreCase("Registrar")) {
            registrarEmpresa();
            Validar.mostrarMensaje("Empresa registrada con éxito", "Registrar", JOptionPane.INFORMATION_MESSAGE);
            vre.dispose();
            new CMenu();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (ke.getSource().equals(vre.getTxtNombre())) {
            Validar.ValidarLetras(ke);
        } else if (ke.getSource().equals(vre.getTxtTelefono())) {
            Validar.ValidarNumeros(ke);
            Validar.ValidarCajasTexto(ke, 11, vre.getTxtTelefono());
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (!vre.getTxtRifSt().isEmpty() && !vre.getTxtNombreSt().isEmpty() && !vre.getTxtDireccionSt().isEmpty() && !vre.getTxtTelefonoSt().isEmpty()) {
            vre.setBtnModificarEm(true);
        } else {
            vre.setBtnModificarEm(false);
        }

    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }
}
