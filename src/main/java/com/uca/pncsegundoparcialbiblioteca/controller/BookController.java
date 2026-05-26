package com.uca.pncsegundoparcialbiblioteca.controller;

import com.uca.pncsegundoparcialbiblioteca.dto.GeneralResponse;
import com.uca.pncsegundoparcialbiblioteca.dto.request.BookDTORequest;
import com.uca.pncsegundoparcialbiblioteca.dto.response.BookDTOResponse;
import com.uca.pncsegundoparcialbiblioteca.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<GeneralResponse> findAll(@RequestParam(required = false) String genre){

        List<BookDTOResponse> books = (genre == null ? bookService.findAllBooks():bookService.findAllBooks(genre));

        return ResponseEntity.ok(GeneralResponse.builder()
                .data(books)
                .message("Books found")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getById(@PathVariable int id) {
        return ResponseEntity.ok(GeneralResponse.builder()
                .data(bookService.findBookById(id))
                .message("Book found with id: " + id)
                .build());
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> createBook(@Valid @RequestBody BookDTORequest book) {
        bookService.createBook(book);

        return new ResponseEntity<>(GeneralResponse.builder()
                .data(book)
                .message("Book has been created")
                .build(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updatePokemon(@PathVariable int id,@Valid @RequestBody BookDTORequest book) {
        bookService.updateBook(id,book);
        return ResponseEntity.ok(GeneralResponse.builder()
                .data(book)
                .message("Book has been updated")
                .build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deletePokemon(@PathVariable int id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok(GeneralResponse.builder()
                .message("Book has been deleted")
                .build());
    }

}
