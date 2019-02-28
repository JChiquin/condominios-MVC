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
package proyectolab1;
import Controlador.*;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class ProyectoLab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
  
        Estado estado;
        Contexto contexto = Contexto.getInstancia();
        ServerSocket serverSocket = null;
        
        estado = new ConectarCanalEnchufe(serverSocket);
        contexto.setEstado(estado);
        serverSocket = contexto.solicitar();           
    
        }
        
    }
    
