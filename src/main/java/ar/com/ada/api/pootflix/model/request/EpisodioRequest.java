package ar.com.ada.api.pootflix.model.request;

import org.bson.types.ObjectId;

public class EpisodioRequest {

    public ObjectId idSerie;
    public double duracion;
    public String nombre;
    public Integer numero;
    public Integer numeroTemporada;
}
