package com.aluracursos.DesafioSpring.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
    @JsonIgnoreProperties
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAuthor> autor,
        @JsonAlias("lenguagues") List<String> idiomas,
        @JsonAlias("download_count") double numeroDescargas
) {
}
