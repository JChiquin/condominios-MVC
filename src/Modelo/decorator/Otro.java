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


public class Otro extends GastosBasicosDecorator {

    private String descripcion;
    private double monto;
    ComponenteGasto gasto;

    public Otro(String descripcion, double monto, ComponenteGasto gasto) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.gasto = gasto;
    }

    

    @Override
    public double TotalGastos() {
        return gasto.TotalGastos() + monto;
    }
    
    @Override
    public String getDescripcion() {
        return gasto.getDescripcion()+" + "+descripcion;
    }

}
