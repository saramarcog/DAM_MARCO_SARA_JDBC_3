package examen.marco.sara.beans;

public class InformeIncidente {
    private int id;
   private boolean malwareDetectado;
   private int nivelSeveridad;
   private String conclusion;
   private String autorExamen;

    public InformeIncidente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMalwareDetectado() {
        return malwareDetectado;
    }

    public void setMalwareDetectado(boolean malwareDetectado) {
        this.malwareDetectado = malwareDetectado;
    }

    public int getNivelSeveridad() {
        return nivelSeveridad;
    }

    public void setNivelSeveridad(int nivelSeveridad) {
        this.nivelSeveridad = nivelSeveridad;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    public String getAutorExamen() { return autorExamen; }
    public void setAutorExamen(String autorExamen) { this.autorExamen = autorExamen;}
}
