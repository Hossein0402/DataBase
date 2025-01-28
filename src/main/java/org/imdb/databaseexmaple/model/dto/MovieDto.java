package org.imdb.databaseexmaple.model.dto;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link org.imdb.databaseexmaple.model.entity.Movie}
 */
@Builder
public record MovieDto(String title, int year, double rating, String genre, String director) implements Serializable {
}