package ar.com.ada.api.pootflix.entities;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "series")
public class Serie extends Contenido {
    private ObjectId _id;
    private List<Temporada> temporadas = new ArrayList<>();

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
     * @return the temporadas
     */
    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    /**
     * @param temporadas the temporadas to set
     */
    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

}
