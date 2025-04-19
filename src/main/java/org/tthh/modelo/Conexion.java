/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tthh.modelo;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;


public class Conexion {
    String url = "jdbc:mysql://localhost:3306/tthh-oficial";
    String usuario = "root";
    String password = "72270";

    public Conexion() {
    }

    public Connection conectarBD(){
        Connection conexion = null;
        try {
            //realizar la conexion a BD
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Error al conectar a BD: " + e.getMessage());
        }
        return conexion;
    }
}
