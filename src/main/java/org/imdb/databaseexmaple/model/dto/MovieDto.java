package org.imdb.databaseexmaple.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

/**
 * DTO for {@link org.imdb.databaseexmaple.model.entity.Movie}
 */
@Getter
@Setter
@Builder
@Jacksonized
public class MovieDto implements Serializable {
    String title;
    int year;
    double rating;
    String genre;
    String director;
    String image;
}