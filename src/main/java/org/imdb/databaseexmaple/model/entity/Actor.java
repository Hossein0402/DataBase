package org.imdb.databaseexmaple.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.imdb.databaseexmaple.model.dto.ActorDto;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @Column(name = "name", nullable = false)
    private String name;
    private int age;
    private String nationality;
    private String gender;

    @ManyToMany
    @JoinTable(
            name = "actor_movie", // Join table name
            joinColumns = @JoinColumn(name = "actor_name"), // Column for actor
            inverseJoinColumns = @JoinColumn(name = "movie_title") // Column for movie
    )
    private List<Movie> movies;

}