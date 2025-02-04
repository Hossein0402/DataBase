package org.imdb.databaseexmaple.repository;

import org.imdb.databaseexmaple.model.entity.Actor;
import org.imdb.databaseexmaple.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query("select m from Movie m where m.genre like concat('%', ?1, '%')")
    List<Movie> findByGenreContaining(String genre);

    @Query("select m from Movie m where m.title = ?1")
    Optional<Movie> findByTitle(String title);

    @Query("select m from Movie m where m.title = ?1 and m.genre = ?2")
    Optional<Movie> findByTitleAndGenre(String title, String genre);

    @Query("select m from Movie m where m.actors = ?1 or m.year = ?2")
    Optional<Movie> findMovieByActorsOrYear(List<Actor> actors, int year);

    @Query("select m from Movie m where m.year = ?1")
    Optional<Movie> findByYear(int year);

    @Query("select m from Movie m where m.year = ?1 and m.genre = ?2")
    Optional<Movie> findByYearAndGenre(int year, String genre);
}