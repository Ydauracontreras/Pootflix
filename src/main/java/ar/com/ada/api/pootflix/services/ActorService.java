package ar.com.ada.api.pootflix.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.pootflix.entities.Actor;
import ar.com.ada.api.pootflix.entities.Nivel.TipoNivelEnum;
import ar.com.ada.api.pootflix.entities.Pais.PaisEnum;
import ar.com.ada.api.pootflix.repositories.ActorRepository;

@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    public boolean grabar(Actor actor) {
        actorRepository.save(actor);
        return true;
    }

    public Actor crearActor(String nombre, String apellido, Integer edad, Integer paisOrigen, Integer nivel) {
        Actor actor = new Actor();
        actor.setNombre(nombre);
        actor.setApellido(apellido);
        actor.setEdad(edad);
        actor.setPaisOrigen(PaisEnum.parse(paisOrigen));
        actor.setNivel(TipoNivelEnum.parse(nivel));
        return (grabar(actor) ? actor : null);
    }

    public List<Actor> listarActores() {
        return actorRepository.findAll();
    }

    public Actor obtenerActorId(ObjectId id) {
        Optional<Actor> actor = this.listarActores().stream().filter(a -> id.equals(a.get_id())).findFirst();
        return (actor.isPresent() ? actor.get() : null);
    }

}