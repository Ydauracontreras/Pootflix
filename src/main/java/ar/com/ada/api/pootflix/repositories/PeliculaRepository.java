package ar.com.ada.api.pootflix.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.pootflix.entities.Pelicula;

@Repository
public interface PeliculaRepository extends MongoRepository<Pelicula, ObjectId> {

    Pelicula findByTitulo(String titulo);
}
