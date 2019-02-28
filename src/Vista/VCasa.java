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
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class VCasa extends javax.swing.JDialog {

   
    /**
     * Creates new form VCasa
     */
    public VCasa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    
    public void addCmbUrbanizacion(String item) {
        cmbUrbanizacion.addItem(item);
    }

    public void vaciarCmbUrbanizacione() {
        cmbUrbanizacion.removeAllItems();
    }

    public void addCmbCalle(String item) {
        cmbCalle.addItem(item);
    }

    public void vaciarCmbCalle() {
        cmbCalle.removeAllItems();
        cmbCalle.addItem("Seleccione Calle");
    }

    public void addCmbCasa(String item) {
        cmbCasa.addItem(item);
    }

    public void addCmbPropietario(String item) {
        cmbPropietario.addItem(item);
    }

    public void vaciarCmbCasa() {
        cmbCasa.removeAllItems();
        cmbCasa.addItem("Seleccione Casa");
    }

    public JTable getjTable1() {
        return jTable1;
    }
    
    public void vaciarTabla(){
        String[] titulos = {"Nombre Urb.", "Código Edif.", "Código Apart.", "Cédula Prop.", "Nro. Piso", "Cant. Habitaciones", "Cant. Baños"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(modelo);
  
    }

    public void agregarListener(ActionListener action) {

        btnLimpiar.addActionListener(action);
        btnRegresar.addActionListener(action);
        btnEliminar.addActionListener(action);
        btnIncluir.addActionListener(action);
        btnModificar.addActionListener(action);


        cmbUrbanizacion.addActionListener(action);
        cmbCalle.addActionListener(action);
        cmbCasa.addActionListener(action);

    }

    public void limpiarCajas() {
        cmbPropietario.setSelectedIndex(0);
        cmbCalle.setSelectedIndex(0);
        cmbUrbanizacion.setSelectedIndex(0);
        cmbCasa.setSelectedIndex(0);
        spnPiso.setValue(1);
        spnBaños.setValue(1);
        spnHabitaciones.setValue(1);
        btnIncluir.setText("Incluir");

        cmbCalle.setEnabled(false);
        cmbCasa.setEnabled(false);
        cmbPropietario.setEnabled(false);

        spnPiso.setEnabled(false);
        spnBaños.setEnabled(false);
        spnHabitaciones.setEnabled(false);

        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnIncluir.setEnabled(false);
        cmbUrbanizacion.setEnabled(true);
        Tabla.vaciarTabla(jTable1);
    }

    public void limpiarCampos() {
        cmbPropietario.setSelectedIndex(0);
        spnPiso.setValue(1);
        spnBaños.setValue(1);
        spnHabitaciones.setValue(1);

        cmbPropietario.setEnabled(false);

        spnPiso.setEnabled(false);
        spnBaños.setEnabled(false);
        spnHabitaciones.setEnabled(false);

        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);

        Tabla.vaciarTabla(jTable1);
    }

    public void enableBtnEliminar(boolean enabled) {
        btnEliminar.setEnabled(enabled);
    }

    public void enableBtnModificar(boolean enabled) {
        btnModificar.setEnabled(enabled);
    }



    public void enableBtnIncluir(boolean enabled) {
        btnIncluir.setEnabled(enabled);
    }

    public void enableCmbCalle(boolean enabled) {
        cmbCalle.setEnabled(enabled);
    }

    public void enableCmbCasa(boolean enabled) {
        cmbCasa.setEnabled(enabled);
    }

    public void enableParaModificar(boolean enabled) {
        btnModificar.setEnabled(enabled);
        btnEliminar.setEnabled(enabled);

        btnIncluir.setEnabled(!enabled);

        cmbUrbanizacion.setEnabled(!enabled);
        cmbCalle.setEnabled(!enabled);
        cmbCasa.setEnabled(!enabled);

        cmbPropietario.setEnabled(enabled);
        spnBaños.setEnabled(enabled);
        spnPiso.setEnabled(enabled);
        spnHabitaciones.setEnabled(enabled);

    }

    public void enableParaIncluir(boolean enabled) {
        btnModificar.setEnabled(!enabled);
        btnEliminar.setEnabled(!enabled);

        btnIncluir.setEnabled(enabled);

        cmbUrbanizacion.setEnabled(!enabled);
        cmbCalle.setEnabled(!enabled);
        cmbCasa.setEnabled(!enabled);

        cmbPropietario.setEnabled(enabled);
        spnBaños.setEnabled(enabled);
        spnPiso.setEnabled(enabled);
        spnHabitaciones.setEnabled(enabled);

        btnIncluir.setText("Guardar");

    }

    public JComboBox<String> getCmbCalle() {
        return cmbCalle;
    }

    public JComboBox<String> getCmbUrbanizacion() {
        return cmbUrbanizacion;
    }

    public int getIndexCmbUrbanizacion() {
        return cmbUrbanizacion.getSelectedIndex();
    }

    public int getIndexCmbCalle() {
        return cmbCalle.getSelectedIndex();
    }

    public int getIndexCmbCasa() {
        return cmbCasa.getSelectedIndex();
    }

    public int getIndexCmbPropietario() {
        return cmbPropietario.getSelectedIndex();
    }

    public JComboBox<String> getCmbCasa() {
        return cmbCasa;
    }

    public void setValorSpnPiso(int valor) {
        spnPiso.setValue(valor);
    }

    public void setValorSpnBaños(int valor) {
        spnBaños.setValue(valor);
    }

    public void setValorSpnHabitaciones(int valor) {
        spnHabitaciones.setValue(valor);
    }

    public void setValorCmbPropietario(String valor) {
        cmbPropietario.setSelectedItem(valor);

    }

    public void setIndexCmbCasa(int index) {
        cmbCasa.setSelectedIndex(index);
    }

    public String getValorCmbPropietario() {
        return (String) cmbPropietario.getSelectedItem();
    }

    public void setMaximoPisos(int pisos) {
        spnPiso.setModel(new javax.swing.SpinnerNumberModel(1, 1, pisos, 1));
    }

    public int getValorSpnHabitaciones() {
        return (int) spnHabitaciones.getValue();
    }

    public int getValorSpnBaños() {
        return (int) spnBaños.getValue();
    }

    public int getSizeCmbCasa() {
        return cmbCasa.getItemCount();
    }

    public int getValorSpnPiso() {
        return (int) spnPiso.getValue();
    }

    public String getValorCmbCasa() {
        return cmbCasa.getSelectedItem().toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnIncluir = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cmbUrbanizacion = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbCalle = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        spnHabitaciones = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        spnBaños = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        cmbCasa = new javax.swing.JComboBox<>();
        spnPiso = new javax.swing.JSpinner();
        cmbPropietario = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Casa");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Casa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Código de Urbanización");

        jLabel2.setText("Nro. de Calle");

        jLabel3.setText("Código Casa");

        btnIncluir.setText("Incluir");

        btnModificar.setText("Modificar");

        btnEliminar.setText("Eliminar");

        cmbUrbanizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLimpiar.setText("Limpiar");

        jLabel4.setText("Cédula propietario");

        cmbCalle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Calle" }));

        jLabel5.setText("Cantidad de habitaciones");

        spnHabitaciones.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));

        jLabel6.setText("Cantidad de baños");

        spnBaños.setModel(new javax.swing.SpinnerNumberModel(1, 1, 3, 1));

        jLabel7.setText("Cantidad de pisos");

        cmbCasa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Casa" }));
        cmbCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCasaActionPerformed(evt);
            }
        });

        spnPiso.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));

        cmbPropietario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Propietario" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(178, 178, 178)
                        .addComponent(btnIncluir)
                        .addGap(39, 39, 39)
                        .addComponent(btnModificar)
                        .addGap(43, 43, 43)
                        .addComponent(btnEliminar)
                        .addGap(44, 44, 44)
                        .addComponent(btnLimpiar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbCalle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbUrbanizacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbCasa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(spnPiso, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                    .addGap(105, 105, 105)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(spnBaños, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                .addComponent(spnHabitaciones, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(cmbPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 85, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbUrbanizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(spnPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(spnHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(spnBaños, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIncluir)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar))
                .addGap(7, 7, 7))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Urb.", "Nro. Calle", "Código Casa.", "Cédula Prop.", "Cant. Pisos", "Cant. Habitaciones", "Cant. Baños"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnRegresar.setText("Regresar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(25, 25, 25)
                        .addComponent(btnRegresar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCasaActionPerformed

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
            java.util.logging.Logger.getLogger(VCasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VCasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VCasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VCasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VCasa dialog = new VCasa(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbCalle;
    private javax.swing.JComboBox<String> cmbCasa;
    private javax.swing.JComboBox<String> cmbPropietario;
    private javax.swing.JComboBox<String> cmbUrbanizacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JSpinner spnBaños;
    private javax.swing.JSpinner spnHabitaciones;
    private javax.swing.JSpinner spnPiso;
    // End of variables declaration//GEN-END:variables
}
