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

public class Cuota {

    private double monto;
    Urbanizacion urb;
    ArrayList<Gasto> gastos;
    char estatus;
    int nroMes;
    int nroAnio;
    String codigoCuota;
    
    public Cuota(double monto, Urbanizacion urb, ArrayList<Gasto> gastos, char estatus, int nroMes, int nroAnio, String codigocuota) {
        this.monto = monto;
        this.urb = urb;
        this.gastos = gastos;
        this.estatus = estatus;
        this.nroAnio= nroAnio;
        this.nroMes = nroMes;
        this.codigoCuota= codigocuota;
    }

    public Cuota() {
    }

    public void setNroMes(int nroMes) {
        this.nroMes = nroMes;
    }

    public void setNroAnio(int nroAnio) {
        this.nroAnio = nroAnio;
    }

    public void setCodigoCuota(String codigoCuota) {
        this.codigoCuota = codigoCuota;
    }
    

    public String getCodigoCuota() {
        return codigoCuota;
    }
    public int getNroMes() {
        return nroMes;
    }

    public int getNroAnio() {
        return nroAnio;
    }

    public char getEstatus() {
        return estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Urbanizacion getUrb() {
        return urb;
    }

    public void setUrb(Urbanizacion urb) {
        this.urb = urb;
    }

    public ArrayList<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(ArrayList<Gasto> gastos) {
        this.gastos = gastos;
    }

    
}
