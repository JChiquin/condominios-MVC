/**
 * @author Chiquín, Jorge
 * @author Vargas, Alexander
 * @author Bonilla, José
 * @author Fernández, Ysidro
 * @author Rodríguez, Ronald
 */

package lib;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tabla {

    public Tabla() {
        super();
    }

    public static void cargarTabla(String[] titulos, int[] anchos,
            DefaultTableModel modelo, JTable tabla) {
        tabla.setModel(modelo);
        tabla.getTableHeader().setReorderingAllowed(false);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }
    }

    

    public static void vaciarTabla(JTable tabla) {
        String[] titulos = new String[tabla.getColumnCount()];
        for (int i = 0; i < titulos.length; i++) {
            titulos[i] = tabla.getColumnName(i);
        }

        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla.setModel(modelo);
    }
}
