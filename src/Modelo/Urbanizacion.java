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
public class Urbanizacion {
    private String codigo,nombre,direccion;
    private int cantCalle_Edificio;
    private char tipoUrbanizacion;
    ArrayList<Calle> calles;
    ArrayList<Edificio> edificios;

    public Urbanizacion(String codigo, String nombre, String direccion, int cantCalle_Edificio, char tipoUrbanizacion, ArrayList<Calle> calles, ArrayList<Edificio> edificios) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cantCalle_Edificio = cantCalle_Edificio;
        this.tipoUrbanizacion = tipoUrbanizacion;
        this.calles = calles;
        this.edificios = edificios;
    }

    public char getTipoUrbanizacion() {
        return tipoUrbanizacion;
    }

    public void setTipoUrbanizacion(char tipoUrbanizacion) {
        this.tipoUrbanizacion = tipoUrbanizacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public int getCantCalle_Edificio() {
        return cantCalle_Edificio;
    }

    public void setCantCalle_Edificio(int cantCalle_Edificio) {
        this.cantCalle_Edificio = cantCalle_Edificio;
    }

    public ArrayList<Calle> getCalles() {
        return calles;
    }

    public void setCalles(ArrayList<Calle> calles) {
        this.calles = calles;
    }

    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(ArrayList<Edificio> edificios) {
        this.edificios = edificios;
    }

    public Urbanizacion() {
    }
}
