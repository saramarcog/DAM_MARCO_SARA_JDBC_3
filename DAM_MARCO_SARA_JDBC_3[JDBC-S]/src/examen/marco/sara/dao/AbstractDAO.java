package examen.marco.sara.dao;
/*
=========================================
AUTOR: SARA MARCO
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 04/06/26
=========================================
*/
import examen.marco.sara.motores.MotorSQL;

import java.util.ArrayList;

public abstract class AbstractDAO<T> implements DAO<T> {

    protected MotorSQL motorSQL;
    public AbstractDAO(MotorSQL motorSQL) {
        this.motorSQL = motorSQL;
    }

    public void executeDynamicUpdate(String tableName, int id, T beanObject) {
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        java.lang.reflect.Field[] fields = beanObject.getClass().getDeclaredFields();
        ArrayList<Object> values = new ArrayList<>();

        try {
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(beanObject);

                String columnName = field.getName().toLowerCase();

                if (columnName.equals("malwaredetectado")) {
                    columnName = "malware_detectado";
                } else if (columnName.equals("nivelseveridad")) {
                    columnName = "nivel_severidad";
                } else if (columnName.equals("autorexamen")) {
                    columnName = "autor_examen";
                }

                if (value != null && !field.getName().equalsIgnoreCase("id") &&
                        !field.getName().equalsIgnoreCase("soc") && !field.getName().equalsIgnoreCase("informe")) {
                    sql.append(columnName).append(" = ?, ");
                    values.add(value);
                }
            }

            sql.setLength(sql.length() - 2);
            sql.append(" WHERE id = ?");
            values.add(id);

            motorSQL.connect();
            motorSQL.prepare(sql.toString());

            for (int i = 0; i < values.size(); i++) {
                Object val = values.get(i);
                if (val instanceof String) motorSQL.getPs().setString(i + 1, (String) val);
                else if (val instanceof Integer) motorSQL.getPs().setInt(i + 1, (Integer) val);
                else if (val instanceof Boolean) motorSQL.getPs().setBoolean(i + 1, (Boolean) val);
            }

            motorSQL.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fallo en el motor: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
    }

    protected void printError(Exception e) {
        System.out.println("Fallo en la base de datos: " + e.getMessage());
    }

}