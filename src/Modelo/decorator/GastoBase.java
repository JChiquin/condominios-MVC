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
package Modelo.decorator;

import Modelo.*;
import java.util.Date;


public class GastoBase  extends ComponenteGasto{

    @Override
    public double TotalGastos() {
        return 0;
    }
}
