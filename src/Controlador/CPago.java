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
package Controlador;

import DAO.CuotaDAO;
import DAO.FormaPagoDAO;
import DAO.UrbanizacionDAO;
import DAO.PropietarioDAO;
import DAO.PagosDAO;
import DAO.ViviendaDAO;
import Modelo.Cuota;
import Modelo.FormaPago;
import Modelo.Propietario;
import Modelo.Pago;
import Modelo.Urbanizacion;
import Modelo.Vivienda;
import Vista.VPagos;
import Vista.VPropietario;
import lib.Validar;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CPago implements ActionListener {

    VPagos vpa;
    private ArrayList<Pago> pagos;
    private ArrayList<FormaPago> formaPagos;
    private ArrayList<Urbanizacion> urbanizaciones;
    private ArrayList<Propietario> propietarios;
    private ArrayList<Vivienda> viviendas;
    private ArrayList<Cuota> cuotas;
    UrbanizacionDAO urbDAO;
    Urbanizacion urba;
    PagosDAO paDAO;
    FormaPagoDAO fpDAO;
    Pago pag;
    CuotaDAO cDAO;

    public CPago() {
        vpa = new VPagos(null, true);
        vpa.agregarListener(this);
        vpa.setLocationRelativeTo(null);
        cargarCmbUrbanizaciones();
        cargarCmbPagos();
        cargarCmbFormaPago();
        vpa.limpiarCampos();
        vpa.setVisible(true);

    }

    public void generarCodigo() {
        PagosDAO pdao = new PagosDAO();
        int cant = pdao.cantPago();
        String valor;
        if (cant < 9) {
            valor = "000";
        } else if (cant < 99) {
            valor = "00";
        } else if (cant < 999) {
            valor = "0";
        } else {
            valor = "";
        }
        valor += cant + 1;
        vpa.addCmbPago(valor);
        vpa.setValorCmbPago(valor);
        vpa.enableCmbPago(false);

    }

    public void cargarCmbUrbanizaciones() {
        vpa.vaciarCmbUrbanizaciones();
        vpa.addCmbUrbanizacion("Seleccione la urbanización");
        urbDAO = new UrbanizacionDAO();
        urbanizaciones = urbDAO.consultarUrbanizaciones(' ');
        for (Urbanizacion urb : urbanizaciones) {
            vpa.addCmbUrbanizacion(urb.getNombre());
        }
    }

    public void cargarCmbFormaPago() {
        vpa.vaciarCmdFormaPago();
        vpa.addCmbFormaPago("Seleccione Forma de Pago");
        fpDAO = new FormaPagoDAO();
        formaPagos = fpDAO.consultarFormaPago();

        for (FormaPago fp : formaPagos) {
            vpa.addCmbFormaPago(fp.getTipoPago());
        }

    }

    public void cargarCmbPagos() {
        vpa.vaciarCmdPago();
        vpa.addCmbPago("Seleccione un Pago");
        paDAO = new PagosDAO();
        pagos = paDAO.consultarPagos();

        for (Pago pag : pagos) {
            vpa.addCmbPago(pag.getCodigo());
        }

    }

    public void cargarCuota() {
        vpa.vaciarCmbCuota();
        vpa.addCmbCuota("Seleccione Cuota");
        cDAO = new CuotaDAO();
        cuotas = cDAO.ConsultarCuotas(urbanizaciones.get(vpa.getIndexCmbUrbanizacion() - 1).getCodigo());
        for (Cuota c : cuotas) {
            vpa.addCmbCuota(c.getCodigoCuota());
        }

    }

    public void montoPagar() {
        double monto = cuotas.get(vpa.getIndexCmbCuota() - 1).getMonto();
        vpa.setTxtMonto(String.valueOf(monto));
    }

    public void cargarPropietarios() {
        vpa.vaciarCmbCedula();
        vpa.addCmbCedula("Seleccione Propietario");
        PropietarioDAO prDAO = new PropietarioDAO();
        propietarios = prDAO.consultarPropietariosUrbanizacion(urbanizaciones.get(vpa.getIndexCmbUrbanizacion() - 1).getCodigo());
        for (Propietario pr : propietarios) {
            vpa.addCmbCedula(pr.getCedula());
        }
    }

    public void cargarVivienda() {
        vpa.vaciarCmbVivienda();
        ViviendaDAO vDAO = new ViviendaDAO();
        viviendas = vDAO.buscarViviendasPorPro2(vpa.getCmbCedulaSt(), vpa.getCmbUrbaSt());
        for (Vivienda v : viviendas) {
            vpa.addCmbVivienda(v.getCodigo());
        }
    }

    public void eliminarPagos() {
        PagosDAO paDAO = new PagosDAO();
        paDAO.eliminarPago(vpa.getCmbPagoSt());
        vpa.limpiarCampos();
        Validar.mostrarMensaje("Pago Eliminado con éxito!", "Eliminar Pago", JOptionPane.INFORMATION_MESSAGE);

    }

    public void buscarPago(int i) {
//        Pago aux = pagos.get(i);
//        vpa.setValorCmbPropietario(aux.getVivienda().getPropietario().getCedula());
//        vpa.setValorCmbVivienda(aux.getVivienda().getCodigo());
//        vpa.setValorCmbCuota(aux.getMonto());
//     
    }

    public void efectuarPago() {
        Pago p = new Pago(vpa.getCmbPagoSt(), vpa.getTxtMonto(), vpa.getTxtFecha(), null, null);
        Cuota c = new Cuota();
        c.setCodigoCuota(vpa.getCmbCuotaSt());
        Vivienda v = new Vivienda();
        v.setCodigo(vpa.getCmbViviendaSt());
        paDAO.efectuarPago(p, c, v);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(vpa.getCmbUrbanizacion())) {
            if (vpa.getIndexCmbUrbanizacion() > 0) {
                vpa.enableCmbPago(true);
                vpa.setEnabledBtnEfectuar(true);
                vpa.setEnabledBtnCancelar(true);
                vpa.setEnabledBtnBuscar(true);
                cargarCmbPagos();
                cargarCuota();
            } else if (vpa.getIndexCmbUrbanizacion() == 0) {
                vpa.setEnabledBtnEfectuar(false);
                vpa.setEnabledBtnCancelar(false);
                vpa.setEnabledBtnBuscar(false);
                vpa.enableCmbPago(false);
                vpa.vaciarCmdPago();
            }

        } else if (e.getActionCommand().equals("Generar")) {
            generarCodigo();
            vpa.setTxtBtnEfectuar("Efectuar");
            vpa.setEnabledBtnBuscar(false);
            vpa.enableCmbUrba(false);
            vpa.enableCmbCedula(true);
            vpa.enableCmbCuota(true);
            vpa.enableCmbFormaPago(true);
            vpa.enableBtnEfectuar(true);
            
            cargarPropietarios();
        } else if (e.getActionCommand().equals("Efectuar")) {
            if (vpa.getIndexCmbCedula() == 0 || vpa.getIndexCmbVivienda() == 0 || vpa.getIndexCmbCuota() == 0 || vpa.getIndexCmbFormaPago() == 0) {
                Validar.mostrarMensaje("Campos Vacios", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                efectuarPago();
                vpa.limpiarCampos();
            }
        } else if (e.getSource().equals(vpa.getCmbCedula())) {
            if (vpa.getIndexCmbCedula() > 0) {
                cargarVivienda();
                vpa.enableCmbVivienda(true);
            } else if (vpa.getIndexCmbCedula() == 0) {
                vpa.enableCmbVivienda(false);
                vpa.vaciarCmbVivienda();
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Cancelar")) {
            vpa.limpiarCampos();

        } else if (e.getSource().equals(vpa.getCmbCuota())) {
            if (vpa.getIndexCmbCuota() > 0) {
                montoPagar();

            } else if (vpa.getIndexCmbCuota() == 0) {
                vpa.setTxtMonto("0.00");
            }
        } else if (e.getSource().equals(vpa.getCmbPago())) {
            if (vpa.getIndexCmbPagos() > 0) {
                vpa.setEnabledBtnBuscar(true);
                vpa.setEnabledBtnEfectuar(false);
            } else if (vpa.getIndexCmbPagos() == 0) {
                vpa.setEnabledBtnBuscar(false);
                vpa.setEnabledBtnEfectuar(true);
            }
        }

// else if (e.getActionCommand().equalsIgnoreCase("Eliminar")) {
//            eliminarPagos();
//        }             
    }

}
