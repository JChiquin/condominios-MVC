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
package lib;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Validar {

    public static void ValidarCajasTexto(java.awt.event.KeyEvent evt, int limite, JTextField jt) {

        if (jt.getText().length() == limite) {
            evt.consume();
        }
    }

    public static void ValidarLetras(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if (Character.isDigit(c)) {

            evt.consume();
        }

    }

    public static void ValidarNumeros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if (Character.isLetter(c)) {

            evt.consume();
        }

    }

    public static void mostrarMensaje(String mensaje, String title, int tipo){
        JOptionPane.showMessageDialog(null, mensaje, title, tipo);
    }
    
   




}
