package examen.marco.sara.dao;

import examen.marco.sara.beans.Incidente;
import java.util.ArrayList;

public interface IIncidenteDAO extends DAO<Incidente> {
    ArrayList<Incidente> findIncidentesBySoc(int socId);
    ArrayList<Incidente> findIncidentesCriticos();
    Incidente findIncidenteWithInforme(int incidenteId);
}
