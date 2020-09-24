package ar.com.ada.api.pootflix.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.pootflix.entities.Actor;
import ar.com.ada.api.pootflix.entities.Pelicula;
import ar.com.ada.api.pootflix.entities.Genero.TipoGeneroEnum;
import ar.com.ada.api.pootflix.entities.Pais.PaisEnum;
import ar.com.ada.api.pootflix.repositories.PeliculaRepository;

@Service
public class PeliculaService {
    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    ActorService actorService;
    @Autowired
    DirectorService directorService;

    public boolean grabar(Pelicula pelicula) {
        peliculaRepository.save(pelicula);
        return true;
    }

    public Pelicula registarPelicula(String titulo, String premio, Integer genero, ObjectId idActor,
            Double calificacion, Integer paisOrigen, Integer anio, ObjectId idDirector, double duracion,
            boolean filmadaenImax) {

        if (existePelicula(titulo)) {
            return null;
        } else {
            Pelicula pelicula = new Pelicula();
            List<Actor> actores = new ArrayList<>();
            List<TipoGeneroEnum> generos = new ArrayList<>();
            List<String> premios = new ArrayList<>();
            pelicula.setTitulo(titulo);
            pelicula.setTiempoVisto(0.0);
            premios.add(premio);
            pelicula.setPremios(premios);
            generos.add(TipoGeneroEnum.parse(genero));
            pelicula.setGeneros(generos);
            actores.add(actorService.obtenerActorId(idActor));
            pelicula.setActores(actores);
            pelicula.setPaisOrigen(PaisEnum.parse(paisOrigen));
            pelicula.setFilmadaenImax(filmadaenImax);
            pelicula.setDirector(directorService.obtenerDirectorId(idDirector));
            pelicula.setDuracion(duracion);
            pelicula.setCalificacion(calificacion);
            pelicula.setAnio(anio);
            grabar(pelicula);
            return (pelicula);
        }
    }

    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll();
    }

    public Pelicula obtenerPeliculaId(ObjectId id) {
        Optional<Pelicula> pelicula = this.listarPeliculas().stream().filter(p -> id.equals(p.get_id())).findFirst();
        return (pelicula.isPresent() ? pelicula.get() : null);
    }

    public Pelicula obtenerPeliculaPorTitulo(String titulo) {
        Optional<Pelicula> pelicula = this.listarPeliculas().stream()
                .filter(p -> titulo.compareToIgnoreCase(p.getTitulo()) <= 0).findFirst();
        return (pelicula.isPresent() ? pelicula.get() : null);
    }

    public Pelicula buscarPorTitulo(String titulo) {
        Pelicula pelicula = peliculaRepository.findByTitulo(titulo);
        return pelicula;
    }

    public boolean tieneGenero(Pelicula pelicula, String genero) {
        return pelicula.getGeneros().stream().filter(g -> g.getValue().toString().equals(genero)).count() > 0;
    }

    public List<Pelicula> obtenerPeliculasPorGenero(Integer genero) {
        List<Pelicula> peliculasPorGenero = new ArrayList<>();
        for (Pelicula pelicula : this.listarPeliculas()) {
            for (TipoGeneroEnum g : pelicula.getGeneros())
                if (g.getValue().equals(genero))

                    peliculasPorGenero.add(pelicula);
        }

        return peliculasPorGenero;

    }

    public List<Pelicula> buscarPorGenero(String genero) {

        return listarPeliculas().stream().filter(p -> this.tieneGenero(p, genero)).collect(Collectors.toList());
    }

    boolean existePelicula(String titulo) {
        Pelicula pelicula = peliculaRepository.findByTitulo(titulo);
        return (pelicula != null ? true : false);
    }

}
