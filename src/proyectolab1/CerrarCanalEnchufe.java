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

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class CerrarCanalEnchufe implements Estado{
    ServerSocket serverSocket;

    public CerrarCanalEnchufe(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public ServerSocket procesarEstado() {

            JOptionPane.showMessageDialog(null, "La Aplicación ya esta en uso", "Advertencia",JOptionPane.WARNING_MESSAGE);
      
        return serverSocket;
    }
    
}
