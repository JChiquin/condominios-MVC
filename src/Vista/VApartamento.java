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
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class VApartamento extends javax.swing.JDialog {

    /**
     * Creates new form VAparment
     */
    public VApartamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void addCmbUrbanizacion(String item) {
        cmbUrbanizacion.addItem(item);
    }

    public void vaciarCmbUrbanizacione() {
        cmbUrbanizacion.removeAllItems();
    }

    public void addCmbEdificio(String item) {
        cmbEdificio.addItem(item);
    }

    public void vaciarCmbEdificio() {
        cmbEdificio.removeAllItems();
        cmbEdificio.addItem("Seleccione Edificio");
    }

    public void addCmbApartamento(String item) {
        cmbApartamento.addItem(item);
    }

    public void addCmbPropietario(String item) {
        cmbPropietario.addItem(item);
    }

    public void vaciarCmbApartamento() {
        cmbApartamento.removeAllItems();
        cmbApartamento.addItem("Seleccione Apartamento");
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
        cmbEdificio.addActionListener(action);
        cmbApartamento.addActionListener(action);

    }

    public void limpiarCajas() {
        cmbPropietario.setSelectedIndex(0);
        cmbEdificio.setSelectedIndex(0);
        cmbUrbanizacion.setSelectedIndex(0);
        cmbApartamento.setSelectedIndex(0);
        spnPiso.setValue(1);
        spnBaños.setValue(1);
        spnHabitaciones.setValue(1);
        btnIncluir.setText("Incluir");

        cmbEdificio.setEnabled(false);
        cmbApartamento.setEnabled(false);
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

    public void enableCmbEdificio(boolean enabled) {
        cmbEdificio.setEnabled(enabled);
    }

    public void enableCmbApartamento(boolean enabled) {
        cmbApartamento.setEnabled(enabled);
    }

    public void enableParaModificar(boolean enabled) {
        btnModificar.setEnabled(enabled);
        btnEliminar.setEnabled(enabled);

        btnIncluir.setEnabled(!enabled);

        cmbUrbanizacion.setEnabled(!enabled);
        cmbEdificio.setEnabled(!enabled);
        cmbApartamento.setEnabled(!enabled);

        cmbPropietario.setEnabled(enabled);
        spnBaños.setEnabled(enabled);
        spnHabitaciones.setEnabled(enabled);

    }

    public void enableParaIncluir(boolean enabled) {
        btnModificar.setEnabled(!enabled);
        btnEliminar.setEnabled(!enabled);

        btnIncluir.setEnabled(enabled);

        cmbUrbanizacion.setEnabled(!enabled);
        cmbEdificio.setEnabled(!enabled);
        cmbApartamento.setEnabled(!enabled);

        cmbPropietario.setEnabled(enabled);
        spnBaños.setEnabled(enabled);
        spnPiso.setEnabled(enabled);
        spnHabitaciones.setEnabled(enabled);

        btnIncluir.setText("Guardar");

    }

    public JComboBox<String> getCmbEdificio() {
        return cmbEdificio;
    }

    public JComboBox<String> getCmbUrbanizacion() {
        return cmbUrbanizacion;
    }

    public int getIndexCmbUrbanizacion() {
        return cmbUrbanizacion.getSelectedIndex();
    }

    public int getIndexCmbEdificio() {
        return cmbEdificio.getSelectedIndex();
    }

    public int getIndexCmbApartamento() {
        return cmbApartamento.getSelectedIndex();
    }

    public int getIndexCmbPropietario() {
        return cmbPropietario.getSelectedIndex();
    }

    public JComboBox<String> getCmbApartamento() {
        return cmbApartamento;
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

    public void setIndexCmbApartamento(int index) {
        cmbApartamento.setSelectedIndex(index);
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

    public int getSizeCmbApartamento() {
        return cmbApartamento.getItemCount();
    }

    public int getValorSpnPiso() {
        return (int) spnPiso.getValue();
    }

    public String getValorCmbApartamento() {
        return cmbApartamento.getSelectedItem().toString();
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
        cmbEdificio = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        spnHabitaciones = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        spnBaños = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        cmbApartamento = new javax.swing.JComboBox<>();
        spnPiso = new javax.swing.JSpinner();
        cmbPropietario = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Apartamento");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Apartamento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Código de Urbanización");

        jLabel2.setText("Código de Edificio");

        jLabel3.setText("Código Apart.");

        btnIncluir.setText("Incluir");

        btnModificar.setText("Modificar");

        btnEliminar.setText("Eliminar");

        cmbUrbanizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLimpiar.setText("Limpiar");

        jLabel4.setText("Cédula propietario");

        cmbEdificio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Edificio" }));

        jLabel5.setText("Cantidad de habitaciones");

        spnHabitaciones.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));

        jLabel6.setText("Cantidad de baños");

        spnBaños.setModel(new javax.swing.SpinnerNumberModel(1, 1, 3, 1));

        jLabel7.setText("Nro. piso al que pertenece");

        cmbApartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Apartamento" }));
        cmbApartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbApartamentoActionPerformed(evt);
            }
        });

        spnPiso.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

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
                        .addGap(36, 36, 36)
                        .addComponent(btnModificar)
                        .addGap(46, 46, 46)
                        .addComponent(btnEliminar)
                        .addGap(44, 44, 44)
                        .addComponent(btnLimpiar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbEdificio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbUrbanizacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbApartamento, 0, 160, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(spnPiso)
                                .addGap(105, 105, 105))
                            .addComponent(cmbPropietario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnBaños, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(spnHabitaciones))))
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
                    .addComponent(cmbEdificio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbApartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                "Nombre Urb.", "Código Edif.", "Código Apart.", "Cédula Prop.", "Nro. Piso", "Cant. Habitaciones", "Cant. Baños"
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

    private void cmbApartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbApartamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbApartamentoActionPerformed

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
            java.util.logging.Logger.getLogger(VApartamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VApartamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VApartamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VApartamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VApartamento dialog = new VApartamento(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cmbApartamento;
    private javax.swing.JComboBox<String> cmbEdificio;
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
