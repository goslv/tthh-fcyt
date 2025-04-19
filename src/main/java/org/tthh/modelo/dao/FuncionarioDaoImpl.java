/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tthh.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.tthh.modelo.Conexion;
import org.tthh.modelo.Funcionario;

/**
 *
 * @author Usuario
 */
public class FuncionarioDaoImpl implements dao<Funcionario>{
    Connection conec;
    PreparedStatement sentencia;
    
    public FuncionarioDaoImpl(){
        Conexion conectar = new Conexion();
        conec = conectar.conectarBD();
    }
    
     @Override
    public void insertar(Funcionario funcionario) {
        String cSQL = "INSERT INTO funcionario (nombre,apellido,cedula, estado_civil,cargo, departamento, estado, fecha_ingreso) VALUES (?,?,?,?,?,?,?,?)";
        try {
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setString(1, funcionario.getNombre());
            sentencia.setString(2, funcionario.getApellido());
            sentencia.setString(3, funcionario.getCedula());
            sentencia.setString(4, funcionario.getEstado_civil());
            sentencia.setString(5, funcionario.getCargo());
            sentencia.setString(6, funcionario.getDepartamento());
            sentencia.setString(7, funcionario.getEstado());
            LocalDate fechaLocal = funcionario.getFecha_ingreso();
            java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaLocal);
            sentencia.setDate(8, fechaSQL);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void actualizar(Funcionario funcionario) {
        String cSql= "update funcionario set nombre=?,apellido=?, cedula=?, estado_civil=?, cargo=?, departamento=?, estado=?, fecha_ingreso=? where id=?";
        try {
            sentencia = conec.prepareStatement(cSql);
            sentencia.setString(1, funcionario.getNombre());
            sentencia.setString(2, funcionario.getApellido());
            sentencia.setString(3, funcionario.getCedula());
            sentencia.setString(4, funcionario.getEstado_civil());
            sentencia.setString(5, funcionario.getCargo());
            sentencia.setString(6, funcionario.getDepartamento());
            sentencia.setString(7, funcionario.getEstado());
            java.sql.Date fechaSQL = java.sql.Date.valueOf(funcionario.getFecha_ingreso());
            sentencia.setDate(8, fechaSQL);
            sentencia.setInt(9, funcionario.getId());
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
     @Override
    public void eliminar(Funcionario funcionario) {
        String cSql = "delete from funcionario where id=?";
        try {
            sentencia = conec.prepareStatement(cSql);
            sentencia.setInt(1, funcionario.getId());
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
      @Override
    public List<Funcionario> listar() {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
        try {
            //Preparar sentencia SQL
            String sql = "SELECT * FROM funcionario";
            sentencia = conec.prepareStatement(sql);
            //Ejecutar sentencia SQL
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNombre(rs.getString("nombre"));
                funcionario.setApellido(rs.getString("apellido"));
                funcionario.setCedula(rs.getString("cedula"));
                funcionario.setEstado_civil(rs.getString("estado_civil"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setDepartamento(rs.getString("departamento"));
                funcionario.setEstado(rs.getString("estado"));
                funcionario.setFecha_ingreso(rs.getDate("fecha_ingreso").toLocalDate());
                listaFuncionarios.add(funcionario);
            }
        } catch (Exception e) {
            System.out.println("Error al listar Funcionario: " + e.getMessage());
        }
        return listaFuncionarios;
    }


}
