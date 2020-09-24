package ar.com.ada.api.pootflix.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.pootflix.entities.Director;
import ar.com.ada.api.pootflix.model.request.DirectorRequest;
import ar.com.ada.api.pootflix.model.response.GenericResponse;
import ar.com.ada.api.pootflix.services.DirectorService;

@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @PostMapping("/api/directores")
    public ResponseEntity<?> registrarDirector(@RequestBody DirectorRequest directorR) {
        GenericResponse r = new GenericResponse();
        Director director = directorService.registrarDirector(directorR.nombre, directorR.apellido, directorR.edad,
                directorR.paisOrigen, directorR.estilo);
        if (director == null) {
            r.isOk = false;
            r.message = "No se puedo registrar la directora";
            return ResponseEntity.badRequest().body(r);
        } else {
            r.isOk = true;
            r.id = director.get_id().toHexString();
            r.message = "Registraste con exito la director " + director.getNombre() + " " + director.getApellido()
                    + " nivel " + director.getEstilo().toString();

            return ResponseEntity.ok(r);
        }
    }

    @GetMapping("/api/directores")
    public ResponseEntity<?> listarActores() {
        return ResponseEntity.ok(directorService.listarDirectors());
    }

    @GetMapping("/api/directores/{id}")
    public ResponseEntity<?> obtenerActor(@PathVariable ObjectId id) {
        GenericResponse r = new GenericResponse();
        Director director = directorService.obtenerDirectorId(id);
        if (director == null) {
            r.isOk = false;
            r.message = "Director no encontrada";
            return ResponseEntity.badRequest().body(r);
        } else {

            return ResponseEntity.ok(director);
        }

    }

}
