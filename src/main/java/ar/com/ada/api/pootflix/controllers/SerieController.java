package ar.com.ada.api.pootflix.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.pootflix.entities.Episodio;
import ar.com.ada.api.pootflix.entities.Serie;
import ar.com.ada.api.pootflix.model.request.EpisodioRequest;
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
            r.message = "No se pudo registrar la serie";
            return ResponseEntity.badRequest().body(r);
        } else {
            r.isOk = true;
            r.id = serie.get_id().toHexString();
            r.message = "Registraste con exito la serie " + serie.getTitulo();
            return ResponseEntity.ok(r);
        }
    }

    @PostMapping("api/series/episodios")
    public ResponseEntity<?> registrarEpisodio(@RequestBody EpisodioRequest episodioRequest) {
        GenericResponse r = new GenericResponse();
        Episodio episodio = serieService.registarEpisodio(episodioRequest.idSerie, episodioRequest.duracion,
                episodioRequest.nombre, episodioRequest.numero, episodioRequest.numeroTemporada);
        if (episodio == null) {
            r.isOk = false;
            r.message = "No se pudo registar el episodio";
            return ResponseEntity.badRequest().body(r);
        } else {
            r.isOk = true;
            r.id = episodio.get_id().toHexString();
            r.message = "Registraste con exito el episodio " + episodio.getNombre() + " a la serie "
                    + serieService.obtenerSerieId(episodioRequest.idSerie).getTitulo();
            return ResponseEntity.ok(r);
        }
    }

    @GetMapping(value = "/api/series")
    public ResponseEntity<?> obtenerPeliculas() {
        return ResponseEntity.ok(serieService.listarSeries());
    }

    @GetMapping("api/series/{id}")
    public ResponseEntity<?> traerSeriePorId(@PathVariable ObjectId id) {

        return ResponseEntity.ok(serieService.obtenerSerieId(id));
    }

    @GetMapping("api/series/titulo/{titulo}")
    public ResponseEntity<?> traerSeriePorTitulo(@PathVariable String titulo) {

        return ResponseEntity.ok(serieService.buscarPorTitulo(titulo));
    }

    // /series?genero=Ciencia%20Ficción
    @GetMapping("api/series/genero/{genero}")
    public ResponseEntity<?> traerSeriesPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(serieService.buscarPorGenero(genero));
    }

    @GetMapping("api/series/temporadas/{id}")
    public ResponseEntity<?> traerTemporadasPorSerieId(@PathVariable ObjectId id) {

        return ResponseEntity.ok(serieService.traerTemporadasPorSerieId(id));
    }

    //
    @GetMapping("api/series/{id}/episodios")
    public ResponseEntity<?> traerEpisodios(@PathVariable ObjectId id) {
        return ResponseEntity.ok(serieService.obtenerEpisodiosSerie(id));
    }

    // @GetMapping("/series/{id}/temporadas/{nroTemporada}/episodios/{nroEpisodio}")
    @GetMapping("api/series/{id}/episodios/{nroTemporada}-{nroEpisodio}")
    public ResponseEntity<?> traerEpisodio(@PathVariable ObjectId id, @PathVariable int nroTemporada,
            @PathVariable int nroEpisodio) {

        Episodio episodio = serieService.obtenerEpisodioPorNroEpisodio(id, nroTemporada, nroEpisodio);
        if (episodio == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(episodio);

    }

}
