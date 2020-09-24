package ar.com.ada.api.pootflix.model.request;

import org.bson.types.ObjectId;

public class SerieRequest {
    public String titulo;
    public String premio;
    public Integer genero;
    public ObjectId idActor;
    public double calificacion;
    public Integer paisOrigen;
    public Integer anio;
    public ObjectId idDirector;

}
