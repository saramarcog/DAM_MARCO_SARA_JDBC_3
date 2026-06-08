package examen.marco.sara.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import examen.marco.sara.beans.Soc;
import examen.marco.sara.motores.MotorSQL;

public class SocDAOImpl extends AbstractDAO<Soc> {
    
    public SocDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    // CONSULTAS SQL
    private static final String SQL_INSERT = 
        "INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen) VALUES (?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = 
        "UPDATE socs SET nombre = ?, pais = ?, nivel_seguridad = ? WHERE id = ?";
        
    private static final String SQL_DELETE = 
        "DELETE FROM socs WHERE id = ?";
        
    private static final String SQL_FIND_BY_ID = 
        "SELECT * FROM socs WHERE id = ?";
        
    private static final String SQL_FIND_ALL = 
        "SELECT * FROM socs ORDER BY id";

    @Override
    public void add(Soc s) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            
            motorSQL.getPs().setString(1, s.getNombre());
            motorSQL.getPs().setString(2, s.getPais());
            motorSQL.getPs().setInt(3, s.getNivelSeguridad()); // Corregido el nombre del método
            motorSQL.getPs().setString(4, s.getAutorExamen());

            int rows = motorSQL.executeUpdate();
            System.out.println("✨ [SUCCESS] Registro insertado en SOCs. Filas afectadas: " + rows);
        } catch (Exception e) {
            System.out.println("❌ Error en ADD SOC: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, Soc s) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, s.getNombre());
            motorSQL.getPs().setString(2, s.getPais());
            motorSQL.getPs().setInt(3, s.getNivelSeguridad());
            motorSQL.getPs().setInt(4, id);

            motorSQL.executeUpdate();
            System.out.println("✨ [SUCCESS] Registro con ID " + id + " actualizado en SOCs.");
        } catch (Exception e) {
            System.out.println("❌ Error en UPDATE SOC: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void delete(int id) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_DELETE);
            motorSQL.getPs().setInt(1, id);

            int rows = motorSQL.executeUpdate();
            System.out.println("✨ [SUCCESS] Registro con ID " + id + " eliminado. Filas afectadas: " + rows);
        } catch (Exception e) {
            System.out.println("❌ Error en DELETE SOC: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public Soc find(int id) {
        Soc s = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_ID);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();

            if (rs.next()) {
                s = new Soc();
                s.setId(rs.getInt("id"));
                s.setNombre(rs.getString("nombre"));
                s.setPais(rs.getString("pais"));
                s.setNivelSeguridad(rs.getInt("nivel_seguridad")); // Corregido el typo "Niuvel"
                s.setAutorExamen(rs.getString("autor_examen"));
            }
        } catch (Exception e) {
            System.out.println("❌ Error en FIND SOC: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
        return s;
    }

    @Override
    public ArrayList<Soc> findAll() {
        ArrayList<Soc> lista = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                Soc s = new Soc();
                s.setId(rs.getInt("id"));
                s.setNombre(rs.getString("nombre"));
                s.setPais(rs.getString("pais"));
                s.setNivelSeguridad(rs.getInt("nivel_seguridad"));
                s.setAutorExamen(rs.getString("autor_examen"));
                
                lista.add(s);
            }
        } catch (Exception e) {
            System.out.println("Error en FIND ALL SOCs: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
        return lista;
    }
}