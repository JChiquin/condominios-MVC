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
package Modelo;

import java.util.ArrayList;
import java.util.Date;


public class Pago {
    private String codigo;
    private double monto;
    private Date fecha;
    ArrayList<FormaPago> formasPago;
    Vivienda vivienda;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Pago() {
    }

    public Pago(String codigo, double monto, Date fecha, ArrayList<FormaPago> formasPago, Vivienda vivienda) {
        this.codigo = codigo;
        this.monto = monto;
        this.fecha = fecha;
        this.formasPago = formasPago;
        this.vivienda = vivienda;
    }

    
}
