package ar.com.ada.api.pootflix.entities;

import ar.com.ada.api.pootflix.entities.Pais.PaisEnum;

public class Persona {
    private String nombre;
    private String apellido;
    private Integer edad;
    private Integer paisOrigen;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public PaisEnum getPaisOrigen() {
        return PaisEnum.parse(this.paisOrigen);
    }

    /**
     * @param paisOrigen the paisOrigen to set
     */
    public void setPaisOrigen(Integer paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public void setPaisOrigen(PaisEnum paisOrigen) {
        this.paisOrigen = paisOrigen.getValue();
    }

}
