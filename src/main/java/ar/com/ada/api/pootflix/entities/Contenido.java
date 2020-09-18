package ar.com.ada.api.pootflix.entities;

import java.util.*;

import ar.com.ada.api.pootflix.entities.Genero.TipoGeneroEnum;
import ar.com.ada.api.pootflix.entities.Pais.PaisEnum;

public class Contenido {
    private String titulo;
    private double tiempoVisto;
    private List<String> premios = new ArrayList<String>();
    private List<TipoGeneroEnum> generos = new ArrayList<TipoGeneroEnum>();
    private List<Actor> actores = new ArrayList<Actor>();
    private Integer paisOrigen;
    private Integer calificacion;
    private Director director;

    /**
     * @return the titulo
     * 
     * 
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the tiempoVisto
     */
    public double getTiempoVisto() {
        return tiempoVisto;
    }

    /**
     * @param tiempoVisto the tiempoVisto to set
     */
    public void setTiempoVisto(double tiempoVisto) {
        this.tiempoVisto = tiempoVisto;
    }

    /**
     * @return the premios
     */
    public List<String> getPremios() {
        return premios;
    }

    /**
     * @param premios the premios to set
     */
    public void setPremios(List<String> premios) {
        this.premios = premios;
    }

    /**
     * @return the actores
     */
    public List<Actor> getActores() {
        return actores;
    }

    /**
     * @param actores the actores to set
     */
    public void setActores(List<Actor> actores) {
        this.actores = actores;

    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the generos
     */
    public List<TipoGeneroEnum> getGeneros() {
        return generos;
    }

    /**
     * @param generos the generos to set
     */
    public void setGeneros(List<TipoGeneroEnum> generos) {
        this.generos = generos;
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

    /**
     * @return the director
     */
    public Director getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(Director director) {
        this.director = director;
    }

}
