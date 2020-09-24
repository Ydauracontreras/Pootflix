package ar.com.ada.api.pootflix.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.pootflix.entities.Director;

@Repository
public interface DirectorRepository extends MongoRepository<Director, ObjectId> {

    Director findByNombre(String nombre);
}
