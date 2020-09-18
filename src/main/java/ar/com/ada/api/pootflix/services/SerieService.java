package ar.com.ada.api.pootflix.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.pootflix.entities.Actor;
import ar.com.ada.api.pootflix.entities.Episodio;
import ar.com.ada.api.pootflix.entities.Serie;
import ar.com.ada.api.pootflix.entities.Temporada;
import ar.com.ada.api.pootflix.entities.Genero.TipoGeneroEnum;
import ar.com.ada.api.pootflix.entities.Pais.PaisEnum;
import ar.com.ada.api.pootflix.repositories.SerieRepository;

@Service
public class SerieService {
    @Autowired
    SerieRepository serieRepository;
    @Autowired
    ActorService actorService;
    @Autowired
    DirectorService directorService;

    public boolean grabar(Serie serie) {
        serieRepository.save(serie);
        return true;
    }

    public Serie registarSerie(String titulo, String premio, Integer genero, ObjectId idActor, Integer calificacion,
            Integer paisOrigen, ObjectId idDirector) {

        Serie serie = new Serie();
        List<Actor> actores = new ArrayList<>();
        List<TipoGeneroEnum> generos = new ArrayList<>();
        List<String> premios = new ArrayList<>();
        List<Temporada> temporadas = new ArrayList<>();

        serie.setTitulo(titulo);
        serie.setTiempoVisto(0.0);
        premios.add(premio);
        serie.setPremios(premios);
        generos.add(TipoGeneroEnum.parse(genero));
        serie.setGeneros(generos);
        actores.add(actorService.obtenerActorId(idActor));
        serie.setActores(actores);
        serie.setPaisOrigen(PaisEnum.parse(paisOrigen));
        serie.setDirector(directorService.obtenerDirectorId(idDirector));
        return (grabar(serie) ? serie : null);

    }

    public Episodio registarEpisodio(ObjectId idSerie, double duracion, String nombre, Integer numero) {
        List<Episodio> episodios = new ArrayList<Episodio>();
        Episodio episodio = new Episodio();
        episodio.setDuracion(duracion);
        episodio.setNombre(nombre);
        episodio.setNumero(numero);
        episodio.set_id(ObjectId.get());
        episodios.add(episodio);

        return episodio;
    }

    public Temporada registarTemporada(Episodio episodio) {
        List<Episodio> episodios = new ArrayList<Episodio>();
        Temporada temporada = new Temporada();
        temporada.setEpisodios(episodios);
        temporada.set_id(ObjectId.get());
        return temporada;
    }

    public List<Serie> listarSeries() {
        return serieRepository.findAll();
    }

    public Serie obtenerSerieId(ObjectId id) {
        Optional<Serie> serie = this.listarSeries().stream().filter(s -> id.equals(s.get_id())).findFirst();
        return (serie.isPresent() ? serie.get() : null);
    }

}
