package ar.com.ada.api.pootflix.entities;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

public class Temporada {
    public ObjectId _id;
    private List<Episodio> episodios = new ArrayList<Episodio>();
    private Integer numero;

    /**
     * @return the episodios
     */
    public List<Episodio> getEpisodios() {
        return episodios;
    }

    /**
     * @param episodios the episodios to set
     */
    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;

    }

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
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

}
