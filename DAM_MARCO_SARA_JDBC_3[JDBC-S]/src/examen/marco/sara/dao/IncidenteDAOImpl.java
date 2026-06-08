package examen.marco.sara.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import examen.marco.sara.beans.Incidente;
import examen.marco.sara.beans.Soc;
import examen.marco.sara.beans.InformeIncidente;
import examen.marco.sara.motores.MotorSQL;

public class IncidenteDAOImpl extends AbstractDAO<Incidente> {

    public IncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    private static final String SQL_INSERT = "INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE incidentes SET codigo_incidente = ?, tipo_incidente = ?, fecha_deteccion = ?, estado = ?, fk_soc_id = ? WHERE id = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM incidentes WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM incidentes WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM incidentes ORDER BY id";

    @Override
    public void add(Incidente i) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, i.getCodigoIncidente());
            motorSQL.getPs().setString(2, i.getTipoIncidente());
            motorSQL.getPs().setString(3, i.getFechaDeteccion());
            motorSQL.getPs().setString(4, i.getEstado());
            motorSQL.getPs().setInt(5, i.getSoc().getId());
            motorSQL.getPs().setString(6, i.getAutorExamen());

            motorSQL.executeUpdate();
            System.out.println("Incidente guardado!!!");
        } catch (Exception e) {
            System.out.println("Error en ADD: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, Incidente i) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, i.getCodigoIncidente());
            motorSQL.getPs().setString(2, i.getTipoIncidente());
            motorSQL.getPs().setString(3, i.getFechaDeteccion());
            motorSQL.getPs().setString(4, i.getEstado());
            
            if (i.getSoc() != null) {
                motorSQL.getPs().setInt(5, i.getSoc().getId());
            } else {
                motorSQL.getPs().setNull(5, java.sql.Types.INTEGER);
            }
            motorSQL.getPs().setInt(6, id);

            motorSQL.executeUpdate();
            System.out.println("Registro con ID " + id + " actualizado.");
        } catch (Exception e) {
            System.out.println("Error en UPDATE: " + e.getMessage());
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
            motorSQL.executeUpdate();
            System.out.println("Registro eliminado.");
        } catch (Exception e) {
            System.out.println("error en DELETE: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public Incidente find(int id) {
        Incidente incidente = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_ID);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();

            if (rs.next()) {
                incidente = new Incidente();
                incidente.setId(rs.getInt("id"));
                incidente.setCodigoIncidente(rs.getString("codigo_incidente"));
                incidente.setTipoIncidente(rs.getString("tipo_incidente"));
                incidente.setFechaDeteccion(rs.getString("fecha_deteccion"));
                incidente.setEstado(rs.getString("estado"));
                
                Soc s = new Soc();
                s.setId(rs.getInt("fk_soc_id"));
                incidente.setSoc(s);
            }
        } catch (Exception e) {
            System.out.println("error en FIND: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
        return incidente;
    }

    @Override
    public ArrayList<Incidente> findAll() {
        ArrayList<Incidente> lista = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();

            while (rs.next()) {
                Incidente i = new Incidente();
                i.setId(rs.getInt("id"));
                i.setCodigoIncidente(rs.getString("codigo_incidente"));
                i.setTipoIncidente(rs.getString("tipo_incidente"));
                i.setFechaDeteccion(rs.getString("fecha_deteccion"));
                i.setEstado(rs.getString("estado"));

                Soc s = new Soc();
                s.setId(rs.getInt("fk_soc_id"));
                i.setSoc(s);

                lista.add(i);
            }
        } catch (Exception e) {
            System.out.println("Error en FIND ALL: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
        return lista;
    }

    public ArrayList<Incidente> findIncidentesCriticos() {
        ArrayList<Incidente> incidentesCriticos = new ArrayList<>();
       
        String sql = "SELECT I1.id AS inci_id, I1.codigo_incidente, I1.tipo_incidente, I1.fecha_deteccion, I1.estado, I1.autor_examen, " +
                     "       S.id AS soc_id, S.nombre AS soc_nombre, S.pais, " +
                     "       I2.id AS inf_id, I2.malware_detectado, I2.nivel_severidad, I2.conclusion " +
                     "FROM incidentes I1 " +
                     "INNER JOIN socs S ON I1.fk_soc_id = S.id " +
                     "INNER JOIN informes_incidente I2 ON I1.id = I2.fk_incidente_id " +
                     "WHERE I2.malware_detectado = true AND I2.nivel_severidad > 90 AND S.pais = 'ESPAÑA'";
        try {
            motorSQL.connect();
            motorSQL.prepare(sql);
            ResultSet rs = motorSQL.executeQuery();

            while (rs.next()) {
                Soc s = new Soc();
                s.setId(rs.getInt("soc_id"));
                s.setNombre(rs.getString("soc_nombre"));
                s.setPais(rs.getString("pais"));

                InformeIncidente inf = new InformeIncidente();
                inf.setId(rs.getInt("inf_id"));
                inf.setNivelSeveridad(rs.getInt("nivel_severidad"));
                inf.setConclusion(rs.getString("conclusion"));

                Incidente inci = new Incidente();
                inci.setId(rs.getInt("inci_id"));
                inci.setCodigoIncidente(rs.getString("codigo_incidente"));
                inci.setTipoIncidente(rs.getString("tipo_incidente"));
                inci.setFechaDeteccion(rs.getString("fecha_deteccion"));
                inci.setEstado(rs.getString("estado"));
                inci.setAutorExamen(rs.getString("autor_examen"));

                inci.setSoc(s);
                inci.setInforme(inf);

                incidentesCriticos.add(inci);
            }
        } catch (Exception e) {
            System.out.println("Fallo" + e.getMessage());
        } finally {
            motorSQL.close();
        }
        return incidentesCriticos;
    }
}