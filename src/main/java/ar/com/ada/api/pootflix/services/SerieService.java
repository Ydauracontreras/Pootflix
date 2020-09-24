package ar.com.ada.api.pootflix.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.ReplaceRootOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
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

    @Autowired
    MongoTemplate mongoTemplate;

    public boolean grabar(Serie serie) {
        serieRepository.save(serie);
        return true;
    }

    // Registra un serie sin episodios ni temporada

    public Serie registarSerie(String titulo, String premio, Integer genero, ObjectId idActor, Double calificacion,
            Integer paisOrigen, ObjectId idDirector) {
        Serie serie = new Serie();
        List<Actor> actores = new ArrayList<>();
        List<TipoGeneroEnum> generos = new ArrayList<>();
        List<String> premios = new ArrayList<>();
        if (existeSerie(titulo)) {
            return null;
        } else {
            serie.setTitulo(titulo);
            serie.setTiempoVisto(0.0);
            premios.add(premio);
            serie.setCalificacion(calificacion);
            serie.setPremios(premios);
            generos.add(TipoGeneroEnum.parse(genero));
            serie.setGeneros(generos);
            actores.add(actorService.obtenerActorId(idActor));
            serie.setActores(actores);
            serie.setPaisOrigen(PaisEnum.parse(paisOrigen));
            serie.setDirector(directorService.obtenerDirectorId(idDirector));
            grabar(serie);
            return serie;
        }
    }

    public Episodio registarEpisodio(ObjectId idSerie, double duracion, String nombre, Integer numero,
            Integer numeroTemporada) {
        List<Temporada> temporadas = new ArrayList<>();
        List<Episodio> episodios = new ArrayList<Episodio>();
        Serie serie = obtenerSerieId(idSerie);
        // Creo un episodio
        Episodio episodio = new Episodio();
        episodio.setDuracion(duracion);
        episodio.setNombre(nombre);
        episodio.setNumero(numero);
        episodio.set_id(ObjectId.get());
        // Agrego a la lista de episodios
        episodios.add(episodio);
        temporadas.add(registarTemporada(episodio, numeroTemporada, idSerie));
        serie.setTemporadas(temporadas);
        grabar(serie);
        return episodio;
    }

    public Temporada registarTemporada(Episodio episodio, Integer numeroTemporada, ObjectId idSerie) {
        Serie serie = obtenerSerieId(idSerie);
        Temporada t = new Temporada();
        List<Temporada> temporadas = serie.getTemporadas();
        for (Temporada temporada : temporadas) {
            if (temporada.getNumero().equals(numeroTemporada)) {
                temporada.getEpisodios().add(episodio);
            } else {
                t = crearTemporada(episodio, numeroTemporada);
                return t;
            }
        }
        return null;

    }

    public Temporada crearTemporada(Episodio episodio, Integer numeroTemporada) {
        List<Episodio> episodios = new ArrayList<Episodio>();
        Temporada t = new Temporada();
        episodios.add(episodio);
        t.setEpisodios(episodios);
        t.setNumero(numeroTemporada);
        t.set_id(ObjectId.get());
        return t;
    }

    public List<Serie> listarSeries() {
        return serieRepository.findAll();
    }

    public Serie obtenerSerieId(ObjectId id) {
        Optional<Serie> serie = this.listarSeries().stream().filter(s -> id.equals(s.get_id())).findFirst();
        return (serie.isPresent() ? serie.get() : null);
    }

    boolean existeSerie(String titulo) {
        Serie serie = serieRepository.findByTitulo(titulo);
        return (serie != null ? true : false);
    }

    public Serie buscarPorTitulo(String titulo) {
        Serie serie = serieRepository.findByTitulo(titulo);
        return serie;
    }

    public List<Serie> obtenerSerie() {
        return serieRepository.findAll();
    }

    public Serie buscarPorId(ObjectId idSerie) {
        Optional<Serie> opSerie = serieRepository.findById(idSerie);

        if (opSerie.isPresent())
            return opSerie.get();
        else
            return null;
    }

    public boolean tieneGenero(Serie serie, String genero) {
        return serie.getGeneros().stream().filter(g -> g.getValue().toString().equals(genero)).count() > 0;
    }

    public List<Serie> buscarPorGenero(String genero) {

        return obtenerSerie().stream().filter(p -> this.tieneGenero(p, genero)).collect(Collectors.toList());
        /*
         * return obtenerPeliculas().stream() .filter(p-> p.getGeneros().stream()
         * .filter(g -> g.getNombreGenero().equals(genero)).count() >
         * 0).collect(Collectors.toList());
         */
    }

    public List<Temporada> traerTemporadasPorSerieId(ObjectId id) {

        return buscarPorId(id).getTemporadas();
    }

    public List<Serie> obtenerSeriesByActor(ObjectId actorId) {
        // Trae todas las Series.
        /*
         * List<Serie> series = serieRepo.findAll().stream() .filter(s ->
         * s.getActores().stream() .filter(a->
         * a.get_id().equals(actorId.toHexString())).count() > 0)
         * .collect(Collectors.toList());
         */

        // Busco por un metodo del repo
        // List<Serie> series = serieRepo.findByActores__id(actorId);

        // En este caso trae solo info del Actor.
        List<Serie> series = serieRepository.findSeriesByActores_IdSoloInfoActor(actorId);
        // List<Serie> series = serieRepo.findSeriesByActor_IdEntero(actorId);
        return series;
    }

    public List<Episodio> obtenerEpisodiosSerie(ObjectId serieId) {

        return this.buscarPorId(serieId).getTemporadas().stream().map(t -> t.getEpisodios()).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public Episodio obtenerEpisodioPorNroEpisodio(ObjectId serieId, Integer temporadaNro, Integer episodioNumero) {
        return obtenerEpisodioPorNroEpisodioVersionPerfomante(serieId, temporadaNro, episodioNumero);
    }

    public Episodio obtenerEpisodioPorNroEpisodioVersionPesada(ObjectId serieId, Integer temporadaNro,
            Integer episodioNumero) {

        Episodio episodio = serieRepository.findBy_id(serieId).getTemporadas().stream()
                .filter(t -> t.getNumero() == temporadaNro).findFirst().get().getEpisodios().stream()
                .filter(e -> e.getNumero() == episodioNumero).findFirst().get();

        return episodio;
    }

    public Episodio obtenerEpisodioPorNroEpisodioVersionPerfomante(ObjectId serieId, Integer temporadaNro,
            Integer episodioNumero) {

        /*
         * 
         * Este es el PIPELINE EN JS [ { '$match': { '_id': new
         * ObjectId('5f652e4f857bbf55ecbccfe6') } }, { '$project': { 'temporadas': 1 }
         * }, { '$unwind': { 'path': '$temporadas' } }, { '$match': {
         * 'temporadas.numero': 5 } }, { '$replaceRoot': { 'newRoot': '$temporadas' } },
         * { '$unwind': { 'path': '$episodios' } }, { '$replaceRoot': { 'newRoot':
         * '$episodios' } }, { '$match': { 'numero': 7 } } ]
         * 
         * 
         */

        // Pipelines
        // Stage1: filtro por la serie especifica
        MatchOperation matchSerieStage = Aggregation.match(new Criteria("_id").is(serieId));

        // Stage2: Proyectar solo temporadas
        ProjectionOperation projectTemporadaStage = Aggregation.project("temporadas");

        // Stage3: Unwind temporada (las separa)
        UnwindOperation unwindTemporadaStage = Aggregation.unwind("temporadas");

        // Stage4: Match temporada(filtra de las separadas)
        MatchOperation matchTemporadaStage = Aggregation.match(new Criteria("temporadas.numero").is(temporadaNro));

        // Stage5: Reemplaza la raiz para que tome ahora el de documentos
        ReplaceRootOperation replaceRootTemporadaStage = Aggregation.replaceRoot("temporadas");

        // Stage6: Unwind episodios (separa los episodios)
        UnwindOperation unwindEpisodiosStage = Aggregation.unwind("episodios");

        // Stage7: Reemplaza la raiz para que tome ahora el de documentos
        ReplaceRootOperation replaceRootEpisodiosStage = Aggregation.replaceRoot("episodios");
        // Stage8: Match episodio(filtra de los separados)
        MatchOperation matchEpisodioStage = Aggregation.match(new Criteria("numero").is(episodioNumero));

        Aggregation aggregation = Aggregation.newAggregation(matchSerieStage, projectTemporadaStage,
                unwindTemporadaStage, matchTemporadaStage, replaceRootTemporadaStage, unwindEpisodiosStage,
                replaceRootEpisodiosStage, matchEpisodioStage);

        AggregationResults<Episodio> result = mongoTemplate.aggregate(aggregation, "series", Episodio.class);

        Episodio episodio = result.getUniqueMappedResult();
        return episodio;

    }

}
