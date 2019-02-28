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


public class Vivienda{
    private int numPiso;
    private Propietario propietario;
    private String codigo;
    private int cantHabitaciones, cantBanios;
    private char estatus, tipoVivienda; // A: apartamento - C: casa
    private ArrayList<Pago> pagos;
    
    public int getNumPiso() {
        return numPiso;
    }

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }

    public Vivienda(int numPiso, Propietario propietario, String codigo, int cantHabitaciones, int cantBanios, char estatus, char tipoVivienda, ArrayList<Pago> pagos) {
        this.numPiso = numPiso;
        this.propietario = propietario;
        this.codigo = codigo;
        this.cantHabitaciones = cantHabitaciones;
        this.cantBanios = cantBanios;
        this.estatus = estatus;
        this.tipoVivienda = tipoVivienda;
        this.pagos = pagos;
    }

    public Vivienda() {
    }
    

    public void setNumPiso(int numPiso) {
        this.numPiso = numPiso;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantHabitaciones() {
        return cantHabitaciones;
    }

    public void setCantHabitaciones(int cantHabitaciones) {
        this.cantHabitaciones = cantHabitaciones;
    }

    public int getCantBanios() {
        return cantBanios;
    }

    public void setCantBanios(int cantBanios) {
        this.cantBanios = cantBanios;
    }

    public char getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(char tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public char getEstatus() {
        return estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    

}