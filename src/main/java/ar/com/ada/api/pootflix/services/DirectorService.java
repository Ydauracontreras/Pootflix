package ar.com.ada.api.pootflix.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.pootflix.entities.Director;
import ar.com.ada.api.pootflix.entities.Director.TipoEstiloEnum;
import ar.com.ada.api.pootflix.entities.Pais.PaisEnum;
import ar.com.ada.api.pootflix.repositories.DirectorRepository;

@Service
public class DirectorService {
    @Autowired
    DirectorRepository directorRepository;

    public boolean grabar(Director director) {
        directorRepository.save(director);
        return true;
    }

    public Director registrarDirector(String nombre, String apellido, Integer edad, Integer paisOrigen,
            Integer estilo) {
        Director director = new Director();
        director.setNombre(nombre);
        director.setApellido(apellido);
        director.setEdad(edad);
        director.setPaisOrigen(PaisEnum.parse(paisOrigen));
        director.setEstilo(TipoEstiloEnum.parse(estilo));
        return (grabar(director) ? director : null);
    }

    public List<Director> listarDirectors() {
        return directorRepository.findAll();
    }

    public Director obtenerDirectorId(ObjectId id) {
        Optional<Director> director = this.listarDirectors().stream().filter(d -> id.equals(d.get_id())).findFirst();
        return (director.isPresent() ? director.get() : null);
    }

}