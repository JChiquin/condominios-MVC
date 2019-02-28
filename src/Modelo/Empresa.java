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
public class Empresa {
    private String rif,nombre,direccion,telefono;
    ArrayList<Urbanizacion> urbanizaciones;
    private char estatus;

    public String getRif() {
        return rif;
    }

    public Empresa() {
    }

    public Empresa(String rif, String nombre, String direccion, String telefono, ArrayList<Urbanizacion> urbanizaciones, char estatus) {
        this.rif = rif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.urbanizaciones = urbanizaciones;
        this.estatus = estatus;
    }

    public char getEstatus() {
        return estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }


    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Urbanizacion> getUrbanizaciones() {
        return urbanizaciones;
    }

    public void setUrbanizaciones(ArrayList<Urbanizacion> urbanizaciones) {
        this.urbanizaciones = urbanizaciones;
    }
    
}
