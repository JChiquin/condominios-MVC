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

import Controlador.CMenu;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ConectarCanalEnchufe implements Estado{
    
    ServerSocket serverSocket;  

    public ConectarCanalEnchufe(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
     @Override
    public ServerSocket procesarEstado() {
        
        try {
            serverSocket = new ServerSocket(1334);
            CMenu cMenu = new CMenu(); 
        } catch (IOException ex) {
            
        Estado estado;
        Contexto contexto = Contexto.getInstancia();
        ServerSocket serverSocket = null;
        estado = new CerrarCanalEnchufe(serverSocket);
        contexto.setEstado(estado);
        serverSocket = contexto.solicitar();   
        
            Logger.getLogger(ConectarCanalEnchufe.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        return serverSocket;
    }
}
