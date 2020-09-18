package ar.com.ada.api.pootflix.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "directores")
public class Director extends Persona {
    public ObjectId _id;
    public Integer estilo;

    public enum TipoEstiloEnum {
        INDEPENDIENTE(1), EJECUTIVO(2), EXTERNO(3);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private TipoEstiloEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoEstiloEnum parse(Integer id) {
            TipoEstiloEnum status = null; // Default
            for (TipoEstiloEnum item : TipoEstiloEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
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
     * @return the estilo
     */
    public TipoEstiloEnum getEstilo() {
        return TipoEstiloEnum.parse(this.estilo);
    }

    /**
     * @param estilo the estilo to set
     */
    public void setEstilo(TipoEstiloEnum estilo) {
        this.estilo = estilo.getValue();
    }
}
