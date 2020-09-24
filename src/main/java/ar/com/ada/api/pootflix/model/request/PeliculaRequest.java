package ar.com.ada.api.pootflix.model.request;

import org.bson.types.ObjectId;

public class PeliculaRequest {
    public String titulo;
    public String premio;
    public Integer genero;
    public ObjectId idActor;
    public Double calificacion;
    public boolean filmadaenImax;
    public Integer paisOrigen;
    public Integer anio;
    public double duracion;
    public ObjectId idDirector;

}
