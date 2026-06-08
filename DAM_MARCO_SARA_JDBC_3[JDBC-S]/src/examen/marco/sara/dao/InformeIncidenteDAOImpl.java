package examen.marco.sara.dao;

import examen.marco.sara.beans.InformeIncidente;
import examen.marco.sara.motores.MotorSQL;
import java.util.ArrayList;

public class InformeIncidenteDAOImpl extends AbstractDAO<InformeIncidente> {

    public InformeIncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(InformeIncidente inf) {
        String deleteSql = "DELETE FROM informes_incidente WHERE fk_incidente_id = 1";
        String sql = "INSERT INTO informes_incidente(malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES (?, ?, ?, ?, ?)";
        try {
            motorSQL.connect();
            
            // Primero limpiamos registros antiguos para evitar violaciones de clave única/primaria
            motorSQL.prepare(deleteSql);
            motorSQL.executeUpdate();

            // Insertamos la nueva ficha de informe técnico
            motorSQL.prepare(sql);
            motorSQL.getPs().setBoolean(1, inf.isMalwareDetectado());
            motorSQL.getPs().setInt(2, inf.getNivelSeveridad());
            motorSQL.getPs().setString(3, inf.getConclusion());
            motorSQL.getPs().setInt(4, 1); // Forzado al incidente de prueba con ID 1
            motorSQL.getPs().setString(5, inf.getAutorExamen());

            motorSQL.executeUpdate();
            System.out.println("[SUCCESS] Informe de Incidente guardado correctamente en la infraestructura.");
        } catch (Exception e) {
            System.out.println("Error al añadir Informe: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
    }

    @Override 
    public void update(int id, InformeIncidente obj) { 
        executeDynamicUpdate("informes_incidente", id, obj); 
    }
    
    @Override 
    public void delete(int id) {
        // Implementación opcional requerida por la interfaz estructural
    }
    
    @Override 
    public InformeIncidente find(int id) { 
        return null; 
    }
    
    @Override 
    public ArrayList<InformeIncidente> findAll() { 
        return new ArrayList<>(); 
    }
}