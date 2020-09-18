package ar.com.ada.api.pootflix.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import ar.com.ada.api.pootflix.entities.Nivel.TipoNivelEnum;

@Document(collection = "actores")
public class Actor extends Persona {
    private ObjectId _id;
    private Integer nivel;

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
     * @param nivel the nivel to set
     */
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the estilo
     */
    public TipoNivelEnum getNivel() {
        return TipoNivelEnum.parse(this.nivel);
    }

    /**
     * @param estilo the estilo to set
     */
    public void setNivel(TipoNivelEnum nivel) {
        this.nivel = nivel.getValue();
    }

}
