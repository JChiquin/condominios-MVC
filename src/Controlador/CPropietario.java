/**
 * @author Chiquín, Jorge
 * @author Vargas, Alexander
 * @author Bonilla, José
 * @author Fernández, Ysidro
 * @author Rodríguez, Ronald
 */

package Controlador;

import DAO.EmpresaDAO;
import DAO.PropietarioDAO;
import DAO.ViviendaDAO;
import Modelo.Propietario;
import Modelo.Vivienda;
import Vista.VPropietario;
import lib.Validar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CPropietario implements ActionListener, KeyListener {

    VPropietario vpr;
    PropietarioDAO prodao;
    Propietario pro;
    ArrayList<Vivienda> viv;
    ViviendaDAO vidao;

    public CPropietario() {
        vpr = new VPropietario(null, true, null);
        vpr.agregarActionListener(this, this);
        vpr.setEnabledBtnIncluir(false);
        vpr.limpiar();
        vpr.setVisible(true);

    }

    public void mostrarDatos() {
        prodao = new PropietarioDAO();

        pro = prodao.buscarPropietario(vpr.getTxtCedulaSt());
        if (pro.getEstatus() == 'A') {
            vpr.setTxtNombre(pro.getNombre());
            vpr.setTxtApellido(pro.getApellido());
            vpr.setTxtDireccion(pro.getDireccion());
            vpr.setTxttelefono(pro.getTelefono());
            vpr.setDtcFecNac(pro.getFechaNac());
            vpr.editableCampos(true);
            vpr.setEnabledBtnBuscar(false);
            vpr.enabledBotones(true);
        } else if (pro.getEstatus() == 'I') {
            propietarioInactivo();
            
            vpr.limpiar();
        } else if (pro.getCedula() == null) {
            if (JOptionPane.showConfirmDialog(null, "Este Propietario no existe \n¿Desea incluirlo?", "Propietario no existe", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                vpr.editableCampos(true);
                vpr.setEnabledBtnBuscar(false);
                vpr.setEnabledBtnIncluir(true);               
            }
        }
    }

    public void propietarioInactivo() {
        if (JOptionPane.showConfirmDialog(null, "Este Propietario se encuentra en estado 'Inactivo'\n\t¿Desea reactivarlo?", "Reactivar Propietario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            pro.setEstatus('A');
            pro.setCedula(vpr.getTxtCedulaSt());
            prodao.reactivarPropietario(pro);
            Validar.mostrarMensaje("Propietario reactivado", "Reactivación exitosa", 1);

        }
    }

    public void registrarPropietario() {

       
        Propietario prop = new Propietario(vpr.getTxtCedulaSt(), vpr.getTxtNombreSt(), vpr.getTxtApellidoSt(),
                vpr.getTxtDireccionSt(), vpr.getTxtTelefonoSt(), vpr.getDtcFecNacSt(), null, 'A');

        prodao = new PropietarioDAO();
        prodao.agregarPropietario(prop);
        
        Validar.mostrarMensaje("Se registró el Propietario exitosamente", "Incluir Propietario", 1);
            vpr.limpiar();
         
    }

    public void modificarPropietario() {

       
            pro.setCedula(vpr.getTxtCedulaSt());
            pro.setNombre(vpr.getTxtNombreSt());
            pro.setApellido(vpr.getTxtApellidoSt());
            pro.setDireccion(vpr.getTxtDireccionSt());
            pro.setTelefono(vpr.getTxtTelefonoSt());

            prodao.modificarPropietario(pro);
            
            Validar.mostrarMensaje("Cambios guardados con éxito", "Guardar Cambios", 1);
            vpr.limpiar();

        
    }

    public void eliminarPropietario() {
        vidao = new ViviendaDAO();   
        
        if(vidao.totalViviendasPorProp(vpr.getTxtCedulaSt()) > 0){
            
            Validar.mostrarMensaje("Este propietario tiene vivienda activas.\nNo se puede eliminar mientras tenga viviendas activas", "Viviendas activas", 2);
        }else{
           pro.setCedula(vpr.getTxtCedulaSt());
            prodao.eliminarPropietario(pro);
            Validar.mostrarMensaje("Propietario eliminado exitosamente.", "Eliminar propietario", 2);
            vpr.limpiar();
        }
            
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Limpiar")) {
            vpr.limpiar();
        } else if (e.getActionCommand().equalsIgnoreCase("Incluir")) {
            if(vpr.getTxtNombre().getText().isEmpty() || vpr.getTxtApellido().getText().isEmpty() || vpr.getTxtDireccion().getText().isEmpty() || vpr.getTxttelefono().getText().isEmpty()){
            Validar.mostrarMensaje("Campos vacíos", "Campos Obligatorios", 2);
            }else {
                registrarPropietario();
            
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Buscar")) {
            mostrarDatos();
        } else if (e.getActionCommand().equalsIgnoreCase("Eliminar")) {
            eliminarPropietario();
        } else if (e.getActionCommand().equalsIgnoreCase("Modificar")) {
            modificarPropietario();
            
        } else if (e.getActionCommand().equalsIgnoreCase("Regresar")) {
            vpr.dispose();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource().equals(vpr.getTxtApellido())) {
            Validar.ValidarLetras(e);
        } else if (e.getSource().equals(vpr.getTxttelefono())) {
            Validar.ValidarNumeros(e);
            Validar.ValidarCajasTexto(e, 11, vpr.getTxttelefono());
        } else if (e.getSource().equals(vpr.getTxtNombre())) {
            Validar.ValidarLetras(e);
        } else if (e.getSource().equals(vpr.getTxtCedula())) {
            Validar.ValidarNumeros(e);
            Validar.ValidarCajasTexto(e, 8, vpr.getTxtCedula());
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        

        if (vpr.lenghtCedula() > 6) {
            vpr.setEnabledBtnBuscar(true);
        } else {
            vpr.setEnabledBtnBuscar(false);
        }

        if (vpr.lenghtCedula() > 0) {
            vpr.setEnabledBtnLimpiar(true);
        } else {
            vpr.setEnabledBtnLimpiar(false);
        }
    }

}
