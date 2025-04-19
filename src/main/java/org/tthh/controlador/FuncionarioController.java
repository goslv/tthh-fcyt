/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tthh.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.List;
import org.tthh.modelo.Funcionario;
import org.tthh.modelo.dao.FuncionarioDaoImpl;
import org.tthh.modelo.tabla.FuncionarioTablaModel;
import org.tthh.vista.GUIFuncionario;

/**
 *
 * @author Usuario
 */
public class FuncionarioController implements ActionListener{
    private GUIFuncionario gui;
    private FuncionarioDaoImpl abm;
    private char operacion;

    FuncionarioTablaModel modelo = new FuncionarioTablaModel();
    
      public FuncionarioController(GUIFuncionario gui, FuncionarioDaoImpl abm) {
        this.gui = gui;
        this.abm = abm;

        this.gui.btnGuardar.addActionListener((ActionListener) this);
        this.gui.btnNuevo.addActionListener(this);
        this.gui.btnCancelar.addActionListener(this);
        this.gui.btnEditar.addActionListener(this);
        this.gui.btnEliminar.addActionListener(this);

        this.listar();

        gui.tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JTable tabla = (JTable) evt.getSource();
                int row = tabla.rowAtPoint(evt.getPoint());
                FuncionarioTablaModel model = (FuncionarioTablaModel) tabla.getModel();

                setFuncionarioForm(model.getEntityByRow(row));

            }
        });
    }
      @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hola Class");
        if (e.getSource() == gui.btnNuevo) {
            operacion = 'N';
            System.out.println("Soy el boton nuevo");
            habilitarCampos(true);
            gui.txtNombre.requestFocus();
            limpiar();
        }

        if (e.getSource() == gui.btnEditar) {
            operacion = 'E';
            System.out.println("Soy el boton EDITAR");
            habilitarCampos(true);
        }
        
        if (e.getSource() == gui.btnEliminar) {
          int fila =  gui.tabla.getSelectedRow();
          if(fila >= 0){
              int ok =  JOptionPane.showConfirmDialog(gui, "Realmente desea eliminar el registro?","Confirmar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
              if(ok == 0){
                abm.eliminar(modelo.getEntityByRow(fila));
              listar();  
              limpiar();
              }
             
          }else{
              JOptionPane.showMessageDialog(gui, "Debe seleccionar una fila");
          }
        } 

        if (e.getSource() == gui.btnGuardar) {
            boolean v_control = validarDatos();
            if(v_control == true){
                JOptionPane.showMessageDialog(gui, "Favor completar los datos");
                return;
            }
            switch (operacion) {
                case 'N':
                    abm.insertar(getFuncionarioForm());
                    break;
                case 'E':
                    abm.actualizar(getFuncionarioForm());
                    break;
            }
            
            limpiar();
            listar();
        }

        if (e.getSource() == gui.btnCancelar) {
            habilitarCampos(false);
            limpiar();
        }
    }
    
    // Funcion encargado de mostrar la ventana
    public void mostrarVentana() {
        gui.setLocationRelativeTo(gui);
        gui.setVisible(true);
    }

    //Funcion encargado de recuperar los valos de los textfield en un objeto tipo empresa
    private Funcionario getFuncionarioForm() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNombre(gui.txtNombre.getText());
        funcionario.setApellido(gui.txtApellido.getText());
        funcionario.setCedula(gui.txtCi.getText());
        funcionario.setEstado_civil(gui.txtCivil.getText());
        funcionario.setCargo(gui.txtCargo.getText());
        funcionario.setDepartamento(gui.txtDepart.getText());
        funcionario.setEstado(gui.txtEstado.getText());
         java.util.Date date = gui.dcIngreso.getDate();
    if (date != null) {
        LocalDate fechaIngreso = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        funcionario.setFecha_ingreso(fechaIngreso);
    } else {
        funcionario.setFecha_ingreso(null); // o manejarlo según sea necesario
    }
        return funcionario;
    }

    private void setFuncionarioForm(Funcionario funcionario) {
        gui.txtId.setText(funcionario.getId().toString());
        gui.txtNombre.setText(funcionario.getNombre());
        gui.txtApellido.setText(funcionario.getApellido());
        gui.txtCi.setText(funcionario.getCedula());
        gui.txtCivil.setText(funcionario.getEstado_civil());
        gui.txtCargo.setText(funcionario.getCargo());
        gui.txtDepart.setText(funcionario.getDepartamento());
        gui.txtEstado.setText(funcionario.getEstado());
        LocalDate localDate = funcionario.getFecha_ingreso();
        if (localDate != null) {
        Date fechaUtil = (Date) java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        gui.dcIngreso.setDate(fechaUtil);
        }
   }

    private void limpiar() {
        gui.txtId.setText("0");
        gui.txtNombre.setText("");
        gui.txtApellido.setText("");
        gui.txtCi.setText("");
        gui.txtCivil.setText("");
        gui.txtCargo.setText("");
        gui.txtDepart.setText("");
        gui.txtEstado.setText("");
        gui.dcIngreso.setDate(null); // Limpia la fecha
    }

    public void listar() {
        List<Funcionario> lista = this.abm.listar();
        modelo.setLista(lista);
        llenarTabla(gui.tabla);

    }

    public void llenarTabla(JTable tabla) {
        gui.tabla.setModel(modelo);
        gui.tabla.updateUI();
    }

    private void habilitarCampos(Boolean h) {
        gui.txtId.setEnabled(h);
        gui.txtNombre.setEnabled(h);
        gui.txtApellido.setEnabled(h);
        gui.txtCi.setEnabled(h);
        gui.txtCargo.setEnabled(h);
        gui.txtDepart.setEnabled(h);
        gui.txtEstado.setEnabled(h);
        gui.dcIngreso.setEnabled(h);
    }
    
    private boolean validarDatos(){
        boolean vacio = false;
        
        if(gui.txtCi.getText().isEmpty()){
            vacio = true;
        }
        return vacio;
    }

}