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
package Modelo.decorator;


public abstract class ComponenteGasto {

    String descripcion = "";

    public String getDescripcion() {
        return descripcion;
    }

    public abstract double TotalGastos();

}
