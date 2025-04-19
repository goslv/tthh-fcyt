/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.tthh.modelo.dao;

import java.util.List;

public interface dao<T> {
    public void insertar(T t);
    public void actualizar(T t);
    public void eliminar(T t);
    public List<T> listar();
}