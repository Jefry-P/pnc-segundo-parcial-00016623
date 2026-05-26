package com.uca.pncsegundoparcialbiblioteca.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record BookDTOResponse(
        String title,
        String author,
        String isbn,
        String genre,
        Integer totalCopies,
        Date publishedDate,
        String description,
        boolean available,
        Integer availableCopies
) {
}
