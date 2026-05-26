package com.uca.pncsegundoparcialbiblioteca.dto.request;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record BookDTORequest(
        @NotNull(message = "El titulo no debe de ser nulo")
        @NotEmpty(message = "El titulo no puede ser vacío")
        String title,
        @NotNull(message = "El autor no debe de ser nulo")
        @NotEmpty(message = "El autor no puede ser vacío")
        String author,
        @NotNull(message = "El isbn no puede ser vacío")
        @NotEmpty(message = "El isbn no puede ser vacío")
        String isbn,
        @NotNull(message = "El género no puede ser vacío")
        @NotEmpty(message = "El género no puede ser vacío")
        String genre,
        @NotNull(message = "El numero de copias no puede ser vacío")
        @Min(value=1, message = "El numero de copias no puede ser menor que 1")
        Integer totalCopies,
        Date publishedDate,
        String description,
        @Min(value=1, message = "El numero de copias disponibles no puede ser menor que 1")
        Integer availableCopies
) {

}
