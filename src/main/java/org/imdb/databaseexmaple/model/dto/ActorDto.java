package org.imdb.databaseexmaple.model.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link org.imdb.databaseexmaple.model.entity.Actor}
 */
@Value
@Builder
public class ActorDto implements Serializable {
    String name;
    int age;
    String nationality;
    String gender;
    List<MovieDto> movies;
}