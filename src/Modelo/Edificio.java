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


public class Edificio {
    private String codigo;
    private int cantPiso, cantApartamentos; 
    private ArrayList<Vivienda> apartamentos;
    private char estatus;

    public Edificio(String codigo, int cantPiso, int cantApartamentos, ArrayList<Vivienda> apartamentos, char estatus) {
        this.codigo = codigo;
        this.cantPiso = cantPiso;
        this.cantApartamentos = cantApartamentos;
        this.apartamentos = apartamentos;
        this.estatus = estatus;
    }

    public char getEstatus() {
        return estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }


    public Edificio() {
    }    

    public int getCantApartamentos() {
        return cantApartamentos;
    }

    public void setCantApartamentos(int cantApartamentos) {
        this.cantApartamentos = cantApartamentos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantPiso() {
        return cantPiso;
    }

    public void setCantPiso(int cantPiso) {
        this.cantPiso = cantPiso;
    }

    public ArrayList<Vivienda> getApartamentos() {
        return apartamentos;
    }

    public void setApartamentos(ArrayList<Vivienda> apartamentos) {
        this.apartamentos = apartamentos;
    }
    
}
