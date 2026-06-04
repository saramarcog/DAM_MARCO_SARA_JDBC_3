public class Main {
    public static void main(String[] args) {
        System.out.println("INICIANDO PRUEBAS - SARA MARCO");
        
        //Creamos motor sql 
        MotorSQL motorPostgres = MotorFactory.create(MotorFactory.POSTGRE);
        SocDAOImpl socDAO = new SocDAOImpl(motorPostgres);
        IncidenteDAOImpl incidenteDAO = new IncidenteDAOImpl(motorPostgres);
        InformeIncidenteDAOImpl informeDAO = new InformeIncidenteDAOImpl(motorPostgres);

        Soc socnuevo = new Soc();
        socnuevo.setNombre("Soc SV España");

        Incidente inc1 = new Incidente();
        inc1.setCodigoIncidente("INCIDENTE-SARA-2026");
        inc1.setTipoIncidente("Hackeo a san valero");
        inc1.setFechaDeteccion("04/06/2026");
        inc1.setEstadoCustodia("Bajo Custodia Activa");

        inc1.setCentro(socnuevo);

        incidenteDAO.add(inc1);
        System.out.println("PRUEBA UPDATE");
        inc1.setCodigoIncidente("SARA-INCI-MODIFICADO");
        inc1.setTipoIncidente("Hackeo a seas");
        incidenteDAO.update(1, inc1);

        InformeIncidente nuevoInforme = new InformeIncidente();
        nuevoInforme.setAdnPositivo(true);
        nuevoInforme.setNivelRiesgo(95);
        nuevoInforme.setConclusion("Mapeo Exitoso (?)");
        nuevoInforme.setNivelRiesgo(95);

        informeDAO.add(nuevoInforme);


        ArrayList<Incidente> incidentescriticos = incidenteDAO.findIncidentesCriticos();

        if (incidentescriticos.isEmpty()) {
            System.out.println("No hay incidentes criticos yujuu");
        } else {
            for (Incidentes in : incidentescriticos) {
                System.out.println("Incidente codigo: " + in.getCodigoIncidente() +
                                   " | Tipo: " + in.getTipoIncidente() +
                                   " | Soc: " + in.getSoc().getNombre() +
                                   " | Riesgo: " + in.getInforme().getNivelSeveridad() + "%");
            }
        }

    }
}
