package examen.marco.sara;

import examen.marco.sara.beans.*;
import examen.marco.sara.dao.*;
import examen.marco.sara.motores.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("INICIANDO PRUEBAS - SARA MARCO");
        
        MotorSQL motorPostgres = MotorFactory.create(MotorFactory.POSTGRE);
        SocDAOImpl socDAO = new SocDAOImpl(motorPostgres);
        IncidenteDAOImpl incidenteDAO = new IncidenteDAOImpl(motorPostgres);
        InformeIncidenteDAOImpl informeDAO = new InformeIncidenteDAOImpl(motorPostgres);

        Soc socnuevo = new Soc();
        socnuevo.setNombre("Soc SV España");
        socnuevo.setPais("ESPAÑA");
        socnuevo.setNivelSeguridad(95);
        socnuevo.setAutorExamen("SARA MARCO");
        socDAO.add(socnuevo);

        ArrayList<Soc> todosSocs = socDAO.findAll();
        if (!todosSocs.isEmpty()) {
            socnuevo = todosSocs.get(todosSocs.size() - 1);
        }

        Incidente inc1 = new Incidente();
        inc1.setCodigoIncidente("INCIDENTE-SARA-2026");
        inc1.setTipoIncidente("Hackeo a san valero");
        inc1.setFechaDeteccion("04/06/2026");
        inc1.setEstado("Bajo Custodia Activa");
        inc1.setAutorExamen("SARA MARCO");
        inc1.setSoc(socnuevo);

        incidenteDAO.add(inc1);
        System.out.println("PRUEBA UPDATE");
        inc1.setCodigoIncidente("SARA-INCI-MODIFICADO");
        inc1.setTipoIncidente("Hackeo a seas");
        inc1.setAutorExamen("SARA MARCO");
        incidenteDAO.update(1, inc1);

        InformeIncidente nuevoInforme = new InformeIncidente();
        nuevoInforme.setMalwareDetectado(true);
        nuevoInforme.setNivelSeveridad(95);
        nuevoInforme.setConclusion("Mapeo Exitoso (?)");
        nuevoInforme.setAutorExamen("SARA MARCO");

        informeDAO.add(nuevoInforme);


        ArrayList<Incidente> incidentescriticos = incidenteDAO.findIncidentesCriticos();

        if (incidentescriticos.isEmpty()) {
            System.out.println("No hay incidentes criticos yujuu");
        } else {
            for (Incidente in : incidentescriticos) {
                System.out.println("Incidente codigo: " + in.getCodigoIncidente() +
                                   "Tipo: " + in.getTipoIncidente() +
                                   "Soc: " + in.getSoc().getNombre() +
                                   "Riesgo: " + in.getInforme().getNivelSeveridad() + "%");
            }
        }

    }
}
