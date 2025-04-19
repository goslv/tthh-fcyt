/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.tthh;

import org.tthh.controlador.FuncionarioController;
import org.tthh.modelo.dao.FuncionarioDaoImpl;
import org.tthh.vista.GUIFuncionario;

/**
 *
 * @author Usuario
 */
public class TthhCodejava {

    public static void main(String[] args) {
        FuncionarioDaoImpl dao = new FuncionarioDaoImpl();
        GUIFuncionario gui = new GUIFuncionario(null, true);
        FuncionarioController ctrl = new FuncionarioController(gui, dao);
        ctrl.mostrarVentana();
       
    }
}
