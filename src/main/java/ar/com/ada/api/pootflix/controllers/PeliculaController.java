package ar.com.ada.api.pootflix.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.pootflix.entities.Pelicula;
import ar.com.ada.api.pootflix.model.request.PeliculaRequest;
import ar.com.ada.api.pootflix.model.response.GenericResponse;
import ar.com.ada.api.pootflix.services.PeliculaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PeliculaController {
    @Autowired
    PeliculaService peliculaService;

    @PostMapping("/api/peliculas")
    public ResponseEntity<?> registarPelicula(@RequestBody PeliculaRequest pRequest) {
        GenericResponse r = new GenericResponse();
        Pelicula pelicula = peliculaService.registarPelicula(pRequest.titulo, pRequest.premio, pRequest.genero,
                pRequest.idActor, pRequest.calificacion, pRequest.paisOrigen, pRequest.anio, pRequest.idDirector,
                pRequest.duracion, pRequest.filmadaenImax);

        if (pelicula == null) {
            r.isOk = false;
            r.message = "No se puedo crear la pelicula";
            return ResponseEntity.badRequest().body(r);
        } else {
            r.isOk = true;
            r.id = pelicula.get_id().toHexString();
            r.message = "Registraste con exito la pelicula " + pelicula.getTitulo();
            return ResponseEntity.ok(r);
        }
    }

    @GetMapping(value = "/api/peliculas")
    public ResponseEntity<?> obtenerPeliculas() {
        return ResponseEntity.ok(peliculaService.listarPeliculas());
    }

    @GetMapping(value = "/api/peliculas/{id}")
    public ResponseEntity<?> obtenerPeliculaPorId(@PathVariable ObjectId id) {
        GenericResponse r = new GenericResponse();
        Pelicula pelicula = peliculaService.obtenerPeliculaId(id);
        if (pelicula == null) {
            r.isOk = false;
            r.message = "Pelicula no encontrada";
            return ResponseEntity.badRequest().body(r);
        } else {

            return ResponseEntity.ok(pelicula);
        }

    }

    // Buscar Pelicula por Titulo y por Genero

    @GetMapping("/api/peliculas/titulos/{titulo}")
    public ResponseEntity<?> obtenerPeliculaporTitulo(@PathVariable String titulo) {
        GenericResponse r = new GenericResponse();
        Pelicula pelicula = peliculaService.obtenerPeliculaPorTitulo(titulo);
        if (pelicula == null) {
            r.isOk = false;
            r.message = "Pelicula no encontrada";
            return ResponseEntity.badRequest().body(r);
        } else {

            return ResponseEntity.ok(pelicula);
        }

    }

    @GetMapping("/api/peliculas/generos/{genero}")
    public ResponseEntity<?> obtenerPeliculaporGenero(@PathVariable Integer genero) {
        return ResponseEntity.ok(peliculaService.obtenerPeliculasPorGenero(genero));

    }
}
