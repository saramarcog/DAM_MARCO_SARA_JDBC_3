package examen.marco.sara.dao;

import examen.marco.sara.beans.InformeForense;
import examen.marco.sara.motores.MotorSQL;
import java.util.ArrayList;

public class InformeIncidenteDAOImpl extends AbstractDAO<InformeIncidente> {

    public InformeIncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(InformeIncidente inf) {
        String sql = "INSERT INTO informes_incidente(malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES (?, ?, ?, ?, ?)";
        try {
            motorSQL.connect();
            motorSQL.prepare(sql);
            motorSQL.getPs().setBoolean(1, inf.isMalwareDetectado());
            motorSQL.getPs().setInt(2, inf.getNivelSeveridad());
            motorSQL.getPs().setString(3, inf.getConclusion());
            motorSQL.getPs().setInt(4, 1);
            motorSQL.getPs().setString(5, inf.getAutorExamen());

            motorSQL.executeUpdate();
            System.out.println("Ficha de Informe Forense guardada.");
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override public void update(int id, InformeForense obj) { executeDynamicUpdate("informes_forenses", id, obj); }
    @Override public void delete(int id) {}
    @Override public InformeForense find(int id) { return null; }
    @Override public ArrayList<InformeForense> findAll() { return new ArrayList<>(); }
}