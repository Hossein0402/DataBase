package org.imdb.databaseexmaple.repository;

import org.imdb.databaseexmaple.model.entity.Actor;
import org.imdb.databaseexmaple.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, String> {

    @Query("select a from Actor a where a.name = ?1")
    Optional<Actor> findByName(String name);

    @Query("select a from Actor a where a.name like concat('%', ?1, '%')")
    Optional<Actor> findByNameContaining(String name);

    @Query("select a from Actor a where a.age = ?1")
    Optional<Actor> findByAge(int age);

    @Query("select a from Actor a where a.nationality = ?1")
    Optional<Actor> findActorsByNationality(String nationality);

    @Query("select a from Actor a where a.gender = ?1 and a.age = ?2")
    Optional<Actor> findActorByGenderAndAge(String nationality, int age);
}