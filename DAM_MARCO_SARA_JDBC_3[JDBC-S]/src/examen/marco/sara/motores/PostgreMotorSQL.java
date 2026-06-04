package examen.marco.sara.motores;
/*
=========================================
AUTOR: SARA MARCO 
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 04/06/26
=========================================
*/
import java.sql.DriverManager;

public class PostgreMotorSQL extends MotorSQL {

    private static PostgreMotorSQL instanciaUnica;

    private static final String URL = "jdbc:postgresql://marco-sara-dam-rec3.cuwwltd1mfyq.us-east-1.rds.amazonaws.com:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";
    private static final String DRIVER = "org.postgresql.Driver";

    // 2. Utilizo el singleton
    private PostgreMotorSQL(){
        super(URL, USER, PASSWORD, DRIVER);
    }

    // Método de acceso global para acceder al objeto UNICO
    public static PostgreMotorSQL getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new PostgreMotorSQL();
        }
        return instanciaUnica;
    }

    @Override
    public void connect(){

        try{

            Class.forName(driver);

            conn =
                    DriverManager.getConnection(
                            url,
                            user,
                            password
                    );

            System.out.println(
                    "CONEXIÓN OK");

        }catch (Exception e){

            System.out.println(
                    e.getMessage());
        }
    }
}
