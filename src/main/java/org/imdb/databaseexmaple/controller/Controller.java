package org.imdb.databaseexmaple.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.imdb.databaseexmaple.model.dto.ActorDto;
import org.imdb.databaseexmaple.model.dto.MovieDto;
import org.imdb.databaseexmaple.model.entity.Actor;
import org.imdb.databaseexmaple.repository.ActorRepository;
import org.imdb.databaseexmaple.repository.MovieRepository;
import org.imdb.databaseexmaple.service.ImageProcessor;
import org.imdb.databaseexmaple.service.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/imdb")
@RequiredArgsConstructor
public class Controller {
    private static final String FILE_NAME = "movies.json";
    private static final String DIRECTORY_PATH = "C:\\Users\\dd\\Desktop\\IMDB\\database-exmaple\\front";

    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final ImageProcessor imageProcessor;
    private final Mapper mapper;

    @GetMapping("/actors")
    public List<ActorDto> getAllActors() {
        return actorRepository.findAll().stream().map(mapper::toActorDto).toList();
    }

    @GetMapping("/moviesByGender/{genre}")
    public List<MovieDto> getAllMoviesByGender(@PathVariable String genre) {
        log.info("getAllMoviesByGender +{}+", genre);
        List<MovieDto> list = movieRepository.findByGenreContaining(genre).stream().map(mapper::toMovieDto).toList();
        list.forEach(movie -> movie.setImage(imageProcessor.getMoviePicture(movie.getTitle())));
        writeMovieToJsonFile(list);
        return list;
    }

    @GetMapping("/movies/{title}")
    public MovieDto getMovieByTitle(@PathVariable String title) {
        log.info("title: {}", title);
        try {
            MovieDto movie = mapper.toMovieDto(movieRepository.findById(title).orElseThrow());
            movie.setImage(imageProcessor.getMoviePicture(movie.getTitle()));
            writeMovieToJsonFile(List.of(movie));
            // write movie on this path: C:\Users\dd\Desktop\IMDB\database-exmaple\front\movies.json
            return movie;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/actors/{name}")
    public ActorDto getActorByName(@PathVariable String name) {
        Actor actor = actorRepository.findById(name).orElseThrow();
        List<MovieDto> movies = actor.getMovies().stream().map(mapper::toMovieDto).toList();
        movies.forEach(movie -> movie.setImage(imageProcessor.getMoviePicture(movie.getTitle())));
        writeMovieToJsonFile(movies);
        return ActorDto.builder().build();
    }

    private void writeMovieToJsonFile(List<MovieDto> movie) {
        try {
            ObjectMapper objectMapper = new ObjectMapper(); // Alternatively, use the injected ObjectMapper
            File outputFile = Paths.get(DIRECTORY_PATH, FILE_NAME).toFile(); // Create file object with specified directory and file name

            // Write the movie instance to the JSON file
            objectMapper.writeValue(outputFile, movie);
            log.info("Movie written to file: {}", outputFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("Error writing movie to JSON file: {}", e.getMessage());
        }
    }
}
