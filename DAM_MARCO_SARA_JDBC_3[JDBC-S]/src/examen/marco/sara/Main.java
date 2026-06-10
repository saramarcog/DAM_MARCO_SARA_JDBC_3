package examen.marco.sara;

import examen.marco.sara.beans.*;
import examen.marco.sara.dao.*;
import examen.marco.sara.motores.*;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("INICIANDO PRUEBAS - SARA MARCO");
        
        MotorSQL motorPostgres = MotorFactory.create(MotorFactory.POSTGRE);
        SocDAOImpl socDAO = new SocDAOImpl(motorPostgres);
        IncidenteDAOImpl incidenteDAO = new IncidenteDAOImpl(motorPostgres);
        InformeIncidenteDAOImpl informeDAO = new InformeIncidenteDAOImpl(motorPostgres);

        Soc socnuevo = new Soc();
        socnuevo.setNombre("Soc 22");
        socnuevo.setPais("ESPAÑA");
        socnuevo.setNivelSeguridad(95);
        socnuevo.setAutorExamen("SARA MARCO");
        socDAO.add(socnuevo);

        ArrayList<Soc> todosSocs = socDAO.findAll();
        if (!todosSocs.isEmpty()) {
            socnuevo = todosSocs.get(todosSocs.size() - 1);
        }


        System.out.println("PRUEBA UNITARIA ADD");
        Incidente inc1 = new Incidente();
        inc1.setCodigoIncidente("S444");
        inc1.setTipoIncidente("Hackeo");
        inc1.setFechaDeteccion("10/06/2026");
        inc1.setEstado("Pendiente");
        inc1.setAutorExamen("SARA MARCO");
        inc1.setSoc(socnuevo);

        incidenteDAO.add(inc1);
        System.out.println("PRUEBA UNITARIA UPDATE ");
        inc1.setCodigoIncidente("MODIFICADO 3");
        inc1.setTipoIncidente("Hackeo a seas jeje ");
        inc1.setAutorExamen("SARA MARCO");
        incidenteDAO.update(9, inc1);
/*
        InformeIncidente nuevoInforme = new InformeIncidente();
        nuevoInforme.setMalwareDetectado(true);
        nuevoInforme.setNivelSeveridad(80);
        nuevoInforme.setConclusion("Mapeo Exitoso (ojala)");
        nuevoInforme.setAutorExamen("SARA MARCO");

        informeDAO.add(nuevoInforme);*/

        System.out.println("PRUEBA UNITARIA FIND");
        Incidente infind = incidenteDAO.find(5);
        System.out.println(infind.toString());

        System.out.println("PRUEBA UNITARIA FIND ALL");
        ArrayList<Incidente> incidentesALL = incidenteDAO.findAll();
        for (Incidente inc : incidentesALL) {
            System.out.println(inc.toString());
        }
        System.out.println("FIN PRUEBA UNITARIA FIND ALL");


        ArrayList<Incidente> incidentescriticos = incidenteDAO.findIncidentesCriticos();
        System.out.println("INCIDENTES CRITICOS");
        if (incidentescriticos.isEmpty()) {
            System.out.println("No hay incidentes criticos yujuu");
        } else {
            for (Incidente in : incidentescriticos) {
                System.out.println("Incidente codigo: " + in.getCodigoIncidente() +
                                   "/ Tipo: " + in.getTipoIncidente() +
                                   "/ Soc: " + in.getSoc().getNombre() +
                                   "/ Riesgo: " + in.getInforme().getNivelSeveridad() + "%");
            }
        }


        System.out.println("TEST UNITARIO: DYNAMIC UPDATE");
        InformeIncidente informeModificado = new InformeIncidente();
        informeModificado.setMalwareDetectado(true);
        informeModificado.setNivelSeveridad(99);
        informeModificado.setConclusion("Intrusión confirmada por análisis dinámico.");
        informeModificado.setAutorExamen("SARA MARCO");
        informeDAO.update(7, informeModificado);

        System.out.println("FIN DE PRUEBAS");
    }
}
