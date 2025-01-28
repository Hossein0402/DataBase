package org.imdb.databaseexmaple.service;

import org.imdb.databaseexmaple.model.dto.ActorDto;
import org.imdb.databaseexmaple.model.dto.MovieDto;
import org.imdb.databaseexmaple.model.entity.Actor;
import org.imdb.databaseexmaple.model.entity.Movie;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public MovieDto toMovieDto(Movie movie) {
        return MovieDto.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .rating(movie.getRating())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .build();
    }

    public ActorDto toActorDto(Actor actor) {
        return ActorDto.builder()
                .name(actor.getName())
                .age(actor.getAge())
                .nationality(actor.getNationality())
                .gender(actor.getGender())
                .movies(actor.getMovies().stream().map(this::toMovieDto).toList())
                .build();
    }
}
