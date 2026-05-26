package com.uca.pncsegundoparcialbiblioteca.utils;

import com.uca.pncsegundoparcialbiblioteca.dto.request.BookDTORequest;
import com.uca.pncsegundoparcialbiblioteca.dto.response.BookDTOResponse;
import com.uca.pncsegundoparcialbiblioteca.entities.Book;

public class BookMappper {

    public static Book toEntity(BookDTORequest request){
        return Book.builder()
                .title(request.title().toLowerCase())
                .author(request.author())
                .isbn(request.isbn())
                .genre(request.genre())
                .totalCopies(request.totalCopies())
                .publishedDate(request.publishedDate())
                .description(request.description())
                .build();
    }

    public static BookDTOResponse toResponse(Book book){
        return new BookDTOResponse(
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getGenre(),
                book.getTotalCopies(),
                book.getPublishedDate(),
                book.getDescription(),
                book.getAvailableCopies() > 0,
                book.getAvailableCopies()
        );
    }

}
