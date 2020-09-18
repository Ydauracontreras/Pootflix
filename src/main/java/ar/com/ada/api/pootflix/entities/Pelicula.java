package ar.com.ada.api.pootflix.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "peliculas")
public class Pelicula extends Contenido {
    private ObjectId _id;
    private boolean filmadaenImax;
    private Integer anio;
    private double duracion;

    /**
     * @return the _id
     */
    public ObjectId get_id() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    /**
     * @return the filmadaenImax
     */
    public boolean isFilmadaenImax() {
        return filmadaenImax;
    }

    /**
     * @param filmadaenImax the filmadaenImax to set
     */
    public void setFilmadaenImax(boolean filmadaenImax) {
        this.filmadaenImax = filmadaenImax;
    }

    /**
     * @return the anio
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * @return the duracion
     */
    public double getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

}
