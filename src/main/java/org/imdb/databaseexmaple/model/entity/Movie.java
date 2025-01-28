package org.imdb.databaseexmaple.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.imdb.databaseexmaple.model.dto.MovieDto;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "title", nullable = false)
    private String title;
    private int year;
    private double rating;
    private String genre;
    private String director;
    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;


}
