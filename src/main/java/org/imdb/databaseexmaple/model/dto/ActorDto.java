package org.imdb.databaseexmaple.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link org.imdb.databaseexmaple.model.entity.Actor}
 */
@Builder
public record ActorDto(String name, int age, String nationality, String gender,
                       List<MovieDto> movies) implements Serializable {
}