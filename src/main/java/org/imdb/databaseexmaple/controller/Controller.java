package org.imdb.databaseexmaple.controller;

import lombok.AllArgsConstructor;
import org.imdb.databaseexmaple.model.dto.ActorDto;
import org.imdb.databaseexmaple.model.dto.MovieDto;
import org.imdb.databaseexmaple.repository.ActorRepository;
import org.imdb.databaseexmaple.repository.MovieRepository;
import org.imdb.databaseexmaple.service.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imdb")
@AllArgsConstructor
public class Controller {
    private ActorRepository actorRepository;
    private MovieRepository movieRepository;
    private Mapper mapper;

    @GetMapping("/actors")
    public List<ActorDto> getAllActors() {
        return actorRepository.findAll().stream().map(actor -> mapper.toActorDto(actor)).toList();
    }

    @GetMapping("/moviesByGender")
    public List<MovieDto> getAllMoviesByGender() {
        return movieRepository.findAll().stream().map(movie -> mapper.toMovieDto(movie)).toList();
    }


}
