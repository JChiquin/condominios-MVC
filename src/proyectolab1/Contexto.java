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

import java.net.ServerSocket;


class Contexto {

    private static Contexto instancia;

    public static Contexto getInstancia() {
        if(instancia==null){
            instancia=new Contexto();
        }
        return instancia;
    }


    private Estado estado;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public ServerSocket solicitar(){
        return estado.procesarEstado();
    }
}
