package examen.marco.sara.dao;
import java.util.ArrayList;
/*
=========================================
AUTOR: SARA MARCO 
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 04/06/26
=========================================
*/
public interface DAO<T> {
    void add(T object);
    void update(int id, T object);
    void delete(int id);
    T find(int id);
    ArrayList<T> findAll();
}