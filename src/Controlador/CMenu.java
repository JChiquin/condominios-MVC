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

import Vista.VMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CMenu implements ActionListener {

    VMenu vm;

    public CMenu() {
        super();
        vm = new VMenu();
        vm.setLocationRelativeTo(null);
        vm.setVisible(true);
        vm.setExtendedState(6);
        vm.agregarListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase("Propietario")) {
            new CPropietario();
        } else if (e.getActionCommand().equalsIgnoreCase("Calle")) {
            new CCalle();
        } else if (e.getActionCommand().equalsIgnoreCase("Edificio")) {
            new CEdificio();
        } else if (e.getActionCommand().equalsIgnoreCase("Apartamento")) {
            new CApartamento();
        } else if (e.getActionCommand().equalsIgnoreCase("Empresa")) {
            new CUrbanizacion();
        } else if (e.getActionCommand().equalsIgnoreCase("Casa")) {
            new CCasa();
        } else if (e.getActionCommand().equalsIgnoreCase("Gastos")) {
            new CGastos();
        } else if (e.getActionCommand().equalsIgnoreCase("Cuotas")) {
            new CCuotas();
        } else if (e.getActionCommand().equalsIgnoreCase("Generar Pago")) {
            new CPago();
        } else if (e.getActionCommand().equalsIgnoreCase("Propietarios por Urb")) {
            new CReportePropietarioUrb();
        } else if (e.getActionCommand().equalsIgnoreCase("Cuotas por Urbanización")) {
            new CreporteCuotaporUrbanizacion();
        } else if (e.getActionCommand().equalsIgnoreCase("Salir")) {
            System.exit(0);
        } else if (e.getActionCommand().equalsIgnoreCase("Cuotas detalladas")) {
            new CReporteGastoCuota();
        }

    }

}
