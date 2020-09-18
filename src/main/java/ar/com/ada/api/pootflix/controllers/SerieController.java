package ar.com.ada.api.pootflix.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.pootflix.entities.Serie;
import ar.com.ada.api.pootflix.model.request.EpisodiosRequest;
import ar.com.ada.api.pootflix.model.request.SerieRequest;
import ar.com.ada.api.pootflix.model.response.GenericResponse;
import ar.com.ada.api.pootflix.services.SerieService;

@RestController
public class SerieController {
    @Autowired
    SerieService serieService;

    @PostMapping("/api/series")
    public ResponseEntity<?> registarSerie(@RequestBody SerieRequest serieR) {
        GenericResponse r = new GenericResponse();
        Serie serie = serieService.registarSerie(serieR.titulo, serieR.premio, serieR.genero, serieR.idActor,
                serieR.calificacion, serieR.paisOrigen, serieR.idDirector);

        if (serie == null) {
            r.isOk = false;
            r.message = "No se puedo crear la serie";
            return ResponseEntity.badRequest().body(r);
        } else {
            r.isOk = true;
            r.id = serie.get_id().toHexString();
            r.message = "Registraste con exito la serie " + serie.getTitulo();
            return ResponseEntity.ok(r);
        }
    }

    @GetMapping(value = "/api/series")
    public ResponseEntity<?> obtenerPeliculas() {
        return ResponseEntity.ok(serieService.listarSeries());
    }

    @PostMapping("/api/series/temporadas/{idSerie}")
    public ResponseEntity<?> registarTemporadas(@PathVariable ObjectId idSerie, @RequestBody EpisodiosRequest episodioR ) {
        Episodio episodio =
        

        return null;

    }

}
