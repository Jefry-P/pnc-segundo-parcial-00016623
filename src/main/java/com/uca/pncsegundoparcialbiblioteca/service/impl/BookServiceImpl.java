package com.uca.pncsegundoparcialbiblioteca.service.impl;

import com.uca.pncsegundoparcialbiblioteca.dto.request.BookDTORequest;
import com.uca.pncsegundoparcialbiblioteca.dto.response.BookDTOResponse;
import com.uca.pncsegundoparcialbiblioteca.entities.Book;
import com.uca.pncsegundoparcialbiblioteca.exception.BusinessRuleException;
import com.uca.pncsegundoparcialbiblioteca.exception.ResourceNotFound;
import com.uca.pncsegundoparcialbiblioteca.repository.BookRepository;
import com.uca.pncsegundoparcialbiblioteca.service.BookService;
import com.uca.pncsegundoparcialbiblioteca.utils.BookMappper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public void createBook(BookDTORequest book) throws BusinessRuleException {
        if (bookRepository.existsByTitle(book.title().toLowerCase())){
            throw new BusinessRuleException("Book with title " + book.title() + " already exists");
        }

        if (bookRepository.existsByIsbn(book.isbn().toLowerCase())){
            throw new BusinessRuleException("Book with ISBN " + book.isbn() + " already exists");
        }

        if(book.publishedDate()!=null && book.publishedDate().after(new Date())){
            throw new BusinessRuleException("Book published date cannot be future");
        }

        Book bookEntity = BookMappper.toEntity(book);
        bookEntity.setAvailableCopies(book.totalCopies());
        bookEntity.setAvailable(true);
        bookRepository.save(bookEntity);
    }

    @Override
    public BookDTOResponse findBookById(Integer id) throws ResourceNotFound {
        return BookMappper.toResponse(bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Book not found with id " + id)
        ));
    }

    @Override
    public void updateBook(Integer id, BookDTORequest book) throws ResourceNotFound, BusinessRuleException {
        Book bookToUpdate = BookMappper.toEntity(book);
        if (bookRepository.existsById(id)){
            bookToUpdate.setId(id);
        }else{
            throw new ResourceNotFound("Book not found with id " + id);
        }
        if(book.availableCopies() != null && book.availableCopies() == 0){
            bookToUpdate.setAvailable(false);
        }
        bookRepository.save(bookToUpdate);
    }

    @Override
    public List<BookDTOResponse> findAllBooks(String genre) throws ResourceNotFound {
        return bookRepository.findAllByGenre(genre).stream().map(
                BookMappper::toResponse
                ).toList();
    }

    @Override
    public List<BookDTOResponse> findAllBooks() throws ResourceNotFound {
        return bookRepository.findAll().stream().map(
                BookMappper::toResponse
        ).toList();
    }

    @Override
    public void deleteBookById(int id) throws ResourceNotFound {
        if (bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        }else{
            throw new ResourceNotFound("Book not found with id " + id);
        }

    }
}
