package examen.marco.sara.beans;

public class Incidente {
    private int id;
    private String codigoIncidente;
    private String tipoIncidente;
    private String fechaDeteccion;
    private String estado;
    private Soc soc;
    private InformeIncidente informe;
    private String autorExamen;

    public Incidente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoIncidente() {
        return codigoIncidente;
    }

    public void setCodigoIncidente(String codigoIncidente) {
        this.codigoIncidente = codigoIncidente;
    }

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public String getFechaDeteccion() {
        return fechaDeteccion;
    }

    public void setFechaDeteccion(String fechaDeteccion) {
        this.fechaDeteccion = fechaDeteccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Soc getSoc() {
        return soc;
    }

    public void setSoc(Soc soc) {
        this.soc = soc;
    }

    public InformeIncidente getInforme() {
        return informe;
    }

    public void setInforme(InformeIncidente informe) {
        this.informe = informe;
    }

    public String getAutorExamen() {
        return autorExamen;
    }

    public void setAutorExamen(String autorExamen) {
        this.autorExamen = autorExamen;
    }

    @Override
    public String toString() {
        return "Incidente{" +
                "id=" + id +
                ", codigoIncidente='" + codigoIncidente + '\'' +
                ", tipoIncidente='" + tipoIncidente + '\'' +
                ", fechaDeteccion='" + fechaDeteccion + '\'' +
                ", estado='" + estado + '\'' +
                ", soc=" + soc +
                ", informe=" + informe +
                ", autorExamen='" + autorExamen + '\'' +
                '}';
    }
}
