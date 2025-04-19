/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tthh.modelo.tabla;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.tthh.modelo.Funcionario;
/**
 *
 * @author Usuario
 */
public class FuncionarioTablaModel extends AbstractTableModel{
    
    List<Funcionario> lista;
    private String[] columnNames = new String []{"ID", "NOMBRE", "APELLIDO", "CÉDULA", "ESTADO CIVIL", "CARGO", "DEPARTAMENTO", "ESTADO", "FECHA INGRESO"}; //Definimos los nombres de la columna
    
    Class []  columnClass = new Class[]{Integer.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class};
      public String getColumnName(int i) {
        return columnNames[i];
    }
      
      @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
    
    @Override
    public int getRowCount() {
       return lista.size(); 
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcionario funcionario = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return funcionario.getId();
            case 1:
                return funcionario.getNombre();
            case 2:
                return funcionario.getApellido();
            case 3:
                return funcionario.getCedula();
            case 4:
                return funcionario.getEstado_civil();
            case 5:
                return funcionario.getCargo();
            case 6:
                return funcionario.getDepartamento();
            case 7:
                return funcionario.getEstado();
            case 8:
                return funcionario.getFecha_ingreso() != null 
           ? funcionario.getFecha_ingreso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) 
           : "";
        }
        return "";
    }
    
    public List<Funcionario> getLista() {
        return lista;
    }

    public void setLista(List<Funcionario> lista) {
        this.lista = lista;
    }
    
    public Funcionario getEntityByRow(int index){
        return lista.get(index);
    }
}
