package ar.com.ada.api.pootflix.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.pootflix.entities.Actor;

@Repository
public interface ActorRepository extends MongoRepository<Actor, ObjectId> {

    Actor findByNombre(String nombre);
}