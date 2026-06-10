package examen.marco.sara.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import examen.marco.sara.beans.InformeIncidente;
import examen.marco.sara.motores.MotorSQL;

public class InformeIncidenteDAOImpl extends AbstractDAO<InformeIncidente> {
    
    public InformeIncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    private static final String SQL_INSERT = 
        "INSERT INTO informes_incidente (malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES (?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = 
        "UPDATE informes_incidente SET malware_detectado = ?, nivel_severidad = ?, conclusion = ? WHERE id = ?";
        
    private static final String SQL_DELETE = 
        "DELETE FROM informes_incidente WHERE id = ?";
        
    private static final String SQL_FIND_BY_ID = 
        "SELECT * FROM informes_incidente WHERE id = ?";
        
    private static final String SQL_FIND_ALL = 
        "SELECT * FROM informes_incidente ORDER BY id";

    @Override
    public void add(InformeIncidente inf) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            
            motorSQL.getPs().setBoolean(1, inf.isMalwareDetectado());
            motorSQL.getPs().setInt(2, inf.getNivelSeveridad());
            motorSQL.getPs().setString(3, inf.getConclusion());
            motorSQL.getPs().setInt(4, 1);
            motorSQL.getPs().setString(5, inf.getAutorExamen());

            int rows = motorSQL.executeUpdate();
            System.out.println("Registro insertado en Informes. Filas afectadas: " + rows);
        } catch (Exception e) {
            System.out.println("Error en ADD INFORME: " + e.getMessage());
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
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_DELETE);
            motorSQL.getPs().setInt(1, id);

            int rows = motorSQL.executeUpdate();
            System.out.println("registro con ID " + id + " eliminado. Filas afectadas: " + rows);
        } catch (Exception e) {
            System.out.println("Error en DELETE INFORME: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public InformeIncidente find(int id) {
        InformeIncidente inf = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_ID);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();

            if (rs.next()) {
                inf = new InformeIncidente();
                inf.setId(rs.getInt("id"));
                inf.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                inf.setNivelSeveridad(rs.getInt("nivel_severidad"));
                inf.setConclusion(rs.getString("conclusion"));
                inf.setAutorExamen(rs.getString("autor_examen"));
            }
        } catch (Exception e) {
            System.out.println("Error en FIND INFORME: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
        return inf;
    }

    @Override
    public ArrayList<InformeIncidente> findAll() {
        ArrayList<InformeIncidente> lista = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                InformeIncidente inf = new InformeIncidente();
                inf.setId(rs.getInt("id"));
                inf.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                inf.setNivelSeveridad(rs.getInt("nivel_severidad"));
                inf.setConclusion(rs.getString("conclusion"));
                inf.setAutorExamen(rs.getString("autor_examen"));
                
                lista.add(inf);
            }
        } catch (Exception e) {
            System.out.println("Error en FIND ALL Informes: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
        return lista;
    }
}