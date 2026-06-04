package examen.marco.sara.motores;
/*
=========================================
AUTOR: SARA MARCO 
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 04/06/26
=========================================
*/
public class MotorFactory {
    public static final String ORACLE = "ORACLE";
    public static final String POSTGRE = "POSTGRE";
    public static final String MARIADB = "MARIADB";

    public static MotorSQL create(String motor) {
        switch (motor) {
            case MARIADB:
                return null;
            case POSTGRE:
                return PostgreMotorSQL.getInstancia();
            case ORACLE:
                return null;
            default:
                throw new IllegalArgumentException("Motor no soportado: " + motor);
        }
    }
}
