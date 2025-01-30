package org.imdb.databaseexmaple.repository;

import org.imdb.databaseexmaple.model.entity.Actor;
import org.imdb.databaseexmaple.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, String> {
}