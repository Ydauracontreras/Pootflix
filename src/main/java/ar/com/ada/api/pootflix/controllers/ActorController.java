package ar.com.ada.api.pootflix.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.pootflix.entities.Actor;
import ar.com.ada.api.pootflix.model.request.ActorRequest;
import ar.com.ada.api.pootflix.model.response.GenericResponse;
import ar.com.ada.api.pootflix.services.ActorService;
import ar.com.ada.api.pootflix.services.SerieService;

@RestController
public class ActorController {
    @Autowired
    ActorService actorService;

    @Autowired
    SerieService serieService;

    @PostMapping("api/actores")
    public ResponseEntity<?> crearActor(@RequestBody ActorRequest actorR) {
        GenericResponse r = new GenericResponse();
        Actor actorNuevo = actorService.crearActor(actorR.nombre, actorR.apellido, actorR.edad, actorR.paisOrigen,
                actorR.nivel);
        if (actorNuevo == null) {
            r.isOk = false;
            r.message = "No se pudo crear un nuevo actor";
            return ResponseEntity.badRequest().body(r);
        } else {
            r.isOk = true;
            r.id = actorNuevo.get_id().toHexString();
            r.message = "Registraste con exito a la actriz " + actorNuevo.getNombre() + " " + actorNuevo.getApellido()
                    + " nivel " + actorNuevo.getNivel().toString();

            return ResponseEntity.ok(r);
        }
    }

    @GetMapping("api/actores")
    public ResponseEntity<?> listarActores() {
        return ResponseEntity.ok(actorService.listarActores());
    }

    @GetMapping("api/actores/{id}")
    public ResponseEntity<?> obtenerActor(@PathVariable ObjectId id) {
        GenericResponse r = new GenericResponse();
        Actor actor = actorService.obtenerActorId(id);
        if (actor == null) {
            r.isOk = false;
            r.message = "Actor no encontrado";
            return ResponseEntity.badRequest().body(r);
        } else {

            return ResponseEntity.ok(actor);
        }

    }

    @GetMapping("/actores/{id}/series")
    ResponseEntity<?> seriesActor(@PathVariable ObjectId id) {
        return ResponseEntity.ok(serieService.obtenerSeriesByActor(id));
    }

}