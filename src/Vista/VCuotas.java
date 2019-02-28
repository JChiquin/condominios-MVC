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
package Vista;

import lib.Tabla;
import Controlador.CCuotas;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class VCuotas extends javax.swing.JDialog {

    /**
     * Creates new form VCuotas
     */
    public VCuotas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        java.util.Date now = new java.util.Date(System.currentTimeMillis());
        dtcFechaGasto.setDate(now);
        txtTotalCuota.setEditable(false);
        txtTotalViviendas.setEditable(false);
        txtTotalGasto.setEditable(false);
    }

    public void setEnabledBtnIncluir(boolean enabled) {
        this.btnAgregar.setEnabled(enabled);
    }
    
    public void setTextTotalGasto(String text){
        txtTotalGasto.setText(text);
    }
    public void setTextTotalViviendas(String text){
        txtTotalViviendas.setText(text);
    }
    public void setTextTotalCuota(String text){
        txtTotalCuota.setText(text);
    }
    public int getTextTotalViviendas(){
        return Integer.valueOf(txtTotalViviendas.getText());
    }
    public double getTextTotalCuota() throws ParseException{
        return Double.parseDouble(txtTotalCuota.getText().replace(',', '.'));
    }

    public void setEnabledSpnAnio(boolean enabled) {
        this.spnAnio.setEnabled(enabled);
    }

    public void vaciarCmbUrbanizaciones() {
        cmbUrbanizacion.removeAllItems();
        cmbCuotas.addItem("Seleccione una Urbanización");

    }

    public void vaciarCmbCuota() {
        cmbCuotas.removeAllItems();
        cmbCuotas.addItem("Seleccione una Cuota");

    }

    public void vaciarCmbGasto() {
        cmbGasto.removeAllItems();
        cmbGasto.addItem("Seleccione un Gasto");

    }

    public void addCmbUrbanizaciones(String item) {
        cmbUrbanizacion.addItem(item);
    }

    public void addCmbCuotas(String item) {
        cmbCuotas.addItem(item);
    }

    public void addCmbGastos(String item) {
        cmbGasto.addItem(item);
    }

    public void enabledCmbCuota(boolean enabled) {
        cmbCuotas.setEnabled(enabled);
    }
    
    public void enabledCmbGastos(boolean enabled) {
        cmbGasto.setEnabled(enabled);
    }

    public void enableBtnAgregar(boolean enabled) {
        btnAgregar.setEnabled(enabled);
    }
    
    public void editableTxtMonto(boolean enabled) {
        spnMonto.setEnabled(enabled);
    }

    public void enableBtnIncluir(boolean enabled) {
        btnIncluirCuota.setEnabled(enabled);
    }

    public void enableBtnCancelar(boolean enabled) {
        btnCancelar.setEnabled(enabled);
    }

    public void enableBtnEliminar(boolean enabled) {
        btnEliminar.setEnabled(enabled);
    }

    public JComboBox<String> getCmbUrbanizacion() {
        return cmbUrbanizacion;
    }

    public JComboBox<String> getCmbCuota() {
        return cmbCuotas;
    }

    public JComboBox<String> getCmbGasto() {
        return cmbGasto;
    }

    public int getIndexCmbUrbanizacion() {
        return cmbUrbanizacion.getSelectedIndex();
    }

    public int getIndexCmbCuota() {
        return cmbCuotas.getSelectedIndex();
    }

    public String getValorCmbCuota() {
        return cmbCuotas.getSelectedItem().toString();
    }

    public int getIndexCmbGasto() {
        return cmbGasto.getSelectedIndex();
    }

    public void selectedCmbCuota(int index) {
        cmbCuotas.setSelectedIndex(index);
    }

    public void setSelectedRbtFijo(boolean seleccionar) {
        this.rbtFijo.setSelected(seleccionar);
    }

    public void setSelectedRbtExtra(boolean seleccionar) {
        this.rbtExtraordinario.setSelected(seleccionar);
    }

    public void setValorSpnAnio(int valor) {
        spnAnio.setValue(valor);
    }

    public void setValorSpnMes(int valor) {
        spnMes.setValue(valor);
    }

    public void setSpnMonto(double valor) {
        spnMonto.setValue(valor);
    }

    public double getMontoSt() {
        return (double)spnMonto.getValue();
    }

    public void setIndexCmbCuota(int index) {
        cmbCuotas.setSelectedIndex(index);
    }
    
    public void setIndexCmbGastos(int index) {
        cmbGasto.setSelectedIndex(index);
    }
    
    public void setSelectedItemCmbGastos(Object o){
        cmbGasto.setSelectedItem(o);
    }
    
    public int getSelectedRow(){
        return tblGastos.getSelectedRow();
    }

    public void setFecha(Date fecha){
        dtcFechaGasto.setDate(fecha);
    }
    
    public Date getFecha(){
        return dtcFechaGasto.getDate();
    }
    public String getValorCmbUrbanizacion() {
        return (String) cmbUrbanizacion.getSelectedItem();
    }

    public int getValorSpnAnio() {
        return (int) spnAnio.getValue();
    }

    public int getValorSpnMes() {
        return (int) spnMes.getValue();
    }
    
    public JTable getTblGastos() {
        return tblGastos;
    }

    public String getTituloIncluirCuota() {
        return btnIncluirCuota.getText();
    }
    
    public boolean getFijoSeleccionado(){
        return rbtFijo.getModel().isSelected();
    }
    
    public boolean getExtraSeleccionado(){
        return rbtExtraordinario.getModel().isSelected();
    }
    public void agregarListener(ActionListener action, MouseListener mouse) {

        btnCancelar.addActionListener(action);
        btnEliminar.addActionListener(action);
        btnAgregar.addActionListener(action);
        btnIncluirCuota.addActionListener(action);
        btnEliminarCuota.addActionListener(action);
        cmbUrbanizacion.addActionListener(action);
        cmbCuotas.addActionListener(action);
        cmbGasto.addActionListener(action);
        tblGastos.addMouseListener(mouse);

    }

    public void limpiarCajas() {
        cmbUrbanizacion.setEnabled(true);
        cmbUrbanizacion.setSelectedIndex(0);
        cmbCuotas.setSelectedIndex(0);
        cmbCuotas.setEnabled(false);
        spnAnio.setValue(2000);
        spnAnio.setEnabled(false);
        spnMes.setValue(1);
        spnMes.setEnabled(false);
        spnMonto.setValue(0);
        spnMonto.setEnabled(false);
        cmbGasto.setSelectedIndex(0);
        cmbGasto.setEnabled(false);
        btnIncluirCuota.setText("Incluir Cuota");
        btnAgregar.setEnabled(false);
        btnIncluirCuota.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnEliminarCuota.setEnabled(false);
        btnCancelar.setEnabled(false);
        rbtFijo.setSelected(true);
        dtcFechaGasto.setEnabled(false);

        Tabla.vaciarTabla(tblGastos);
    }

    public void limpiarCampos() {
        spnAnio.setValue(2000);
        spnAnio.setEnabled(false);
        spnMes.setValue(1);
        spnMes.setEnabled(false);
        spnMonto.setValue(0);
        spnMonto.setEnabled(false);
        cmbGasto.setSelectedIndex(0);
        cmbGasto.setEnabled(false);
        rbtFijo.setSelected(true);
        dtcFechaGasto.setEnabled(false);
        btnEliminar.setEnabled(false);
        txtTotalCuota.setText("");
        txtTotalGasto.setText("");
        
    }

    public void enableParaIncluir(boolean enabled) {
        btnAgregar.setEnabled(enabled);
       
        btnEliminar.setEnabled(enabled);
        cmbUrbanizacion.setEnabled(!enabled);
        cmbCuotas.setEnabled(!enabled);
        cmbGasto.setEnabled(enabled);
        spnAnio.setEnabled(enabled);
        spnMes.setEnabled(enabled);
        spnMonto.setEnabled(enabled);  
        dtcFechaGasto.setEnabled(enabled);

        btnIncluirCuota.setText("Guardar Cuota");

    }

    public void enablePorBusqueda(boolean enabled) {
        btnAgregar.setEnabled(enabled);
        btnEliminarCuota.setEnabled(enabled);
        btnEliminar.setEnabled(enabled);
        cmbUrbanizacion.setEnabled(!enabled);
        cmbCuotas.setEnabled(!enabled);
        cmbGasto.setEnabled(enabled);
        spnMonto.setEnabled(enabled);  
        dtcFechaGasto.setEnabled(enabled);
        btnIncluirCuota.setText("Modificar Cuota");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbUrbanizacion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbCuotas = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        spnAnio = new javax.swing.JSpinner();
        spnMes = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cmbGasto = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        rbtFijo = new javax.swing.JRadioButton();
        rbtExtraordinario = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dtcFechaGasto = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGastos = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        spnMonto = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        txtTotalGasto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotalViviendas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotalCuota = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnIncluirCuota = new javax.swing.JButton();
        btnEliminarCuota = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cuota");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Cuota", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Código de Urbanización");

        cmbUrbanizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccion Urbanización" }));

        jLabel2.setText("Código de la cuota");

        cmbCuotas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Cuota" }));

        jLabel3.setText("Año");

        jLabel4.setText("Mes");

        spnAnio.setModel(new javax.swing.SpinnerNumberModel(2000, 2000, 2030, 1));

        spnMes.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Gastos"));

        jLabel5.setText("Gasto");

        cmbGasto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Gasto" }));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de Gasto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        buttonGroup1.add(rbtFijo);
        rbtFijo.setText("Fijo");
        rbtFijo.setEnabled(false);

        buttonGroup1.add(rbtExtraordinario);
        rbtExtraordinario.setText("Extraordinario");
        rbtExtraordinario.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(rbtFijo)
                .addGap(18, 18, 18)
                .addComponent(rbtExtraordinario)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtFijo)
                    .addComponent(rbtExtraordinario))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel6.setText("Monto");

        jLabel7.setText("Fecha gasto");

        dtcFechaGasto.setEnabled(false);

        tblGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Tipo", "Fecha", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblGastos);

        btnAgregar.setText("Agregar");

        btnEliminar.setText("Eliminar");

        spnMonto.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));

        jLabel8.setText("Total Gasto");

        jLabel9.setText("Total Viviendas");

        jLabel10.setText("Total Cuota");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbGasto, 0, 196, Short.MAX_VALUE)
                            .addComponent(spnMonto)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(dtcFechaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalViviendas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addGap(80, 80, 80)
                                .addComponent(btnEliminar)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(spnMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(1, 1, 1)
                        .addComponent(dtcFechaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTotalGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTotalViviendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTotalCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setText("Cancelar");

        btnIncluirCuota.setText("Incluir Cuota");

        btnEliminarCuota.setText("Eliminar Cuota");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnIncluirCuota)
                .addGap(49, 49, 49)
                .addComponent(btnEliminarCuota)
                .addGap(55, 55, 55)
                .addComponent(btnCancelar)
                .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbCuotas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spnMes, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spnAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(19, 19, 19)
                        .addComponent(cmbUrbanizacion, 0, 179, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbUrbanizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(spnAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnIncluirCuota)
                    .addComponent(btnEliminarCuota))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VCuotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VCuotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VCuotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VCuotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VCuotas dialog = new VCuotas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarCuota;
    private javax.swing.JButton btnIncluirCuota;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbCuotas;
    private javax.swing.JComboBox<String> cmbGasto;
    private javax.swing.JComboBox<String> cmbUrbanizacion;
    private com.toedter.calendar.JDateChooser dtcFechaGasto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtExtraordinario;
    private javax.swing.JRadioButton rbtFijo;
    private javax.swing.JSpinner spnAnio;
    private javax.swing.JSpinner spnMes;
    private javax.swing.JSpinner spnMonto;
    private javax.swing.JTable tblGastos;
    private javax.swing.JTextField txtTotalCuota;
    private javax.swing.JTextField txtTotalGasto;
    private javax.swing.JTextField txtTotalViviendas;
    // End of variables declaration//GEN-END:variables

}
