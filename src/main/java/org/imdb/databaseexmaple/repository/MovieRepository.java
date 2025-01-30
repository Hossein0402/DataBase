package org.imdb.databaseexmaple.repository;

import org.imdb.databaseexmaple.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findByGenreContaining(String genre);
}