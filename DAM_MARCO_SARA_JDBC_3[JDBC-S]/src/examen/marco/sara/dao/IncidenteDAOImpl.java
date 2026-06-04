package examen.marco.sara.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import examen.marco.sara.beans.CentroForense;
import examen.marco.sara.beans.MuestraForense;
import examen.marco.sara.beans.InformeForense;
import examen.marco.sara.motores.MotorSQL;

public class IncidenteDAOImpl extends AbstractDAO<Incidente>{

    public IncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }
    private static final String SQL_INSERT = "INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE incidentes SET codigo_incidente = ?, tipo_incidente = ?, fecha_deteccion = ?, estado = ?, fk_soc_id = ? WHERE id = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM incidentes WHERE id = ?";
    private static final String SQL_DELETE =
            "DELETE FROM incidentes WHERE id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT * FROM incidentes ORDER BY id";

    @Override
    public void add(Incidente i) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, i.getCodigoIncidente());
            motorSQL.getPs().setString(2, i.getTipoIncidente());
            motorSQL.getPs().setString(3,i.getFechaDeteccion());
            motorSQL.getPs().setString(4, i.getEstado());
            motorSQL.getPs().setInt(5, i.getSoc().getId());
            motorSQL.getPs().setString(6,i.getAutorExamen() );

            motorSQL.executeUpdate();
            System.out.println("Incidente guardado de forma real en AWS.");
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, Incidente i ) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, i.getCodigoIncidente());
            motorSQL.getPs().setString(2, i.getTipoIncidente());
            motorSQL.getPs().setString(3,i.getFechaDeteccion());
            motorSQL.getPs().setString(4, i.getEstado());
            motorSQL.getPs().setInt(5, i.getSoc().getId());
            motorSQL.getPs().setInt(6, id);

            motorSQL.executeUpdate();
            System.out.println("Registro con ID " + id + " actualizado de verdad.");
        } catch(Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void delete(int id) {
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_DELETE);
            motorSQL.getPs().setInt(1, id);

            int rows = motorSQL.executeUpdate();
            System.out.println("Registro con id: "+ id+ "eliminado!");
        }catch(Exception e){

        }finally{
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
            printError(e);
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
                Soc s = new Soc();
                s.setId(rs.getInt("id"));
                s.setCodigoCaso(rs.getString("codigo_incidente"));
                s.setTipoMuestra(rs.getString("tipo_incidente"));
                s.setFechaRecogida(rs.getString("fecha_deteccion"));
                s.setEstadoCustodia(rs.getString("estado"));

                lista.add(s);
            }
        } catch (Exception e) {
        } finally {
            motorSQL.close();
        }
        return lista;
    }

    public ArrayList<MuestraForense> findIncidentesCriticos() {
        ArrayList<Incidentes> incidentesCriticos = new ArrayList<>();
       
        String sql = "SELECT I1.id AS inci_id, I1.codigo_incidente, I1.tipo_incidente,I1.fecha_deteccion, I1.estado, I1.autor_examen, " +
                "       s.id AS soc_id, s.nombre AS soc_nombre, S.pais, " +
                "       i2.id AS inf_id, I2.malware_detectado, I2.nivel_severidad, I2.conclusion " +
                "FROM Incidentes I1 " +
                "INNER JOIN socs S ON I1.fk_soc_id = S.id " +
                "INNER JOIN informes_incidente I2 ON I1.id = I2.FK_incidente_id " +
                "WHERE  I2.malware_detectado = true AND I2.nivel_severidad > 90 AND S.pais = 'ESPAÑA'";
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
                inf.setNivelRiesgo(rs.getInt("nivel_severidad"));
                inf.setConclusion(rs.getString("conclusion"));

                Incidente inci = new Incidente();
                inci.setId(rs.getInt("inci_id"));
                inci.setCodigoIncidente(rs.getString("codigo_incidente"));
                inci.setTipoIncidente(rs.getString("tipo_incidente"));
                inci.setFechaDeteccion(rs.getString("fecha_deteccion"));
                inci.setEstado(rs.getString("estado"));

                inci.setSoc(s);
                inci.setInforme(inf);

                incidentesCriticos.add(inci);
            }
        } catch (Exception e) {
            System.out.println("Fallo en query avanzada: " + e.getMessage());
        } finally {
            motorSQL.close();
        }
        return incidentesCriticos;
    }
}

