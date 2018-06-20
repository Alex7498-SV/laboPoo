
package modelo;

public class Filtro {
    private String nombre;
    private String director;
    private String pais;
    private String clasificacion;
    private int annio;
    private boolean en_proyeccion;

    public Filtro() {
    }

    public Filtro(String nombre, String director, String pais, String clasificacion, int annio, boolean en_proyeccion) {
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.annio = annio;
        this.en_proyeccion = en_proyeccion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getAnnio() {
        return annio;
    }

    public void setAnnio(int annio) {
        this.annio = annio;
    }

    public boolean isEn_proyeccion() {
        return en_proyeccion;
    }

    public void setEn_proyeccion(boolean en_proyeccion) {
        this.en_proyeccion = en_proyeccion;
    }
    
    
}
