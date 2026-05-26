package com.uca.pncsegundoparcialbiblioteca.service;

import com.uca.pncsegundoparcialbiblioteca.dto.request.BookDTORequest;
import com.uca.pncsegundoparcialbiblioteca.dto.response.BookDTOResponse;
import com.uca.pncsegundoparcialbiblioteca.exception.BusinessRuleException;
import com.uca.pncsegundoparcialbiblioteca.exception.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void createBook(BookDTORequest bookDTORequest) throws BusinessRuleException;
    BookDTOResponse findBookById(Integer id) throws ResourceNotFound;
    void updateBook(Integer id, BookDTORequest book) throws ResourceNotFound, BusinessRuleException;
    List<BookDTOResponse> findAllBooks(String genre) throws ResourceNotFound;
    List<BookDTOResponse> findAllBooks() throws ResourceNotFound;
    public void deleteBookById(int id) throws ResourceNotFound;
}
