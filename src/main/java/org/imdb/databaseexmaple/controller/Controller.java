package org.imdb.databaseexmaple.controller;

import lombok.RequiredArgsConstructor;
import org.imdb.databaseexmaple.model.dto.ActorDto;
import org.imdb.databaseexmaple.model.dto.MovieDto;
import org.imdb.databaseexmaple.repository.ActorRepository;
import org.imdb.databaseexmaple.repository.MovieRepository;
import org.imdb.databaseexmaple.service.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imdb")
@RequiredArgsConstructor
public class Controller {
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final Mapper mapper;

    @GetMapping("/actors")
    public List<ActorDto> getAllActors() {
        return actorRepository.findAll().stream().map(mapper::toActorDto).toList();
    }

    @GetMapping("/moviesByGender")
    public List<MovieDto> getAllMoviesByGender() {
        return movieRepository.findAll().stream().map(mapper::toMovieDto).toList();
    }

    @GetMapping("/movies/{title}")
    public MovieDto getMovieByTitle(@PathVariable String title) {
        MovieDto movie = mapper.toMovieDto(movieRepository.findById(title).orElseThrow());
        movie.setImage("");
        return movie;
    }

    @GetMapping("/actors/{name}")
    public ActorDto getActorByName(@PathVariable String name) {
        return mapper.toActorDto(actorRepository.findById(name).orElseThrow());
    }
}
