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

import lib.Tabla;
import DAO.UrbanizacionDAO;
import DAO.CuotaDAO;
import DAO.GastosDAO;
import DAO.ViviendaDAO;
import Modelo.Urbanizacion;
import Modelo.Cuota;
import Modelo.Gasto;
import Modelo.decorator.*;
import Vista.VCuotas;
import lib.Validar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class CCuotas implements ActionListener, MouseListener {

    VCuotas vcu;
    private ArrayList<Urbanizacion> urbanizaciones;
    private ArrayList<Cuota> cuotas;
    private ArrayList<Gasto> gastos;
    private ArrayList<Gasto> gastosporcuota = new ArrayList<>();
    private boolean porcuota = false;

    public CCuotas() {
        super();
        vcu = new VCuotas(null, true);
        vcu.agregarListener(this, this);
        vcu.setLocationRelativeTo(null);
        vcu.limpiarCajas();
        cargarUrbanizaciones();
        cargarGastosCombo();
        vcu.setVisible(true);
    }

    public void cargarUrbanizaciones() {
        vcu.vaciarCmbUrbanizaciones();
        vcu.addCmbUrbanizaciones("Seleccione urbanización");
        UrbanizacionDAO urbDAO = new UrbanizacionDAO();
        urbanizaciones = urbDAO.consultarUrbanizaciones(' ');
        for (Urbanizacion urb : urbanizaciones) {
            vcu.addCmbUrbanizaciones(urb.getNombre());
        }
    }

    public void cargarTotalViviendas() {
        ViviendaDAO vidao = new ViviendaDAO();
        int cant = vidao.totalViviendasPorUrb(urbanizaciones.get(vcu.getIndexCmbUrbanizacion() - 1).getCodigo());
        vcu.setTextTotalViviendas(String.valueOf(cant));
    }

    public void cargarCuotas() {
        vcu.vaciarCmbCuota();
        CuotaDAO cuDAO = new CuotaDAO();
        cuotas = cuDAO.ConsultarCuotas(urbanizaciones.get(vcu.getIndexCmbUrbanizacion() - 1).getCodigo());
        for (Cuota cu : cuotas) {
            vcu.addCmbCuotas(cu.getCodigoCuota());
        }

    }

    public void cargarGastosPorCuota() {
        GastosDAO gaDAO = new GastosDAO();
        gastosporcuota = gaDAO.GastoPorCuota(vcu.getValorCmbCuota());
    }

    public void cargarGastosCombo() {
        vcu.vaciarCmbGasto();
        GastosDAO gaDAO = new GastosDAO();
        gastos = gaDAO.consultarGastos();
        for (Gasto ga : gastos) {
            vcu.addCmbGastos(ga.getNombre());
        }
    }

    public void buscarCuota(int i) {
        Cuota aux = cuotas.get(i);
        vcu.setValorSpnAnio(aux.getNroAnio());
        vcu.setValorSpnMes(aux.getNroMes());
    }

    public void tipoGasto(int i) {
        Gasto aux = gastos.get(i);
        vcu.setSelectedRbtFijo(aux.getTipo() == 1);
        vcu.setSelectedRbtExtra(aux.getTipo() == 2);
    }

    public void generarCodigoCuota() {
        CuotaDAO cuDAO = new CuotaDAO();
        int cant = cuDAO.totalCuotas(urbanizaciones.get(vcu.getIndexCmbUrbanizacion() - 1).getCodigo());
        String valor;
        if (cant < 9) {
            valor = "0";
        } else {
            valor = "";
        }
        valor += cant + 1;
        vcu.addCmbCuotas(urbanizaciones.get(vcu.getIndexCmbUrbanizacion() - 1).getCodigo() + " - " + valor);

        vcu.setIndexCmbCuota(cant + 1);
    }

    public void cargarTablaGastos() {
        String[] titulos = {"Nombre Gasto", "Tipo", "Fecha", "Monto"};
        String[] datos = new String[4];
        int[] anchos = {50, 20, 20, 30};

        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ComponenteGasto gasto= new GastoBase();
        double totalGasto = 0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        for (Gasto ga : gastosporcuota) {
            datos[0] = ga.getNombre();
            datos[1] = (ga.getTipo() == 1 ? "Fijo" : "Extraordinario");
            datos[2] = formato.format(ga.getFecha());
            datos[3] = String.valueOf(ga.getMonto());
            modelo.addRow(datos);
            //Agregando gastos con Decortator.
            if(ga.getNombre()=="Aseo")
                gasto=new Aseo(ga.getMonto(),gasto);
            else if(ga.getNombre()=="Luz")
                gasto=new Luz(ga.getMonto(),gasto);
            else if(ga.getNombre()=="AgugetNombrea")
                gasto=new Agua(ga.getMonto(),gasto);
            else
                gasto=new Otro(ga.getNombre(),ga.getMonto(),gasto);
             
        }
        Tabla.cargarTabla(titulos, anchos, modelo, vcu.getTblGastos());
        vcu.setTextTotalGasto(String.valueOf(gasto.TotalGastos()));

        DecimalFormat formateador = new DecimalFormat("#####.##");
        vcu.setTextTotalCuota(String.valueOf(formateador.format(gasto.TotalGastos() / vcu.getTextTotalViviendas())));
        Validar.mostrarMensaje("Gastos (decorator):\n"+gasto.getDescripcion(), "Cuota", 0);
    }

    public void agregarGastoATabla() {
        int tipo = (vcu.getFijoSeleccionado() ? 1 : 2);
        if (cantidadGastosExtra() == 3 && tipo == 2) {
            Validar.mostrarMensaje("La cuota ya tiene 3 gastos extraordinarios", "Cuotas", 0);
        } else {
            String codigo = gastos.get(vcu.getIndexCmbGasto() - 1).getCodigo();
            String nombre = gastos.get(vcu.getIndexCmbGasto() - 1).getNombre();

            Gasto ga = new Gasto(codigo, nombre, null, tipo, vcu.getMontoSt(), 'A', vcu.getFecha());
            gastosporcuota.add(ga);
            cargarTablaGastos();
        }
    }

    public int cantidadGastosExtra() {
        int cant = 0;
        for (Gasto ga : gastosporcuota) {
            if (ga.getTipo() == 2) {
                cant++;
            }
        }
        return cant;
    }

    public void guardarCuota() throws ParseException {
        Cuota cu = new Cuota(vcu.getTextTotalCuota(), urbanizaciones.get(vcu.getIndexCmbUrbanizacion() - 1), gastosporcuota, 'A', vcu.getValorSpnMes(), vcu.getValorSpnAnio(), vcu.getValorCmbCuota());
        CuotaDAO cuDAO = new CuotaDAO();
        if (cuDAO.incluirCuota(cu)) {
            Validar.mostrarMensaje("Cuota incluida con éxito!", "Cuota", 1);
        } else {
            Validar.mostrarMensaje("¡Error!", "Casa", 0);
        }

    }

    public void guardarGastos() {
        String codCuota = vcu.getValorCmbCuota();
        GastosDAO gaDAO = new GastosDAO();
        for (Gasto ga : gastosporcuota) {
            gaDAO.IncluirGastoPorCuota(codCuota, ga);
        }

    }

    public void eliminarCuota() {
        CuotaDAO cuDAO = new CuotaDAO();
        if (cuDAO.eliminarCuota(cuotas.get(vcu.getIndexCmbCuota() - 1))) {
            Validar.mostrarMensaje("Cuota eliminada con éxito!", "Cuota", 1);
        } else {
            Validar.mostrarMensaje("Error!", "Cuota", 0);
        }

    }

    private void eliminarGasto() {
        if (vcu.getSelectedRow() >= 0) {
            gastosporcuota.remove(vcu.getSelectedRow());
            cargarTablaGastos();
            Validar.mostrarMensaje("Gasto eliminado con éxito!", "Cuota", 0);
        } else {
            Validar.mostrarMensaje("Seleccione un gasto de la tabla", "Cuota", 0);
        }
    }

    private void modificarCuota() throws ParseException {
        CuotaDAO cuDAO = new CuotaDAO();
        cuDAO.modificarCuota(vcu.getValorCmbCuota(), vcu.getTextTotalCuota());
        GastosDAO gaDAO = new GastosDAO();
        gaDAO.eliminarGastosPorCuota(vcu.getValorCmbCuota());
        guardarGastos();
        Validar.mostrarMensaje("Cuota modificada con éxito!", "Cuota", 0);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("Cancelar")) {
            vcu.limpiarCajas();
            vcu.setTextTotalViviendas("");
        } else if (ae.getActionCommand().equalsIgnoreCase("Agregar")) {
            agregarGastoATabla();
        } else if (ae.getActionCommand().equalsIgnoreCase("Incluir Cuota")) {

            generarCodigoCuota();
            vcu.enableParaIncluir(true);
            vcu.enabledCmbGastos(true);
            vcu.enableBtnAgregar(true);
            vcu.editableTxtMonto(true);
            gastosporcuota.clear();

        } else if (ae.getActionCommand().equalsIgnoreCase("Guardar Cuota")) {
            if (gastosporcuota.isEmpty()) {
                Validar.mostrarMensaje("Debe agregar gastos", "Cuota", 0);
            } else {
                try {
                    guardarCuota();
                } catch (ParseException ex) {
                    Logger.getLogger(CCuotas.class.getName()).log(Level.SEVERE, null, ex);
                }
                guardarGastos();
                vcu.limpiarCajas();
            }

        } else if (ae.getActionCommand().equalsIgnoreCase("Eliminar Cuota")) {
            eliminarCuota();
            vcu.limpiarCajas();
        } else if (ae.getActionCommand().equalsIgnoreCase("Eliminar")) {
            eliminarGasto();
        } else if (ae.getActionCommand().equalsIgnoreCase("Modificar Cuota")) {
            if (gastosporcuota.isEmpty()) {
                Validar.mostrarMensaje("Debe agregar gastos", "Cuota", 0);
            } else {
                try {
                    modificarCuota();
                } catch (ParseException ex) {
                    Logger.getLogger(CCuotas.class.getName()).log(Level.SEVERE, null, ex);
                }
                vcu.limpiarCajas();
            }
        } else if (ae.getSource().equals(vcu.getCmbUrbanizacion())) {
            if (vcu.getIndexCmbUrbanizacion() > 0) {
                vcu.enabledCmbCuota(true);
                vcu.enableBtnIncluir(true);
                vcu.enableBtnCancelar(true);
                cargarCuotas();
                cargarTotalViviendas();
            } else if (vcu.getIndexCmbUrbanizacion() == 0) {
                vcu.limpiarCajas();
                vcu.setTextTotalViviendas("");
                vcu.vaciarCmbCuota();
            }
        } else if (ae.getSource().equals(vcu.getCmbCuota())) {
            if (vcu.getIndexCmbCuota() > 0 && vcu.getIndexCmbCuota() <= cuotas.size()) {
                buscarCuota(vcu.getIndexCmbCuota() - 1);
                cargarGastosPorCuota();
                cargarTablaGastos();
                vcu.enablePorBusqueda(true);
            } else if (vcu.getIndexCmbCuota() == 0) {
                vcu.limpiarCampos();
            }

        } else if (ae.getSource().equals(vcu.getCmbGasto())) {
            if (vcu.getIndexCmbGasto() > 0) {
                tipoGasto(vcu.getIndexCmbGasto() - 1);
            } else if (vcu.getIndexCmbGasto() == 0) {
                vcu.setSelectedRbtFijo(true);
                vcu.setSpnMonto(0);
                vcu.setFecha(new java.util.Date(System.currentTimeMillis()));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
