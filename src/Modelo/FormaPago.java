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


public class FormaPago {
    private String codigoPago;
    private String TipoPago;
    ArrayList<Pago> pagos;
    char estatus;

    public FormaPago(String codigoPago, String TipoPago, ArrayList<Pago> pagos, char estatus) {
        this.codigoPago = codigoPago;
        this.TipoPago = TipoPago;
        this.pagos = pagos;
        this.estatus = estatus;
    }

    public FormaPago() {
    }

    
    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }

    public char getEstatus() {
        return estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }



    public String getCodigoPago() {
        return codigoPago;
    }

    public void setCodigoPago(String codigoPago) {
        this.codigoPago = codigoPago;
    }

    public String getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(String TipoPago) {
        this.TipoPago = TipoPago;
    }
}

