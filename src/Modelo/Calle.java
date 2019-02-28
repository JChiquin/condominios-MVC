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
import java.util.Vector;


public class Calle {
    private String Nombre;
    private String NroCalle;
    private int cantCasas;
    ArrayList<Vivienda> casas;

    public Calle() {
    }
    private char estatus;

    public Calle( String NroCalle, String Nombre, int cantCasas, ArrayList<Vivienda> casas, char estatus) {
        this.Nombre = Nombre;
        this.NroCalle = NroCalle;
        this.casas = casas;
        this.estatus = estatus;
        this.cantCasas = cantCasas;
    }

    public int getCantCasas() {
        return cantCasas;
    }

    public void setCantCasas(int cantCasas) {
        this.cantCasas = cantCasas;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNroCalle() {
        return NroCalle;
    }

    public void setNroCalle(String NroCalle) {
        this.NroCalle = NroCalle;
    }

    public ArrayList<Vivienda> getCasas() {
        return casas;
    }

    public void setCasas(ArrayList<Vivienda> casas) {
        this.casas = casas;
    }

    public char getEstatus() {
        return estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }
    
    
    

}
